package com.simba.qas;

import java.util.List;

/**
 * 问答服务返回结果
 */
public class QaResponse {

	private String id;
	private Boolean success;
	private String error_code;
	private String error_message;
	private List<QaAnswer> answers;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	public List<QaAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<QaAnswer> answers) {
		this.answers = answers;
	}
}
