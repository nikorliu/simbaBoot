package com.simba.asr;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.idst.nls.NlsClient;
import com.alibaba.idst.nls.NlsFuture;
import com.alibaba.idst.nls.protocol.NlsRequest;
import com.simba.asr.model.NLUVoice;
import com.simba.asr.model.OneVoice;
import com.simba.asr.model.TTSVoice;
import com.simba.common.EnvironmentUtil;
import com.simba.framework.util.applicationcontext.ApplicationContextUtil;

/**
 * 阿里云智能语音识别工具类(单例模式)
 * 
 * @author caozhejun
 *
 */
public class NlsClientUtil {

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if (client != null) {
			client.close();
			logger.info("close NLS client");
		}
	}

	private static final Log logger = LogFactory.getLog(NlsClientUtil.class);

	private NlsClient client = null;

	private String accessKeyId = null;

	private String accessKeySecret = null;

	private NlsClientUtil() {
		init();
	}

	private static final class NlsClientUtilHolder {
		private static NlsClientUtil instance = new NlsClientUtil();
	}

	public static NlsClientUtil getInstance() {
		return NlsClientUtilHolder.instance;
	}

	private void init() {
		client = new NlsClient();
		// 初始化NlsClient
		client.init();
		logger.info("init Nls client...");
		EnvironmentUtil env = ApplicationContextUtil.getBean(EnvironmentUtil.class);
		accessKeyId = env.get("aliyun.accessKeyId");
		accessKeySecret = env.get("aliyun.accessKeySecret");
	}

	/**
	 * 发送声音文件到阿里云识别(异步操作)
	 * 
	 * @param fileData
	 *            声音文件数据
	 * @param req
	 *            请求对象
	 * @param listener
	 *            监听器
	 * @throws Exception
	 */
	public void sendVoice(byte[] fileData, NlsRequest req, SimbaNlsListener listener) throws Exception {
		NlsFuture future = client.createNlsFuture(req, listener);
		int size = fileData.length;
		if (size < 16 * 1024) {
			future.sendVoice(fileData, 0, size);
		} else {
			int once = 2 * 1024;
			int index = 0;
			while (index < size) {
				int to = index + once;
				if (to > size) {
					to = size;
				}
				byte[] onceBytes = Arrays.copyOfRange(fileData, index, to);
				future.sendVoice(onceBytes, 0, onceBytes.length);
				index = to;
				Thread.sleep(50);
			}
		}
		future.sendFinishSignal();
		future.await(10000); // 设置服务端结果返回的超时时间
		logger.info("calling NLS service end");
	}

	/**
	 * 一句话识别
	 * 
	 * @param fileData
	 *            声音文件数据
	 * @param listener
	 *            监听器
	 * @param voice
	 *            一句话识别对象
	 * @throws Exception
	 */
	public void sendOne(byte[] fileData, SimbaNlsListener listener, OneVoice voice) throws Exception {
		NlsRequest req = new NlsRequest();
		req.setAppKey(voice.getAppKey()); // appkey请从 "快速开始" 帮助页面的appkey列表中获取
		req.setAsrFormat(voice.getAsrFormat()); // 设置语音文件格式为pcm,我们支持16k 16bit
												// 的无头的pcm文件。
		/* 热词相关配置 */
		if (StringUtils.isNotEmpty(voice.getAsrUserId())) {
			req.setAsrUserId(voice.getAsrUserId());
			req.setAsrVocabularyId(voice.getAsrVocabularyId());// 热词词表id
		}
		/* 热词相关配置 */
		req.authorize(accessKeyId, accessKeySecret); // 请替换为用户申请到的Access Key
														// ID和Access Key Secret
		sendVoice(fileData, req, listener);
	}

	/**
	 * 自然语言理解
	 * 
	 * @param fileData
	 *            声音文件数据
	 * @param listener
	 *            监听器
	 * @param voice
	 *            自然语言理解对象
	 * @throws Exception
	 */
	public void sendNLU(byte[] fileData, SimbaNlsListener listener, NLUVoice voice) throws Exception {
		NlsRequest req = new NlsRequest();
		req.setAppKey(voice.getAppKey()); // appkey列表中获取，本demo可同时支持流式和非流式appkey
		req.setAsrFormat("pcm"); // 设置语音文件格式为pcm,我们支持16k 16bit 的无头的pcm文件。
		/* 热词相关配置 */
		if (StringUtils.isNotEmpty(voice.getAsrUserId())) {
			req.setAsrUserId(voice.getAsrUserId());
			req.setAsrVocabularyId(voice.getAsrVocabularyId());// 热词词表id
		}
		/* 热词相关配置 */
		req.authorize(accessKeyId, accessKeySecret); // 请替换为用户申请到的Access Key
														// ID和Access Key Secret
		req.enableNLUResult(); // 设置nlu请求
		sendVoice(fileData, req, listener);
	}

	/**
	 * 自然语言理解
	 * 
	 * @param text
	 *            需要理解的文字
	 * @param listener
	 *            监听器
	 * @param voice
	 *            自然语言理解对象
	 * @throws Exception
	 */
	public void sendNLU(String text, SimbaNlsListener listener, NLUVoice voice) throws Exception {
		NlsRequest req = new NlsRequest();
		req.setAppKey(voice.getAppKey()); // appkey列表中获取，本demo可同时支持流式和非流式appkey
		req.setAsrFormat("pcm"); // 设置语音文件格式为pcm,我们支持16k 16bit 的无头的pcm文件。
		/* 热词相关配置 */
		if (StringUtils.isNotEmpty(voice.getAsrUserId())) {
			req.setAsrUserId(voice.getAsrUserId());
			req.setAsrVocabularyId(voice.getAsrVocabularyId());// 热词词表id
		}
		/* 热词相关配置 */
		req.authorize(accessKeyId, accessKeySecret); // 请替换为用户申请到的Access Key
														// ID和Access Key Secret
		/* 单独使用nlu */
		req.setAsrFake(text); // 使用文本请求nlu结果
		/* 单独使用nlu */
		req.enableNLUResult(); // 设置nlu请求
		NlsFuture future = client.createNlsFuture(req, listener);
		future.await(10000); // 设置服务端结果返回的超时时间
	}

	/**
	 * 合成声音文件
	 * 
	 * @param text
	 *            文本
	 * @param voice
	 *            对象
	 * @param listener
	 *            监听器
	 * @param outputFile
	 *            输出文件对象
	 * @throws Exception
	 */
	public void generateVoice(String text, TTSVoice voice, SimbaNlsListener listener, File outputFile) throws Exception {
		if (!outputFile.exists()) {
			outputFile.createNewFile();
		}
		NlsRequest req = new NlsRequest();
		req.setAppKey(voice.getAppKey()); // 设置语音文件格式
		req.setTtsRequest(text); // 传入测试文本，返回语音结果
		req.setTtsEncodeType(voice.getEncodeType());// 返回语音数据格式，支持pcm,wav.alaw
		req.setTtsVolume(voice.getVolumn()); // 音量大小默认50，阈值0-100
		req.setTtsSpeechRate(voice.getRate()); // 语速，阈值-500~500
		req.setTtsBackgroundMusic(1);// 背景音乐编号
		req.authorize(accessKeyId, accessKeySecret); // 请替换为用户申请到的Access Key
		// ID和Access Key Secret
		FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
		NlsFuture future = client.createNlsFuture(req, listener); // 实例化请求,传入请求和监听器
		int total_len = 0;
		byte[] data;
		while ((data = future.read()) != null) {
			fileOutputStream.write(data, 0, data.length);
			total_len += data.length;
			logger.info("tts length " + data.length);
		}
		fileOutputStream.close();
		logger.info("tts audio file size is :" + total_len);
		future.await(10000); // 设置服务端结果返回的超时时间
	}

}
