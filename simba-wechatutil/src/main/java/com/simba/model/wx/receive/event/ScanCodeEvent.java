package com.simba.model.wx.receive.event;

/**
 * 扫码推事件的事件推送/扫码推事件且弹出“消息接收中”提示框的事件推送
 * 
 * @author caozhejun
 *
 */
public class ScanCodeEvent extends BaseEvent {

	/**
	 * 扫描类型，一般是qrcode
	 */
	private String scanType;

	/**
	 * 扫描结果，即二维码对应的字符串信息
	 */
	private String scanResult;

	public String getScanType() {
		return scanType;
	}

	public void setScanType(String scanType) {
		this.scanType = scanType;
	}

	public String getScanResult() {
		return scanResult;
	}

	public void setScanResult(String scanResult) {
		this.scanResult = scanResult;
	}

}
