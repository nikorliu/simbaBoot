package com.simba.util.send;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.exception.WxException;
import com.simba.framework.util.http.HttpClientUtil;
import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wx.ErrMsg;
import com.simba.model.wx.kf.Kf;
import com.simba.model.wx.kf.KfSearch;
import com.simba.model.wx.kf.KfSearchResult;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.FileUploadUtil;
import com.simba.util.common.PostJsonUtil;
import com.simba.util.common.WxConstantData;

/**
 * 客服管理
 * 
 * @author caozhejun
 *
 */
@Component
public class KfWxUtil {

	private static final Log logger = LogFactory.getLog(KfWxUtil.class);

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private PostJsonUtil postJsonUtil;

	@Autowired
	private FileUploadUtil fileUploadUtil;

	/**
	 * 添加客服帐号(每个公众号最多添加10个客服账号)
	 * 
	 * @param kf
	 */
	public void create(Kf kf) {
		String url = WxConstantData.createKfAccountUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(kf);
		postJsonUtil.postJson(url, json, "添加客服帐号", ErrMsg.class);
	}

	/**
	 * 修改客服帐号
	 * 
	 * @param kf
	 */
	public void update(Kf kf) {
		String url = WxConstantData.updateKfAccountUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(kf);
		postJsonUtil.postJson(url, json, "修改客服帐号", ErrMsg.class);
	}

	/**
	 * 删除客服帐号
	 * 
	 * @param kf
	 */
	public void delete(Kf kf) {
		String url = WxConstantData.deleteKfAccountUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(kf);
		postJsonUtil.postJson(url, json, "删除客服帐号", ErrMsg.class);
	}

	/**
	 * 设置客服帐号的头像(头像图片文件必须是jpg格式，推荐使用640*640大小的图片以达到最佳效果)
	 * 
	 * @param headImg
	 * @param account
	 */
	public void setHeadImg(String headImg, String account) {
		String url = WxConstantData.setKfHeadImgUrl + "?access_token=" + accessTokenUtil.getAccessToken() + "&kf_account=" + account;
		fileUploadUtil.upload(url, headImg, "设置客服帐号的头像", ErrMsg.class);
	}

	/**
	 * 获取所有客服账号
	 * 
	 * @return
	 */
	public List<KfSearch> listAllKf() {
		String url = WxConstantData.listAllKfUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String resp = HttpClientUtil.get(url);
		logger.info("获取所有客服账号返回结果:" + resp);
		KfSearchResult result = FastJsonUtil.toObject(resp, KfSearchResult.class);
		if (result.getErrcode() != 0 && StringUtils.isNotEmpty(result.getErrmsg())) {
			logger.error("获取所有客服账号失败:" + result.getErrcode() + "," + result.getErrmsg());
			throw new WxException("获取所有客服账号失败", result.getErrcode(), result.getErrmsg());
		}
		return result.getKf_list();
	}
}
