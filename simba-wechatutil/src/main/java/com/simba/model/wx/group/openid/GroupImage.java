package com.simba.model.wx.group.openid;

import com.simba.model.wx.msg.Image;

public class GroupImage {

	private ToUser touser;

	private Image image;

	private String msgtype = "image";

	public ToUser getTouser() {
		return touser;
	}

	public void setTouser(ToUser touser) {
		this.touser = touser;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

}
