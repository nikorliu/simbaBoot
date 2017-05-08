package com.simba.model.wx.group.filter;

import com.simba.model.wx.msg.Image;

public class GroupVideo {

	private Filter filter;

	// 这里使用image，是因为image和video都是相同的只有一个属性media_id
	private Image mpvideo;

	private String msgtype = "mpvideo";

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
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
