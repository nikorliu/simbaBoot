package com.simba.model.wx.media;

import com.simba.model.wx.ErrMsg;

public class UploadResult extends ErrMsg {

	/**
	 * 新增的图文消息素材的media_id
	 */
	private String media_id;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

}
