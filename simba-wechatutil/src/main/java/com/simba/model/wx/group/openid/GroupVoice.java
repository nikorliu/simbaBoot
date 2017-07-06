package com.simba.model.wx.group.openid;

import java.util.List;

import com.simba.model.wx.msg.Voice;

public class GroupVoice {

	private List<String> touser;

	private Voice voice;

	private String msgtype = "voice";

	public List<String> getTouser() {
		return touser;
	}

	public void setTouser(List<String> touser) {
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
