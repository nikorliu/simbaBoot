package com.simba.model.wx.receive.event;

import com.simba.model.wx.receive.BaseReceiveObject;

/**
 * 事件基类/点击菜单拉取消息时的事件推送/关注/取消关注事件
 * 
 * @author caozhejun
 *
 */
public class BaseEvent extends BaseReceiveObject {

	/**
	 * 事件类型
	 */
	private String event;

	/**
	 * 事件KEY值
	 */
	private String eventKey;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseEvent [event=");
		builder.append(event);
		builder.append(", eventKey=");
		builder.append(eventKey);
		builder.append("]");
		return builder.toString();
	}

}
