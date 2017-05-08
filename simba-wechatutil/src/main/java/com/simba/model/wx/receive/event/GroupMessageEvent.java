package com.simba.model.wx.receive.event;

/**
 * 事件推送群发结果(由于群发任务彻底完成需要较长时间，将会在群发任务即将完成的时候，就推送群发结果，此时的推送人数数据将会与实际情形存在一定误差)
 * 
 * @author caozhejun
 *
 */
public class GroupMessageEvent extends BaseEvent {

	/**
	 * 群发的结构，为“send success”或“send fail”或“err(num)”。但send
	 * success时，也有可能因用户拒收公众号的消息、系统错误等原因造成少量用户接收失败。err(num)是审核失败的具体原因，可能的情况如下：
	 * err(10001), //涉嫌广告 err(20001), //涉嫌政治 err(20004), //涉嫌社会 err(20002),
	 * //涉嫌色情 err(20006), //涉嫌违法犯罪 err(20008), //涉嫌欺诈 err(20013), //涉嫌版权
	 * err(22000), //涉嫌互推(互相宣传) err(21000), //涉嫌其他 err(30001) //
	 * 原创校验出现系统错误且用户选择了被判为转载就不群发 err(30002) // 原创校验被判定为不能群发 err(30003) //
	 * 原创校验被判定为转载文且用户选择了被判为转载就不群发
	 */
	private String status;

	/**
	 * 1-未被判为转载，可以群发，2-被判为转载，可以群发，3-被判为转载，不能群发
	 */
	private int checkState;

	/**
	 * tag_id下粉丝数；或者openid_list中的粉丝数
	 */
	private int totalCount;

	/**
	 * 过滤（过滤是指特定地区、性别的过滤、用户设置拒收的过滤，用户接收已超4条的过滤）后，准备发送的粉丝数，原则上，FilterCount =
	 * SentCount + ErrorCount
	 */
	private int filterCount;

	/**
	 * 发送成功的粉丝数
	 */
	private int sentCount;

	/**
	 * 发送失败的粉丝数
	 */
	private int errorCount;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCheckState() {
		return checkState;
	}

	public void setCheckState(int checkState) {
		this.checkState = checkState;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getFilterCount() {
		return filterCount;
	}

	public void setFilterCount(int filterCount) {
		this.filterCount = filterCount;
	}

	public int getSentCount() {
		return sentCount;
	}

	public void setSentCount(int sentCount) {
		this.sentCount = sentCount;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

}
