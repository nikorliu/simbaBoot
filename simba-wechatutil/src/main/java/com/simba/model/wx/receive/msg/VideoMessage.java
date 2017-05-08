package com.simba.model.wx.receive.msg;

/**
 * 视频消息/小视频消息
 * 
 * @author caozhejun
 *
 */
public class VideoMessage extends MediaMessage {

	/**
	 * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	private String thumbMediaId;

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VideoMessage [thumbMediaId=");
		builder.append(thumbMediaId);
		builder.append("]");
		return builder.toString();
	}

}
