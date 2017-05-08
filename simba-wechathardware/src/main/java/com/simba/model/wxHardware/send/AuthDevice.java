package com.simba.model.wxHardware.send;

import java.util.List;

public class AuthDevice {

	/**
	 * 设备id的个数
	 */
	private String device_num;

	/**
	 * 设备的列表
	 */
	private List<Device> device_list;

	/**
	 * 请求操作的类型，限定取值为：0：设备授权（缺省值为0） 1：设备更新（更新已授权设备的各属性值）
	 */
	private String op_type;

	/**
	 * 设备的产品编号（由微信硬件平台分配）。可在公众号设备功能管理页面查询。 当 op_type 为‘0’，product_id 为‘1’时，不要填写
	 * product_id 字段（会引起不必要错误）； 当 op_typy 为‘0’，product_id 不为‘1’时，必须填写 product_id
	 * 字段； 当 op_type 为 1 时，不要填写 product_id 字段。
	 */
	private String product_id;

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

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthDevice [device_num=");
		builder.append(device_num);
		builder.append(", device_list=");
		builder.append(device_list);
		builder.append(", op_type=");
		builder.append(op_type);
		builder.append(", product_id=");
		builder.append(product_id);
		builder.append("]");
		return builder.toString();
	}

}
