package com.simba.model.wx.group;

import com.simba.model.wx.ErrMsg;

public class GroupStatus extends ErrMsg {

	/**
	 * 群发消息后返回的消息id
	 */
	private long msg_id;

	/**
	 * 消息发送后的状态，SEND_SUCCESS表示发送成功
	 */
	private String msg_status;

	public long getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(long msg_id) {
		this.msg_id = msg_id;
	}

	public String getMsg_status() {
		return msg_status;
	}

	public void setMsg_status(String msg_status) {
		this.msg_status = msg_status;
	}

}
