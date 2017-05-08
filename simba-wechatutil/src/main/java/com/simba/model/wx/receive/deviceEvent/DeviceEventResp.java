package com.simba.model.wx.receive.deviceEvent;

import com.simba.model.wx.receive.BaseReceiveObject;

/**
 * 设备事件响应
 * 
 * @author caozhejun
 *
 */
public class DeviceEventResp extends BaseReceiveObject {

	/**
	 * 设备类型，目前为“公众账号原始ID”
	 */
	private String deviceType;

	/**
	 * 设备ID，第三方提供
	 */
	private String deviceId;

	/**
	 * 设备状态： 0--未连接； 1--已连接
	 */
	private String deviceStatus;

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String toXml() {
		StringBuilder s = new StringBuilder();
		s.append("<xml>");
		s.append("<ToUserName><![CDATA[").append(this.getToUserName()).append("]]></ToUserName>");
		s.append("<FromUserName><![CDATA[").append(this.getFromUserName()).append("]]></FromUserName>");
		s.append("<CreateTime>").append(this.getCreateTime()).append("</CreateTime>");
		s.append("<MsgType><![CDATA[").append(this.getMsgType()).append("]]></MsgType>");
		s.append("<DeviceType><![CDATA[").append(this.getDeviceType()).append("]]></DeviceType>");
		s.append("<DeviceID><![CDATA[").append(this.getDeviceId()).append("]]></DeviceID>");
		s.append("<DeviceStatus>").append(this.getDeviceStatus()).append("</DeviceStatus>");
		s.append("</xml>");
		return s.toString();
	}

	@Override
	public String toString() {
		return toXml();
	}

}
