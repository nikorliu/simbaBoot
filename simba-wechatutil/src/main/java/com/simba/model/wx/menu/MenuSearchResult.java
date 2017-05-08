package com.simba.model.wx.menu;

import java.util.List;

import com.simba.model.wx.ErrMsg;

/**
 * 自定义菜单查询接口返回的菜单对象
 * 
 * @author caozhejun
 *
 */
public class MenuSearchResult extends ErrMsg {

	private MenuSearch menu;

	private List<ConditionalMenuSearch> conditionalmenu;

	public MenuSearch getMenu() {
		return menu;
	}

	public void setMenu(MenuSearch menu) {
		this.menu = menu;
	}

	public List<ConditionalMenuSearch> getConditionalmenu() {
		return conditionalmenu;
	}

	public void setConditionalmenu(List<ConditionalMenuSearch> conditionalmenu) {
		this.conditionalmenu = conditionalmenu;
	}

}
