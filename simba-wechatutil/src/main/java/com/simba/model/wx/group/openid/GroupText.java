package com.simba.model.wx.group.openid;

import java.util.List;

import com.simba.model.wx.msg.Text;

public class GroupText {

	private List<String> touser;

	private Text text;

	private String msgtype = "text";

	public List<String> getTouser() {
		return touser;
	}

	public void setTouser(List<String> touser) {
		this.touser = touser;
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
