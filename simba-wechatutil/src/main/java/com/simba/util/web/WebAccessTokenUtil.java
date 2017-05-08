package com.simba.util.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.simba.model.wx.ErrMsg;
import com.simba.model.wx.web.UserInfo;
import com.simba.model.wx.web.WebAccessToken;
import com.simba.util.common.GetUtil;
import com.simba.util.common.WxConstantData;

/**
 * 网页授权工具类
 * 
 * @author caozhejun
 *
 */
@Component
public class WebAccessTokenUtil {

	@Autowired
	private GetUtil getUtil;

	@Value("${appID}")
	private String appID;

	@Value("${appsecret}")
	private String appsecret;

	/**
	 * 通过code换取网页授权access_token
	 * 
	 * @param code
	 * @return
	 */
	public WebAccessToken get(String code) {
		String url = WxConstantData.getWebAccessTokenUrl + "?appid=" + appID + "&secret=" + appsecret + "&code=" + code + "&grant_type=authorization_code";
		return getUtil.get(url, "通过code换取网页授权access_token", WebAccessToken.class);
	}

	/**
	 * 刷新access_token
	 * 
	 * @param refreshToken
	 * @return
	 */
	public WebAccessToken refresh(String refreshToken) {
		String url = WxConstantData.refreshWebAccessTokenUrl + "?appid=" + appID + "&grant_type=refresh_token&refresh_token=" + refreshToken;
		return getUtil.get(url, "通过code换取网页授权access_token", WebAccessToken.class);
	}

	/**
	 * 拉取用户信息(需scope为 snsapi_userinfo)
	 * 
	 * @param accessToken
	 * @param openid
	 * @return
	 */
	public UserInfo getUserInfo(String accessToken, String openid) {
		String url = WxConstantData.getUserInfoByWebAccessTokenUrl + "?access_token=" + accessToken + "&openid=" + openid + "&lang=zh_CN";
		return getUtil.get(url, "拉取用户信息", UserInfo.class);
	}

	/**
	 * 检验授权凭证（access_token）是否有效
	 * 
	 * @param accessToken
	 *            网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	 * @param openid
	 *            用户的唯一标识
	 */
	public void check(String accessToken, String openid) {
		String url = WxConstantData.checkWebAccessTokenUrl + "?access_token=" + accessToken + "&openid=" + openid;
		getUtil.get(url, "检验授权凭证（access_token）是否有效", ErrMsg.class);
	}
}
