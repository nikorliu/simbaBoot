package com.simba.asr.record.model;

/**
 * 录音转写对象
 * 
 * @author caozhejun
 *
 */
public class RecordVoice {

	/**
	 * 使用热词特性的用户id
	 */
	private String userId;

	/**
	 * 使用热词特性的词表id
	 */
	private String vocabularyId;

	/**
	 * 音频文件存放的OSS link地址(推荐使用oss存储文件。链接大小限制为128MB)
	 */
	private String ossLink;

	/**
	 * 业务方或者业务场景的标记
	 */
	private String appKey;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getVocabularyId() {
		return vocabularyId;
	}

	public void setVocabularyId(String vocabularyId) {
		this.vocabularyId = vocabularyId;
	}

	public String getOssLink() {
		return ossLink;
	}

	public void setOssLink(String ossLink) {
		this.ossLink = ossLink;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

}
