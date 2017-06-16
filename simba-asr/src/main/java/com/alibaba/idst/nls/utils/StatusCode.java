package com.alibaba.idst.nls.utils;

public enum StatusCode {
	SUCCESS(0),

	ASR_UNSUPPORTED_FORMAT(20100),

	ASR_UNSUPPORTED_SAMPLE_RATE(20101),

	ASR_TOO_SMALL_PACKET(20102),

	ASR_TOO_LARGE_PACKET(20103),

	ASR_TOO_LONG_SPEECH(20104),

	ASR_SILENT_SPEECH(20105),

	ASR_DECODE_ERROR(20110),

	ASR_TOO_MANY_CHANNELS(20111),

	ASR_IDLE_TIMEOUT(20120),

	ASR_ALREADY_FINISHED(20121),

	ASR_RECOGNITION_FAILED(20200),

	ASR_RECOGNITION_TIMEOUT(20201),

	ASR_SERVICE_BUSY(20202),

	ASR_SERVICE_NOT_AVALIABLE(20300),

	UNKNOWN(999999);

	private int value;

	private StatusCode(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}

	public static StatusCode valueOf(int statusCode) {
		for (StatusCode code : values()) {
			if (code.value == statusCode) {
				return code;
			}
		}

		return UNKNOWN;
	}
}