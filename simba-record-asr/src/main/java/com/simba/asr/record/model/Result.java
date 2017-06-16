package com.simba.asr.record.model;

import java.util.List;

public class Result {

	/**
	 * 转写任务ID
	 */
	private String id;

	/**
	 * 该转写任务的当前状态, 三种取值：RUNNING, SUCCEED, FAILED
	 */
	private String status;

	/**
	 * 错误码。当status为FAILED时存在。
	 */
	private int status_code;

	/**
	 * 对错误状态的进一步描述。当status为FAILED时存在
	 */
	private String error_message;

	/**
	 * 转写的结果数据。当status为SUCCEED时存在
	 */
	private List<SentenceResult> result;

	/**
	 * 转写的音频文件总时长(ms)。当status为SUCCEED时存在
	 */
	private long biz_duration;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStatus_code() {
		return status_code;
	}

	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	public List<SentenceResult> getResult() {
		return result;
	}

	public void setResult(List<SentenceResult> result) {
		this.result = result;
	}

	public long getBiz_duration() {
		return biz_duration;
	}

	public void setBiz_duration(long biz_duration) {
		this.biz_duration = biz_duration;
	}

}
