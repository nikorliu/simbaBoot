package com.simba.model.wx.menu;

import com.simba.model.wx.ErrMsg;

public class ConditionalMenuCreateResult extends ErrMsg {

	private String menuid;

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

}
