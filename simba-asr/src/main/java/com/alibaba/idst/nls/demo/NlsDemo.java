package com.alibaba.idst.nls.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.idst.nls.NlsClient;
import com.alibaba.idst.nls.NlsFuture;
import com.alibaba.idst.nls.event.NlsEvent;
import com.alibaba.idst.nls.event.NlsListener;
import com.alibaba.idst.nls.protocol.NlsRequest;
import com.alibaba.idst.nls.protocol.NlsRequestASR;
import com.alibaba.idst.nls.protocol.NlsResponse;

public class NlsDemo implements NlsListener {
	private static Log logger = LogFactory.getLog(NlsDemo.class);

	private NlsClient client = new NlsClient();
	public String filePath = null;
	public String appKey = null;
	public String auth_Id = null;
	public String auth_Secret = null;
	public String tts_text = "薄雾浓云愁永昼。瑞脑消金兽。佳节又重阳，玉枕纱厨，半夜凉初透。东篱把酒黄昏后。有暗香盈袖。莫道不消魂，帘卷西风，人比黄花瘦。";

	public void shutDown() {
		logger.info("close NLS client");
		this.client.close();
		logger.info("demo done");
	}

	public void start() {
		logger.info("init Nls client...");
		this.client.init();
	}

	public void sayIt() {
		NlsRequest req = new NlsRequest();
		File file = new File("test.wav");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		req.setApp_key(this.appKey);
		req.setTts_req(this.tts_text, "16000");
		req.setTtsEncodeType("wav");
		req.setTtsVoice("xiaogang");

		req.setTtsVolume(30);
		req.setTtsNus(0);
		req.setTtsSpeechRate(0);
		req.setTtsBackgroundMusic(1, 0);

		req.authorize(this.auth_Id, this.auth_Secret);
		try {
			FileOutputStream outputStream = new FileOutputStream(file);
			NlsFuture future = this.client.createNlsFuture(req, this);
			int total_len = 0;
			byte[] data;
			while ((data = future.read()) != null) {
				outputStream.write(data, 0, data.length);
				total_len += data.length;
			}
			outputStream.close();
			logger.info("tts audio file size is :" + total_len);
			future.await(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void hearIt() {
		logger.info("open audio file...");

		FileInputStream fis = null;
		try {
			File file = new File(this.filePath);
			fis = new FileInputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (fis != null) {
			logger.info("create NLS future");
			try {
				NlsRequest req = new NlsRequest();

				req.setAppKey(this.appKey);
				req.setAsrFormat("pcm");
				req.setAsrResposeMode(NlsRequestASR.mode.NORMAL);

				req.setAsr_fake("查一下明天从上海到北京的机票");

				req.enableCloudNLUResult();

				req.authorize(this.auth_Id, this.auth_Secret);
				NlsFuture future = this.client.createNlsFuture(req, this);
				logger.info("call NLS service");
				byte[] b = new byte[8000];
				int len = 0;
				while ((len = fis.read(b)) == 8000) {
					future.sendVoice(b, 0, len);
					Thread.sleep(250L);
				}

				future.sendVoice(b, 0, len);

				future.sendFinishSignal();
				logger.info("main thread enter waiting for less than 10s.");
				logger.debug("before await : " + Long.valueOf(System.currentTimeMillis()));
				future.await(10000);
				logger.debug("after await : " + Long.valueOf(System.currentTimeMillis()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("calling NLS service end");
		}
	}

	public void onMessageReceived(NlsEvent e) {
		NlsResponse response = e.getResponse();
		String result = "";
		if (response.getDs_ret() != null) {
			result = "get ds result: " + response.getDs_ret();
		}
		if (response.getAsr_ret() != null) {
			result = result + "\nget asr result: " + response.getAsr_ret();
		}
		if (response.getTts_ret() != null) {
			result = result + "\nget tts result: " + response.getTts_ret();
		}
		if (response.getGds_ret() != null) {
			result = result + "\nget gds result: " + response.getGds_ret();
		}
		if (!result.isEmpty())
			logger.info(result);
		else if (response.jsonResults != null)
			logger.info(response.jsonResults.toString());
		else
			logger.info("get an acknowledge package from server.");
	}

	public void onOperationFailed(NlsEvent e) {
		logger.error(
				"Error message is:" + e.getErrorMessage() + ", Error code is: " + Integer.valueOf(e.getResponse().getStatus_code()) + ", result error massage is " + e.getResponse().getErrorMassage());
	}

	public void onChannelClosed(NlsEvent e) {
		logger.info("on websocket closed.");
	}

	public static void main(String[] args) throws Exception {
		NlsDemo lun = new NlsDemo();

		if (args.length < 4) {
			logger.info("NlsDemo <app-key> <Id> <Secret> <voice-file>");
			System.exit(-1);
		}

		lun.appKey = args[0];
		lun.auth_Id = args[1];
		lun.auth_Secret = args[2];
		lun.filePath = args[3];

		lun.start();
		lun.sayIt();
		lun.hearIt();
		lun.shutDown();
		System.exit(0);
	}
}