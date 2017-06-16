package com.simba.qas;

/**
 * 答案 Created by yukong.lxx on 2017/3/31 下午12:59.
 */
public class QaAnswer {
	/** 提问问题 */
	private String question;
	/** 答案 */
	private String answer;
	/** 评分(0-1.0)，分值越高准确度越高 */
	private Float score;
	/** 主题 */
	private String domain;
	/** 可选数据，其实topic_id为知识点id，body为原始问题 */
	private Object optional;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Object getOptional() {
		return optional;
	}

	public void setOptional(Object optional) {
		this.optional = optional;
	}
}
