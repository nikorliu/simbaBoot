package com.simba.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.simba.framework.util.common.XmlUtil;
import com.simba.interfaces.PayInterface;
import com.simba.model.pay.result.PayResult;
import com.simba.model.pay.result.PayResultRes;

/**
 * 支付结果通用通知 (支付完成后，微信会把相关支付结果和用户信息发送给商户，商户需要接收处理，并返回应答。
 * 
 * 对后台通知交互时，如果微信收到商户的应答不是成功或超时，微信认为通知失败，微信会通过一定的策略定期重新发起通知，尽可能提高通知的成功率，
 * 但微信不保证通知最终能成功。 （通知频率为15/15/30/180/1800/1800/1800/1800/3600，单位：秒）
 * 
 * 注意：同样的通知可能会多次发送给商户系统。商户系统必须能够正确处理重复的通知。
 * 推荐的做法是，当收到通知进行处理时，首先检查对应业务数据的状态，判断该通知是否已经处理过，如果没有处理过再进行处理，如果处理过直接返回结果成功。
 * 在对业务数据进行状态检查和处理之前，要采用数据锁进行并发控制，以避免函数重入造成的数据混乱。
 * 
 * 特别提醒：商户系统对于支付结果通知的内容一定要做签名验证,并校验返回的订单金额是否与商户侧的订单金额一致，防止数据泄漏导致出现“假通知”，造成资金损失。
 * )
 * 
 * @author caozhejun
 *
 */
@Controller
@RequestMapping("/payCallback")
public class PayCallbackController {

	@Autowired(required = false)
	private PayInterface payService;

	/**
	 * 接收微信支付结果通知
	 * 
	 * @param body
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 * @throws DOMException
	 */
	@RequestMapping("/receive")
	public String receive(@RequestBody String body, ModelMap model)
			throws DOMException, XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		PayResult payResult = XmlUtil.toOject(body, PayResult.class);
		payResult.composeCoupons(body);
		payService.dealResult(payResult);
		PayResultRes res = new PayResultRes();
		res.setReturn_code("SUCCESS");
		res.setReturn_msg("OK");
		model.put("message", res.toXML());
		return "message";
	}

}
