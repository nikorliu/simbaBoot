package com.simba.model.wx.receive.event;

/**
 * 点击菜单跳转链接时的事件推送
 * 
 * @author caozhejun
 *
 */
public class MenuEvent extends BaseEvent {

	/**
	 * 指菜单ID，如果是个性化菜单，则可以通过这个字段，知道是哪个规则的菜单被点击了。
	 */
	private String menuId;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}
