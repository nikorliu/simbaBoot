package com.simba.util.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.model.wx.autoreply.AutoReplyResult;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.GetUtil;
import com.simba.util.common.WxConstantData;

/**
 * 获取公众号的自动回复规则
 * 
 * @author caozhejun
 *
 */
@Component
public class AutoReplyWxUtil {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private GetUtil getUtil;

	/**
	 * 获取公众号的自动回复规则
	 * 
	 * @return
	 */
	public AutoReplyResult get() {
		String url = WxConstantData.autoReplyUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		return getUtil.get(url, "获取公众号的自动回复规则", AutoReplyResult.class);
	}
}
