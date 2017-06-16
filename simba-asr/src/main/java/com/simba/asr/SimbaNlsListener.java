package com.simba.asr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.idst.nls.event.NlsEvent;
import com.alibaba.idst.nls.event.NlsListener;

public abstract class SimbaNlsListener implements NlsListener {

	private static final Log logger = LogFactory.getLog(SimbaNlsListener.class);

	@Override
	public void onOperationFailed(NlsEvent paramNlsEvent) {
		// 识别失败的回调
		String result = "";
		result += "on operation failed: statusCode=[" + paramNlsEvent.getResponse().getStatus_code() + "], " + paramNlsEvent.getErrorMessage();
		logger.error(result);
	}

	@Override
	public void onChannelClosed(NlsEvent paramNlsEvent) {
		// socket 连接关闭的回调
		logger.info("on websocket closed.");
	}

}
