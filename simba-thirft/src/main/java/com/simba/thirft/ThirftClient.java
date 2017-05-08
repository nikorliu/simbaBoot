package com.simba.thirft;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * thirft 客户端抽象类(具体调用逻辑放在request方法中，自己实现)
 * 
 * @author caozhejun
 *
 */
public abstract class ThirftClient {

	private static final Log logger = LogFactory.getLog(ThirftClient.class);

	/**
	 * thirft服务器的ip地址(必须)
	 */
	private String ip;

	/**
	 * thirft服务器的端口号(必须)
	 */
	private int port;

	/**
	 * 调用接口要使用的参数，多个参数，可以自定义对象或者直接使用Map
	 */
	private Object data;

	/**
	 * 调用thirft服务返回的结果
	 */
	private Object result;

	/**
	 * thirft回调方法，只有客户端类型为 异步非阻塞时才有效
	 */
	private AsyncMethodCallback<?> callback;

	/**
	 * thirft客户端的类型(必须)
	 */
	private ThirftClientType type;

	/**
	 * 
	 * 以下属性是预留给实现类使用的，可用在request方法中，构造thirft工具生成的代码客户端
	 * 
	 */
	/**
	 * 同步阻塞和同步非阻塞时使用
	 */
	protected TProtocol protocol;

	/**
	 * 以下三个字段是异步非阻塞时使用
	 */
	protected TAsyncClientManager clientManager;

	protected TNonblockingTransport asyncTransport;

	protected TProtocolFactory protocolFactory;

	/**
	 * 具体的业务逻辑实现
	 */
	protected abstract Object request();

	/**
	 * 发送thirft请求
	 */
	public void send() {
		try {
			sendRequst();
		} catch (Exception e) {
			logger.error("发送thirft请求异常", e);
		}
	}

	private void sendRequst() throws TTransportException, IOException {
		if (type == ThirftClientType.SyncBlock || type == ThirftClientType.SyncUnblock) {
			sendSyncRequest();
		} else {
			sendAsynRequest();
		}
	}

	/**
	 * 发送同步请求
	 * 
	 * @throws TTransportException
	 */
	private void sendSyncRequest() throws TTransportException {
		TTransport syncTransport = null;
		if (type == ThirftClientType.SyncBlock) {
			syncTransport = new TSocket(ip, port);
		} else {
			syncTransport = new TFramedTransport(new TSocket(ip, port));
		}
		syncTransport.open();
		// 使用高密度二进制协议
		protocol = new TCompactProtocol(syncTransport);
		result = request();
		syncTransport.close();
	}

	/**
	 * 发送异步请求
	 * 
	 * @throws IOException
	 */
	private void sendAsynRequest() throws IOException {
		clientManager = new TAsyncClientManager();
		asyncTransport = new TNonblockingSocket(ip, port);
		// 使用高密度二进制协议
		protocolFactory = new TCompactProtocol.Factory();
		result = request();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public AsyncMethodCallback<?> getCallback() {
		return callback;
	}

	public void setCallback(AsyncMethodCallback<?> callback) {
		this.callback = callback;
	}

	public ThirftClientType getType() {
		return type;
	}

	public void setType(ThirftClientType type) {
		this.type = type;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
