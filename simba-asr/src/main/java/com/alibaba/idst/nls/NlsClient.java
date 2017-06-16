package com.alibaba.idst.nls;

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

import com.alibaba.idst.nls.event.NlsListener;
import com.alibaba.idst.nls.protocol.NlsRequest;
import com.alibaba.idst.nls.protocol.NlsRequestASR;
import com.alibaba.idst.nls.session.NlsSession;
import com.alibaba.idst.nls.session.SessionListener;

public class NlsClient {
	private String host = "nls.dataapi.aliyun.com";
	private int port = 443;
	private ClientBootstrap bootstrap;
	private NioClientSocketChannelFactory channelFactory;
	private NlsClientPipelineFactory pipelineFactory;
	private HashedWheelTimer timer;
	private boolean sslMode = true;
	private int ConnectTimeoutMillis = 10000;
	private ExecutorService futureCompressService;
	public static final int timeout = 300;
	public static ChannelLocal<SessionListener> listeners = new ChannelLocal<>(true);
	public static ChannelLocal<WebSocketClientHandshaker> handshakers = new ChannelLocal<>(true);
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
		this.bootstrap.setOption("connectTimeoutMillis", Integer.valueOf(this.ConnectTimeoutMillis));
	}

	public NlsFuture createNlsFuture(NlsRequest req, NlsListener listener) throws Exception {
		if ((req == null) || (req.getApp_key() == null) || (listener == null)) {
			throw new IllegalArgumentException("arguments can't be null");
		}
		NlsRequestASR asr_in = req.getASR_req();
		if ((asr_in == null) && (req.getAsrFake() == null) && (req.getTtsRequest() == null)) {
			throw new IllegalArgumentException("No input audio/text specified!");
		}

		if (asr_in != null) {
			String asrSC = asr_in.asrSC.toLowerCase();
			if ((!asrSC.startsWith("amr")) && (!asrSC.startsWith("opu")) && (!asrSC.startsWith("pcm")) && (!asrSC.startsWith("opus")) && (!asrSC.startsWith("wav")) && (!asrSC.startsWith("speex"))) {
				throw new IllegalArgumentException("Unsupported asr source: " + asr_in.asrSC);
			}

		}

		String prefix = "wss://";
		if (!this.sslMode) {
			prefix = "ws://";
		}
		URI uri = URI.create(prefix + this.host + ":" + this.port);
		logger.info("url is: " + uri.getHost());
		NlsSession session = new NlsSession(this.bootstrap, uri, req, listener);
		session.start();

		NlsFuture future = new NlsFuture(session);

		if (asr_in != null) {
			if ((req.isEnableCompress()) && ((asr_in.asrSC.equals("pcm")) || (asr_in.asrSC.equals("wav")) || (asr_in.asrSC.equals("opu"))) && (asr_in.sampleRate.equals("16"))) {
				future.setSampleRate(16000);
				future.setAsrSC("pcm");
			} else {
				future.setAsrSC(asr_in.asrSC);
			}
		}

		if (req.isEnableCompress()) {
			this.futureCompressService = Executors.newCachedThreadPool();
			future.setService(this.futureCompressService);
			future.enableVoiceCompress();
		}

		if (!req.getBstreamAttached().booleanValue()) {
			session.sendFinishSignal();
		}

		return future;
	}

	public void setConnectTimeoutMillis(int connectTimeoutMillis) {
		this.ConnectTimeoutMillis = connectTimeoutMillis;
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