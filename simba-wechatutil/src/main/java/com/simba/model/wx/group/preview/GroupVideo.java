package com.simba.model.wx.group.preview;

import com.simba.model.wx.msg.Image;

public class GroupVideo {

	private String touser;

	private String towxname;

	// 这里使用image，是因为image和video都是相同的只有一个属性media_id
	private Image mpvideo;

	private String msgtype = "mpvideo";

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

	public Image getMpvideo() {
		return mpvideo;
	}

	public void setMpvideo(Image mpvideo) {
		this.mpvideo = mpvideo;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

}
