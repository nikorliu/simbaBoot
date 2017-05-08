package com.simba.util.send;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.framework.util.http.HttpClientUtil;
import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wx.menu.ConditionalMenu;
import com.simba.model.wx.menu.ConditionalMenuCreateResult;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.PostJsonUtil;
import com.simba.util.common.WxConstantData;

/**
 * 个性化菜单 (开发者需要更新菜单时，需将完整配置重新发布一轮)
 * 
 * @author caozhejun
 *
 */
@Component
public class ConditionalMenuWxUtil {

	private static final Log logger = LogFactory.getLog(ConditionalMenuWxUtil.class);

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private PostJsonUtil postJsonUtil;

	/**
	 * 创建个性化菜单
	 * 
	 * @param menu
	 */
	public String create(ConditionalMenu menu) {
		String url = WxConstantData.createConditionalMenuUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(menu);
		ConditionalMenuCreateResult result = postJsonUtil.postJson(url, json, "创建个性化菜单", ConditionalMenuCreateResult.class);
		return result.getMenuid();
	}

	/**
	 * 删除个性化菜单
	 * 
	 * @param menuid
	 */
	public void delete(String menuid) {
		String url = WxConstantData.deleteConditionalMenuUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = "{\"menuid\":\"" + menuid + "\"}";
		String resp = HttpClientUtil.postJson(url, json);
		logger.info("删除个性化菜单返回:" + resp);
	}

	/**
	 * 测试个性化菜单匹配结果
	 * 
	 * @param userID
	 *            可以是粉丝的OpenID，也可以是粉丝的微信号
	 * @return
	 */
	public String tryMatch(String userID) {
		String url = WxConstantData.tryMatchConditionalMenuUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = "{\"user_id\":\"" + userID + "\"}";
		String resp = HttpClientUtil.postJson(url, json);
		logger.info("测试个性化菜单匹配结果返回:" + resp);
		return resp;
	}

}
