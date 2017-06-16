package com.alibaba.idst.nls.realtime.demo;

import java.io.File;
import java.io.FileInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.idst.nls.realtime.NlsClient;
import com.alibaba.idst.nls.realtime.NlsFuture;
import com.alibaba.idst.nls.realtime.event.NlsEvent;
import com.alibaba.idst.nls.realtime.event.NlsListener;
import com.alibaba.idst.nls.realtime.protocol.NlsRequest;
import com.alibaba.idst.nls.realtime.protocol.NlsResponse;

public class NlsDemo implements NlsListener {
	private NlsClient client = new NlsClient();
	private String asrSC = "pcm";

	static Logger logger = LoggerFactory.getLogger(NlsDemo.class);
	public String filePath = "";
	public String appKey = "nls-service-shurufa16khz";
	String ak_id = "";
	String ak_secret = "";
	public int smapleRate = 16000;

	public void shutDown() {
		logger.debug("close NLS client manually!");
		this.client.close();
		logger.debug("demo done");
	}

	public void start() {
		logger.debug("init Nls client...");
		this.client.init();
	}

	public void hearIt() {
		logger.debug("open audio file...");
		FileInputStream fis = null;
		try {
			File file = new File(this.filePath);
			fis = new FileInputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (fis != null) {
			logger.debug("create NLS future");
			try {
				NlsRequest req = new NlsRequest();

				req.setAppkey(this.appKey);
				req.setFormat(this.asrSC);
				req.setResponseMode("streaming");
				req.setSampleRate(this.smapleRate);
				req.setUserId("testuser");
				req.setVocabularyId("1");

				req.authorize(this.ak_id, this.ak_secret);

				NlsFuture future = this.client.createNlsFuture(req, this);

				logger.debug("call NLS service");
				byte[] b = new byte[8000];
				int len = 0;
				while ((len = fis.read(b)) > 0) {
					future.sendVoice(b, 0, len);
					Thread.sleep(250L);
				}
				logger.debug("send finish signal!");
				future.sendFinishSignal();

				logger.debug("main thread enter waiting .");
				future.await(10000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.debug("calling NLS service end");
		}
	}

	public void onMessageReceived(NlsEvent e) {
		NlsResponse response = e.getResponse();
		if (response.result != null) {
			logger.info("status code = {},get finish is {},get recognize result: {}", new Object[] { Integer.valueOf(response.getStatus_code()), response.getFinish(), response.getResult() });
		} else
			logger.info("Successful connect to service.");
	}

	public void onOperationFailed(NlsEvent e) {
		logger.error("status code is {}, on operation failed: {}", Integer.valueOf(e.getResponse().getStatus_code()), e.getErrorMessage());
	}

	public void onChannelClosed(NlsEvent e) {
		logger.debug("on websocket closed.");
	}

	public static void main(String[] args) {
		NlsDemo lun = new NlsDemo();

		logger.info("start ....");
		if (args.length < 4) {
			logger.info("NlsDemo  <apps-key> <ak-id> <ak-secret> <wav-path> ");
			System.exit(-1);
		}

		lun.appKey = args[0];
		lun.ak_id = args[1];
		lun.ak_secret = args[2];
		lun.filePath = args[3];

		lun.start();
		lun.hearIt();
		lun.shutDown();
	}
}