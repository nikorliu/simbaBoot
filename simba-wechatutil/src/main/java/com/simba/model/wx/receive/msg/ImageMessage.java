package com.simba.model.wx.receive.msg;

/**
 * 图片消息
 * 
 * @author caozhejun
 *
 */
public class ImageMessage extends MediaMessage {

	/**
	 * 图片链接（由系统生成）
	 */
	private String picUrl;

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ImageMessage [picUrl=");
		builder.append(picUrl);
		builder.append("]");
		return builder.toString();
	}

}
