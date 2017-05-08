package com.simba.util.common;

/**
 * 微信硬件常量类
 * 
 * @author caozhejun
 *
 */
public interface WxHardwareConstantData {

	/**
	 * 主动发送消息给设备
	 */
	String sendMessageToDeviceUrl = "https://" + WxConstantData.wxDomain + "/device/transmsg";

	/**
	 * 获取设备二维码
	 */
	String createQrcodeUrl = "https://" + WxConstantData.wxDomain + "/device/create_qrcode";

	/**
	 * 设备授权
	 */
	String authUrl = "https://" + WxConstantData.wxDomain + "/device/authorize_device";

	/**
	 * 获取deviceid和二维码
	 */
	String getDeviceAndQrcodeUrl = "https://" + WxConstantData.wxDomain + "/device/getqrcode";

	/**
	 * 利用deviceid更新设备属性
	 */
	String updateDeviceInfoUrl = "https://" + WxConstantData.wxDomain + "/device/authorize_device";

	/**
	 * 绑定成功通知
	 */
	String bindUrl = "https://" + WxConstantData.wxDomain + "/device/bind";

	/**
	 * 解绑成功通知
	 */
	String unbindUrl = "https://" + WxConstantData.wxDomain + "/device/unbind";

	/**
	 * 强制绑定用户和设备
	 */
	String bindForceUrl = "https://" + WxConstantData.wxDomain + "/device/compel_bind";

	/**
	 * 强制解绑用户和设备
	 */
	String unbindForceUrl = "https://" + WxConstantData.wxDomain + "/device/compel_unbind";

	/**
	 * 设备状态查询
	 */
	String searchStatusUrl = "https://" + WxConstantData.wxDomain + "/device/get_stat";

	/**
	 * 验证二维码
	 */
	String qrcodeVerifyUrl = "https://" + WxConstantData.wxDomain + "/device/verify_qrcode";

	/**
	 * 获取设备绑定openID
	 */
	String searchOpenIdUrl = "https://" + WxConstantData.wxDomain + "/device/get_openid";

	/**
	 * 通过openid获取用户绑定的deviceid
	 */
	String searchDeviceIdUrl = "https://" + WxConstantData.wxDomain + "/device/get_bind_device";

	/**
	 * 第三方主动发送设备状态消息给微信终端
	 */
	String sendStatusUrl = "https://" + WxConstantData.wxDomain + "/device/transmsg";
}
