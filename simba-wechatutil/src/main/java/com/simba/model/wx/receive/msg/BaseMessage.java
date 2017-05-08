package com.simba.model.wx.receive.msg;

import com.simba.model.wx.receive.BaseReceiveObject;

/**
 * 消息基类
 * 
 * @author caozhejun
 *
 */
public class BaseMessage extends BaseReceiveObject {

	/**
	 * 消息id，64位整型
	 */
	private String msgId;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

}
