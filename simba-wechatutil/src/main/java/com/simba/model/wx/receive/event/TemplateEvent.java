package com.simba.model.wx.receive.event;

/**
 * 模版消息事件推送
 * 
 * @author caozhejun
 *
 */
public class TemplateEvent extends BaseEvent {

	/**
	 * 状态(success/failed:user block/failed: system failed)
	 */
	private String status;

	private String msgID;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsgID() {
		return msgID;
	}

	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

}
