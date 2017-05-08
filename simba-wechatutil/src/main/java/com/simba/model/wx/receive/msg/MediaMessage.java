package com.simba.model.wx.receive.msg;

/**
 * 媒体消息
 * 
 * @author caozhejun
 *
 */
public class MediaMessage extends BaseMessage {

	/**
	 * 消息媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	private String mediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}
