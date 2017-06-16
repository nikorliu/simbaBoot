package com.alibaba.idst.nls.protocol.GdsContent;

public class GdsContentPrior {
	private String domain;
	private String intent;
	private PriorQud qud;

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getIntent() {
		return this.intent;
	}

	public void setIntent(String intent) {
		this.intent = intent;
	}

	public PriorQud getQud() {
		return this.qud;
	}

	public void setQud(PriorQud qud) {
		this.qud = qud;
	}
}