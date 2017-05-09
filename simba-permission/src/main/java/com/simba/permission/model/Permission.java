package com.simba.permission.model;

import java.io.Serializable;

public class Permission implements Serializable {

	private static final long serialVersionUID = 4619631216312892345L;

	/**
	 * 节点唯一 id
	 */
	private int id;

	/**
	 * 显示节点名称
	 */
	private String text;

	/**
	 * 权限代表的url，多个用,隔开
	 */
	private String url;

	private int parentID;

	// ///扩展属性////
	/**
	 * 树节点状态,easyUI使用
	 */
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
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

}
