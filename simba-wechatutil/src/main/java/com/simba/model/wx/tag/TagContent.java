package com.simba.model.wx.tag;

public class TagContent {

	/**
	 * 标签id，由微信分配
	 */
	private long id;

	/**
	 * 标签名，UTF8编码
	 */
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
