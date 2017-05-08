package com.simba.model.wxHardware.send;

import java.util.List;

import com.simba.model.wx.ErrMsg;

public class SearchOpenIDResult extends ErrMsg {

	private List<String> open_id;

	private RespMsg resp_msg;

	public List<String> getOpen_id() {
		return open_id;
	}

	public void setOpen_id(List<String> open_id) {
		this.open_id = open_id;
	}

	public RespMsg getResp_msg() {
		return resp_msg;
	}

	public void setResp_msg(RespMsg resp_msg) {
		this.resp_msg = resp_msg;
	}

}
