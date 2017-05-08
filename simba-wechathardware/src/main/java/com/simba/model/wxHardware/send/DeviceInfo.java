package com.simba.model.wxHardware.send;

import java.util.List;

public class DeviceInfo {

	/**
	 * 设备id的个数
	 */
	private String device_num;

	/**
	 * 设备id的列表
	 */
	private List<Device> device_list;

	/**
	 * 请求操作的类型，限定取值为： 1：设备更新（更新已授权设备的各属性值）
	 */
	private String op_type;

	public String getDevice_num() {
		return device_num;
	}

	public void setDevice_num(String device_num) {
		this.device_num = device_num;
	}

	public List<Device> getDevice_list() {
		return device_list;
	}

	public void setDevice_list(List<Device> device_list) {
		this.device_list = device_list;
	}

	public String getOp_type() {
		return op_type;
	}

	public void setOp_type(String op_type) {
		this.op_type = op_type;
	}

}
