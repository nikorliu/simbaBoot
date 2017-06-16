package com.alibaba.idst.nls.protocol;

public class NlsRequestASR {

	public String asrSC = "opu";
	public String user_id;
	public String vocabulary_id;
	public String organization_id;
	public String sampleRate = null;
	public String response_mode;
	public int max_end_silence = -1;
	public String customization_id;

	public static enum mode {
		STREAMING, NORMAL;
	}

	public static class out {
		public String version = "4.0";

		public String result = "";

		public boolean fake = true;
	}
}