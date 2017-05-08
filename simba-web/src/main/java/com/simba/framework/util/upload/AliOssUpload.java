package com.simba.framework.util.upload;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.csource.common.FastdfsException;

import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.SimplifiedObjectMeta;
import com.simba.common.EnvironmentUtil;
import com.simba.framework.util.applicationcontext.ApplicationContextUtil;
import com.simba.util.aliyun.oss.OssClientManager;

/**
 * 阿里云OSS操作文件实现类
 * 
 * @author caozhejun
 *
 */
public class AliOssUpload implements UploadInterface {

	private String bucketName;

	private AliOssUpload() {
		init();
	}

	private void init() {
		bucketName = ApplicationContextUtil.getBean(EnvironmentUtil.class).get("aliyun.bucket");
	}

	private static final class AliOssUploadHolder {
		private static final AliOssUpload instance = new AliOssUpload();
	}

	public static AliOssUpload getInstance() {
		return AliOssUploadHolder.instance;
	}

	@Override
	public String upload(byte[] content, String fileName, String type) throws IOException, FastdfsException {
		fileName = getUniqueFileName(fileName);
		if (StringUtils.isNotEmpty(type)) {
			fileName = type + "/" + fileName;
		}
		OssClientManager.getClient().putObject(bucketName, fileName, new ByteArrayInputStream(content));
		return OssClientManager.getInstance().getFileUrl(bucketName, fileName);
	}

	@Override
	public String upload(byte[] content, String fileName) throws IOException, FastdfsException {
		return upload(content, fileName, null);
	}

	@Override
	public InputStream download(String fileName) throws IOException, FastdfsException {
		String[] res = OssClientManager.getInstance().getFileInfo(fileName);
		OSSObject ossObject = OssClientManager.getClient().getObject(res[0], res[1]);
		return ossObject.getObjectContent();
	}

	@Override
	public void delete(String fileName) throws IOException, FastdfsException {
		String[] res = OssClientManager.getInstance().getFileInfo(fileName);
		OssClientManager.getClient().deleteObject(res[0], res[1]);
	}

	@Override
	public long size(String fileName) throws IOException, FastdfsException {
		String[] res = OssClientManager.getInstance().getFileInfo(fileName);
		SimplifiedObjectMeta objectMeta = OssClientManager.getClient().getSimplifiedObjectMeta(res[0], res[1]);
		return objectMeta.getSize();
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
