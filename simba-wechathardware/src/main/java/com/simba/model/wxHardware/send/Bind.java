package com.simba.model.wxHardware.send;

public class Bind {

	/**
	 * 绑定操作合法性的凭证（由微信后台生成，第三方H5通过客户端jsapi获得）
	 */
	private String ticket;

	/**
	 * 设备id
	 */
	private String device_id;

	/**
	 * 用户对应的openid
	 */
	private String openid;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}
