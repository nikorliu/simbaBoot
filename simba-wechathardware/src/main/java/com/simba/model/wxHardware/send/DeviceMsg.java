package com.simba.model.wxHardware.send;

/**
 * 主动发送消息给设备
 * 
 * @author caozhejun
 *
 */
public class DeviceMsg {

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
	 * 消息内容，BASE64编码
	 */
	private String content;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeviceMsg [device_type=");
		builder.append(device_type);
		builder.append(", device_id=");
		builder.append(device_id);
		builder.append(", open_id=");
		builder.append(open_id);
		builder.append(", content=");
		builder.append(content);
		builder.append("]");
		return builder.toString();
	}

}
