package com.simba.util.send;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wx.ErrMsg;
import com.simba.model.wx.tag.BatchTag;
import com.simba.model.wx.tag.FansSearch;
import com.simba.model.wx.tag.ListFansResult;
import com.simba.model.wx.tag.ListTagResult;
import com.simba.model.wx.tag.TagContent;
import com.simba.model.wx.tag.TagIdList;
import com.simba.model.wx.tag.TagResult;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.GetUtil;
import com.simba.util.common.PostJsonUtil;
import com.simba.util.common.WxConstantData;

/**
 * 标签管理(一个公众号，最多可以创建100个标签)
 * 
 * @author caozhejun
 *
 */
@Component
public class TagWxUtil {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private PostJsonUtil postJsonUtil;

	@Autowired
	private GetUtil getUtil;

	/**
	 * 创建标签
	 * 
	 * @param name
	 * @return
	 */
	public TagResult create(String name) {
		String url = WxConstantData.createTagUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		Map<String, String> tagName = new HashMap<>();
		tagName.put("name", name);
		Map<String, Map<String, String>> tag = new HashMap<>();
		tag.put("tag", tagName);
		String json = FastJsonUtil.toJson(tag);
		return postJsonUtil.postJson(url, json, "创建标签", TagResult.class);
	}

	/**
	 * 获取公众号已创建的标签
	 * 
	 * @return
	 */
	public ListTagResult list() {
		String url = WxConstantData.listTagsUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		return getUtil.get(url, "获取公众号已创建的标签", ListTagResult.class);
	}

	/**
	 * 编辑标签
	 * 
	 * @param tag
	 */
	public void update(TagContent tag) {
		String url = WxConstantData.updateTagUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(tag);
		postJsonUtil.postJson(url, json, "编辑标签", ErrMsg.class);
	}

	/**
	 * 删除标签(当某个标签下的粉丝超过10w时，后台不可直接删除标签。此时，开发者可以对该标签下的openid列表，先进行取消标签的操作，
	 * 直到粉丝数不超过10w后，才可直接删除该标签。)
	 * 
	 * @param tagId
	 */
	public void delete(long tagId) {
		String url = WxConstantData.deleteTagUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		Map<String, Long> map = new HashMap<>();
		map.put("id", tagId);
		Map<String, Map<String, Long>> tag = new HashMap<>();
		tag.put("tag", map);
		String json = FastJsonUtil.toJson(tag);
		postJsonUtil.postJson(url, json, "删除标签", ErrMsg.class);
	}

	/**
	 * 获取标签下粉丝列表
	 * 
	 * @param tag
	 * @return
	 */
	public ListFansResult listFansByTag(FansSearch tag) {
		String url = WxConstantData.listFansByTagUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(tag);
		return postJsonUtil.postJson(url, json, "获取标签下粉丝列表", ListFansResult.class);
	}

	/**
	 * 批量为用户打标签
	 * 
	 * @param tag
	 */
	public void batchTag(BatchTag tag) {
		String url = WxConstantData.batchTagUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(tag);
		postJsonUtil.postJson(url, json, "批量为用户打标签", ErrMsg.class);
	}

	/**
	 * 批量为用户取消标签
	 * 
	 * @param tag
	 */
	public void batchCancelTag(BatchTag tag) {
		String url = WxConstantData.batchCancelTagUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(tag);
		postJsonUtil.postJson(url, json, "批量为用户取消标签", ErrMsg.class);
	}

	/**
	 * 获取用户身上的标签列表
	 * 
	 * @param openid
	 * @return
	 */
	public TagIdList getTagIdList(String openid) {
		String url = WxConstantData.getTagIdsUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		Map<String, String> map = new HashMap<>();
		map.put("openid", openid);
		String json = FastJsonUtil.toJson(map);
		return postJsonUtil.postJson(url, json, "获取用户身上的标签列表", TagIdList.class);
	}
}
