package com.simba.menu.model;

import java.io.Serializable;

/**
 * 菜单
 * 
 * @author caozj
 * 
 */
public class Menu implements Serializable {

	private static final long serialVersionUID = 962450806979152617L;

	/**
	 * 节点唯一 id
	 */
	private int id;

	/**
	 * 显示节点名称
	 */
	private String text;

	/**
	 * 父菜单id
	 */
	private int parentID;

	/**
	 * 菜单url地址
	 */
	private String url;

	/**
	 * 排序
	 */
	private int orderNo;

	// ///扩展属性////
	private String state;

	private String icon;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Menu [id=");
		builder.append(id);
		builder.append(", text=");
		builder.append(text);
		builder.append(", parentID=");
		builder.append(parentID);
		builder.append(", url=");
		builder.append(url);
		builder.append(", orderNo=");
		builder.append(orderNo);
		builder.append(", state=");
		builder.append(state);
		builder.append("]");
		return builder.toString();
	}

}
