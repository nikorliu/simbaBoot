package com.alibaba.idst.nls.session;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelPipelineException;
import org.jboss.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketVersion;
import org.jboss.netty.handler.timeout.ReadTimeoutException;

import com.alibaba.idst.nls.NlsClient;
import com.alibaba.idst.nls.event.NlsEvent;
import com.alibaba.idst.nls.event.NlsListener;
import com.alibaba.idst.nls.protocol.NlsRequest;
import com.alibaba.idst.nls.protocol.NlsResponse;
import com.alibaba.idst.nls.utils.RawJsonText;
import com.alibaba.idst.nls.utils.Utility;
import com.google.gson.Gson;

public class NlsSession implements SessionListener {
	private ClientBootstrap bootstrap;
	private URI uri;
	private NlsRequest req;
	private WebSocketClientHandshaker handshaker;
	private NlsListener listener;
	private final Lock lock = new ReentrantLock();
	private final Condition done = this.lock.newCondition();
	private Channel channel;
	private List<ChannelBuffer> bufferQueue = new ArrayList<>();
	private static final byte[] finishSignal = { 0, 0, 0, 0 };

	private State state = State.CREATED;
	private Gson gson = RawJsonText.createGson();
	private Queue<byte[]> bstreamQueue = new ConcurrentLinkedQueue<>();
	private static Log logger = LogFactory.getLog(NlsSession.class);

	public ChannelFutureListener CONNECT = new ChannelFutureListener() {
		public void operationComplete(ChannelFuture future) throws Exception {
			if (future.isSuccess()) {
				SessionEvent e = new SessionEvent();
				e.setChannel(future.getChannel());
				NlsSession.this.onConnected(e);
			} else {
				SessionEvent e = new SessionEvent();
				e.setErrorMessage("connection failed");
				NlsSession.this.onOperationFailed(e);
				future.getChannel().close();
			}
		}
	};

	public ChannelFutureListener HANDSHAKE = new ChannelFutureListener() {
		public void operationComplete(ChannelFuture future) throws Exception {
			if (!future.isSuccess()) {
				SessionEvent e = new SessionEvent();
				e.setErrorMessage("handshake failed");
				NlsSession.this.onOperationFailed(e);
				future.getChannel().close();
			}
		}
	};

	public NlsSession(ClientBootstrap bootstrap, URI uri, NlsRequest req, NlsListener listener) throws Exception {
		this.bootstrap = bootstrap;
		this.uri = uri;
		this.req = req;
		this.handshaker = new WebSocketClientHandshakerFactory().newHandshaker(uri, WebSocketVersion.V13, null, false, null);

		this.listener = listener;
	}

	public void start() throws Exception {
		try {
			ChannelFuture cf = this.bootstrap.connect(new InetSocketAddress(this.uri.getHost(), this.uri.getPort()));
			this.channel = cf.getChannel();
			cf.addListener(this.CONNECT);
		} catch (ReadTimeoutException ex) {
			SessionEvent e = new SessionEvent();
			e.setErrorMessage("Timeout when connect to server!");
			onOperationFailed(e);
		} catch (ChannelPipelineException cpex) {
			SessionEvent e = new SessionEvent();
			e.setErrorMessage(cpex.getMessage());
			onOperationFailed(e);
		}
	}

	public byte[] readBstream() {
		return (byte[]) this.bstreamQueue.poll();
	}

	public void sendVoiceData(byte[] data) {
		byte[] pack = new byte[data.length + 4];
		System.arraycopy(Utility.int2byte(data.length), 0, pack, 0, 4);
		System.arraycopy(data, 0, pack, 4, data.length);
		ChannelBuffer buffer = ChannelBuffers.copiedBuffer(pack);
		synchronized (this.bufferQueue) {
			this.bufferQueue.add(buffer);
			if (this.state.equals(State.TRANSFERRING)) {
				for (ChannelBuffer cb : this.bufferQueue) {
					BinaryWebSocketFrame frame = new BinaryWebSocketFrame(cb);
					getChannel().write(frame);
				}

				this.bufferQueue.clear();
			}
		}
	}

	public void sendFinishSignal() {
		ChannelBuffer buffer = ChannelBuffers.copiedBuffer(finishSignal);
		synchronized (this.bufferQueue) {
			this.bufferQueue.add(buffer);
			if (this.state.equals(State.TRANSFERRING)) {
				for (ChannelBuffer cb : this.bufferQueue) {
					BinaryWebSocketFrame frame = new BinaryWebSocketFrame(cb);
					getChannel().write(frame);
				}
				this.bufferQueue.clear();
			}
		}
	}

