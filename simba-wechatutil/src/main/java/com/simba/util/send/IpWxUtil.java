package com.simba.util.send;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.model.wx.IpList;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.GetUtil;
import com.simba.util.common.WxConstantData;

/**
 * 获取微信服务器IP地址
 * 
 * @author caozhejun
 *
 */
@Component
public class IpWxUtil {

	private static final Log logger = LogFactory.getLog(IpWxUtil.class);

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private GetUtil getUtil;

	/**
	 * 获取微信服务器IP地址列表
	 * 
	 * @return
	 */
	public List<String> getIpList() {
		String url = WxConstantData.serverIpUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		IpList ipList = getUtil.get(url, "从微信服务器获取微信服务器IP地址列表", IpList.class);
		if (ipList.getIp_list() != null && ipList.getIp_list().size() > 0) {
			logger.info("从微信服务器获取微信服务器IP地址列表:" + ipList.getIp_list());
		}
		return ipList.getIp_list();
	}
}
