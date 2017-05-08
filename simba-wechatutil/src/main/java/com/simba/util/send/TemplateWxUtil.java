package com.simba.util.send;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wx.ErrMsg;
import com.simba.model.wx.template.Industry;
import com.simba.model.wx.template.IndustrySearchResult;
import com.simba.model.wx.template.TemplateListResult;
import com.simba.model.wx.template.TemplateMessage;
import com.simba.model.wx.template.TemplateResult;
import com.simba.model.wx.template.TemplateSendResult;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.GetUtil;
import com.simba.util.common.PostJsonUtil;
import com.simba.util.common.WxConstantData;

/**
 * 模板消息(模板消息仅用于公众号向用户发送重要的服务通知，只能用于符合其要求的服务场景中，如信用卡刷卡通知，商品购买成功通知等。
 * 不支持广告等营销类消息以及其它所有可能对用户造成骚扰的消息。)
 * 1、所有服务号都可以在功能->添加功能插件处看到申请模板消息功能的入口，但只有认证后的服务号才可以申请模板消息的使用权限并获得该权限；
 * 2、需要选择公众账号服务所处的2个行业，每月可更改1次所选行业； 3、在所选择行业的模板库中选用已有的模板进行调用； 4、每个账号可以同时使用25个模板。
 * 5、当前每个账号的模板消息的日调用上限为10万次，单个模板没有特殊限制。【2014年11月18日将接口调用频率从默认的日1万次提升为日10万次，
 * 可在MP登录后的开发者中心查看】。当账号粉丝数超过10W/100W/1000W时，模板消息的日调用上限会相应提升，
 * 以公众号MP后台开发者中心页面中标明的数字为准。 1、模板消息调用时主要需要模板ID和模板中各参数的赋值内容；
 * 2、模板中参数内容必须以".DATA"结尾，否则视为保留字； 3、模板保留符号"{{ }}"。
 * 
 * @author caozhejun
 *
 */
@Component
public class TemplateWxUtil {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private PostJsonUtil postJsonUtil;

	@Autowired
	private GetUtil getUtil;

	/**
	 * 设置所属行业
	 * 
	 * @param industry
	 */
	public void setIndustry(Industry industry) {
		String url = WxConstantData.setIndustryUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(industry);
		postJsonUtil.postJson(url, json, "设置所属行业", ErrMsg.class);
	}

	/**
	 * 获取设置的行业信息
	 * 
	 * @return
	 */
	public IndustrySearchResult getIndustry() {
		String url = WxConstantData.getIndustryUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		return getUtil.get(url, "获取设置的行业信息", IndustrySearchResult.class);
	}

	/**
	 * 获得模板ID
	 * 
	 * @param templateIdShort
	 *            模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
	 * @return
	 */
	public TemplateResult getTemplateId(String templateIdShort) {
		String url = WxConstantData.getTemplateUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		Map<String, String> map = new HashMap<>();
		map.put("template_id_short", templateIdShort);
		String json = FastJsonUtil.toJson(map);
		return postJsonUtil.postJson(url, json, "获得模板ID", TemplateResult.class);
	}

	/**
	 * 获取模板列表
	 * 
	 * @return
	 */
	public TemplateListResult listAllTemplates() {
		String url = WxConstantData.listTemplatesUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		return getUtil.get(url, "获取模板列表", TemplateListResult.class);
	}

	/**
	 * 删除模板
	 * 
	 * @param templateId
	 */
	public void delteTemplate(String templateId) {
		String url = WxConstantData.deleteTemplateUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		Map<String, String> map = new HashMap<>();
		map.put("template_id", templateId);
		String json = FastJsonUtil.toJson(map);
		postJsonUtil.postJson(url, json, "删除模板", ErrMsg.class);
	}

	/**
	 * 发送模板消息
	 * 
	 * @param msg
	 */
	public TemplateSendResult send(TemplateMessage msg) {
		String url = WxConstantData.sendTemplateUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(msg);
		return postJsonUtil.postJson(url, json, "发送模板消息", TemplateSendResult.class);
	}

}
