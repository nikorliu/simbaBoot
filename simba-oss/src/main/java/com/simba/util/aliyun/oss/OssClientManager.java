package com.simba.util.aliyun.oss;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.simba.common.EnvironmentUtil;
import com.simba.framework.util.applicationcontext.ApplicationContextUtil;

/**
 * 阿里云的oss客户端管理类
 * 
 * @author caozhejun
 *
 */
public class OssClientManager {

	private OSSClient ossClient;

	private String endPoint;

	private OSSClient getOssClient() {
		return ossClient;
	}

	private OssClientManager() {
		init();
	}

	private void init() {
		EnvironmentUtil env = ApplicationContextUtil.getBean(EnvironmentUtil.class);
		endPoint = env.get("aliyun.endPoint");
		String accessKeyId = env.get("aliyun.accessKeyId");
		String accessKeySecret = env.get("aliyun.accessKeySecret");
		// 创建ClientConfiguration实例
		ClientConfiguration conf = new ClientConfiguration();
		// 设置OSSClient使用的最大连接数，默认1024
		conf.setMaxConnections(1024);
		// 设置请求超时时间，默认50秒
		conf.setSocketTimeout(300000);
		// 设置失败请求重试次数，默认3次
		conf.setMaxErrorRetry(5);
		ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret, conf);
	}

	private static final class OssClientManagerHolder {
		private static final OssClientManager instance = new OssClientManager();
	}

	public static OssClientManager getInstance() {
		return OssClientManagerHolder.instance;
	}

	public static OSSClient getClient() {
		return OssClientManagerHolder.instance.getOssClient();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if (ossClient != null) {
			ossClient.shutdown();
		}
	}

	/**
	 * 创建Bucket(公共读权限)<如果直接使用ossClient.createBucket，权限为私有>
	 * 
	 * @param bucketName
	 */
	public void createBucket(String bucketName) {
		CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
		// 设置bucket权限
		createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
		ossClient.createBucket(createBucketRequest);
	}

	/**
	 * 获取文件对外访问的url地址
	 * 
	 * @param bucketName
	 * @param fileName
	 * @return
	 */
	public String getFileUrl(String bucketName, String fileName) {
		int index = endPoint.indexOf(":");
		String schema = endPoint.substring(0, index);
		String endPointDomain = endPoint.substring(index + 3);
		String fileUrl = schema + "://" + bucketName + "." + endPointDomain + "/" + fileName;
		return fileUrl;
	}

	/**
	 * 根据文件url地址获取文件信息[bucketName,fileName]
	 * 
	 * @param fileUrl
	 * @return [bucketName,fileName]
	 */
	public String[] getFileInfo(String fileUrl) {
		int index = endPoint.indexOf(":");
		String schema = endPoint.substring(0, index);
		String endPointDomain = endPoint.substring(index + 3);
		int pointIndex = fileUrl.indexOf(endPointDomain);
		String bucketName = fileUrl.substring(schema.length() + 3, pointIndex - 1);
		String fileName = fileUrl.substring(pointIndex + endPointDomain.length() + 1);
		String[] res = new String[2];
		res[0] = bucketName;
		res[1] = fileName;
		return res;
	}

}
