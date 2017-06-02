package com.simba.interfaces;

import com.simba.model.wx.receive.deviceEvent.DeviceEvent;
import com.simba.model.wx.receive.deviceEvent.DeviceEventResp;
import com.simba.model.wx.receive.event.BaseEvent;
import com.simba.model.wx.receive.event.GroupMessageEvent;
import com.simba.model.wx.receive.event.LocationEvent;
import com.simba.model.wx.receive.event.MenuEvent;
import com.simba.model.wx.receive.event.ReportLocationEvent;
import com.simba.model.wx.receive.event.ScanCodeEvent;
import com.simba.model.wx.receive.event.SendPhotoEvent;
import com.simba.model.wx.receive.event.TemplateEvent;
import com.simba.model.wx.receive.event.TicketEvent;
import com.simba.model.wx.receive.event.VerifyFailEvent;
import com.simba.model.wx.receive.event.VerifySuccessEvent;
import com.simba.model.wx.receive.msg.ImageMessage;
import com.simba.model.wx.receive.msg.LinkMessage;
import com.simba.model.wx.receive.msg.LocationMessage;
import com.simba.model.wx.receive.msg.TextMessage;
import com.simba.model.wx.receive.msg.VideoMessage;
import com.simba.model.wx.receive.msg.VoiceMessage;

/**
 * 处理收到微信消息的接口类
 * 
 * @author caozhejun
 *
 */
public interface DealInterface {

	/**
	 * 文本消息
	 * 
	 * @param msg
	 * @param xml
	 */
	void text(TextMessage msg, String xml);

	/**
	 * 图片消息
	 * 
	 * @param msg
	 * @param xml
	 */
	void image(ImageMessage msg, String xml);

	/**
	 * 语音消息
	 * 
	 * @param msg
	 * @param xml
	 */
	void voice(VoiceMessage msg, String xml);

	/**
	 * 视频消息/小视频消息
	 * 
	 * @param msg
	 * @param xml
	 */
	void video(VideoMessage msg, String xml);

	/**
	 * 地理位置消息
	 * 
	 * @param msg
	 * @param xml
	 */
	void location(LocationMessage msg, String xml);

	/**
	 * 链接消息
	 * 
	 * @param msg
	 * @param xml
	 */
	void link(LinkMessage msg, String xml);

	/**
	 * 点击菜单拉取消息时的事件推送
	 * 
	 * @param event
	 * @param xml
	 */
	void clickMenu(BaseEvent event, String xml);

	/**
	 * 点击菜单跳转链接时的事件推送
	 * 
	 * @param event
	 * @param xml
	 */
	void clickForward(MenuEvent event, String xml);

	/**
	 * 扫码推事件的事件推送
	 * 
	 * @param event
	 * @param xml
	 */
	void scanPush(ScanCodeEvent event, String xml);

	/**
	 * 扫码推事件且弹出“消息接收中”提示框的事件推送
	 * 
	 * @param event
	 * @param xml
	 */
	void scanWait(ScanCodeEvent event, String xml);

	/**
	 * 弹出系统拍照发图的事件推送
	 * 
	 * @param event
	 * @param xml
	 */
	void sysPhoto(SendPhotoEvent event, String xml);

	/**
	 * 弹出拍照或者相册发图的事件推送
	 * 
	 * @param event
	 * @param xml
	 */
	void photoOrAlbum(SendPhotoEvent event, String xml);

	/**
	 * 弹出微信相册发图器的事件推送
	 * 
	 * @param event
	 * @param xml
	 */
	void weixinPhoto(SendPhotoEvent event, String xml);

	/**
	 * 弹出地理位置选择器的事件推送
	 * 
	 * @param event
	 * @param xml
	 */
	void locationSelect(LocationEvent event, String xml);

	/**
	 * 关注事件/扫描带参数二维码事件,用户未关注时，进行关注后的事件推送
	 * 
	 * @param event
	 * @param xml
	 */
	void subscribe(TicketEvent event, String xml);

	/**
	 * 取消关注事件
	 * 
	 * @param event
	 * @param xml
	 */
	void unsubscribe(BaseEvent event, String xml);

	/**
	 * 扫描带参数二维码事件,用户已关注时的事件推送
	 * 
	 * @param event
	 */
	void scan(TicketEvent event, String xml);

	/**
	 * 上报地理位置事件
	 * 
	 * @param event
	 * @param xml
	 */
	void reportLocation(ReportLocationEvent event, String xml);

	/**
	 * 事件推送群发结果
	 * 
	 * @param event
	 * @param xml
	 */
	void groupMessage(GroupMessageEvent event, String xml);

	/**
	 * 事件推送模板
	 * 
	 * @param event
	 * @param xml
	 */
	void template(TemplateEvent event, String xml);

	/**
	 * 资质认证成功
	 * 
	 * @param event
	 * @param xml
	 */
	void qualificationVerifySuccess(VerifySuccessEvent event, String xml);

	/**
	 * 资质认证失败
	 * 
	 * @param event
	 * @param xml
	 */
	void qualificationVerifyFail(VerifyFailEvent event, String xml);

	/**
	 * 名称认证成功
	 * 
	 * @param event
	 * @param xml
	 */
	void namingVerifySuccess(VerifySuccessEvent event, String xml);

	/**
	 * 名称认证失败
	 * 
	 * @param event
	 * @param xml
	 */
	void namingVerifyFail(VerifyFailEvent event, String xml);

	/**
	 * 年审通知
	 * 
	 * @param event
	 * @param xml
	 */
	void annualRenew(VerifySuccessEvent event, String xml);

	/**
	 * 认证过期失效通知
	 * 
	 * @param event
	 * @param xml
	 */
	void verifyExpired(VerifySuccessEvent event, String xml);

	/**
	 * 订阅设备状态
	 * 
	 * @param event
	 * @param xml
	 */
	DeviceEventResp subscribeStatus(DeviceEvent event, String xml) throws Exception;

	/**
	 * 退订设备状态
	 * 
	 * @param event
	 * @param xml
	 */
	DeviceEventResp unsubscribeStatus(DeviceEvent event, String xml);
}
