package com.simba.util.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.simba.exception.WxException;
import com.simba.framework.util.http.HttpClientUtil;
import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wx.ErrMsg;

/**
 * 发送get请求到微信服务器的工具类
 * 
 * @author caozhejun
 *
 */
@Component
public class GetUtil {

	private static final Log logger = LogFactory.getLog(GetUtil.class);

	/**
	 * 发送get请求到微信服务器
	 * 
	 * @param url
	 *            微信服务器url地址
	 * @param msg
	 *            提示的log信息
	 * @param t
	 * @return
	 */
	public <T extends ErrMsg> T get(String url, String msg, Class<T> t) {
		String resp = HttpClientUtil.get(url);
		logger.info(msg + "返回结果:" + resp);
		T result = FastJsonUtil.toObject(resp, t);
		if (result.getErrcode() != 0) {
			logger.error(msg + "失败:" + result.getErrcode() + "," + result.getErrmsg());
			throw new WxException(msg + "失败", result.getErrcode(), result.getErrmsg());
		}
		return result;
	}
}
