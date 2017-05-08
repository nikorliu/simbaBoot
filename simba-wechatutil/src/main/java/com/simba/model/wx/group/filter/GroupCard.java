package com.simba.model.wx.group.filter;

import com.simba.model.wx.msg.WxCard;

public class GroupCard {

	private Filter filter;

	private WxCard wxcard;

	private String msgtype = "wxcard";

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
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
