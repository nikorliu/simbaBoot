package com.simba.model.wx.menu;

import java.util.List;

public class ConditionalMenu {

	private List<MenuButton> button;

	private MatchRule matchrule;

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

}
