package com.simba.model.wxHardware.send;

import com.simba.model.wx.ErrMsg;

public class GetDeviceQrcodeResult extends ErrMsg {

	private RespMsg resp_msg;

	private String deviceid;

	/**
	 * 设备二维码生产串
	 */
	private String qrticket;

	/**
	 * 产品使用直连SDK时返回的设备证书
	 */
	private String devicelicence;

	public String getDevicelicence() {
		return devicelicence;
	}

	public void setDevicelicence(String devicelicence) {
		this.devicelicence = devicelicence;
	}

	public RespMsg getResp_msg() {
		return resp_msg;
	}

	public void setResp_msg(RespMsg resp_msg) {
		this.resp_msg = resp_msg;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getQrticket() {
		return qrticket;
	}

	public void setQrticket(String qrticket) {
		this.qrticket = qrticket;
	}

}
