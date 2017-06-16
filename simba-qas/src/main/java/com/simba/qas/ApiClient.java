package com.simba.qas;

import com.simba.common.EnvironmentUtil;
import com.simba.framework.util.applicationcontext.ApplicationContextUtil;

/**
 * 知识库管理Client
 */
public class ApiClient {

	public static String sendRequest(ApiRequest request) {
		String url = Constants.QA_MANAGE_URL + "?action=" + request.getAction();
		EnvironmentUtil env = ApplicationContextUtil.getBean(EnvironmentUtil.class);
		String accessKeyId = env.get("aliyun.accessKeyId");
		String accessKeySecret = env.get("aliyun.accessKeySecret");
		return HttpProxy.sendRequest(url, request.getBody(), accessKeyId, accessKeySecret);
	}

}
