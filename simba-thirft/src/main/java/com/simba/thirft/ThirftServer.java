package com.simba.thirft;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServerEventHandler;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.server.TThreadedSelectorServer.Args;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

/**
 * thirft服务器端(非阻塞)
 * 
 * @author caozhejun
 *
 */
public class ThirftServer {

	private static final Log logger = LogFactory.getLog(ThirftServer.class);

	/**
	 * 启动thirft非阻塞服务器
	 * 
	 * @param port
	 *            端口号
	 * @param processor
	 *            处理器
	 */
	public static void start(int port, TProcessor processor) throws TTransportException {
		start(port, processor, null);
	}

	/**
	 * 启动thirft非阻塞服务器
	 * 
	 * @param port
	 *            端口号
	 * @param processor
	 *            处理器
	 * @param handler
	 *            事件处理器
	 */
	public static void start(int port, TProcessor processor, TServerEventHandler handler) throws TTransportException {
		// 传输通道 - 非阻塞方式
		TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(port);
		// 异步IO，需要使用TFramedTransport，它将分块缓存读取。
		TTransportFactory transportFactory = new TFramedTransport.Factory();
		// 使用高密度二进制协议
		TProtocolFactory proFactory = new TCompactProtocol.Factory();
		// 创建服务器
		TServer server = new TThreadedSelectorServer(new Args(serverTransport).protocolFactory(proFactory).transportFactory(transportFactory).processor(processor));
		if (handler != null) {
			server.setServerEventHandler(handler);
		}
		logger.info("启动thirft服务器成功:" + port);
		server.serve();
	}

}
