package com.simba.model.wx.group.preview;

import com.simba.model.wx.msg.Voice;

public class GroupVoice {

	private String touser;

	private String towxname;

	private Voice voice;

	private String msgtype = "voice";

	public String getTowxname() {
		return towxname;
	}

	public void setTowxname(String towxname) {
		this.towxname = towxname;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
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
