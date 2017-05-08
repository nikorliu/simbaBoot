package com.simba.model.wxHardware.receive;

/**
 * 绑定/解绑 设备事件请求信息
 * 
 * @author caozhejun
 *
 */
public class BindRequestEvent {

	/**
	 * 设备ID，第三方提供
	 */
	private String device_id;

	/**
	 * 设备类型，目前为“公众账号原始ID”
	 */
	private String device_type;

	/**
	 * 消息id
	 */
	private String msg_id;

	/**
	 * 消息类型：取值为bind/unbind bind：绑定设备 unbind：解除绑定
	 */
	private String msg_type;

	/**
	 * 消息创建时间，消息后台生成
	 */
	private long create_time;

	/**
	 * 微信账号的OpenID
	 */
	private String open_id;

	/**
	 * 微信客户端生成的session id
	 */
	private String session_id;

	/**
	 * 第三方追加的自定义的数据
	 */
	private String content;

	/**
	 * 二维码字段
	 */
	private String qrcode_suffix_data;

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}

	public String getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}

	public long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getQrcode_suffix_data() {
		return qrcode_suffix_data;
	}

	public void setQrcode_suffix_data(String qrcode_suffix_data) {
		this.qrcode_suffix_data = qrcode_suffix_data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BindRequestEvent [device_id=");
		builder.append(device_id);
		builder.append(", device_type=");
		builder.append(device_type);
		builder.append(", msg_id=");
		builder.append(msg_id);
		builder.append(", msg_type=");
		builder.append(msg_type);
		builder.append(", create_time=");
		builder.append(create_time);
		builder.append(", open_id=");
		builder.append(open_id);
		builder.append(", session_id=");
		builder.append(session_id);
		builder.append(", content=");
		builder.append(content);
		builder.append(", qrcode_suffix_data=");
		builder.append(qrcode_suffix_data);
		builder.append("]");
		return builder.toString();
	}

}
