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
	public void send(String openid, String content) {
		TextMsg textMsg = new TextMsg();
		Text text = new Text();
		text.setContent(content);
		textMsg.setText(text);
		textMsg.setTouser(openid);
		send(textMsg);
	}

	/**
	 * 发送文本消息
	 * 
	 * @param msg
	 */
	public void send(TextMsg msg) {
		send(FastJsonUtil.toJson(msg));
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
	public void send(String openid, String content, String kfAccount) {
		CustomServiceTextMsg msg = new CustomServiceTextMsg();
		Text text = new Text();
		text.setContent(content);
		msg.setText(text);
		msg.setTouser(openid);
		CustomService service = new CustomService();
		service.setKf_account(kfAccount);
		msg.setCustomservice(service);
		send(msg);
	}

	/**
	 * 发送文本消息(以某个客服帐号来发消息)
	 * 
	 * @param msg
	 */
	public void send(CustomServiceTextMsg msg) {
		send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送图片消息
	 * 
	 * @param msg
	 */
	public void send(ImageMsg msg) {
		send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送图片消息(以某个客服帐号来发消息)
	 * 
	 * @param msg
	 */
	public void send(CustomServiceImageMsg msg) {
		send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送语音消息
	 * 
	 * @param msg
	 */
	public void send(VoiceMsg msg) {
		send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送语音消息(以某个客服帐号来发消息)
	 * 
	 * @param msg
	 */
	public void send(CustomServiceVoiceMsg msg) {
		send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送视频消息
	 * 
	 * @param msg
	 */
	public void send(VideoMsg msg) {
		send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送视频消息(以某个客服帐号来发消息)
	 * 
	 * @param msg
	 */
	public void send(CustomServiceVideoMsg msg) {
		send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送音乐消息
	 * 
	 * @param msg
	 */
	public void send(MusicMsg msg) {
		send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送音乐消息(以某个客服帐号来发消息)
	 * 
	 * @param msg
	 */
	public void send(CustomServiceMusicMsg msg) {
		send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送图文消息（点击跳转到外链） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应
	 * 
	 * @param msg
	 */
	public void send(NewsMsg msg) {
		send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送图文消息（点击跳转到外链） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应(以某个客服帐号来发消息)
	 * 
	 * @param msg
	 */
	public void send(CustomServiceNewsMsg msg) {
		send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送图文消息（点击跳转到图文消息页面） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
	 * 
	 * @param msg
	 */
	public void send(MpNewsMsg msg) {
		send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送图文消息（点击跳转到图文消息页面） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。(以某个客服帐号来发消息)
	 * 
	 * @param msg
	 */
	public void send(CustomServiceMpNewsMsg msg) {
		send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送卡券(仅支持非自定义Code码和导入code模式的卡券的卡券)
	 * 
	 * @param msg
	 */
	public void send(WxCardMsg msg) {
		send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送卡券(仅支持非自定义Code码和导入code模式的卡券的卡券,以某个客服帐号来发消息)
	 * 
	 * @param msg
	 */
	public void send(CustomServiceWxCardMsg msg) {
		send(FastJsonUtil.toJson(msg));
	}

	/**
	 * 发送客服消息
	 * 
	 * @param json
	 */
	private void send(String json) {
		String url = WxConstantData.sendMsgUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		postJsonUtil.postJson(url, json, "发送客服消息", ErrMsg.class);
	}
}
