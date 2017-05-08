package com.simba.model.wx.tag;

import com.simba.model.wx.ErrMsg;

public class ListFansResult extends ErrMsg {

	/**
	 * 这次获取的粉丝数量
	 */
	private int count;

	private FansData data;

	/**
	 * 拉取列表最后一个用户的openid
	 */
	private String next_openid;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public FansData getData() {
		return data;
	}

	public void setData(FansData data) {
		this.data = data;
	}

	public String getNext_openid() {
		return next_openid;
	}

	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}

}
