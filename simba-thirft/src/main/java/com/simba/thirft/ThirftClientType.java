package com.simba.thirft;

/**
 * thirft客户端类型
 * 
 * @author caozhejun
 *
 */
public enum ThirftClientType {

	/**
	 * 同步阻塞
	 */
	SyncBlock,

	/**
	 * 同步非阻塞
	 */
	SyncUnblock,

	/**
	 * 异步非阻塞
	 */
	AsyncUnblock;
}
