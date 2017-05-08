package com.simba.model.wx.group.preview;

public class GroupCard {

	private String touser;

	private String towxname;

	private WxCard wxcard;

	private String msgtype = "wxcard";

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

	public WxCard getWxcard() {
		return wxcard;
	}

	public void setWxcard(WxCard wxcard) {
		this.wxcard = wxcard;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

}
