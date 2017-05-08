package com.simba.util.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.simba.exception.WxException;
import com.simba.framework.util.http.HttpClientUtil;
import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wx.ErrMsg;

/**
 * 提交json数据post到微信服务器的工具类
 * 
 * @author caozhejun
 *
 */
@Component
public class PostJsonUtil {

	private static final Log logger = LogFactory.getLog(PostJsonUtil.class);

	/**
	 * 提交post请求到微信服务器
	 * 
	 * @param url
	 *            提交数据的url地址
	 * @param json
	 *            提交的json数据
	 * @param msg
	 *            log提示信息
	 * @param t
	 *            返回的类型
	 * @return
	 */
	public <T extends ErrMsg> T postJson(String url, String json, String msg, Class<T> t) {
		logger.info(msg + "提交json数据:" + json);
		String resp = HttpClientUtil.postJson(url, json);
		logger.info(msg + "返回结果:" + resp);
		T result = FastJsonUtil.toObject(resp, t);
		if (result.getErrcode() != 0) {
			logger.error(msg + "失败:" + result.getErrcode() + "," + result.getErrmsg());
			throw new WxException(msg + "失败", result.getErrcode(), result.getErrmsg());
		}
		return result;
	}

}
