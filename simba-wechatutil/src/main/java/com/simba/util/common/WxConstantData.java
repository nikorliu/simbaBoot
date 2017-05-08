package com.simba.util.common;

/**
 * 微信常量类
 * 
 * @author caozhejun
 *
 */
public interface WxConstantData {

	/**
	 * 微信服务器域名
	 */
	String wxDomain = "api.weixin.qq.com";

	/**
	 * 获取access_token的接口url
	 */
	String accessTokenUrl = "https://" + wxDomain + "/cgi-bin/token?grant_type=client_credential";

	/**
	 * 获取微信服务器IP地址
	 */
	String serverIpUrl = "https://" + wxDomain + "/cgi-bin/getcallbackip";

	/**
	 * 自定义菜单创建接口
	 */
	String createMenuUrl = "https://" + wxDomain + "/cgi-bin/menu/create";

	/**
	 * 自定义菜单查询接口
	 */
	String searchMenuUrl = "https://" + wxDomain + "/cgi-bin/menu/get";

	/**
	 * 自定义菜单删除接口
	 */
	String deleteMenuUrl = "https://" + wxDomain + "/cgi-bin/menu/delete";

	/**
	 * 创建个性化菜单
	 */
	String createConditionalMenuUrl = "https://" + wxDomain + "/cgi-bin/menu/addconditional";

	/**
	 * 删除个性化菜单
	 */
	String deleteConditionalMenuUrl = "https://" + wxDomain + "/cgi-bin/menu/delconditional";

	/**
	 * 测试个性化菜单匹配结果
	 */
	String tryMatchConditionalMenuUrl = "https://" + wxDomain + "/cgi-bin/menu/trymatch";

	/**
	 * 获取自定义菜单配置接口
	 */
	String searchAllMenuUrl = "https://" + wxDomain + "/cgi-bin/get_current_selfmenu_info";

	/**
	 * 添加客服帐号
	 */
	String createKfAccountUrl = "https://" + wxDomain + "/customservice/kfaccount/add";

	/**
	 * 修改客服帐号
	 */
	String updateKfAccountUrl = "https://" + wxDomain + "/customservice/kfaccount/update";

	/**
	 * 删除客服帐号
	 */
	String deleteKfAccountUrl = "https://" + wxDomain + "/customservice/kfaccount/del";

	/**
	 * 设置客服帐号的头像
	 */
	String setKfHeadImgUrl = "http://" + wxDomain + "/customservice/kfaccount/uploadheadimg";

	/**
	 * 获取所有客服账号
	 */
	String listAllKfUrl = "https://" + wxDomain + "/cgi-bin/customservice/getkflist";

	/**
	 * 客服接口-发消息
	 */
	String sendMsgUrl = "https://" + wxDomain + "/cgi-bin/message/custom/send";

	/**
	 * 上传图文消息内的图片获取URL
	 */
	String uploadImageUrl = "https://" + wxDomain + "/cgi-bin/media/uploadimg";

	/**
	 * 上传图文消息素材
	 */
	String uploadNewsUrl = "https://" + wxDomain + "/cgi-bin/media/uploadnews";

	/**
	 * 根据标签进行群发
	 */
	String sendGroupByTag = "https://" + wxDomain + "/cgi-bin/message/mass/sendall";

	/**
	 * 根据OpenID列表群发【订阅号不可用，服务号认证后可用】
	 */
	String sendGroupByOpenID = "https://" + wxDomain + "/cgi-bin/message/mass/send";

	/**
	 * 上传视频
	 */
	String uploadVideoUrl = "https://file.api.weixin.qq.com/cgi-bin/media/uploadvideo";

	/**
	 * 删除群发【订阅号与服务号认证后均可用】
	 */
	String deleteGroupMessageUrl = "https://" + wxDomain + "/cgi-bin/message/mass/delete";

	/**
	 * 预览群发接口【订阅号与服务号认证后均可用】
	 */
	String previewGroupMessageUrl = "https://" + wxDomain + "/cgi-bin/message/mass/preview";

	/**
	 * 查询群发消息发送状态【订阅号与服务号认证后均可用】
	 */
	String groupMessageStatusUrl = "https://" + wxDomain + "/cgi-bin/message/mass/get";

	/**
	 * 设置所属行业
	 */
	String setIndustryUrl = "https://" + wxDomain + "/cgi-bin/template/api_set_industry";

	/**
	 * 获取设置的行业信息
	 */
	String getIndustryUrl = "https://" + wxDomain + "/cgi-bin/template/get_industry";

	/**
	 * 获得模板ID
	 */
	String getTemplateUrl = "https://" + wxDomain + "/cgi-bin/template/api_add_template";

	/**
	 * 获取模板列表
	 */
	String listTemplatesUrl = "https://" + wxDomain + "/cgi-bin/template/get_all_private_template";

	/**
	 * 删除模板
	 */
	String deleteTemplateUrl = "https://" + wxDomain + "/cgi-bin/template/del_private_template";

	/**
	 * 发送模板消息
	 */
	String sendTemplateUrl = "https://" + wxDomain + "/cgi-bin/message/template/send";

