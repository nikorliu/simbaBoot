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
	 */
	void text(TextMessage msg);

	/**
	 * 图片消息
	 * 
	 * @param msg
	 */
	void image(ImageMessage msg);

	/**
	 * 语音消息
	 * 
	 * @param msg
	 */
	void voice(VoiceMessage msg);

	/**
	 * 视频消息/小视频消息
	 * 
	 * @param msg
	 */
	void video(VideoMessage msg);

	/**
	 * 地理位置消息
	 * 
	 * @param msg
	 */
	void location(LocationMessage msg);

	/**
	 * 链接消息
	 * 
	 * @param msg
	 */
	void link(LinkMessage msg);

	/**
	 * 点击菜单拉取消息时的事件推送
	 * 
	 * @param event
	 */
	void clickMenu(BaseEvent event);

	/**
	 * 点击菜单跳转链接时的事件推送
	 * 
	 * @param event
	 */
	void clickForward(MenuEvent event);

	/**
	 * 扫码推事件的事件推送
	 * 
	 * @param event
	 */
	void scanPush(ScanCodeEvent event);

	/**
	 * 扫码推事件且弹出“消息接收中”提示框的事件推送
	 * 
	 * @param event
	 */
	void scanWait(ScanCodeEvent event);

	/**
	 * 弹出系统拍照发图的事件推送
	 * 
	 * @param event
	 */
	void sysPhoto(SendPhotoEvent event);

	/**
	 * 弹出拍照或者相册发图的事件推送
	 * 
	 * @param event
	 */
	void photoOrAlbum(SendPhotoEvent event);

	/**
	 * 弹出微信相册发图器的事件推送
	 * 
	 * @param event
	 */
	void weixinPhoto(SendPhotoEvent event);

	/**
	 * 弹出地理位置选择器的事件推送
	 * 
	 * @param event
	 */
	void locationSelect(LocationEvent event);

	/**
	 * 关注事件/扫描带参数二维码事件,用户未关注时，进行关注后的事件推送
	 * 
	 * @param event
	 */
	void subscribe(TicketEvent event);

	/**
	 * 取消关注事件
	 * 
	 * @param event
	 */
	void unsubscribe(BaseEvent event);

	/**
	 * 扫描带参数二维码事件,用户已关注时的事件推送
	 * 
	 * @param event
	 */
	void scan(TicketEvent event);

	/**
	 * 上报地理位置事件
	 * 
	 * @param event
	 */
	void reportLocation(ReportLocationEvent event);

	/**
	 * 事件推送群发结果
	 * 
	 * @param event
	 */
	void groupMessage(GroupMessageEvent event);

	/**
	 * 事件推送模板
	 * 
	 * @param event
	 */
	void template(TemplateEvent event);

	/**
	 * 资质认证成功
	 * 
	 * @param event
	 */
	void qualificationVerifySuccess(VerifySuccessEvent event);

	/**
	 * 资质认证失败
	 * 
	 * @param event
	 */
	void qualificationVerifyFail(VerifyFailEvent event);

	/**
	 * 名称认证成功
	 * 
	 * @param event
	 */
	void namingVerifySuccess(VerifySuccessEvent event);

	/**
	 * 名称认证失败
	 * 
	 * @param event
	 */
	void namingVerifyFail(VerifyFailEvent event);

	/**
	 * 年审通知
	 * 
	 * @param event
	 */
	void annualRenew(VerifySuccessEvent event);

	/**
	 * 认证过期失效通知
	 * 
	 * @param event
	 */
	void verifyExpired(VerifySuccessEvent event);

	/**
	 * 订阅设备状态
	 * 
	 * @param event
	 */
	DeviceEventResp subscribeStatus(DeviceEvent event) throws Exception;

	/**
	 * 退订设备状态
	 * 
	 * @param event
	 */
	DeviceEventResp unsubscribeStatus(DeviceEvent event);
}
