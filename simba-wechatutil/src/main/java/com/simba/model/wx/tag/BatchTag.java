package com.simba.model.wx.tag;

import java.util.List;

public class BatchTag {

	private List<String> openid_list;

	private long tagid;

	public List<String> getOpenid_list() {
		return openid_list;
	}

	public void setOpenid_list(List<String> openid_list) {
		this.openid_list = openid_list;
	}

	public long getTagid() {
		return tagid;
	}

	public void setTagid(long tagid) {
		this.tagid = tagid;
	}

}
