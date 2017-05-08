package com.simba.model.wx.device;

/**
 * 第三方主动发送设备状态消息给微信终端
 * 
 * @author caozhejun
 *
 */
public class DeviceStatus {

	/**
	 * 设备类型，目前为“公众账号原始ID”
	 */
	private String device_type;

	/**
	 * 设备ID
	 */
	private String device_id;

	/**
	 * 微信用户账号的openid
	 */
	private String open_id;

	/**
	 * 消息类型：2--设备状态消息
	 */
	private String msg_type;

	/**
	 * 设备状态：0--未连接， 1--已连接
	 */
	private String device_status;

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

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public String getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}

	public String getDevice_status() {
		return device_status;
	}

	public void setDevice_status(String device_status) {
		this.device_status = device_status;
	}

}
