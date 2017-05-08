package com.simba.model.wxHardware.send;

import java.util.List;

/**
 * 设备二维码
 * 
 * @author caozhejun
 *
 */
public class DeviceQrcode {

	/**
	 * 设备id的个数
	 */
	private String device_num;

	/**
	 * 设备id的列表
	 */
	private List<String> device_id_list;

	public String getDevice_num() {
		return device_num;
	}

	public void setDevice_num(String device_num) {
		this.device_num = device_num;
	}

	public List<String> getDevice_id_list() {
		return device_id_list;
	}

	public void setDevice_id_list(List<String> device_id_list) {
		this.device_id_list = device_id_list;
	}

}
