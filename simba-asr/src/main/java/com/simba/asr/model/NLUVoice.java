package com.simba.asr.model;

/**
 * 自然语言理解对象
 * 
 * @author caozhejun
 *
 */
public class NLUVoice {

	private String asrUserId;

	private String asrVocabularyId;

	private String appKey;

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
