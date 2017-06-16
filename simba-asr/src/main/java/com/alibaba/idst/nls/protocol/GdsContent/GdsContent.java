package com.alibaba.idst.nls.protocol.GdsContent;

import com.alibaba.idst.nls.protocol.Content;

public class GdsContent extends Content {
	private String text = "";
	private String conversation_id = null;
	private int turn_id;
	private String web_session = null;
	private String asr_score = null;
	private boolean use_asr_result = true;
	private String query_type = null;
	@SuppressWarnings("unused")
	private String optional = null;
	private GdsContentPrior prior = null;

	public GdsContentPrior getPrior() {
		return this.prior;
	}

	public void setPrior(GdsContentPrior prior) {
		this.prior = prior;
	}

	public void setOptional(String optional) {
		this.optional = optional;
	}

	public String getWeb_session() {
		return this.web_session;
	}

	public void setWeb_session(String web_session) {
		this.web_session = web_session;
	}

	public String getQuery_type() {
		return this.query_type;
	}

	public void setQuery_type(String query_type) {
		this.query_type = query_type;
	}

	public String getConversation_id() {
		return this.conversation_id;
	}

	public void setConversation_id(String conversation_id) {
		this.conversation_id = conversation_id;
	}

	public int getTurn_id() {
		return this.turn_id;
	}

	public void setTurn_id(int turn_id) {
		this.turn_id = turn_id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAsr_score() {
		return this.asr_score;
	}

	public void setAsr_score(String asr_score) {
		this.asr_score = asr_score;
	}

	public boolean isUse_asr_result() {
		return this.use_asr_result;
	}

	public void setUse_asr_result(boolean use_asr_result) {
		this.use_asr_result = use_asr_result;
	}
}