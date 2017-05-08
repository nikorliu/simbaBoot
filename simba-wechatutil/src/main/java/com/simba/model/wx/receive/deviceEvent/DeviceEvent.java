package com.simba.model.wx.receive.deviceEvent;

import com.simba.model.wx.receive.BaseReceiveObject;

/**
 * 设备事件
 * 
 * @author caozhejun
 *
 */
public class DeviceEvent extends BaseReceiveObject {

	/**
	 * 事件类型
	 */
	private String event;

	/**
	 * 设备类型，目前为“公众账号原始ID”
	 */
	private String deviceType;

	/**
	 * 设备ID，第三方提供
	 */
	private String deviceId;

	/**
	 * 请求类型： 0：退订设备状态； 1：心跳；（心跳的处理方式跟订阅一样） 2：订阅设备状态;
	 */
	private String opType;

	/**
	 * 微信用户账号的OpenID
	 * 
	 */
	private String openid;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

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

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}
