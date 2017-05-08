package com.simba.model.wxHardware.send;

import com.simba.model.wx.ErrMsg;

public class SendStatusResult extends ErrMsg {

	private int ret;

	private String ret_info;

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public String getRet_info() {
		return ret_info;
	}

	public void setRet_info(String ret_info) {
		this.ret_info = ret_info;
	}

}
