package com.simba.model.wx.group.filter;

import com.simba.model.wx.msg.Voice;

public class GroupVoice {

	private Filter filter;

	private Voice voice;

	private String msgtype = "voice";

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

}
