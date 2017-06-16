package com.alibaba.idst.nls.realtime.websocket;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.jboss.netty.util.CharsetUtil;

import com.alibaba.idst.nls.realtime.NlsClient;
import com.alibaba.idst.nls.realtime.session.SessionEvent;
import com.alibaba.idst.nls.realtime.session.SessionListener;

public class WebSocketClientHandler extends SimpleChannelUpstreamHandler {
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		Channel ch = ctx.getChannel();
		WebSocketClientHandshaker handshaker = (WebSocketClientHandshaker) NlsClient.handshakers.get(ch);
		if ((handshaker != null) && (!handshaker.isHandshakeComplete())) {
			handshaker.finishHandshake(ch, (HttpResponse) e.getMessage());
			SessionEvent evt = new SessionEvent();
			evt.setChannel(ch);
			SessionListener listener = (SessionListener) NlsClient.listeners.get(ch);
			if (listener != null) {
				listener.onHandshakeSucceeded(evt);
			} else {
				throw new Exception("Can't locate the session object.");
			}
			return;
		}
		if ((e.getMessage() instanceof HttpResponse)) {
			HttpResponse response = (HttpResponse) e.getMessage();

			throw new Exception("Unexpected HttpResponse (status=" + response.getStatus() + ", content=" + response.getContent().toString(CharsetUtil.UTF_8) + ")");
		}
		if ((e.getMessage() instanceof WebSocketFrame))
			ctx.sendUpstream(e);
	}
}