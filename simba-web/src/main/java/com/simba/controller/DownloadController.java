package com.simba.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simba.framework.util.upload.UploadUtil;

/**
 * 下载文件Controller
 * 
 * @author caozhejun
 *
 */
@Controller
@RequestMapping("/download")
public class DownloadController {

	private static final Log logger = LogFactory.getLog(DownloadController.class);

	@Value("${files.dir}")
	private String fileDir;

	@Value("${files.storage}")
	private String fileStorge;

	/**
	 * 下载文件
	 * 
	 * @param fileName
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/download")
	public void download(String fileName, HttpServletResponse response) {
		OutputStream out = null;
		InputStream in = null;
		try {
			logger.info("下载文件:" + fileName);
			out = response.getOutputStream();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			in = UploadUtil.getInstance().getUpload().download(fileName);
			IOUtils.copy(in, out);
		} catch (Exception e) {
			logger.error("下载文件:[" + fileName + "]出现异常", e);
		} finally {
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(in);
		}
	}

}
