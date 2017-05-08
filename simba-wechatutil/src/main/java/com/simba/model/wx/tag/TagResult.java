package com.simba.model.wx.tag;

import com.simba.model.wx.ErrMsg;

public class TagResult extends ErrMsg {

	private TagContent tag;

	public TagContent getTag() {
		return tag;
	}

	public void setTag(TagContent tag) {
		this.tag = tag;
	}

}
