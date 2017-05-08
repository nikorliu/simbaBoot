package com.simba.model.wx.tag;

import java.util.List;

public class BatchTag {

	private List<Fans> openid_list;

	private long tagid;

	public List<Fans> getOpenid_list() {
		return openid_list;
	}

	public void setOpenid_list(List<Fans> openid_list) {
		this.openid_list = openid_list;
	}

	public long getTagid() {
		return tagid;
	}

	public void setTagid(long tagid) {
		this.tagid = tagid;
	}

}
