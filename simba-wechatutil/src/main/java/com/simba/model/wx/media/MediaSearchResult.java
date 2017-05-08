package com.simba.model.wx.media;

import java.util.List;

import com.simba.model.wx.ErrMsg;

public class MediaSearchResult extends ErrMsg {

	/**
	 * 该类型的素材的总数
	 */
	private int total_count;

	/**
	 * 本次调用获取的素材的数量
	 */
	private int item_count;

	private List<MediaSearchItem> item;

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	public int getItem_count() {
		return item_count;
	}

	public void setItem_count(int item_count) {
		this.item_count = item_count;
	}

	public List<MediaSearchItem> getItem() {
		return item;
	}

	public void setItem(List<MediaSearchItem> item) {
		this.item = item;
	}

}
