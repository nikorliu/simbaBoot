package com.simba.model.wx.receive.event;

/**
 * 资质认证失败/名称认证失败（这时虽然客户端不打勾，但仍有接口权限）
 * 
 * @author caozhejun
 *
 */
public class VerifyFailEvent extends BaseEvent {

	/**
	 * 失败发生时间 (整形)，时间戳
	 */
	private long failTime;

	/**
	 * 认证失败的原因
	 */
	private String failReason;

	public long getFailTime() {
		return failTime;
	}

	public void setFailTime(long failTime) {
		this.failTime = failTime;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

}
