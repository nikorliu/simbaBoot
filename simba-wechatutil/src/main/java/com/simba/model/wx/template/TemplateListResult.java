package com.simba.model.wx.template;

import java.util.List;

import com.simba.model.wx.ErrMsg;

public class TemplateListResult extends ErrMsg {

	private List<TemplateContent> template_list;

	public List<TemplateContent> getTemplate_list() {
		return template_list;
	}

	public void setTemplate_list(List<TemplateContent> template_list) {
		this.template_list = template_list;
	}

}
