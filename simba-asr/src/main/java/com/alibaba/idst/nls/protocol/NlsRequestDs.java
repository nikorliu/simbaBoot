package com.alibaba.idst.nls.protocol;

import com.alibaba.idst.nls.utils.RawJsonText;

public class NlsRequestDs {
	private String type = "dialogue";

	private RawJsonText content = null;

	public String getContent() {
		return this.content.text;
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