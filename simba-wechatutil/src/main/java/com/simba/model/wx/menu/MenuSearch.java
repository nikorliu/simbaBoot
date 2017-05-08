package com.simba.model.wx.menu;

import java.util.List;

/**
 * 自定义菜单
 * 
 * @author caozhejun
 *
 */
public class MenuSearch {

	private List<MenuButton> button;

	private long menuid;

	public List<MenuButton> getButton() {
		return button;
	}

	public void setButton(List<MenuButton> button) {
		this.button = button;
	}

	public long getMenuid() {
		return menuid;
	}

	public void setMenuid(long menuid) {
		this.menuid = menuid;
	}

}
