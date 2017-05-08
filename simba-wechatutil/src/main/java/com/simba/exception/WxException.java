package com.simba.exception;

/**
 * 微信异常
 * 
 * @author caozhejun
 *
 */
public class WxException extends RuntimeException {

	private static final long serialVersionUID = -8089141058418572463L;

	private int errcode;

	private String errmsg;

	private String message;

	public WxException(String message, int errcode, String errmsg) {
		super(message + ",errcode:" + errcode + ",errmsg:" + errmsg);
		this.message = message;
		this.errcode = errcode;
		this.errmsg = errmsg;
	}

	public int getErrcode() {
		return errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public String getMessage() {
		return message;
	}

}
