package com.alibaba.idst.nls.realtime.websocket;

import org.jboss.netty.handler.codec.http.HttpMessage;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseDecoder;

public class WebSocketHttpResponseDecoder extends HttpResponseDecoder {
	protected boolean isContentAlwaysEmpty(HttpMessage msg) {
		if ((msg instanceof HttpResponse)) {
			HttpResponse res = (HttpResponse) msg;
			int code = res.getStatus().getCode();

			if (code == 101) {
				return false;
			}

			if (code < 200) {
				return true;
			}
			switch (code) {
			case 204:
			case 205:
			case 304:
				return true;
			}
		}
		return false;
	}
}