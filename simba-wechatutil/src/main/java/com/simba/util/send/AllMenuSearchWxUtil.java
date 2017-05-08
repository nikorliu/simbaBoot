package com.simba.util.send;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.framework.util.http.HttpClientUtil;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.WxConstantData;

/**
 * 获取自定义菜单配置接口
 * 
 * @author caozhejun
 *
 */
@Component
public class AllMenuSearchWxUtil {

	private static final Log logger = LogFactory.getLog(AllMenuSearchWxUtil.class);

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	/**
	 * 本接口无论公众号的接口是如何设置的，都能查询到接口，而自定义菜单查询接口则仅能查询到使用API设置的菜单配置。
	 * 
	 * @return
	 */
	public String search() {
		String url = WxConstantData.searchAllMenuUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String resp = HttpClientUtil.get(url);
		logger.info("获取自定义菜单配置返回结果:" + resp);
		return resp;
	}
}
