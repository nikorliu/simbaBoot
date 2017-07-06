package com.simba.model.wx.group.openid;

import java.util.List;

public class GroupVideo {

	private List<String> touser;

	private MpVideo mpvideo;

	private String msgtype = "mpvideo";

	public List<String> getTouser() {
		return touser;
	}

	public void setTouser(List<String> touser) {
		this.touser = touser;
	}

	public MpVideo getMpvideo() {
		return mpvideo;
	}

	public void setMpvideo(MpVideo mpvideo) {
		this.mpvideo = mpvideo;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

}
