package com.simba.model.wxHardware.send;

import java.util.List;

import com.simba.model.wx.ErrMsg;

public class DeviceQrcodeResult extends ErrMsg {

	/**
	 * 成功生成二维码的数量
	 */
	private int device_num;

	/**
	 * 二维码列表
	 */
	private List<QrcodeTicket> code_list;

	public int getDevice_num() {
		return device_num;
	}

	public void setDevice_num(int device_num) {
		this.device_num = device_num;
	}

	public List<QrcodeTicket> getCode_list() {
		return code_list;
	}

	public void setCode_list(List<QrcodeTicket> code_list) {
		this.code_list = code_list;
	}

}
