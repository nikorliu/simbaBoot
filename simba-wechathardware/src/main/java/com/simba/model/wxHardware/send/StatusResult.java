package com.simba.model.wxHardware.send;

import com.simba.model.wx.ErrMsg;

public class StatusResult extends ErrMsg {

	/**
	 * 设备状态，目前取值如下： 0：未授权 1：已经授权（尚未被用户绑定） 2：已经被用户绑定 3：属性未设置
	 */
	private int status;

	/**
	 * 
	 */
	private String status_info;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatus_info() {
		return status_info;
	}

	public void setStatus_info(String status_info) {
		this.status_info = status_info;
	}

}
