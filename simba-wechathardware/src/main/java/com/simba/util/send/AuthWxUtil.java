package com.simba.util.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wxHardware.send.AuthDevice;
import com.simba.model.wxHardware.send.AuthResult;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.PostJsonUtil;
import com.simba.util.common.WxHardwareConstantData;

/**
 * 授权工具类
 * 
 * @author caozhejun
 *
 */
@Component
public class AuthWxUtil {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private PostJsonUtil postJsonUtil;

	/**
	 * 设备授权
	 * 
	 * @param device
	 * @return
	 */
	public AuthResult send(AuthDevice device) {
		String json = FastJsonUtil.toJson(device);
		String url = WxHardwareConstantData.authUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		return postJsonUtil.postJson(url, json, "设备授权", AuthResult.class);
	}
}
