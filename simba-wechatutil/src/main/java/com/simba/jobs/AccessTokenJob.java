package com.simba.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.simba.util.common.AccessTokenUtil;

/**
 * 定时获取access_token的定时器(每小时执行一次)
 * 
 * @author caozhejun
 *
 */
@Component
public class AccessTokenJob {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Value("${wx.access.token.job.enable}")
	private String enable;

	@Scheduled(fixedRate = 3600000, initialDelay = 5000)
	public void requestAccessToken() {
		if ("true".equals(enable)) {
			accessTokenUtil.requestAccessToken();
		}
	}

}
