package com.alibaba.idst.nls.realtime.utils;

public class Utility {
	public static int byte2int(byte[] byteData, int offset) {
		return (byteData[offset] & 0xFF) << 24 | (byteData[(offset + 1)] & 0xFF) << 16 | (byteData[(offset + 2)] & 0xFF) << 8 | byteData[(offset + 3)] & 0xFF;
	}

	public static byte[] int2byte(int intData) {
		byte[] byteData = new byte[4];
		byteData[0] = ((byte) (0xFF & intData >> 24));
		byteData[1] = ((byte) (0xFF & intData >> 16));
		byteData[2] = ((byte) (0xFF & intData >> 8));
		byteData[3] = ((byte) (0xFF & intData));
		return byteData;
	}
}