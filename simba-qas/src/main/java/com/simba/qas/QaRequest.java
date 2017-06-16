package com.simba.qas;

/**
 * 问答服务请求参数
 */
public class QaRequest {
	/** 协议版本，必须2.0 */
	private String version = "2.0";
	/** nui-前缀加appid */
	private String app_key;
	/** 问题 */
	private String question;
	/** 可选参数，domains为主题列表，多个主题用英文半角分号隔开 */
	private Object optional;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getApp_key() {
		return app_key;
	}

	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Object getOptional() {
		return optional;
	}

	public void setOptional(Object optional) {
		this.optional = optional;
	}
}
