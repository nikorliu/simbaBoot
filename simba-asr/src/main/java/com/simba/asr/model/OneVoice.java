package com.simba.asr.model;

/**
 * 一句话识别对象
 * 
 * @author caozhejun
 *
 */
public class OneVoice {

	private String asrUserId;

	private String asrVocabularyId;

	private String appKey;

	private String asrFormat;

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

	public String getAsrFormat() {
		return asrFormat;
	}

	public void setAsrFormat(String asrFormat) {
		this.asrFormat = asrFormat;
	}

}
