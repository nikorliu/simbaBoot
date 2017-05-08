package com.simba.util.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.framework.util.code.EncryptUtil;
import com.simba.util.send.JsApiTicketUtil;

/**
 * 签名工具类
 * 
 * @author caozhejun
 *
 */
@Component
public class SignUtil {

	@Autowired
	private JsApiTicketUtil jsApiTicketUtil;

	/**
	 * 获取签名
	 * 
	 * @param url
	 * @param noncestr
	 * @param timestamp
	 * @return
	 */
	public String sign(String url, String noncestr, long timestamp) {
		int index = url.lastIndexOf("#");
		if (index > -1) {
			url = url.substring(0, index);
		}
		String ticket = jsApiTicketUtil.getTicket();
		String source = "jsapi_ticket=" + ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
		String sign = EncryptUtil.sha1(source);
		return sign;
	}

}
