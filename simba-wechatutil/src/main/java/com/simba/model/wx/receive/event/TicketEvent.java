package com.simba.model.wx.receive.event;

/**
 * 扫描带参数二维码事件
 * 
 * @author caozhejun
 *
 */
public class TicketEvent extends BaseEvent {

	/**
	 * 二维码的ticket，可用来换取二维码图片
	 */
	private String ticket;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

}
