package com.simba.model.wx.menu;

import java.util.List;

public class ConditionalMenuSearch {

	private List<MenuButton> button;

	private MatchRule matchrule;

	private long menuid;

	public List<MenuButton> getButton() {
		return button;
	}

	public void setButton(List<MenuButton> button) {
		this.button = button;
	}

	public MatchRule getMatchrule() {
		return matchrule;
	}

	public void setMatchrule(MatchRule matchrule) {
		this.matchrule = matchrule;
	}

	public long getMenuid() {
		return menuid;
	}

	public void setMenuid(long menuid) {
		this.menuid = menuid;
	}

}
