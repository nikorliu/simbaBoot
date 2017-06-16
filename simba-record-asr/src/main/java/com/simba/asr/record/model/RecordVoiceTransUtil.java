package com.simba.asr.record.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.idst.nls.response.HttpResponse;
import com.alibaba.idst.nls.utils.HttpUtil;
import com.alibaba.idst.nls.utils.RequestBody;
import com.simba.common.EnvironmentUtil;
import com.simba.framework.util.applicationcontext.ApplicationContextUtil;
import com.simba.framework.util.json.FastJsonUtil;

/**
 * 录音文件识别工具类(单例模式)
 * 
 * @author caozhejun
 *
 */
public class RecordVoiceTransUtil {

	private static final Log logger = LogFactory.getLog(RecordVoiceTransUtil.class);

	/**
	 * 服务url
	 */
	private String url = "https://nlsapi.aliyun.com/transcriptions";

	private String runningStatus = "RUNNING";

	private String accessKeyId = null;

	private String accessKeySecret = null;

	private RecordVoiceTransUtil() {
		init();
	}

	private static final class RecordVoiceTransUtilHolder {
		private static final RecordVoiceTransUtil instance = new RecordVoiceTransUtil();
	}

	public static RecordVoiceTransUtil getInstance() {
		return RecordVoiceTransUtilHolder.instance;
	}

	private void init() {
		EnvironmentUtil env = ApplicationContextUtil.getBean(EnvironmentUtil.class);
		accessKeyId = env.get("aliyun.accessKeyId");
		accessKeySecret = env.get("aliyun.accessKeySecret");
	}

	/**
	 * 发送录音识别请求
	 * 
	 * @param voice
	 * @return
	 * @throws InterruptedException
	 */
	public Result send(RecordVoice voice) throws InterruptedException {
		RequestBody body = new RequestBody();
		body.setApp_key(voice.getAppKey()); // 简介页面给出的Appkey
		body.setFile_link(voice.getOssLink());// 离线文件识别的文件url,推荐使用oss存储文件。链接大小限制为128MB
		if (StringUtils.isNotEmpty(voice.getUserId())) {
			/* 热词接口 */
			// 使用热词需要指定user_id和Vocabulary_id两个字段，如何设置热词参考文档：[热词设置](~~49179~~)
			body.setUser_id(voice.getUserId());
			body.setVocabulary_id(voice.getVocabularyId());
			/* 热词接口 */
		}
		logger.info("开始识别录音文件");
		/*
		 * 发送录音转写请求
		 **/
		String bodyString = JSON.toJSONString(body, true);
		logger.info("bodyString is:" + bodyString);
		HttpResponse httpResponse = HttpUtil.sendPost(url, bodyString, accessKeyId, accessKeySecret);
		if (httpResponse.getStatus() == 200) {
			logger.info("post response is:" + httpResponse.getResult());
		} else {
			logger.error("error msg: " + httpResponse.getMassage());
			throw new RuntimeException("发送录音转写请求发生异常");
		}
		Result result = FastJsonUtil.toObject(httpResponse.getResult(), Result.class);
		String taskId = result.getId();
		String status = runningStatus;
		HttpResponse getResponse = null;
		while (status.equals(runningStatus)) {
			Thread.sleep(3000);
			getResponse = HttpUtil.sendGet(url, taskId, accessKeyId, accessKeySecret);
			if (getResponse.getStatus() == 200) {
				result = FastJsonUtil.toObject(getResponse.getResult(), Result.class);
				status = result.getStatus();
				logger.info("get response is:" + getResponse.getResult());
			} else {
				logger.error("error msg: " + getResponse.getMassage());
				break;
			}
		}
		return result;
	}
}
