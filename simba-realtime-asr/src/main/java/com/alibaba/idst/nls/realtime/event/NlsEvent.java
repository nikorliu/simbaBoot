package com.alibaba.idst.nls.realtime.event;

import com.alibaba.idst.nls.realtime.protocol.NlsResponse;

public class NlsEvent {
	private String errorMessage;
	private NlsResponse response = new NlsResponse();

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public NlsResponse getResponse() {
		return this.response;
	}

	public void setResponse(NlsResponse response) {
		this.response = response;
	}
}