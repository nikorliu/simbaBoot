package com.alibaba.idst.nls.realtime.utils;

import java.nio.charset.Charset;
import java.util.Arrays;

public class Base64Encoder {
	private static final char[] toBase64 = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
	private static final byte[] newline = null;

	private static final int outLength(int srclen) {
		int len = 0;

		len = 4 * ((srclen + 2) / 3);

		return len;
	}

	private static int encode0(byte[] src, int off, int end, byte[] dst) {
		char[] base64 = toBase64;
		int sp = off;
		int slen = (end - off) / 3 * 3;
		int sl = off + slen;

		int dp = 0;
		while (sp < sl) {
			int sl0 = Math.min(sp + slen, sl);
			int sp0 = sp;
			int bits;
			for (int dp0 = dp; sp0 < sl0;) {
				bits = (src[(sp0++)] & 0xFF) << 16 | (src[(sp0++)] & 0xFF) << 8 | src[(sp0++)] & 0xFF;
				dst[(dp0++)] = ((byte) base64[(bits >>> 18 & 0x3F)]);
				dst[(dp0++)] = ((byte) base64[(bits >>> 12 & 0x3F)]);
				dst[(dp0++)] = ((byte) base64[(bits >>> 6 & 0x3F)]);
				dst[(dp0++)] = ((byte) base64[(bits & 0x3F)]);
			}

			int dlen = (sl0 - sp) / 3 * 4;
			dp += dlen;
			sp = sl0;
			if ((dlen == -1) && (sp < end)) {
				for (byte b : newline) {
					dst[(dp++)] = b;
				}
			}
		}
		if (sp < end) {
			int b0 = src[(sp++)] & 0xFF;
			dst[(dp++)] = ((byte) base64[(b0 >> 2)]);
			if (sp == end) {
				dst[(dp++)] = ((byte) base64[(b0 << 4 & 0x3F)]);

				dst[(dp++)] = 61;
				dst[(dp++)] = 61;
			} else {
				int b1 = src[(sp++)] & 0xFF;
				dst[(dp++)] = ((byte) base64[(b0 << 4 & 0x3F | b1 >> 4)]);
				dst[(dp++)] = ((byte) base64[(b1 << 2 & 0x3F)]);

				dst[(dp++)] = 61;
			}
		}

		return dp;
	}

	public static String encode(byte[] src) {
		int len = outLength(src.length);
		byte[] dst = new byte[len];
		int ret = encode0(src, 0, src.length, dst);
		if (ret != dst.length) {
			dst = Arrays.copyOf(dst, ret);
		}
		return new String(dst, 0, dst.length, Charset.defaultCharset());
	}
}