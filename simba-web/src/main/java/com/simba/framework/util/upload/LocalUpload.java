package com.simba.framework.util.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.csource.common.FastdfsException;

import com.simba.common.EnvironmentUtil;
import com.simba.framework.util.applicationcontext.ApplicationContextUtil;

/**
 * 本地上传文件管理
 * 
 * @author caozhejun
 *
 */
public class LocalUpload implements UploadInterface {

	private static final SimpleDateFormat format = new SimpleDateFormat("/yyyy/MM/dd");

	private static String localDir;

	private LocalUpload() {
		init();
	}

	private void init() {
		localDir = ApplicationContextUtil.getBean(EnvironmentUtil.class).get("files.dir");
	}

	private static final class LocalUploadHolder {
		private static final LocalUpload instance = new LocalUpload();
	}

	public static LocalUpload getInstance() {
		return LocalUploadHolder.instance;
	}

	@Override
	public String upload(byte[] content, String fileName, String type) throws IOException, FastdfsException {
		String absPath = "/" + type + getPath() + getUniqueFileName(fileName);
		String fullPath = localDir + absPath;
		FileUtils.writeByteArrayToFile(new File(fullPath), content);
		return absPath;
	}

	@Override
	public String upload(byte[] content, String fileName) throws IOException, FastdfsException {
		return upload(content, fileName, StringUtils.EMPTY);
	}

	@Override
	public InputStream download(String fileName) throws IOException, FastdfsException {
		return new FileInputStream(new File(localDir + fileName));
	}

	@Override
	public void delete(String fileName) throws IOException, FastdfsException {
		FileUtils.deleteQuietly(new File(localDir + fileName));
	}

	@Override
	public long size(String fileName) throws IOException, FastdfsException {
		return FileUtils.sizeOf(new File(localDir + fileName));
	}

	/**
	 * 获取上传文件的路径
	 * 
	 * @return
	 */
	private String getPath() {
		return format.format(new Date()) + "/";
	}

	/**
	 * 获取唯一的文件名
	 * 
	 * @param fileName
	 *            原文件名
	 * @return
	 */
	private String getUniqueFileName(String fileName) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		int beginIndex = fileName.lastIndexOf(".");
		if (beginIndex == -1) {
			return uuid;
		}
		String ext = fileName.substring(beginIndex);
		return uuid + ext;
	}

}
