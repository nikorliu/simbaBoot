package com.simba.util.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wx.ErrMsg;
import com.simba.model.wx.msg.CustomService;
import com.simba.model.wx.msg.CustomServiceImageMsg;
import com.simba.model.wx.msg.CustomServiceMpNewsMsg;
import com.simba.model.wx.msg.CustomServiceMusicMsg;
import com.simba.model.wx.msg.CustomServiceNewsMsg;
import com.simba.model.wx.msg.CustomServiceTextMsg;
import com.simba.model.wx.msg.CustomServiceVideoMsg;
import com.simba.model.wx.msg.CustomServiceVoiceMsg;
import com.simba.model.wx.msg.CustomServiceWxCardMsg;
import com.simba.model.wx.msg.ImageMsg;
import com.simba.model.wx.msg.MpNewsMsg;
import com.simba.model.wx.msg.MusicMsg;
import com.simba.model.wx.msg.NewsMsg;
import com.simba.model.wx.msg.Text;
import com.simba.model.wx.msg.TextMsg;
import com.simba.model.wx.msg.VideoMsg;
import com.simba.model.wx.msg.VoiceMsg;
import com.simba.model.wx.msg.WxCardMsg;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.PostJsonUtil;
import com.simba.util.common.WxConstantData;

/**
 * 发送客服消息
 * 
 * @author caozhejun
 *
 */
@Component
public class SendMessageWxUtil {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private PostJsonUtil postJsonUtil;

	/**
	 * 发送文本消息
	 * 
	 * @param openid
	 *            接收者
	 * @param content
	 *            内容
	 */
	public String send(String openid, String content) {
		TextMsg textMsg = new TextMsg();
		Text text = new Text();
		text.setContent(content);
		textMsg.setText(text);
		textMsg.setTouser(openid);
		return send(textMsg);
	}

	/**
	 * 发送文本消息
	 * 
	 * @param msg
	 */
	public String send(TextMsg msg) {
		return send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送文本消息
	 * 
	 * @param openid
	 *            接收者
	 * @param content
	 *            内容
	 * @param kfAccount
	 *            客服账号
	 */
	public String send(String openid, String content, String kfAccount) {
		CustomServiceTextMsg msg = new CustomServiceTextMsg();
		Text text = new Text();
		text.setContent(content);
		msg.setText(text);
		msg.setTouser(openid);
		CustomService service = new CustomService();
		service.setKf_account(kfAccount);
		msg.setCustomservice(service);
		return send(msg);
	}

	/**
	 * 发送文本消息(以某个客服帐号来发消息)
	 * 
	 * @param msg
	 */
	public String send(CustomServiceTextMsg msg) {
		return send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送图片消息
	 * 
	 * @param msg
	 */
	public String send(ImageMsg msg) {
		return send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送图片消息(以某个客服帐号来发消息)
	 * 
	 * @param msg
	 */
	public String send(CustomServiceImageMsg msg) {
		return send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送语音消息
	 * 
	 * @param msg
	 */
	public String send(VoiceMsg msg) {
		return send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送语音消息(以某个客服帐号来发消息)
	 * 
	 * @param msg
	 */
	public String send(CustomServiceVoiceMsg msg) {
		return send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送视频消息
	 * 
	 * @param msg
	 */
	public String send(VideoMsg msg) {
		return send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送视频消息(以某个客服帐号来发消息)
	 * 
	 * @param msg
	 */
	public String send(CustomServiceVideoMsg msg) {
		return send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送音乐消息
	 * 
	 * @param msg
	 */
	public String send(MusicMsg msg) {
		return send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送音乐消息(以某个客服帐号来发消息)
	 * 
	 * @param msg
	 */
	public String send(CustomServiceMusicMsg msg) {
		return send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送图文消息（点击跳转到外链） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应
	 * 
	 * @param msg
	 */
	public String send(NewsMsg msg) {
		return send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送图文消息（点击跳转到外链） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应(以某个客服帐号来发消息)
	 * 
	 * @param msg
	 */
	public String send(CustomServiceNewsMsg msg) {
		return send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送图文消息（点击跳转到图文消息页面） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
	 * 
	 * @param msg
	 */
	public String send(MpNewsMsg msg) {
		return send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送图文消息（点击跳转到图文消息页面） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。(以某个客服帐号来发消息)
	 * 
	 * @param msg
	 */
	public String send(CustomServiceMpNewsMsg msg) {
		return send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送卡券(仅支持非自定义Code码和导入code模式的卡券的卡券)
	 * 
	 * @param msg
	 */
	public String send(WxCardMsg msg) {
		return send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送卡券(仅支持非自定义Code码和导入code模式的卡券的卡券,以某个客服帐号来发消息)
	 * 
	 * @param msg
	 */
	public String send(CustomServiceWxCardMsg msg) {
		return send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送客服消息
	 * 
	 * @param json
	 */
	private String send(String json) {
		String url = WxConstantData.sendMsgUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		postJsonUtil.postJson(url, json, "发送客服消息", ErrMsg.class);
		return json;
	}
}
