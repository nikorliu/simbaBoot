package com.simba.model.wx.group.filter;

import com.simba.model.wx.msg.Image;

public class GroupImage {

	private Filter filter;

	private Image image;

	private String msgtype = "image";

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
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
