package com.simba.util.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wx.group.GroupResult;
import com.simba.model.wx.group.preview.GroupImage;
import com.simba.model.wx.group.preview.GroupMpNews;
import com.simba.model.wx.group.preview.GroupText;
import com.simba.model.wx.group.preview.GroupVideo;
import com.simba.model.wx.group.preview.GroupVoice;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.PostJsonUtil;
import com.simba.util.common.WxConstantData;

/**
 * 预览接口(开发者可通过该接口发送消息给指定用户，在手机端查看消息的样式和排版。为了满足第三方平台开发者的需求，在保留对openID预览能力的同时，
 * 增加了对指定微信号发送预览的能力，但该能力每日调用次数有限制（100次），请勿滥用。)
 * 
 * @author caozhejun
 *
 */
@Component
public class PreviewGroupMessageWxUtil {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private PostJsonUtil postJsonUtil;

	/**
	 * 预览接口(图文消息)
	 * 
	 * @param msg
	 */
	public GroupResult send(GroupMpNews msg) {
		String url = WxConstantData.previewGroupMessageUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(msg);
		return postJsonUtil.postJson(url, json, "预览接口", GroupResult.class);
	}

	/**
	 * 预览接口(文本)
	 * 
	 * @param msg
	 */
	public GroupResult send(GroupText msg) {
		String url = WxConstantData.previewGroupMessageUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(msg);
		return postJsonUtil.postJson(url, json, "预览接口", GroupResult.class);
	}

	/**
	 * 预览接口(语音)
	 * 
	 * @param msg
	 */
	public GroupResult send(GroupVoice msg) {
		String url = WxConstantData.previewGroupMessageUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(msg);
		return postJsonUtil.postJson(url, json, "预览接口", GroupResult.class);
	}

	/**
	 * 预览接口(图片)
	 * 
	 * @param msg
	 */
	public GroupResult send(GroupImage msg) {
		String url = WxConstantData.previewGroupMessageUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(msg);
		return postJsonUtil.postJson(url, json, "预览接口", GroupResult.class);
	}

	/**
	 * 预览接口(视频)
	 * 
	 * @param msg
	 */
	public GroupResult send(GroupVideo msg) {
		String url = WxConstantData.previewGroupMessageUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(msg);
		return postJsonUtil.postJson(url, json, "预览接口", GroupResult.class);
	}
}
