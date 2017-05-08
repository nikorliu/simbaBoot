package com.simba.model.wx.template;

import com.simba.model.wx.ErrMsg;

public class IndustrySearchResult extends ErrMsg {

	/**
	 * 帐号设置的主营行业
	 */
	private IndustrySearchObject primary_industry;

	/**
	 * 帐号设置的副营行业
	 */
	private IndustrySearchObject secondary_industry;

	public IndustrySearchObject getPrimary_industry() {
		return primary_industry;
	}

	public void setPrimary_industry(IndustrySearchObject primary_industry) {
		this.primary_industry = primary_industry;
	}

	public IndustrySearchObject getSecondary_industry() {
		return secondary_industry;
	}

	public void setSecondary_industry(IndustrySearchObject secondary_industry) {
		this.secondary_industry = secondary_industry;
	}

}
