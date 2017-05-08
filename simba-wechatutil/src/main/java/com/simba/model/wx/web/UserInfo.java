package com.simba.model.wx.web;

import java.io.Serializable;
import java.util.List;

import com.simba.model.wx.ErrMsg;

public class UserInfo extends ErrMsg implements Serializable {

	private static final long serialVersionUID = 1231032533319542917L;

	/**
	 * 用户的标识，对当前公众号唯一
	 */
	private String openid;

	/**
	 * 用户的昵称
	 */
	private String nickname;

	/**
	 * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	 */
	private int sex;

	/**
	 * 用户所在城市
	 */
	private String city;

	/**
	 * 用户所在省份
	 */
	private String province;

	/**
	 * 用户所在国家
	 */
	private String country;

	/**
	 * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。
	 * 若用户更换头像，原有头像URL将失效。
	 */
	private String headimgurl;

	/**
	 * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	 */
	private String unionid;

	/**
	 * 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
	 */
	private List<String> privilege;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public List<String> getPrivilege() {
		return privilege;
	}

	public void setPrivilege(List<String> privilege) {
		this.privilege = privilege;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserInfo [openid=");
		builder.append(openid);
		builder.append(", nickname=");
		builder.append(nickname);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", city=");
		builder.append(city);
		builder.append(", province=");
		builder.append(province);
		builder.append(", country=");
		builder.append(country);
		builder.append(", headimgurl=");
		builder.append(headimgurl);
		builder.append(", unionid=");
		builder.append(unionid);
		builder.append(", privilege=");
		builder.append(privilege);
		builder.append("]");
		return builder.toString();
	}

}
