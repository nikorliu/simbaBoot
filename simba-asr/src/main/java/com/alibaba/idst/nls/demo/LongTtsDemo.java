package com.alibaba.idst.nls.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.idst.nls.NlsClient;
import com.alibaba.idst.nls.NlsFuture;
import com.alibaba.idst.nls.event.NlsEvent;
import com.alibaba.idst.nls.event.NlsListener;
import com.alibaba.idst.nls.protocol.NlsRequest;
import com.alibaba.idst.nls.protocol.NlsResponse;
import com.alibaba.idst.nls.utils.PcmToWav;

public class LongTtsDemo implements NlsListener {
	private static Log logger = LogFactory.getLog(LongTtsDemo.class);

	private NlsClient client = new NlsClient();
	public String appKey = null;
	public String auth_Id = null;
	public String auth_Secret = null;
	public String tts_text;
	private String fileName = UUID.randomUUID().toString();

	public void shutDown() {
		logger.info("close NLS client");
		this.client.close();
		logger.info("demo done");
	}

	public void start() {
		logger.info("init Nls client...");
		this.client.init();

		this.tts_text = "百草堂与三味书屋 鲁迅 我家的后面有一个很大的园，相传叫作百草园。现在是早已并屋子一起卖给朱文公的子孙了，连那最末次的相见也已经隔了七八年，其中似乎确凿只有一些野草；但那时却是我的乐园。\n　　不必说碧绿的菜畦，光滑的石井栏，高大的皂荚树，紫红的桑葚；也不必说鸣蝉在树叶里长吟，肥胖的黄蜂伏在菜花上，轻捷的叫天子(云雀)忽然从草间直窜向云霄里去了。\n单是周围的短短的泥墙根一带，就有无限趣味。油蛉在这里低唱，蟋蟀们在这里弹琴。翻开断砖来，有时会遇见蜈蚣；还有斑蝥，倘若用手指按住它的脊梁，便会啪的一声，\n从后窍喷出一阵烟雾。何首乌藤和木莲藤缠络着，木莲有莲房一般的果实，何首乌有臃肿的根。有人说，何首乌根是有像人形的，吃了便可以成仙，我于是常常拔它起来，牵连不断地拔起来，\n也曾因此弄坏了泥墙，却从来没有见过有一块根像人样。如果不怕刺，还可以摘到覆盆子，像小珊瑚珠攒成的小球，又酸又甜，色味都比桑葚要好得远。";
	}

	public void sayIt() throws Exception {
		int ttsTextLength = this.tts_text.length();

		int i = 0;
		boolean isHead = false;

		File file = new File(this.fileName + ".pcm");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		FileOutputStream outputStream = new FileOutputStream(file, true);
		String[] longTexts = processLongText(this.tts_text);

		while (ttsTextLength > 0) {
			String tts_part_text = "";
			if (ttsTextLength > 50) {
				if (i == 0)
					isHead = true;
				else {
					isHead = false;
				}
				for (; i < longTexts.length; i++) {
					tts_part_text = tts_part_text + longTexts[i];
					if ((i < longTexts.length - 1) && (tts_part_text.length() + longTexts[(i + 1)].length() >= 50)) {
						i += 1;
						break;
					}
				}
			}

			if (i == 0) {
				isHead = true;
			}
			for (; i < longTexts.length; i++) {
				tts_part_text = tts_part_text + longTexts[i];
			}

			NlsRequest req = new NlsRequest();
			req.setApp_key("nls-service");
			req.setTts_req(tts_part_text, "16000");
			req.setTtsEncodeType("wav");
			req.setTtsVoice("xiaoyun");
			req.setTtsVolume(50);
			req.setTtsBackgroundMusic(1, 0);
			req.authorize(this.auth_Id, this.auth_Secret);

			NlsFuture future = this.client.createNlsFuture(req, this);
			int total_len = 0;
			byte[] data;
			while ((data = future.read()) != null) {
				if (data.length == 8044) {
					logger.debug("data length:" + (data.length - 44) + " , and head is:" + (isHead ? "true" : "false"));
					outputStream.write(data, 44, data.length - 44);
				} else {
					outputStream.write(data, 0, data.length);
				}
				total_len += data.length;
			}

			logger.info("tts audio file size is :" + total_len);
			future.await(10000);

			ttsTextLength -= tts_part_text.length();
		}
		outputStream.close();

		PcmToWav.copyWaveFile(this.fileName + ".pcm", this.fileName + ".wav");
		logger.debug("close the wav file!");
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
		logger.error("Error message is: " + e.getErrorMessage() + ", Error code is: " + Integer.valueOf(e.getResponse().getStatus_code()));
	}

	public static String[] processLongText(String text) {
		text = text.replaceAll("、", "、|");
		text = text.replaceAll("，", "，|");
		text = text.replaceAll("。", "。|");
		text = text.replaceAll("；", "；|");
		text = text.replaceAll("？", "？|");
		text = text.replaceAll("！", "！|");
		text = text.replaceAll(",", ",|");
		text = text.replaceAll(";", ";|");
		text = text.replaceAll("\\?", "?|");
		text = text.replaceAll("!", "!|");
		String[] texts = text.split("\\|");
		return texts;
	}

	public void onChannelClosed(NlsEvent e) {
		logger.info("on websocket closed.");
	}

	public static void main(String[] args) throws Exception {
		LongTtsDemo lun = new LongTtsDemo();

		if (args.length < 4) {
			logger.info("NlsDemo <app-key> <Id> <Secret> <voice-file>");
			System.exit(-1);
		}

		lun.appKey = args[0];
		lun.auth_Id = args[1];
		lun.auth_Secret = args[2];

		lun.start();
		lun.sayIt();
		lun.shutDown();
	}
}