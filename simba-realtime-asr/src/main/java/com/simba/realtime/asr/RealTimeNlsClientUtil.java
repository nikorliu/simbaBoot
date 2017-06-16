package com.simba.realtime.asr;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.idst.nls.realtime.NlsClient;
import com.alibaba.idst.nls.realtime.NlsFuture;
import com.alibaba.idst.nls.realtime.protocol.NlsRequest;
import com.simba.common.EnvironmentUtil;
import com.simba.framework.util.applicationcontext.ApplicationContextUtil;
import com.simba.realtime.asr.model.RealTimeVoice;

/**
 * 阿里云实时语音识别工具类(单例模式)
 * 
 * @author caozhejun
 *
 */
public class RealTimeNlsClientUtil {

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if (client != null) {
			client.close();
			logger.info("close NLS client");
		}
	}

	private static final Log logger = LogFactory.getLog(RealTimeNlsClientUtil.class);

	private NlsClient client = null;

	private String accessKeyId = null;

	private String accessKeySecret = null;

	private RealTimeNlsClientUtil() {
		init();
	}

	private static final class RealTimeNlsClientUtilHolder {
		private static RealTimeNlsClientUtil instance = new RealTimeNlsClientUtil();
	}

	public static RealTimeNlsClientUtil getInstance() {
		return RealTimeNlsClientUtilHolder.instance;
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
	 * 发送实时语音识别
	 * 
	 * @param fileData
	 *            声音文件数据
	 * @param listener
	 *            监听器
	 * @param voice
	 *            实时语音对象
	 * @throws Exception
	 */
	public void send(byte[] fileData, SimbaNlsListener listener, RealTimeVoice voice) throws Exception {
		NlsRequest req = new NlsRequest();
		req.setAppkey(voice.getAppKey());
		req.setFormat(voice.getFormat());
		req.setResponseMode("streaming");
		req.setSampleRate(16000);
		/* 热词相关配置 */
		if (StringUtils.isNotEmpty(voice.getAsrUserId())) {
			req.setUserId(voice.getAsrUserId());
			req.setVocabularyId(voice.getAsrVocabularyId());// 热词词表id
		}
		/* 热词相关配置 */
		req.authorize(accessKeyId, accessKeySecret); // 请替换为用户申请到的Access Key
														// ID和Access Key Secret
		NlsFuture future = client.createNlsFuture(req, listener);
		logger.debug("call NLS service");
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
		logger.debug("send finish signal!");
		future.sendFinishSignal();
		logger.debug("main thread enter waiting .");
		future.await(10000);
		logger.debug("calling NLS service end");
	}

}
