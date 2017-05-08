package com.simba.model.wxHardware.send;

import java.util.List;

import com.simba.model.wx.ErrMsg;

public class AuthResult extends ErrMsg {

	private List<AuthRes> resp;

	public List<AuthRes> getResp() {
		return resp;
	}

	public void setResp(List<AuthRes> resp) {
		this.resp = resp;
	}

}
