package com.simba.model.wx.tag;

import java.util.List;

import com.simba.model.wx.ErrMsg;

public class TagIdList extends ErrMsg {

	private List<Long> tagid_list;

	public List<Long> getTagid_list() {
		return tagid_list;
	}

	public void setTagid_list(List<Long> tagid_list) {
		this.tagid_list = tagid_list;
	}

}
