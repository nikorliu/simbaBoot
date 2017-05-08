package com.simba.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.simba.util.send.JsApiTicketUtil;

/**
 * 定时获取access_token的定时器(每小时执行一次)
 * 
 * @author caozhejun
 *
 */
@Component
public class JsApiTicketJob {

	@Autowired
	private JsApiTicketUtil jsApiTicketUtil;

	@Value("${wx.jsApi.ticket.job.enable}")
	private String enable;

	@Scheduled(fixedRate = 3600000, initialDelay = 15000)
	public void requestAccessToken() {
		if ("true".equals(enable)) {
			jsApiTicketUtil.requestTicket();
		}
	}

}
