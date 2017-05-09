package com.simba.permission.model;

import java.io.Serializable;

/**
 * 机构
 * 
 * @author caozj
 *
 */
public class Org implements Serializable {

	private static final long serialVersionUID = 5050560047226916827L;

	/**
	 * 节点唯一 id
	 */
	private int id;

	/**
	 * 显示节点名称
	 */
	private String text;

	private int parentID;

	/**
	 * 排序
	 */
	private int orderNo;

	// ///扩展属性////
	/**
	 * 树节点状态,easyUI使用
	 */
	private String state;

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
