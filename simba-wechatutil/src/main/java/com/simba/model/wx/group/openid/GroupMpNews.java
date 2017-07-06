package com.simba.model.wx.group.openid;

import java.util.List;

import com.simba.model.wx.msg.MpNews;

public class GroupMpNews {

	private List<String> touser;

	private MpNews mpnews;

	private String msgtype = "mpnews";

	private int send_ignore_reprint = 0;

	public List<String> getTouser() {
		return touser;
	}

	public void setTouser(List<String> touser) {
		this.touser = touser;
	}

	public MpNews getMpnews() {
		return mpnews;
	}

	public void setMpnews(MpNews mpnews) {
		this.mpnews = mpnews;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public int getSend_ignore_reprint() {
		return send_ignore_reprint;
	}

	public void setSend_ignore_reprint(int send_ignore_reprint) {
		this.send_ignore_reprint = send_ignore_reprint;
	}

}
