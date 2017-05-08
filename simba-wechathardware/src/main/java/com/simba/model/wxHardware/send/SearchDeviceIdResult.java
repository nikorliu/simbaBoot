package com.simba.model.wxHardware.send;

import java.util.List;

import com.simba.model.wx.ErrMsg;

public class SearchDeviceIdResult extends ErrMsg {

	private RespMsg resp_msg;

	private String openid;

	private List<DeviceBaseInfo> device_list;

	public RespMsg getResp_msg() {
		return resp_msg;
	}

	public void setResp_msg(RespMsg resp_msg) {
		this.resp_msg = resp_msg;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public List<DeviceBaseInfo> getDevice_list() {
		return device_list;
	}

	public void setDevice_list(List<DeviceBaseInfo> device_list) {
		this.device_list = device_list;
	}

}
