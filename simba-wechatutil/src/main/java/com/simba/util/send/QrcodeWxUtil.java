package com.simba.util.send;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wx.qrcode.ShortUrl;
import com.simba.model.wx.qrcode.TicketResult;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.PostJsonUtil;
import com.simba.util.common.WxConstantData;

/**
 * 二维码微信工具类
 * 
 * @author caozhejun
 *
 */
@Component
public class QrcodeWxUtil {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private PostJsonUtil postJsonUtil;

	/**
	 * 临时二维码
	 * 
	 * @param expire_seconds
	 *            该二维码有效时间，以秒 为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
	 * @param scene_id
	 *            场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	 * @return
	 */
	public TicketResult createTempTicket(int expire_seconds, int scene_id) {
		String url = WxConstantData.createQrcodeTicketUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		Map<String, Integer> sceneId = new HashMap<>(1);
		sceneId.put("scene_id", scene_id);
		Map<String, Map<String, Integer>> scene = new HashMap<>(1);
		scene.put("scene", sceneId);
		Map<String, Object> params = new HashMap<>(1);
		params.put("expire_seconds", expire_seconds);
		params.put("action_name", "QR_SCENE");
		params.put("action_info", scene);
		String json = FastJsonUtil.toJson(params);
		return postJsonUtil.postJson(url, json, "临时二维码", TicketResult.class);
	}

	/**
	 * 永久二维码
	 * 
	 * @param scene_id
	 *            场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	 * @return
	 */
	public TicketResult createicket(int scene_id) {
		String url = WxConstantData.createQrcodeTicketUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		Map<String, Integer> sceneId = new HashMap<>(1);
		sceneId.put("scene_id", scene_id);
		Map<String, Map<String, Integer>> scene = new HashMap<>(1);
		scene.put("scene", sceneId);
		Map<String, Object> params = new HashMap<>(1);
		params.put("action_name", "QR_LIMIT_SCENE");
		params.put("action_info", scene);
		String json = FastJsonUtil.toJson(params);
		return postJsonUtil.postJson(url, json, "永久二维码", TicketResult.class);
	}

	/**
	 * 永久二维码
	 * 
	 * @param scene_str
	 *            场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段
	 * @return
	 */
	public TicketResult createicket(String scene_str) {
		String url = WxConstantData.createQrcodeTicketUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		Map<String, String> sceneId = new HashMap<>(1);
		sceneId.put("scene_str", scene_str);
		Map<String, Map<String, String>> scene = new HashMap<>(1);
		scene.put("scene", sceneId);
		Map<String, Object> params = new HashMap<>(1);
		params.put("action_name", "QR_LIMIT_SCENE");
		params.put("action_info", scene);
		String json = FastJsonUtil.toJson(params);
		return postJsonUtil.postJson(url, json, "永久二维码", TicketResult.class);
	}

	/**
	 * 长链接转短链接
	 * 
	 * @param longUrl
	 *            需要转换的长链接，支持http://、https://、weixin://wxpay 格式的url
	 * @return
	 */
	public String convertShortUrl(String longUrl) {
		String url = WxConstantData.convertShortUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		Map<String, String> params = new HashMap<>(2);
		params.put("action", "long2short");
		params.put("long_url", longUrl);
		String json = FastJsonUtil.toJson(params);
		ShortUrl sUrl = postJsonUtil.postJson(url, json, "长链接转短链接", ShortUrl.class);
		return sUrl.getShort_url();

	}
}
