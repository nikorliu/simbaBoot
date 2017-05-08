package com.simba.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simba.framework.util.json.FastJsonUtil;
import com.simba.util.web.SignUtil;

/**
 * 授权Controller
 * 
 * @author caozhejun
 *
 */
@RequestMapping("/grant")
@Controller
public class GrantController {

	@Value("${appID}")
	private String appID;

	@Value("${appsecret}")
	private String appsecret;

	@Autowired
	private SignUtil signUtil;

	/**
	 * 获取授权接口数据
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/get")
	public String get(String url, ModelMap model) {
		long timestamp = System.currentTimeMillis() / 1000;
		String nonceStr = UUID.randomUUID().toString().replaceAll("-", "");
		String signature = signUtil.sign(url, nonceStr, timestamp);
		Map<String, Object> params = new HashMap<>();
		params.put("appId", appID);
		params.put("timestamp", timestamp);
		params.put("nonceStr", nonceStr);
		params.put("signature", signature);
		String json = FastJsonUtil.toJson(params);
		model.put("message", json);
		return "message";
	}

}
