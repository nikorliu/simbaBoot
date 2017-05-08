package com.simba.model.wx.upload;

import java.util.List;

public class NewsArticles {

	/**
	 * 图文消息，一个图文消息支持1到8条图文
	 */
	private List<NewsArticle> articles;

	public List<NewsArticle> getArticles() {
		return articles;
	}

	public void setArticles(List<NewsArticle> articles) {
		this.articles = articles;
	}

}
