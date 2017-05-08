package com.simba.model.wx.media;

import com.simba.model.wx.ErrMsg;

public class UploadMediaResult extends ErrMsg {

	/**
	 * 新增的图文消息素材的media_id
	 */
	private String media_id;

	/**
	 * 新增的图片素材的图片URL（仅新增图片素材时会返回该字段）
	 */
	private String url;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
