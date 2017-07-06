package com.simba.rest.asr;

import com.simba.common.EnvironmentUtil;
import com.simba.framework.util.applicationcontext.ApplicationContextUtil;
import com.simba.framework.util.json.FastJsonUtil;
import com.simba.rest.asr.model.HttpResponse;
import com.simba.rest.asr.model.RestResult;
import com.simba.rest.asr.util.HttpUtil;

/**
 * rest方式语音识别(单例模式)
 * 
 * @author caozhejun
 *
 */
public class RestAsrUtil {

	private static String url = "http://nlsapi.aliyun.com/recognize?";

	private String accessKeyId = null;

	private String accessKeySecret = null;

	private RestAsrUtil() {
		init();
	}

	private static final class RestAsrUtilHolder {
		private static RestAsrUtil instance = new RestAsrUtil();
	}

	public static RestAsrUtil getInstance() {
		return RestAsrUtilHolder.instance;
	}

	private void init() {
		EnvironmentUtil env = ApplicationContextUtil.getBean(EnvironmentUtil.class);
		accessKeyId = env.get("aliyun.accessKeyId");
		accessKeySecret = env.get("aliyun.accessKeySecret");
	}

	/**
	 * 识别语音
	 * 
	 * @param fileData
	 *            语音文件
	 * @param model
	 *            语音模型
	 * @return
	 */
	public RestResult send(byte[] fileData, String model) {
		// 使用对应的ASR模型 详情见文档部分2
		String requestUrl = url + "model=" + model;
		HttpResponse response = HttpUtil.sendAsrPost(fileData, "pcm", 16000, requestUrl, accessKeyId, accessKeySecret);
		if (response.getStatus() != 200) {
			throw new RuntimeException("识别语音发生异常:" + response.getMessage());
		}
		RestResult result = FastJsonUtil.toObject(response.getResult(), RestResult.class);
		return result;
	}
}
