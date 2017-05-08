package com.simba.model.wx.autoreply;

import com.simba.model.wx.ErrMsg;

public class AutoReplyResult extends ErrMsg {

	/**
	 * 关注后自动回复是否开启，0代表未开启，1代表开启
	 */
	private int is_add_friend_reply_open;

	/**
	 * 消息自动回复是否开启，0代表未开启，1代表开启
	 */
	private int is_autoreply_open;

	/**
	 * 注后自动回复的信息
	 */
	private ReplyContent add_friend_autoreply_info;

	/**
	 * 消息自动回复的信息
	 */
	private ReplyContent message_default_autoreply_info;

	/**
	 * 关键词自动回复的信息
	 */
	private KeyWordInfo keyword_autoreply_info;

	public int getIs_add_friend_reply_open() {
		return is_add_friend_reply_open;
	}

	public void setIs_add_friend_reply_open(int is_add_friend_reply_open) {
		this.is_add_friend_reply_open = is_add_friend_reply_open;
	}

	public int getIs_autoreply_open() {
		return is_autoreply_open;
	}

	public void setIs_autoreply_open(int is_autoreply_open) {
		this.is_autoreply_open = is_autoreply_open;
	}

	public ReplyContent getAdd_friend_autoreply_info() {
		return add_friend_autoreply_info;
	}

	public void setAdd_friend_autoreply_info(ReplyContent add_friend_autoreply_info) {
		this.add_friend_autoreply_info = add_friend_autoreply_info;
	}

	public ReplyContent getMessage_default_autoreply_info() {
		return message_default_autoreply_info;
	}

	public void setMessage_default_autoreply_info(ReplyContent message_default_autoreply_info) {
		this.message_default_autoreply_info = message_default_autoreply_info;
	}

	public KeyWordInfo getKeyword_autoreply_info() {
		return keyword_autoreply_info;
	}

	public void setKeyword_autoreply_info(KeyWordInfo keyword_autoreply_info) {
		this.keyword_autoreply_info = keyword_autoreply_info;
	}

}
