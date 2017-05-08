package com.simba.model.wxHardware.send;

public class QrcodeTicket {

	/**
	 * 设备id
	 */
	private String device_id;

	/**
	 * 设备id对应的二维码生成串
	 */
	private String ticket;

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QrcodeTicket [device_id=");
		builder.append(device_id);
		builder.append(", ticket=");
		builder.append(ticket);
		builder.append("]");
		return builder.toString();
	}

}
