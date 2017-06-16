package com.aliyun.nls.transcription.opu;

public class OpuCodec {

	public native long createEncoder();

	public native long createDecoder();

	public native int encode(long paramLong, short[] paramArrayOfShort, int paramInt, byte[] paramArrayOfByte);

	public native int decode(long paramLong, byte[] paramArrayOfByte, int paramInt, short[] paramArrayOfShort);

	public native void destroyEncoder(long paramLong);

	public native void destroyDecoder(long paramLong);

	static {
		System.loadLibrary("opucodec");
	}
}