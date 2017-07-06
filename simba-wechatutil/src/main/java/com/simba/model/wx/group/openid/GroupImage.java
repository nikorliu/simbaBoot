package com.simba.model.wx.group.openid;

import java.util.List;

import com.simba.model.wx.msg.Image;

public class GroupImage {

	private List<String> touser;

	private Image image;

	private String msgtype = "image";

	public List<String> getTouser() {
		return touser;
	}

	public void setTouser(List<String> touser) {
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
