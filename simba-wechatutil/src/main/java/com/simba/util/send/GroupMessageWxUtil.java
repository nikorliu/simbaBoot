package com.simba.util.send;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wx.ErrMsg;
import com.simba.model.wx.group.GroupStatus;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.PostJsonUtil;
import com.simba.util.common.WxConstantData;

/**
 * 群发消息工具类
 * 
 * @author caozhejun
 *
 */
@Component
public class GroupMessageWxUtil {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private PostJsonUtil postJsonUtil;

	/**
	 * 删除群发 1、只有已经发送成功的消息才能删除 2、删除消息是将消息的图文详情页失效，已经收到的用户，还是能在其本地看到消息卡片。
	 * 3、删除群发消息只能删除图文消息和视频消息，其他类型的消息一经发送，无法删除。
	 * 4、如果多次群发发送的是一个图文消息，那么删除其中一次群发，就会删除掉这个图文消息也，导致所有群发都失效
	 * 
	 * @param msgId
	 *            发送出去的消息ID
	 */
	public void delete(String msgId) {
		String url = WxConstantData.deleteGroupMessageUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		Map<String, String> map = new HashMap<>();
		map.put("msg_id", msgId);
		String json = FastJsonUtil.toJson(map);
		postJsonUtil.postJson(url, json, "删除群发", ErrMsg.class);
	}

	/**
	 * 查询群发消息发送状态
	 * 
	 * @param msgId
	 */
	public GroupStatus getStatus(String msgId) {
		String url = WxConstantData.groupMessageStatusUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		Map<String, String> map = new HashMap<>();
		map.put("msg_id", msgId);
		String json = FastJsonUtil.toJson(map);
		return postJsonUtil.postJson(url, json, "查询群发消息发送状态", GroupStatus.class);
	}

}
