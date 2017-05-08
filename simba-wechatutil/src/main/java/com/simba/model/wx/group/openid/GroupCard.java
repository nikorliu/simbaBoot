package com.simba.model.wx.group.openid;

import com.simba.model.wx.msg.WxCard;

public class GroupCard {

	private ToUser touser;

	private WxCard wxcard;

	private String msgtype = "wxcard";

	public ToUser getTouser() {
		return touser;
	}

	public void setTouser(ToUser touser) {
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
