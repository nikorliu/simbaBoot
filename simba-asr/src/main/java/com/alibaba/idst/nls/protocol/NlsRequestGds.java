package com.alibaba.idst.nls.protocol;

import com.alibaba.idst.nls.utils.RawJsonText;
import com.google.gson.Gson;

public class NlsRequestGds {
	private String type = "dialogue";
	public RawJsonText content;

	public String getContent() {
		return this.content.text;
	}

	public void setContent(Content contentIn) {
		Gson gson = new Gson();
		this.content = new RawJsonText(gson.toJson(contentIn));
	}

	public void setContent(String txt) {
		if (txt == null) {
			this.content = null;
		} else
			this.content = new RawJsonText(txt);
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
}