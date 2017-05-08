package com.simba.model.wxHardware.send;

import com.simba.model.wx.ErrMsg;

public class QrcodeVerifyResult extends ErrMsg {

	/**
	 * 设备类型
	 */
	private String device_type;

	/**
	 * 设备id
	 */
	private String device_id;

	/**
	 * 设备的mac地址
	 */
	private String mac;

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

}