	/**
	 * 获取公众号的自动回复规则
	 */
	String autoReplyUrl = "https://" + wxDomain + "/cgi-bin/get_current_autoreply_info";

	/**
	 * 新增临时素材
	 */
	String uploadTempMediaUrl = "https://" + wxDomain + "/cgi-bin/media/upload";

	/**
	 * 获取临时素材
	 */
	String getTempMediaUrl = "https://" + wxDomain + "/cgi-bin/media/get";

	/**
	 * 新增永久图文素材
	 */
	String addNewsMediaUrl = "https://" + wxDomain + "/cgi-bin/material/add_news";

	/**
	 * 新增其他类型永久素材
	 */
	String addMediaUrl = "https://" + wxDomain + "/cgi-bin/material/add_material";

	/**
	 * 获取永久素材
	 */
	String getMediaUrl = "https://" + wxDomain + "/cgi-bin/material/get_material";

	/**
	 * 删除永久素材
	 */
	String deleteMediaUrl = "https://" + wxDomain + "/cgi-bin/material/del_material";

	/**
	 * 修改永久图文素材
	 */
	String updateNewsMediaUrl = "https://" + wxDomain + "/cgi-bin/material/update_news";

	/**
	 * 获取素材总数
	 */
	String countMediaUrl = "https://" + wxDomain + "/cgi-bin/material/get_materialcount";

	/**
	 * 获取素材列表
	 */
	String listMediaUrl = "https://" + wxDomain + "cgi-bin/material/batchget_material";

	/**
	 * 创建标签
	 */
	String createTagUrl = "https://" + wxDomain + "/cgi-bin/tags/create";

	/**
	 * 获取公众号已创建的标签
	 */
	String listTagsUrl = "https://" + wxDomain + "/cgi-bin/tags/get";

	/**
	 * 编辑标签
	 */
	String updateTagUrl = "https://" + wxDomain + "/cgi-bin/tags/update";

	/**
	 * 删除标签
	 */
	String deleteTagUrl = "https://" + wxDomain + "/cgi-bin/tags/delete";

	/**
	 * 获取标签下粉丝列表
	 */
	String listFansByTagUrl = "https://" + wxDomain + "/cgi-bin/user/tag/get";

	/**
	 * 批量为用户打标签
	 */
	String batchTagUrl = "https://" + wxDomain + "/cgi-bin/tags/members/batchtagging";

	/**
	 * 批量为用户取消标签
	 */
	String batchCancelTagUrl = "https://" + wxDomain + "/cgi-bin/tags/members/batchuntagging";

	/**
	 * 获取用户身上的标签列表
	 */
	String getTagIdsUrl = "https://" + wxDomain + "/cgi-bin/tags/getidlist";

	/**
	 * 设置用户备注名
	 */
	String updateUserRemarkUrl = "https://" + wxDomain + "/cgi-bin/user/info/updateremark";

	/**
	 * 获取用户基本信息（包括UnionID机制）
	 */
	String getUserInfoUrl = "https://" + wxDomain + "/cgi-bin/user/info";

	/**
	 * 批量获取用户基本信息
	 */
	String batchGetUserInfoUrl = "https://" + wxDomain + "/cgi-bin/user/info/batchget";

	/**
	 * 获取用户列表
	 */
	String listAttentUserUrl = "https://" + wxDomain + "/cgi-bin/user/get";

	/**
	 * 获取公众号的黑名单列表
	 */
	String getBlackListUrl = "https://" + wxDomain + "/cgi-bin/tags/members/getblacklist";

	/**
	 * 拉黑用户
	 */
	String batchBlackListUrl = "https://" + wxDomain + "/cgi-bin/tags/members/batchblacklist";

	/**
	 * 取消拉黑用户
	 */
	String cancelBlackListUrl = "https://" + wxDomain + "/cgi-bin/tags/members/batchunblacklist";

	/**
	 * 创建二维码ticket
	 */
	String createQrcodeTicketUrl = "https://" + wxDomain + "/cgi-bin/qrcode/create";

	/**
	 * 长链接转短链接
	 */
	String convertShortUrl = "https://" + wxDomain + "/cgi-bin/shorturl";

	/**
	 * 通过code换取网页授权access_token
	 */
	String getWebAccessTokenUrl = "https://" + wxDomain + "/sns/oauth2/access_token";

	/**
	 * 刷新access_token
	 */
	String refreshWebAccessTokenUrl = "https://" + wxDomain + "/sns/oauth2/refresh_token";

	/**
	 * 拉取用户信息(需scope为 snsapi_userinfo)
	 */
	String getUserInfoByWebAccessTokenUrl = "https://" + wxDomain + "/sns/userinfo";

	/**
	 * 检验授权凭证（access_token）是否有效
	 */
	String checkWebAccessTokenUrl = "https://" + wxDomain + "/sns/auth";

	/**
	 * jsapi_ticket
	 */
	String getJsApiTicketUrl = "https://" + wxDomain + "/cgi-bin/ticket/getticket";

	/**
	 * 第三方主动发送设备状态消息给微信终端
	 */
	String deviceStatusUrl = "https://" + wxDomain + "/device/transmsg";
}
