package com.alibaba.idst.nls.realtime.session;

import org.jboss.netty.handler.codec.http.websocketx.WebSocketFrame;

public abstract interface SessionListener {
	public abstract void onConnected(SessionEvent paramSessionEvent);

	public abstract void onHandshakeSucceeded(SessionEvent paramSessionEvent);

	public abstract SessionEvent onWebSocketFrame(SessionEvent paramSessionEvent, WebSocketFrame paramWebSocketFrame);

	public abstract void onOperationFailed(SessionEvent paramSessionEvent);

	public abstract void onChannelClosed(SessionEvent paramSessionEvent);
}