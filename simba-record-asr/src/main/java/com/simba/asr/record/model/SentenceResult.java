package com.simba.asr.record.model;

public class SentenceResult {

	/**
	 * 该句所属音轨ID
	 */
	private int channel_id;

	/**
	 * 该句的起始时间偏移(单位: ms)
	 */
	private int begin_time;

	/**
	 * 该句的结束时间偏移(单位: ms)
	 */
	private int end_time;

	/**
	 * 该句的识别文本结果
	 */
	private String text;

	public int getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(int channel_id) {
		this.channel_id = channel_id;
	}

	public int getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(int begin_time) {
		this.begin_time = begin_time;
	}

	public int getEnd_time() {
		return end_time;
	}

	public void setEnd_time(int end_time) {
		this.end_time = end_time;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
