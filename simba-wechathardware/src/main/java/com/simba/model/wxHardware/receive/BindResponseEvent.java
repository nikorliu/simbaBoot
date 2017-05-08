package com.simba.model.wxHardware.receive;

import org.apache.commons.lang.StringUtils;

import com.simba.util.common.Base64Util;

/**
 * 绑定/解绑 设备事件响应信息
 * 
 * @author caozhejun
 *
 */
public class BindResponseEvent {

	/**
	 * 接收方（微信用户）的user name
	 */
	private String toUserName;

	/**
	 * 发送方（公众号）的user name
	 */
	private String fromUserName;

	/**
	 * 消息创建时间，第三方自己取当前秒级时间戳
	 */
	private long createTime;

	/**
	 * 消息类型（同请求参数）：device_text
	 */
	private String msgType;

	/**
	 * 设备类型（同请求参数）
	 */
	private String deviceType;

	/**
	 * 设备ID，第三方提供
	 */
	private String deviceID;

	/**
	 * 微信客户端生成的session id，用于request和response对应， 因此响应中该字段第三方需要原封不变的带回
	 */
	private String sessionID;

	/**
	 * 消息内容，BASE64编码
	 */
	private String content;

	/**
	 * 消息id，微信后台生成
	 */
	private String msgId;

	/**
	 * 微信用户账号的OpenID
	 */
	private String openID;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("<xml>");
		builder.append("<ToUserName><![CDATA[").append(toUserName).append("]]></ToUserName>");
		builder.append("<FromUserName><![CDATA[").append(fromUserName).append("]]></FromUserName>");
		builder.append("<CreateTime>").append(createTime).append("</CreateTime>");
		builder.append("<MsgType><![CDATA[").append(msgType).append("]]></MsgType>");
		builder.append("<DeviceType><![CDATA[").append(deviceType).append("]]></DeviceType>");
		builder.append("<DeviceID><![CDATA[").append(deviceID).append("]]></DeviceID>");
		builder.append("<SessionID>").append(sessionID).append("</SessionID>");
		if (StringUtils.isNotEmpty(content)) {
			builder.append("<Content><![CDATA[").append(Base64Util.encode(content)).append("]]></Content>");
		} else {
			builder.append("<Content><![CDATA[").append("]]></Content>");
		}
		builder.append("</xml>");
		return builder.toString();
	}

}
