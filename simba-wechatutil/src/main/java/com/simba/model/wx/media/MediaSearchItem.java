package com.simba.model.wx.media;

public class MediaSearchItem {

	private String media_id;

	/**
	 * 这篇图文消息素材的最后更新时间
	 */
	private long update_time;

	/**
	 * 文件名称
	 */
	private String name;

	/**
	 * 图文页的URL，或者，当获取的列表是图片素材列表时，该字段是图片的URL
	 */
	private String url;

	private MediaContent content;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public long getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(long update_time) {
		this.update_time = update_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MediaContent getContent() {
		return content;
	}

	public void setContent(MediaContent content) {
		this.content = content;
	}

}
