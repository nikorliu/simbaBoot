package com.simba.model.wx.receive.event;

/**
 * 资质认证成功/名称认证成功（即命名成功）/年审通知/认证过期失效通知
 * 
 * @author caozhejun
 *
 */
public class VerifySuccessEvent extends BaseEvent {

	/**
	 * 有效期 (整形)，指的是时间戳，将于该时间戳认证过期
	 */
	private long expiredTime;

	public long getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(long expiredTime) {
		this.expiredTime = expiredTime;
	}

}
