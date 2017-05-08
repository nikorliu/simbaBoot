package com.simba.model.wx.template;

import com.simba.model.wx.ErrMsg;

public class TemplateSendResult extends ErrMsg {

	private long msgid;

	public long getMsgid() {
		return msgid;
	}

	public void setMsgid(long msgid) {
		this.msgid = msgid;
	}

}
