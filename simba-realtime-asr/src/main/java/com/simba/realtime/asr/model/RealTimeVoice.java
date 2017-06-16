package com.simba.realtime.asr.model;

/**
 * 实时语音识别对象
 * 
 * @author caozhejun
 *
 */
public class RealTimeVoice {

	private String format = "pcm";

	private String asrUserId;

	private String asrVocabularyId;

	private String appKey;

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getAsrUserId() {
		return asrUserId;
	}

	public void setAsrUserId(String asrUserId) {
		this.asrUserId = asrUserId;
	}

	public String getAsrVocabularyId() {
		return asrVocabularyId;
	}

	public void setAsrVocabularyId(String asrVocabularyId) {
		this.asrVocabularyId = asrVocabularyId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

}
