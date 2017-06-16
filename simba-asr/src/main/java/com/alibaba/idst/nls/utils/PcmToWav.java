package com.alibaba.idst.nls.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PcmToWav {

	private static int frequency = 16000;
	private static int recBufSize = 640;

	public static void copyWaveFile(String inFilename, String outFilename) {
		FileInputStream in = null;
		FileOutputStream out = null;
		long totalAudioLen = 0L;
		long totalDataLen = totalAudioLen + 36L;
		long longSampleRate = frequency;
		int channels = 1;
		long byteRate = 16 * frequency * channels / 8;

		byte[] data = new byte[recBufSize];
		try {
			in = new FileInputStream(inFilename);
			out = new FileOutputStream(outFilename);
			totalAudioLen = in.getChannel().size();
			totalDataLen = totalAudioLen + 36L;

			WriteWaveFileHeader(out, totalAudioLen, totalDataLen, longSampleRate, channels, byteRate);

			while (in.read(data) != -1) {
				out.write(data);
			}

			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void WriteWaveFileHeader(FileOutputStream out, long totalAudioLen, long totalDataLen, long longSampleRate, int channels, long byteRate) throws IOException {
		byte[] header = new byte[44];

		header[0] = 82;
		header[1] = 73;
		header[2] = 70;
		header[3] = 70;
		header[4] = ((byte) (int) (totalDataLen & 0xFF));
		header[5] = ((byte) (int) (totalDataLen >> 8 & 0xFF));
		header[6] = ((byte) (int) (totalDataLen >> 16 & 0xFF));
		header[7] = ((byte) (int) (totalDataLen >> 24 & 0xFF));
		header[8] = 87;
		header[9] = 65;
		header[10] = 86;
		header[11] = 69;
		header[12] = 102;
		header[13] = 109;
		header[14] = 116;
		header[15] = 32;
		header[16] = 16;
		header[17] = 0;
		header[18] = 0;
		header[19] = 0;
		header[20] = 1;
		header[21] = 0;
		header[22] = ((byte) channels);
		header[23] = 0;
		header[24] = ((byte) (int) (longSampleRate & 0xFF));
		header[25] = ((byte) (int) (longSampleRate >> 8 & 0xFF));
		header[26] = ((byte) (int) (longSampleRate >> 16 & 0xFF));
		header[27] = ((byte) (int) (longSampleRate >> 24 & 0xFF));
		header[28] = ((byte) (int) (byteRate & 0xFF));
		header[29] = ((byte) (int) (byteRate >> 8 & 0xFF));
		header[30] = ((byte) (int) (byteRate >> 16 & 0xFF));
		header[31] = ((byte) (int) (byteRate >> 24 & 0xFF));
		header[32] = 2;
		header[33] = 0;
		header[34] = 16;
		header[35] = 0;
		header[36] = 100;
		header[37] = 97;
		header[38] = 116;
		header[39] = 97;
		header[40] = ((byte) (int) (totalAudioLen & 0xFF));
		header[41] = ((byte) (int) (totalAudioLen >> 8 & 0xFF));
		header[42] = ((byte) (int) (totalAudioLen >> 16 & 0xFF));
		header[43] = ((byte) (int) (totalAudioLen >> 24 & 0xFF));
		out.write(header, 0, 44);
	}
}