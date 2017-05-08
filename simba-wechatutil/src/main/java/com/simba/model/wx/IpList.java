package com.simba.model.wx;

import java.util.List;

/**
 * 微信服务器IP地址
 * 
 * @author caozhejun
 *
 */
public class IpList extends ErrMsg {

	private List<String> ip_list;

	public List<String> getIp_list() {
		return ip_list;
	}

	public void setIp_list(List<String> ip_list) {
		this.ip_list = ip_list;
	}

}
