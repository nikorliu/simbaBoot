package com.simba.util.send;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.exception.WxException;
import com.simba.framework.util.http.HttpClientUtil;
import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wx.ErrMsg;
import com.simba.model.wx.media.MediaCount;
import com.simba.model.wx.media.MediaSearch;
import com.simba.model.wx.media.MediaSearchResult;
import com.simba.model.wx.media.NewsArticlesMedia;
import com.simba.model.wx.media.UploadMediaResult;
import com.simba.model.wx.media.UploadResult;
import com.simba.model.wx.media.UploadTempMediaResult;
import com.simba.model.wx.upload.NewsArticles;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.FileUploadUtil;
import com.simba.util.common.GetUtil;
import com.simba.util.common.PostJsonUtil;
import com.simba.util.common.WxConstantData;

/**
 * 素材工具类( 1、临时素材media_id是可复用的。 2、媒体文件在微信后台保存时间为3天，即3天后media_id失效。
 * 3、上传临时素材的格式、大小限制与公众平台官网一致。 图片（image）: 2M，支持PNG\JPEG\JPG\GIF格式
 * 语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式 视频（video）：10MB，支持MP4格式
 * 缩略图（thumb）：64KB，支持JPG格式
 * )(1、最近更新：永久图片素材新增后，将带有URL返回给开发者，开发者可以在腾讯系域名内使用（腾讯系域名外使用，图片将被屏蔽）。
 * 2、公众号的素材库保存总数量有上限：图文消息素材、图片素材上限为5000，其他类型为1000。 3、素材的格式大小等要求与公众平台官网一致：
 * 图片（image）: 2M，支持bmp/png/jpeg/jpg/gif格式
 * 语音（voice）：2M，播放长度不超过60s，mp3/wma/wav/amr格式 视频（video）：10MB，支持MP4格式
 * 缩略图（thumb）：64KB，支持JPG格式
 * 4、图文消息的具体内容中，微信后台将过滤外部的图片链接，图片url需通过"上传图文消息内的图片获取URL"接口上传图片获取。
 * 5、"上传图文消息内的图片获取URL"接口所上传的图片，不占用公众号的素材库中图片数量的5000个的限制，图片仅支持jpg/png格式，
 * 大小必须在1MB以下。)
 * 
 * @author caozhejun
 *
 */
@Component
public class MediaWxUtil {

	private static final Log logger = LogFactory.getLog(MediaWxUtil.class);

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private PostJsonUtil postJsonUtil;

	@Autowired
	private GetUtil getUtil;

	@Autowired
	private FileUploadUtil fileUploadUtil;

	/**
	 * 新增临时素材
	 * 
	 * @param file
	 *            文件地址
	 * @param type
	 *            媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * @return
	 */
	public UploadTempMediaResult uploadTempMedia(String file, String type) {
		String url = WxConstantData.uploadTempMediaUrl + "?access_token=" + accessTokenUtil.getAccessToken() + "&type=" + type;
		return fileUploadUtil.upload(url, file, "新增临时素材", UploadTempMediaResult.class);
	}

	/**
	 * 下载临时素材
	 * 
	 * @param mediaId
	 * @return
	 */
	public byte[] downloadTempMedia(String mediaId) {
		String url = WxConstantData.getTempMediaUrl + "?access_token=" + accessTokenUtil.getAccessToken() + "&media_id=" + mediaId;
		return HttpClientUtil.getBytes(url);
	}

	/**
	 * 新增永久图文素材
	 * 
	 * @param newsArticles
	 * @return
	 */
	public UploadResult addNewsMedia(NewsArticles newsArticles) {
		String url = WxConstantData.addNewsMediaUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(newsArticles);
		return postJsonUtil.postJson(url, json, "新增永久图文素材", UploadResult.class);
	}

	/**
	 * 新增永久视频素材
	 * 
	 * @param file
	 *            文件地址
	 * @param title
	 *            视频素材的标题
	 * @param introduction
	 *            视频素材的描述
	 * @return
	 */
	public UploadMediaResult addVideoMedia(String file, String title, String introduction) {
		String url = WxConstantData.addMediaUrl + "?access_token=" + accessTokenUtil.getAccessToken() + "&type=video";
		Map<String, String> fileMap = new HashMap<>();
		fileMap.put("media", file);
		Map<String, String> params = new HashMap<>();
		Map<String, String> info = new HashMap<>();
		info.put("title", title);
		info.put("introduction", introduction);
		params.put("description", FastJsonUtil.toJson(info));
		String resp = HttpClientUtil.fileUpload(url, fileMap, params);
		UploadMediaResult result = FastJsonUtil.toObject(resp, UploadMediaResult.class);
		if (result.getErrcode() != 0 && StringUtils.isNotEmpty(result.getErrmsg())) {
			logger.error("新增永久视频素材失败:" + result.getErrcode() + "," + result.getErrmsg());
			throw new WxException("新增永久视频素材失败", result.getErrcode(), result.getErrmsg());
		}
		return result;
	}

	/**
	 * 新增其他类型永久素材
	 * 
	 * @param file
	 *            文件地址
	 * @param type
	 *            媒体文件类型，分别有图片（image）、语音（voice）、和缩略图（thumb）
	 * @return
	 */
	public UploadMediaResult addOtherMedia(String file, String type) {
		String url = WxConstantData.addMediaUrl + "?access_token=" + accessTokenUtil.getAccessToken() + "&type=" + type;
		return fileUploadUtil.upload(url, file, "新增其他类型永久素材", UploadMediaResult.class);
	}

	/**
	 * 获取永久素材
	 * 
	 * @param mediaId
	 * @return
	 */
	public byte[] downloadMedia(String mediaId) {
		String url = WxConstantData.getMediaUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		Map<String, String> params = new HashMap<>();
		params.put("media_id", mediaId);
		return HttpClientUtil.postJson(url, FastJsonUtil.toJson(params)).getBytes();
	}

	/**
	 * 删除永久素材(临时素材无法通过本接口删除)
	 * 
	 * @param mediaId
	 * @return
	 */
	public void deleteMedia(String mediaId) {
		String url = WxConstantData.deleteMediaUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		Map<String, String> params = new HashMap<>();
		params.put("media_id", mediaId);
		postJsonUtil.postJson(url, FastJsonUtil.toJson(params), "删除永久素材", ErrMsg.class);
	}

	/**
	 * 修改永久图文素材
	 * 
	 * @param media
	 * @return
	 */
	public void updateNewsMedia(NewsArticlesMedia media) {
		String url = WxConstantData.updateNewsMediaUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(media);
		postJsonUtil.postJson(url, json, "修改永久图文素材", ErrMsg.class);
	}

	/**
	 * 获取素材总数
	 * 
	 * @return
	 */
	public MediaCount count() {
		String url = WxConstantData.countMediaUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		return getUtil.get(url, "获取素材总数", MediaCount.class);
	}

	/**
	 * 获取素材列表
	 * 
	 * @param search
	 * @return
	 */
	public MediaSearchResult list(MediaSearch search) {
		String url = WxConstantData.listMediaUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(search);
		return postJsonUtil.postJson(url, json, "获取素材列表", MediaSearchResult.class);
	}
}
