package com.simba.model.wx.group.openid;

public class GroupVideo {

	private ToUser touser;

	private MpVideo mpvideo;

	private String msgtype = "mpvideo";

	public ToUser getTouser() {
		return touser;
	}

	public void setTouser(ToUser touser) {
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
