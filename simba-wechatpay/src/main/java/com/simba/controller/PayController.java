package com.simba.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simba.framework.util.common.ServerUtil;
import com.simba.framework.util.data.RandomUtil;
import com.simba.framework.util.json.FastJsonUtil;
import com.simba.interfaces.PayInterface;
import com.simba.model.pay.unifiedorder.UnifiedOrderReq;
import com.simba.model.pay.unifiedorder.UnifiedOrderRes;
import com.simba.util.common.SignUtil;
import com.simba.util.send.WxPayUtil;

/**
 * 微信支付Controller
 * 
 * @author caozhejun
 *
 */
@Controller
@RequestMapping("/pay")
public class PayController {

	@Value("${appID}")
	private String appid;

	@Value("${wx.pay.mchid}")
	private String mchId;

	@Value("${wx.pay.domain}")
	private String wxPayDomain;

	@Value("${wx.pay.key}")
	private String key;

	@Autowired(required = false)
	private PayInterface payService;

	/**
	 * 产生订单
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping("/order")
	public String order(HttpServletRequest request, UnifiedOrderReq req, ModelMap model) {
		req.setAppid(appid);
		req.setMch_id(mchId);
		req.setOut_trade_no(RandomUtil.random32Chars());
		req.setSpbill_create_ip(ServerUtil.getProxyIp(request));
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		req.setTime_start(format.format(now));
		if (StringUtils.isEmpty(req.getNotify_url())) {
			req.setNotify_url(wxPayDomain + "/payCallback/receive.do");
		}
		if (StringUtils.isEmpty(req.getOpenid())) {
			String openid = (String) request.getSession().getAttribute("openid");
			req.setOpenid(openid);
		}
		payService.dealOrder(req);
		UnifiedOrderRes res = WxPayUtil.getInstance().unifiedOrder(req);
		String prePayId = res.getPrepay_id();
		String codeUrl = res.getCode_url();
		Map<String, String> params = new HashMap<>();
		params.put("appId", appid);
		params.put("timeStamp", now.getTime() / 1000 + "");
		params.put("nonceStr", RandomUtil.random32Chars());
		params.put("package", "prepay_id=" + prePayId);
		params.put("signType", "MD5");
		String sign = SignUtil.getInstance().createSign(params, key);
		params.put("paySign", sign);
		params.put("prePayId", prePayId);
		params.put("codeUrl", codeUrl);
		String json = FastJsonUtil.toJson(params);
		model.put("message", json);
		return "message";
	}
}
