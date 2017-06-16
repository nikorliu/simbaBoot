package com.alibaba.idst.nls.realtime;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.http.HttpRequestEncoder;
import org.jboss.netty.handler.ssl.SslHandler;
import org.jboss.netty.util.HashedWheelTimer;

import com.alibaba.idst.nls.realtime.handler.NlsClientHandler;
import com.alibaba.idst.nls.realtime.websocket.WebSocketClientHandler;
import com.alibaba.idst.nls.realtime.websocket.WebSocketHttpResponseDecoder;

public class NlsClientPipelineFactory implements ChannelPipelineFactory {
	@SuppressWarnings("unused")
	private HashedWheelTimer timer;
	@SuppressWarnings("unused")
	private int timeout;
	private boolean isSSL = true;

	TrustManager[] trustAllCerts = { new X509TrustManager() {
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}

		public void checkClientTrusted(X509Certificate[] certs, String authType) {
		}

		public void checkServerTrusted(X509Certificate[] certs, String authType) {
		}
	} };

	public NlsClientPipelineFactory(HashedWheelTimer timer, int timeout, boolean sslMode) {
		this.timer = timer;
		this.timeout = timeout;
		this.isSSL = sslMode;
	}

	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();

		if (this.isSSL) {
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null, this.trustAllCerts, new SecureRandom());
			SSLEngine sslEngine = context.createSSLEngine();
			sslEngine.setUseClientMode(true);
			SslHandler sslHandler = new SslHandler(sslEngine);
			sslHandler.setEnableRenegotiation(true);

			pipeline.addLast("sslhandler", sslHandler);
		}

		pipeline.addLast("decoder", new WebSocketHttpResponseDecoder());
		pipeline.addLast("encoder", new HttpRequestEncoder());
		pipeline.addLast("ws-handler", new WebSocketClientHandler());
		pipeline.addLast("speech-handler", new NlsClientHandler());
		return pipeline;
	}
}