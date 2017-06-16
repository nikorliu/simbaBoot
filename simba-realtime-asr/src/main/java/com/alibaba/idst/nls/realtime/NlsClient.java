package com.alibaba.idst.nls.realtime;

import java.net.URI;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelLocal;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import org.jboss.netty.util.HashedWheelTimer;

import com.alibaba.idst.nls.realtime.event.NlsListener;
import com.alibaba.idst.nls.realtime.protocol.NlsRequest;
import com.alibaba.idst.nls.realtime.session.NlsSession;
import com.alibaba.idst.nls.realtime.session.SessionListener;

public class NlsClient {
	private String host = "nls-trans.dataapi.aliyun.com/realtime";
	private int port = 443;
	private ClientBootstrap bootstrap;
	private NioClientSocketChannelFactory channelFactory;
	private NlsClientPipelineFactory pipelineFactory;
	private HashedWheelTimer timer;
	private boolean sslMode = true;
	public static final int timeout = 300;
	public static ChannelLocal<SessionListener> listeners = new ChannelLocal<>(true);
	public static ChannelLocal<WebSocketClientHandshaker> handshakers = new ChannelLocal<>(true);
	private ExecutorService futureCompressService;

	private static Log logger = LogFactory.getLog(NlsClient.class);

	public void init() {
		init(this.sslMode, this.port);
	}

	public void init(boolean sslMode, int port) {
		Properties prop = new Properties();
		try {
			prop.load(NlsClient.class.getResourceAsStream("config.properties"));
			this.host = prop.getProperty("host");
			logger.info(String.format("load the host (%s) from config.properties file", new Object[] { this.host }));
		} catch (Exception localException) {
		}

		this.sslMode = sslMode;
		this.port = port;

		this.channelFactory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
		this.bootstrap = new ClientBootstrap(this.channelFactory);

		this.timer = new HashedWheelTimer();
		this.pipelineFactory = new NlsClientPipelineFactory(this.timer, 300, sslMode);
		this.bootstrap.setPipelineFactory(this.pipelineFactory);
	}

	@SuppressWarnings("deprecation")
	public NlsFuture createNlsFuture(NlsRequest req, NlsListener listener) throws Exception {
		if ((req == null) || (req.getAppkey() == null) || (listener == null)) {
			throw new IllegalArgumentException("arguments can't be null");
		}

		String asrSC = req.getFormat().toLowerCase();
		if ((!asrSC.startsWith("amr")) && (!asrSC.startsWith("opu")) && (!asrSC.startsWith("pcm"))) {
			throw new IllegalArgumentException("Unsupported asr source: pcm");
		}

		String prefix = "wss://";
		if (!this.sslMode) {
			prefix = "ws://";
		}
		URI uri = URI.create(prefix + this.host);
		URI realuri = URI.create(prefix + uri.getHost() + ":" + this.port + uri.getPath());
		NlsSession session = new NlsSession(this.bootstrap, realuri, req, listener);

		session.start();

		NlsFuture future = new NlsFuture(session);

		if (req != null) {
			future.setAsrSC("pcm");
			future.setSampleRate(req.getSampleRate());
		}

		if (req.isEnableCompress()) {
			this.futureCompressService = Executors.newCachedThreadPool();
			future.setService(this.futureCompressService);
			future.enableVoiceCompress();
		}
		return future;
	}

	public void close() {
		this.timer.stop();
		this.bootstrap.releaseExternalResources();
		this.bootstrap.shutdown();
		if (this.futureCompressService != null)
			this.futureCompressService.shutdown();
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getPort() {
		return this.port;
	}

	public String getHost() {
		return this.host;
	}
}