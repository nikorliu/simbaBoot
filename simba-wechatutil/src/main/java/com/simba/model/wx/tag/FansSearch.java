package com.simba.model.wx.tag;

public class FansSearch {

	private long tagid;
	
	/**
	 * 第一个拉取的OPENID，不填默认从头开始拉取
	 */
	private String next_openid;

	public long getTagid() {
		return tagid;
	}

	public void setTagid(long tagid) {
		this.tagid = tagid;
	}

	public String getNext_openid() {
		return next_openid;
	}

	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
	
	
}
