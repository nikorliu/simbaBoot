package com.simba.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simba.model.constant.ConstantData;
import com.simba.util.receive.DealReceiveUtil;

/**
 * 接收微信服务器信息的Controller
 * 
 * @author caozhejun
 *
 */
@Controller
@RequestMapping("/receive")
public class ReceiveController {

	private static final Log logger = LogFactory.getLog(ReceiveController.class);

	@Autowired
	private DealReceiveUtil dealReceiveUtil;

	/**
	 * 接收微信服务器发来的全部数据
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/receiveMsg")
	public String receiveMsg(HttpServletRequest request, ModelMap model) throws IOException {
		String echostr = request.getParameter("echostr");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		if (StringUtils.isNotEmpty(echostr)) {
			logger.info("收到微信服务器发来的测试数据:signature[" + signature + "],timestamp[" + timestamp + "],nonce[" + nonce
					+ "],echostr[" + echostr + "]");
			model.put("message", echostr);
			return "message";
		}
		logger.info("收到微信服务器发来的数据:signature[" + signature + "],timestamp[" + timestamp + "],nonce[" + nonce
				+ "],echostr[" + echostr + "]");
		InputStream in = null;
		try {
			in = request.getInputStream();
			String xml = IOUtils.toString(in, ConstantData.DEFAULT_CHARSET);
			logger.info("*********************接收到微信服务器发来的消息:" + xml + "**********");
			// 处理消息
			String result = dealReceiveUtil.deal(xml);
			model.put("message", result);
		} catch (Exception e) {
			logger.error("处理接收到的微信服务器信息异常", e);
			model.put("message", "");
		} finally {
			IOUtils.closeQuietly(in);
		}
		return "message";
	}

}
