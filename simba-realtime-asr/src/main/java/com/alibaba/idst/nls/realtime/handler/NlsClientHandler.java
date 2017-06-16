package com.alibaba.idst.nls.realtime.handler;

import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.idst.nls.realtime.NlsClient;
import com.alibaba.idst.nls.realtime.session.SessionEvent;
import com.alibaba.idst.nls.realtime.session.SessionListener;

public class NlsClientHandler extends SimpleChannelUpstreamHandler {
	Logger logger = LoggerFactory.getLogger(NlsClientHandler.class);
	SessionListener listener = null;

	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		SessionEvent evt = new SessionEvent();
		evt.setChannel(ctx.getChannel());
		if (this.listener == null) {
			this.listener = ((SessionListener) NlsClient.listeners.get(ctx.getChannel()));
		}
		if (this.listener != null) {
			this.listener.onChannelClosed(evt);
		} else
			this.logger.error("An isolated channel closed, id = " + ctx.getChannel().getId());
	}

	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		e.getCause().printStackTrace();
		SessionEvent evt = new SessionEvent();
		evt.setErrorMessage("unknown error occur: " + e.getCause().getMessage());
		if (this.listener == null) {
			this.listener = ((SessionListener) NlsClient.listeners.get(ctx.getChannel()));
		}
		if (this.listener != null) {
			this.listener.onOperationFailed(evt);
		} else {
			this.logger.error(evt.getErrorMessage());
		}
		try {
			ctx.getChannel().close();
		} catch (Exception localException) {
		}
	}

	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		if (this.listener == null) {
			this.listener = ((SessionListener) NlsClient.listeners.get(ctx.getChannel()));
		}

		Object msg = e.getMessage();
		if ((msg instanceof WebSocketFrame)) {
			handleWebSocketFrame(ctx, (WebSocketFrame) msg, this.listener);
		} else {
			SessionEvent evt = new SessionEvent();
			evt.setErrorMessage("unexpected message ,only accept text frame");
			ctx.getChannel().write(new CloseWebSocketFrame(1003, "unexpected message only accept text frame")).addListener(ChannelFutureListener.CLOSE);

			this.listener.onOperationFailed(evt);
		}
		ctx.sendUpstream(e);
	}

	private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame, SessionListener listener) {
		SessionEvent evt = new SessionEvent();
		if ((frame instanceof CloseWebSocketFrame)) {
			int code = ((CloseWebSocketFrame) frame).getStatusCode();
			String msg = ((CloseWebSocketFrame) frame).getReasonText();
			this.logger.debug("get close websocket frame with code " + code + " and msg is: " + msg);
			if (code != 1000) {
				evt.getResponse().setStatus_code(code % 1000);
				evt.setErrorMessage(" Received CloseFrame, status code  " + code % 1000 + " with error msg is: " + msg);
				listener.onOperationFailed(evt);
				ctx.getChannel().close();
			} else {
				ctx.getChannel().close();
			}
		} else {
			evt.setChannel(ctx.getChannel());
			evt = listener.onWebSocketFrame(evt, frame);
			if (evt.getErrorMessage() != null)
				listener.onOperationFailed(evt);
		}
	}
}