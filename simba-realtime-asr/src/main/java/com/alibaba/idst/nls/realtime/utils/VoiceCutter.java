package com.alibaba.idst.nls.realtime.utils;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class VoiceCutter {
	private static final int[] sizes = { 12, 13, 15, 17, 19, 20, 26, 31, 5, 6, 5, 5, 0, 0, 0, 0 };

	public static int cutAmr(byte[] data, int offset, int milliSec) {
		if (data.length < 6)
			return 0;
		if ((data[0] != 35) || (data[1] != 33) || (data[2] != 65) || (data[3] != 77) || (data[4] != 82) || (data[5] != 10)) {
			return 0;
		}
		int index = offset < 6 ? 6 : offset;
		int len = 0;
		while ((index < data.length) && (milliSec > 0)) {
			len = sizes[(data[index] >> 3 & 0xF)];
			if (index + len < data.length) {
				index += len + 1;
				milliSec -= 20;
			} else {
				index = 0;
			}
		}

		return index;
	}

	public static int cutOpu(byte[] data, int offset, int milliSec) {
		int index = offset;
		int len = 0;
		while ((index < data.length) && (milliSec > 0)) {
			len = data[index] & 0xFF;
			if (len == 0) {
				index = 0;
			} else if (index + len < data.length) {
				index += len + 1;
				milliSec -= 20;
			} else {
				index = 0;
			}
		}

		return index;
	}

	public static byte[] getAmr(ByteBuffer buffer) {
		buffer.flip();
		int len = 0;
		byte idx = 0;
		byte[] fData = new byte[32];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while (buffer.hasRemaining()) {
			buffer.mark();
			idx = buffer.get();
			len = sizes[(idx >> 3 & 0xF)];
			if (len < buffer.remaining()) {
				buffer.get(fData, 0, len);
				bos.write(idx);
				bos.write(fData, 0, len);
			} else if (len > buffer.remaining()) {
				buffer.reset();
				buffer.compact();
			} else {
				buffer.get(fData, 0, len);
				bos.write(idx);
				bos.write(fData, 0, len);
				buffer.compact();
			}
		}

		return bos.toByteArray();
	}

	public static byte[] getOpu(ByteBuffer buffer) {
		buffer.flip();
		int len = 0;
		byte idx = 0;
		byte[] fData = new byte[256];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while (buffer.hasRemaining()) {
			buffer.mark();
			idx = buffer.get();
			len = idx & 0xFF;
			if (len == 0) {
				buffer.reset();
				buffer.compact();
				return null;
			}
			if (len < buffer.remaining()) {
				buffer.get(fData, 0, len);
				bos.write(idx);
				bos.write(fData, 0, len);
			} else if (len > buffer.remaining()) {
				buffer.reset();
				buffer.compact();
			} else {
				buffer.get(fData, 0, len);
				bos.write(idx);
				bos.write(fData, 0, len);
				buffer.compact();
			}
		}

		return bos.toByteArray();
	}

	public static byte[] getPcm(ByteBuffer buffer) {
		buffer.flip();
		byte[] rs = buffer.limit() % 2 == 0 ? new byte[buffer.limit()] : new byte[buffer.limit() - 1];
		buffer.get(rs);
		buffer.compact();
		return rs;
	}
}