	public void onConnected(SessionEvent e) {
		try {
			if (this.state != State.CREATED) {
				throw new IllegalStateException("state error: expected CREATED while it's " + this.state.name());
			}
			this.channel = e.getChannel();
			NlsClient.handshakers.set(getChannel(), this.handshaker);
			NlsClient.listeners.set(getChannel(), this);
			this.handshaker.handshake(getChannel()).addListener(this.HANDSHAKE);
			synchronized (this.state) {
				if (this.state == State.CREATED)
					this.state = State.CONNECTED;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			NlsEvent evt = new NlsEvent();
			evt.setErrorMessage(e1.getMessage());
			this.listener.onOperationFailed(evt);
		}
	}

	public void onHandshakeSucceeded(SessionEvent e) {
		if (this.state != State.CONNECTED) {
			throw new IllegalStateException("state error: expected CONNECTED while it's " + this.state.name());
		}
		assert (e.getChannel() == getChannel());

		String reqStr = this.req.toJson();
		logger.debug(reqStr);
		getChannel().write(new TextWebSocketFrame(reqStr));
		synchronized (this.state) {
			if (this.state == State.CONNECTED)
				this.state = State.HANDSHAKED;
		}
	}

	public SessionEvent onWebSocketFrame(SessionEvent e, WebSocketFrame binFrame) {
		if ((this.state != State.TRANSFERRING) && (this.state != State.HANDSHAKED)) {
			throw new IllegalStateException("state error: expected HANDSHAKED or TRANSFERRING while it's " + this.state.name());
		}

		if ((binFrame instanceof TextWebSocketFrame)) {
			String jsonStr = ((TextWebSocketFrame) binFrame).getText();
			logger.debug(jsonStr);
			NlsResponse response = (NlsResponse) this.gson.fromJson(jsonStr, NlsResponse.class);
			if ((response.getFinish().equalsIgnoreCase("1")) && (response.getBstream_attached().booleanValue() == true)) {
				response.setFinish("0");
			}

			if (response.getStatus().equals("1")) {
				if (this.state == State.HANDSHAKED) {
					synchronized (this.bufferQueue) {
						for (ChannelBuffer cb : this.bufferQueue) {
							BinaryWebSocketFrame frame = new BinaryWebSocketFrame(cb);
							getChannel().write(frame);
						}
						this.bufferQueue.clear();
					}
					synchronized (this.state) {
						if (this.state == State.HANDSHAKED) {
							this.state = State.TRANSFERRING;
						}
					}

				}

				NlsEvent evt = new NlsEvent();
				evt.setResponse(response);
				this.listener.onMessageReceived(evt);
				try {
					this.lock.lock();
					if (response.getFinish().equalsIgnoreCase("1")) {
						synchronized (this.state) {
							if (this.state == State.TRANSFERRING) {
								this.state = State.FINISHED;
							}
						}
						e.getChannel().close();
						this.done.signal();
					} else {
						response.setFinish("0");
					}
				} finally {
					this.lock.unlock();
				}
			} else {
				NlsEvent evt = new NlsEvent();
				evt.setResponse(response);
				this.listener.onMessageReceived(evt);
				if (response.getErrorMassage() == null)
					e.setErrorMessage("Server result in the failed status.");
				else
					e.setErrorMessage(response.getErrorMassage());
			}
		} else if ((binFrame instanceof BinaryWebSocketFrame)) {
			if (this.state != State.TRANSFERRING) {
				throw new IllegalStateException("state error: expected TRANSFERRING on binary stream while it's " + this.state.name());
			}
			try {
				this.lock.lock();
				byte[] data = binFrame.getBinaryData().array();
				int len = Utility.byte2int(data, 0);
				if (len > 0) {
					this.bstreamQueue.add(Arrays.copyOfRange(data, 4, data.length));
				} else {
					synchronized (this.state) {
						if (this.state == State.TRANSFERRING) {
							this.state = State.FINISHED;
						}
					}
					e.getChannel().close();
					this.done.signal();
				}
			} finally {
				this.lock.unlock();
			}
		}

		return e;
	}

	public void onOperationFailed(SessionEvent e) {
		if (this.state != State.FINISHED) {
			synchronized (this.state) {
				this.state = State.FAILED;
			}

			this.listener.onOperationFailed(e);
			try {
				this.lock.lock();
				if (getChannel() != null) {
					getChannel().close();
					this.channel = null;
				}
				this.done.signal();
			} finally {
				this.lock.unlock();
			}
		}
	}

	public void onChannelClosed(SessionEvent e) {
		if (!State.FINISHED.equals(this.state)) {
			synchronized (this.state) {
				this.state = State.FAILED;
			}
		}

		NlsEvent evt = new NlsEvent();
		evt.setErrorMessage("Unexpected network error");
		this.listener.onChannelClosed(evt);
		this.lock.lock();
		try {
			this.done.signal();
		} finally {
			this.lock.unlock();
		}
	}

	public State getState() {
		return this.state;
	}

	public Channel getChannel() {
		return this.channel;
	}

	public Lock getLock() {
		return this.lock;
	}

	public Condition getDone() {
		return this.done;
	}

	public static enum State {
		CREATED, CONNECTED, HANDSHAKED, TRANSFERRING, FINISHED, FAILED;
	}
}