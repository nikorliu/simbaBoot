package com.alibaba.idst.nls.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketFrame;

import com.alibaba.idst.nls.NlsClient;
import com.alibaba.idst.nls.protocol.NlsResponse;
import com.alibaba.idst.nls.session.SessionEvent;
import com.alibaba.idst.nls.session.SessionListener;
import com.alibaba.idst.nls.utils.RawJsonText;
import com.google.gson.Gson;

public class NlsClientHandler extends SimpleChannelUpstreamHandler {
	private static Log logger = LogFactory.getLog(NlsClientHandler.class);
	SessionListener listener = null;
	private Gson gson = RawJsonText.createGson();

	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		SessionEvent evt = new SessionEvent();
		evt.setChannel(ctx.getChannel());
		if (this.listener == null) {
			this.listener = ((SessionListener) NlsClient.listeners.get(ctx.getChannel()));
		}
		if (this.listener != null) {
			this.listener.onChannelClosed(evt);
		} else
			logger.error("An isolated channel closed, id = " + ctx.getChannel().getId());
	}

	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		SessionEvent evt = new SessionEvent();
		evt.setErrorMessage("unknown error occur: " + e.getCause().getMessage());
		if (this.listener == null) {
			this.listener = ((SessionListener) NlsClient.listeners.get(ctx.getChannel()));
		}
		if (this.listener != null) {
			listener.onOperationFailed(evt);
		} else {
			logger.error(evt.getErrorMessage());
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
			evt.setErrorMessage("unexpected message only accept text frame");
			ctx.getChannel().write(new CloseWebSocketFrame(1003, "unexpected message only accept text frame")).addListener(ChannelFutureListener.CLOSE);

			this.listener.onOperationFailed(evt);
		}
		ctx.sendUpstream(e);
	}

	private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame, SessionListener listener) {
		SessionEvent evt = new SessionEvent();
		if ((frame instanceof CloseWebSocketFrame)) {
			logger.info("receive colse frame");
			int code = ((CloseWebSocketFrame) frame).getStatusCode();
			String msg = ((CloseWebSocketFrame) frame).getReasonText();
			if (code != 1000) {
				evt.getResponse().setStatus_code(code % 1000);
				evt.setErrorMessage(msg);
				listener.onOperationFailed(evt);
				ctx.getChannel().close();
			} else {
				ctx.getChannel().close();
			}
		} else {
			evt.setChannel(ctx.getChannel());
			evt = listener.onWebSocketFrame(evt, frame);
			if (evt.getErrorMessage() != null) {
				String jsonStr = ((TextWebSocketFrame) frame).getText();
				NlsResponse response = (NlsResponse) this.gson.fromJson(jsonStr, NlsResponse.class);
				evt.setResponse(response);
				listener.onOperationFailed(evt);
				ctx.getChannel().close();
			}
		}
	}
}