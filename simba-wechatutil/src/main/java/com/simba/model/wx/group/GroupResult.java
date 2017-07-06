package com.simba.model.wx.group;

import com.simba.model.wx.ErrMsg;

public class GroupResult extends ErrMsg {

	/**
	 * 消息发送任务的ID
	 */
	private long msg_id;

	/**
	 * 消息的数据ID，该字段只有在群发图文消息时，才会出现。可以用于在图文分析数据接口中，获取到对应的图文消息的数据，
	 * 是图文分析数据接口中的msgid字段中的前半部分，详见图文分析数据接口中的msgid字段的介绍。
	 */
	private long msg_data_id;

	/**
	 * 提交的json内容
	 */
	private String json;

	public long getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(long msg_id) {
		this.msg_id = msg_id;
	}

	public long getMsg_data_id() {
		return msg_data_id;
	}

	public void setMsg_data_id(long msg_data_id) {
		this.msg_data_id = msg_data_id;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
