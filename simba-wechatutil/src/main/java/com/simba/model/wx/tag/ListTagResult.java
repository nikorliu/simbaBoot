package com.simba.model.wx.tag;

import java.util.List;

import com.simba.model.wx.ErrMsg;

public class ListTagResult extends ErrMsg {

	private List<ListTagContent> tags;

	public List<ListTagContent> getTags() {
		return tags;
	}

	public void setTags(List<ListTagContent> tags) {
		this.tags = tags;
	}

}
