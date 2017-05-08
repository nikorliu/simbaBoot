package com.simba.framework.util.video;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 视频截图工具类
 * 
 * @author caozj
 *
 */
public class VideoUtil {

	private static final Log logger = LogFactory.getLog(VideoUtil.class);

	/**
	 * 从视频中截图一张图片返回
	 * 
	 * @param videoUrl
	 *            视频地址
	 * @return
	 */
	public static String cutPic(String videoUrl) {
		int index = videoUrl.lastIndexOf(".");
		String imageUrl = videoUrl.substring(0, index) + "_image.jpg";
		VideoUtil.cutPic(videoUrl, imageUrl);
		return imageUrl;
	}

	/**
	 * 从视频中截图
	 * 
	 * @param videoUrl
	 *            视频地址
	 * @param imageUrl
	 *            图片地址
	 */
	public static void cutPic(String videoUrl, String imageUrl) {
		Encoder encoder = new Encoder();
		try {
			encoder.getImage(new File(videoUrl), new File(imageUrl), 10);
		} catch (EncoderException e) {
			logger.error("从视频中截图失败", e);
		} catch (FileNotFoundException e) {
			logger.error("从视频中截图失败", e);
		}
	}
}
