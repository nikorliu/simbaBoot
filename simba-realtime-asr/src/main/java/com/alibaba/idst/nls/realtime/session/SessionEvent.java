package com.alibaba.idst.nls.realtime.session;

import org.jboss.netty.channel.Channel;

import com.alibaba.idst.nls.realtime.event.NlsEvent;

public class SessionEvent extends NlsEvent {
	private Channel channel;

	public Channel getChannel() {
		return this.channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
}