package com.simba.model.wxHardware.send;

import com.simba.model.wx.ErrMsg;

/**
 * 主动发送消息给设备返回的结果对象
 * 
 * @author caozhejun
 *
 */
public class DeviceMsgResult extends ErrMsg {

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
