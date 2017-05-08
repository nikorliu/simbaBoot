package com.simba.model.wx.msg;

public class WxCardMsg {

	private String touser;

	private String msgtype = "wxcard";

	private WxCard wxcard;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public WxCard getWxcard() {
		return wxcard;
	}

	public void setWxcard(WxCard wxcard) {
		this.wxcard = wxcard;
	}

}
