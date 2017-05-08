package com.simba.model.wx.receive.event;

import java.util.List;

/**
 * 弹出系统拍照发图的事件推送/弹出拍照或者相册发图的事件推送/弹出微信相册发图器的事件推送
 * 
 * @author caozhejun
 *
 */
public class SendPhotoEvent extends BaseEvent {

	/**
	 * 发送的图片数量
	 */
	private int count;

	/**
	 * 图片的MD5值，开发者若需要，可用于验证接收到图片
	 */
	private List<String> picMd5List;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<String> getPicMd5List() {
		return picMd5List;
	}

	public void setPicMd5List(List<String> picMd5List) {
		this.picMd5List = picMd5List;
	}

}
