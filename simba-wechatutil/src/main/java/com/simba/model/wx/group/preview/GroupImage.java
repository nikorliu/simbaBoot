package com.simba.model.wx.group.preview;

import com.simba.model.wx.msg.Image;

public class GroupImage {

	private String touser;

	private String towxname;

	private Image image;

	private String msgtype = "image";

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
