package com.simba.qas;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.simba.common.EnvironmentUtil;
import com.simba.framework.util.applicationcontext.ApplicationContextUtil;

/**
 * 问答服务Client
 */
public class QaClient {

	public static QaResponse sendRequest(QaRequest request) {
		if (request == null) {
			return null;
		}
		String json = JSON.toJSONString(request);
		System.out.println(json);
		EnvironmentUtil env = ApplicationContextUtil.getBean(EnvironmentUtil.class);
		String accessKeyId = env.get("aliyun.accessKeyId");
		String accessKeySecret = env.get("aliyun.accessKeySecret");
		String postResult = HttpProxy.sendRequest(Constants.QA_URL, json, accessKeyId, accessKeySecret);
		JSONObject node = JSON.parseObject(postResult);
		if (node.get("status") != null) {
			QaResponse response = new QaResponse();
			response.setSuccess(false);
			response.setError_code(String.valueOf(node.get("status")));
			response.setError_message(String.valueOf(node.get("message")));
			return response;
		} else {
			return JSON.parseObject(postResult, QaResponse.class);
		}
	}

}
