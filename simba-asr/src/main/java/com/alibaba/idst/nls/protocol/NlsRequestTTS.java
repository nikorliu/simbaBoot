package com.alibaba.idst.nls.protocol;

public class NlsRequestTTS {

	private String text = null;

	private String format = "normal";
	private String sample_rate = "16000";
	private String encode_type = "pcm";
	private int speech_rate = 0;
	private int volume = 50;
	@SuppressWarnings("unused")
	private int pitch_rate = 0;
	@SuppressWarnings("unused")
	private int background_music_id;
	@SuppressWarnings("unused")
	private int background_music_offset;
	@SuppressWarnings("unused")
	private int background_music_volume;
	private int nus = 1;
	private String voice;
	private String refer;

	public void setPitchRate(int pitch_rate) {
		this.pitch_rate = pitch_rate;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getEncode_type() {
		return this.encode_type;
	}

	public void setEncode_type(String encode_type) {
		this.encode_type = encode_type;
	}

	public String getSample_rate() {
		return this.sample_rate;
	}

	public void setSample_rate(String sample_rate) {
		this.sample_rate = sample_rate;
	}

	public int getSpeech_rate() {
		return this.speech_rate;
	}

	public void setSpeech_rate(int speech_rate) {
		this.speech_rate = speech_rate;
	}

	public int getVolume() {
		return this.volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getNus() {
		return this.nus;
	}

	public void setNus(int nus) {
		this.nus = nus;
	}

	public String getVoice() {
		return this.voice;
	}

	public void setVoice(String voiceName) {
		this.voice = voiceName;
	}

	public String getReference() {
		return this.refer;
	}

	public void setReference(String pronRefer) {
		this.refer = pronRefer;
	}

	public void setBackground_music_offset(int background_music_offset) {
		this.background_music_offset = background_music_offset;
	}

	public void setBackground_music_id(int background_music_id) {
		this.background_music_id = background_music_id;
	}

	public void setBackground_music_volume(int background_music_volume) {
		this.background_music_volume = background_music_volume;
	}
}