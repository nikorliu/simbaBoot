package com.simba.model.wx.group.openid;

import com.simba.model.wx.msg.Voice;

public class GroupVoice {

	private ToUser touser;

	private Voice voice;

	private String msgtype = "voice";

	public ToUser getTouser() {
		return touser;
	}

	public void setTouser(ToUser touser) {
		this.touser = touser;
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
