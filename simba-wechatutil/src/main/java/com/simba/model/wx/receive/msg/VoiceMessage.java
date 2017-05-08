package com.simba.model.wx.receive.msg;

/**
 * 语音消息
 * 
 * @author caozhejun
 *
 */
public class VoiceMessage extends MediaMessage {

	/**
	 * 语音格式，如amr，speex等
	 */
	private String format;

	/**
	 * 开通语音识别后，用户每次发送语音给公众号时，微信会在推送的语音消息XML数据包中，增加一个Recongnition字段（注：由于客户端缓存，
	 * 开发者开启或者关闭语音识别功能，对新关注者立刻生效，对已关注用户需要24小时生效
	 */
	private String recognition;

	public String getRecognition() {
		return recognition;
	}

	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VoiceMessage [format=");
		builder.append(format);
		builder.append(", recognition=");
		builder.append(recognition);
		builder.append("]");
		return builder.toString();
	}

}
