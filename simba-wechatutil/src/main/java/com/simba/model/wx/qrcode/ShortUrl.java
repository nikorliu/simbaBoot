package com.simba.model.wx.qrcode;

import com.simba.model.wx.ErrMsg;

public class ShortUrl extends ErrMsg {

	/**
	 * 短链接
	 */
	private String short_url;

	public String getShort_url() {
		return short_url;
	}

	public void setShort_url(String short_url) {
		this.short_url = short_url;
	}

}
