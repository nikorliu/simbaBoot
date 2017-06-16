package com.simba.asr.model;

/**
 * 语音合成对象
 * 
 * @author caozhejun
 *
 */
public class TTSVoice {

	private String appKey;

	// 返回语音数据格式，支持pcm,wav.alaw
	private String encodeType = "wav";

	// 音量大小默认50，阈值0-100
	private int volumn = 30;

	// 语速，阈值-500~500
	private int rate = 0;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getEncodeType() {
		return encodeType;
	}

	public void setEncodeType(String encodeType) {
		this.encodeType = encodeType;
	}

	public int getVolumn() {
		return volumn;
	}

	public void setVolumn(int volumn) {
		this.volumn = volumn;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

}
