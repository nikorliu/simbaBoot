package com.simba.util.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.simba.exception.WxException;
import com.simba.framework.util.http.HttpClientUtil;
import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wx.ErrMsg;

/**
 * 上传文件到微信服务器工具类
 * 
 * @author caozhejun
 *
 */
@Component
public class FileUploadUtil {

	private static final Log logger = LogFactory.getLog(FileUploadUtil.class);

	/**
	 * 上传文件
	 * 
	 * @param url
	 *            微信服务器地址
	 * @param filePath
	 *            文件路径
	 * @param msg
	 *            log提示信息
	 * @param t
	 * @return
	 */
	public <T extends ErrMsg> T upload(String url, String filePath, String msg, Class<T> t) {
		Map<String, String> fileMap = new HashMap<>();
		fileMap.put("media", filePath);
		String resp = HttpClientUtil.fileUpload(url, fileMap);
		logger.info(msg + "返回结果:" + resp);
		T result = FastJsonUtil.toObject(resp, t);
		if (result.getErrcode() != 0 && StringUtils.isNotEmpty(result.getErrmsg())) {
			logger.error(msg + "失败:" + result.getErrcode() + "," + result.getErrmsg());
			throw new WxException(msg + "失败", result.getErrcode(), result.getErrmsg());
		}
		return result;
	}
}
