package com.simba.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.exception.WxException;
import com.simba.util.common.AccessTokenUtil;

/**
 * 微信工具类的Aspect Aop工具类，用来处理access_token过期无效
 * 
 * @author caozhejun
 *
 */
@Aspect
@Component
public class WxUtilAspect {

	private static final Log logger = LogFactory.getLog(WxUtilAspect.class);

	private static final int code = 40001;

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Around(value = "execution(* com.*.util.send.*WxUtil.*(..))")
	public Object dealAccessToken(ProceedingJoinPoint pjd) throws Throwable {
		// 获取目标方法签名
		String signature = pjd.getSignature().toString();
		logger.info("调用微信接口工具类:" + signature);
		long now = System.currentTimeMillis();
		Object result = null;
		try {
			result = pjd.proceed();
		} catch (WxException e) {
			if (e.getErrcode() == code) {
				accessTokenUtil.requestAccessToken();
				result = pjd.proceed();
			} else {
				throw e;
			}
		}
		long consumeTime = System.currentTimeMillis() - now;
		logger.info("执行" + signature + "消耗的时间为" + consumeTime + "毫秒");
		return result;
	}
}
