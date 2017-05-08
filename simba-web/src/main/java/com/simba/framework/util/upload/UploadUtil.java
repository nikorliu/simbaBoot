package com.simba.framework.util.upload;

import com.simba.common.EnvironmentUtil;
import com.simba.framework.util.applicationcontext.ApplicationContextUtil;

/**
 * 文件上传工具类
 * 
 * @author caozj
 *
 */
public class UploadUtil {

	private String storage;

	private UploadInterface upload;

	public UploadInterface getUpload() {
		return upload;
	}

	private UploadUtil() {
		init();
	}

	private void init() {
		storage = ApplicationContextUtil.getBean(EnvironmentUtil.class).get("files.storage");
		if ("local".equals(storage)) {
			upload = LocalUpload.getInstance();
		} else if ("fastdfs".equals(storage)) {
			upload = FastdfsUpload.getInstance();
		} else if ("alioss".equals(storage)) {
			upload = AliOssUpload.getInstance();
		}
	}

	private static final class UploadUtilHolder {
		private static final UploadUtil instance = new UploadUtil();
	}

	public static UploadUtil getInstance() {
		return UploadUtilHolder.instance;
	}

}
