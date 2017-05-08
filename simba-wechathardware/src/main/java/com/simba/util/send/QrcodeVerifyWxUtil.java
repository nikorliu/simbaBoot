package com.simba.util.send;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wxHardware.send.QrcodeVerifyResult;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.PostJsonUtil;
import com.simba.util.common.WxHardwareConstantData;

/**
 * 验证二维码
 * 
 * @author caozhejun
 *
 */
@Component
public class QrcodeVerifyWxUtil {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private PostJsonUtil postJsonUtil;

	/**
	 * 验证二维码
	 * 
	 * @param ticket
	 * @return
	 */
	public QrcodeVerifyResult verify(String ticket) {
		Map<String, String> params = new HashMap<>();
		params.put("ticket", ticket);
		String json = FastJsonUtil.toJson(params);
		String url = WxHardwareConstantData.qrcodeVerifyUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		return postJsonUtil.postJson(url, json, "验证二维码", QrcodeVerifyResult.class);
	}
}
