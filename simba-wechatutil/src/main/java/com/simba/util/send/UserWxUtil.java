package com.simba.util.send;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wx.ErrMsg;
import com.simba.model.wx.user.User;
import com.simba.model.wx.user.UserInfoList;
import com.simba.model.wx.user.UserList;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.GetUtil;
import com.simba.util.common.PostJsonUtil;
import com.simba.util.common.WxConstantData;

/**
 * 微信用户管理工具类
 * 
 * @author caozhejun
 *
 */
@Component
public class UserWxUtil {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private PostJsonUtil postJsonUtil;

	@Autowired
	private GetUtil getUtil;

	/**
	 * 设置用户备注名
	 * 
	 * @param openid
	 * @param remark
	 *            新的备注名，长度必须小于30字符
	 */
	public void setUserRemark(String openid, String remark) {
		String url = WxConstantData.updateUserRemarkUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		Map<String, String> map = new HashMap<>();
		map.put("openid", openid);
		map.put("remark", remark);
		String json = FastJsonUtil.toJson(map);
		postJsonUtil.postJson(url, json, "设置用户备注名", ErrMsg.class);
	}

	/**
	 * 获取用户基本信息（包括UnionID机制）
	 * 
	 * @param openid
	 * @return
	 */
	public User getUserInfo(String openid) {
		String url = WxConstantData.getUserInfoUrl + "?access_token=" + accessTokenUtil.getAccessToken() + "&openid=" + openid + "&lang=zh_CN";
		return getUtil.get(url, "获取用户基本信息", User.class);
	}

	/**
	 * 批量获取用户基本信息
	 * 
	 * @param openids
	 * @return
	 */
	public UserInfoList batchGetUserInfo(List<String> openids) {
		String url = WxConstantData.batchGetUserInfoUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		List<Map<String, String>> userList = new ArrayList<Map<String, String>>(openids.size());
		openids.forEach((openid) -> {
			Map<String, String> map = new HashMap<>(2);
			map.put("openid", openid);
			map.put("lang", "zh-CN");
			userList.add(map);
		});
		Map<String, List<Map<String, String>>> params = new HashMap<>(1);
		params.put("user_list", userList);
		String json = FastJsonUtil.toJson(params);
		return postJsonUtil.postJson(url, json, "批量获取用户基本信息", UserInfoList.class);
	}

	/**
	 * 获取用户列表(一次拉取调用最多拉取10000个关注者的OpenID，可以通过多次拉取的方式来满足需求)
	 * 
	 * @param next_openid
	 * @return
	 */
	public UserList listAttendUser(String next_openid) {
		String url = WxConstantData.listAttentUserUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		if (StringUtils.isNotEmpty(next_openid)) {
			url += "&next_openid=" + next_openid;
		}
		return getUtil.get(url, "获取用户列表", UserList.class);
	}

	/**
	 * 获取公众号的黑名单列表(该接口每次调用最多可拉取 10000 个OpenID，当列表数较多时，可以通过多次拉取的方式来满足需求。)
	 * 
	 * @param begin_openid
	 * @return
	 */
	public UserList getBlackList(String begin_openid) {
		String url = WxConstantData.getBlackListUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = StringUtils.EMPTY;
		if (StringUtils.isNotEmpty(begin_openid)) {
			Map<String, String> params = new HashMap<>(1);
			params.put("begin_openid", begin_openid);
			json = FastJsonUtil.toJson(params);
		}
		return postJsonUtil.postJson(url, json, "获取公众号的黑名单列表", UserList.class);
	}

	/**
	 * 拉黑用户
	 * 
	 * @param openids
	 *            需要拉入黑名单的用户的openid，一次拉黑最多允许20个
	 */
	public void batchBlackList(List<String> openids) {
		String url = WxConstantData.batchBlackListUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		Map<String, List<String>> params = new HashMap<>(1);
		params.put("openid_list", openids);
		String json = FastJsonUtil.toJson(params);
		postJsonUtil.postJson(url, json, "拉黑用户", ErrMsg.class);
	}

	/**
	 * 取消拉黑用户
	 * 
	 * @param openids(一次拉黑最多允许20个)
	 */
	public void cancelBlackList(List<String> openids) {
		String url = WxConstantData.cancelBlackListUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		Map<String, List<String>> params = new HashMap<>(1);
		params.put("openid_list", openids);
		String json = FastJsonUtil.toJson(params);
		postJsonUtil.postJson(url, json, "取消拉黑用户", ErrMsg.class);
	}
}
