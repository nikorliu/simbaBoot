package com.simba.util.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wxHardware.send.AuthResult;
import com.simba.model.wxHardware.send.DeviceInfo;
import com.simba.model.wxHardware.send.GetDeviceQrcodeResult;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.GetUtil;
import com.simba.util.common.PostJsonUtil;
import com.simba.util.common.WxHardwareConstantData;

/**
 * 新设备授权接口
 * 
 * @author caozhejun
 *
 */
@Component
public class NewAuthWxUtil {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private PostJsonUtil postJsonUtil;

	@Autowired
	private GetUtil getUtil;

	/**
	 * 获取deviceid和二维码
	 * 
	 * @param productId
	 *            设备的产品编号（由微信硬件平台分配）。可在公众号设备功能管理页面查询。 当product_id
	 *            为‘1’时，不要填写product_id 字段（会引起不必要错误）； 当product_id 不为‘1’时，必须填写
	 *            product_id 字段；
	 * @return
	 */
	public GetDeviceQrcodeResult getDeviceQrcode(String productId) {
		String url = WxHardwareConstantData.getDeviceAndQrcodeUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		if (!"1".equals(productId)) {
			url += "&product_id=" + productId;
		}
		return getUtil.get(url, "获取deviceid和二维码", GetDeviceQrcodeResult.class);
	}

	/**
	 * 利用deviceid更新设备属性
	 * 
	 * @param device
	 * @return
	 */
	public AuthResult updateDeviceInfo(DeviceInfo device) {
		String url = WxHardwareConstantData.updateDeviceInfoUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(device);
		return postJsonUtil.postJson(url, json, "利用deviceid更新设备属性", AuthResult.class);
	}

}
