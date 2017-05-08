package com.simba.model.wx.group.filter;

import com.simba.model.wx.msg.Text;

public class GroupText {

	private Filter filter;

	private Text text;

	private String msgtype = "text";

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

}
