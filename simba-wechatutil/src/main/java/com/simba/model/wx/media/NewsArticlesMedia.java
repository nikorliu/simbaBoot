package com.simba.model.wx.media;

import com.simba.model.wx.upload.NewsArticle;

public class NewsArticlesMedia {

	/**
	 * 要修改的图文消息的id
	 */
	private String media_id;

	/**
	 * 要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0
	 */
	private int index = 0;

	private NewsArticle articles;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public NewsArticle getArticles() {
		return articles;
	}

	public void setArticles(NewsArticle articles) {
		this.articles = articles;
	}

}
