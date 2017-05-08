package com.simba.model.wx;

/**
 * 错误返回对象
 * 
 * @author caozhejun
 *
 */
public class ErrMsg {

	private int errcode;

	private String errmsg;

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
