package com.simba.model.wx.receive.msg;

/**
 * 文本消息
 * 
 * @author caozhejun
 *
 */
public class TextMessage extends BaseMessage {

	/**
	 * 文本消息内容
	 */
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TextMessage [content=");
		builder.append(content);
		builder.append("]");
		return builder.toString();
	}

}
