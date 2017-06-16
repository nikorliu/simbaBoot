package com.alibaba.idst.nls.protocol;

import java.lang.reflect.Type;
import java.util.UUID;

import com.alibaba.idst.nls.utils.RawJsonText;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class NlsRequest {
	private String version = "4.0";
	private String app_key;
	private boolean enableCompress = false;
	private String session_id;
	private String id = UUID.randomUUID().toString().replaceAll("-", "");

	private Boolean bstream_attached = Boolean.valueOf(false);

	private transient Gson gsonCvt = RawJsonText.createGson();

	private RequestSet requests = new RequestSet();
	private NlsRequestContext context = new NlsRequestContext();

	public String getSession_id() {
		return this.session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public Boolean getBstreamAttached() {
		return this.bstream_attached;
	}

	public void setBstream_attached(Boolean bstream_attached) {
		setBstreamAttached(bstream_attached);
	}

	public void setBstreamAttached(Boolean bstream_attached) {
		this.bstream_attached = bstream_attached;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSessionId() {
		return this.session_id;
	}

	public void authorize(String id, String secret) {
		this.context.auth = new NlsRequestAuth();
		if (this.requests.asr_in != null) {
			this.context.auth.add_Request("asr");
		}
		if (this.requests.ds_in != null) {
			this.context.auth.add_Request("ds");
		}
		if (this.requests.gds_in != null) {
			this.context.auth.add_Request("gds");
		}
		if (this.requests.tts_in != null) {
			this.context.auth.add_Request("tts");
		}
		this.context.auth.Authorize(id, secret);
	}

	public void setAsrResposeMode(NlsRequestASR.mode mode) {
		if (mode.equals(NlsRequestASR.mode.STREAMING))
			this.requests.asr_in.response_mode = "0";
		else if (mode.equals(NlsRequestASR.mode.NORMAL))
			this.requests.asr_in.response_mode = "1";
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getApp_key() {
		return this.app_key;
	}

	public String getAppKey() {
		return this.app_key;
	}

	public void setApp_key(String app_key) {
		setAppKey(app_key);
	}

	public void setAppKey(String appKey) {
		this.app_key = appKey;
	}

	public boolean isEnableCompress() {
		return this.enableCompress;
	}

	public void setEnableCompress(boolean enableCompress) {
		this.enableCompress = enableCompress;
		setAsrSampleRate("16");
	}

	public void setAsr_req(NlsRequestASR asr_in) {
		this.requests.asr_in = asr_in;
		setBstream_attached(Boolean.valueOf(asr_in != null));
	}

	public void setAsrMaxEndSilence(int silence) {
		if (this.requests.asr_in == null) {
			this.requests.asr_in = new NlsRequestASR();
		}
		this.requests.asr_in.max_end_silence = silence;
	}

	public void setAsr_sc(String sc) {
		setAsrFormat(sc);
	}

	public void setAsrFormat(String format) {
		NlsRequestASR asrQ = this.requests.asr_in;
		if (asrQ == null) {
			asrQ = new NlsRequestASR();
		}
		if ((this.enableCompress) && (format.equals("pcm")))
			asrQ.asrSC = "opu";
		else {
			asrQ.asrSC = format;
		}
		setAsr_req(asrQ);
	}

	public void setAsrUserId(String id) {
		if (this.requests.asr_in == null) {
			this.requests.asr_in = new NlsRequestASR();
		}
		this.requests.asr_in.user_id = id;
	}

	public void setAsrVocabularyId(String vocabularyId) {
		if (this.requests.asr_in == null) {
			this.requests.asr_in = new NlsRequestASR();
		}
		this.requests.asr_in.vocabulary_id = vocabularyId;
	}

	public void setAsrOrganizationId(String organizationId) {
		if (this.requests.asr_in == null) {
			this.requests.asr_in = new NlsRequestASR();
		}
		this.requests.asr_in.organization_id = organizationId;
	}

	public void setAsrCustomizationId(String customizationId) {
		if (this.requests.asr_in == null) {
			this.requests.asr_in = new NlsRequestASR();
		}
		this.requests.asr_in.customization_id = customizationId;
	}

	public void setAsr_sampleRate(String sampleRate) {
		setAsrSampleRate(sampleRate);
	}

	public void setAsrSampleRate(String sampleRate) {
		NlsRequestASR asrQ = this.requests.asr_in;
		if (asrQ == null) {
			asrQ = new NlsRequestASR();
		}
		asrQ.sampleRate = sampleRate;
		setAsr_req(asrQ);
	}

	public void setAsr_fake(String sc) {
		setAsrFake(sc);
	}

	public void setAsrFake(String fakeText) {
		if (this.requests.asr_fake == null) {
			this.requests.asr_fake = new NlsRequestASR.out();
		}

		this.requests.asr_fake.fake = true;
		this.requests.asr_fake.result = fakeText;
		setBstream_attached(Boolean.valueOf(false));
	}

	public NlsRequestASR.out getAsrFake() {
		return this.requests.asr_fake;
	}

	public NlsRequestASR.out getAsr_fake() {
		return getAsrFake();
	}

	public void setDs_type(String ds_type) {
		setDsType(ds_type);
	}

	public void setDsType(String ds_type) {
		if (this.requests.ds_in == null) {
			this.requests.ds_in = new NlsRequestDs();
		}

		this.requests.ds_in.setType(ds_type);
	}

	public void enableNLUResult() {
		enableCloudNLUResult();
	}

	public void enableCloudNLUResult() {
		this.context.application.application_id = "com.aliyun.dataapi.nls.api.nlu";
		this.context.device.device_type = "aliyun.dataapi.nls";
		this.context.device.device_id = "com.aliyun.dataapi.nls.api.nlu";
		this.context.application.user_id = "com.aliyun.dataapi.nls.api.nlu";
		setDs_req("{ }");
	}

	public void setDs_req(String ds_request) {
		setDsRequest(ds_request);
	}

	public void setDsRequest(String content) {
		if (this.requests.ds_in == null) {
			this.requests.ds_in = new NlsRequestDs();
		}

		this.requests.ds_in.setContent(content);
	}

	public void setGds_type(String gds_type) {
		setGdsType(gds_type);
	}

	public void setGdsType(String gdsType) {
		if (this.requests.gds_in == null) {
			this.requests.gds_in = new NlsRequestGds();
		}
		this.requests.gds_in.setType(gdsType);
	}

	public void setGds_req(String ds_request) {
		setJsonGdsContent(ds_request);
	}

	public void setJsonGdsContent(String content) {
		if (this.requests.gds_in == null) {
			this.requests.gds_in = new NlsRequestGds();
		}

		this.requests.gds_in.setContent(content);
	}

	public void setGds_content(Content content) {
		setGdsContent(content);
	}

	public void setGdsContent(Content content) {
		if (this.requests.gds_in == null) {
			this.requests.gds_in = new NlsRequestGds();
		}

		this.requests.gds_in.setContent(content);
	}

	public void enableGdsDebug(boolean debug) {
		this.context.debug.GDS_AllResultCode = debug;
	}

	public void setTtsEncodeType(String encodeType) {
		if (this.requests.tts_in == null) {
			this.requests.tts_in = new NlsRequestTTS();
		}

		this.requests.tts_in.setEncode_type(encodeType);
	}

	public void setTtsSpeechRate(int speechRate) {
		if (this.requests.tts_in == null) {
			this.requests.tts_in = new NlsRequestTTS();
		}

		if (speechRate > 500)
			speechRate = 500;
		else if (speechRate < -500) {
			speechRate = -500;
		}
		this.requests.tts_in.setSpeech_rate(speechRate);
	}

	public void setTtsVoice(String voiceName) {
		if (this.requests.tts_in == null) {
			this.requests.tts_in = new NlsRequestTTS();
		}
		this.requests.tts_in.setVoice(voiceName);
	}

	public void setTtsReference(String pronRefer) {
		if (this.requests.tts_in == null) {
			this.requests.tts_in = new NlsRequestTTS();
		}
		this.requests.tts_in.setReference(pronRefer);
	}

	public void setTtsIn(NlsRequestTTS ttsIn) {
		this.requests.tts_in = ttsIn;
	}

	public void setTtsVolume(int volume) {
		if (this.requests.tts_in == null) {
			this.requests.tts_in = new NlsRequestTTS();
		}

		if (volume > 100)
			volume = 100;
		else if (volume < 0) {
			volume = 0;
		}

		this.requests.tts_in.setVolume(volume);
	}

	public void setTtsNus(int nus) {
		if (this.requests.tts_in == null) {
			this.requests.tts_in = new NlsRequestTTS();
		}

		this.requests.tts_in.setNus(nus);
	}

	public void setTtsPitchRate(int pitchRate) {
		if (this.requests.tts_in == null) {
			this.requests.tts_in = new NlsRequestTTS();
		}

		this.requests.tts_in.setPitchRate(pitchRate);
	}

	public void setTtsBackgroundMusic(int id) {
		if (this.requests.tts_in == null) {
			this.requests.tts_in = new NlsRequestTTS();
		}

		this.requests.tts_in.setBackground_music_id(id);
	}

	public void setTtsBackgroundMusic(int id, int offset) {
		setTtsBackgroundMusic(id);
		this.requests.tts_in.setBackground_music_offset(offset);
	}

	public void setTtsBackgroundMusic(int id, int offset, int volume) {
		setTtsBackgroundMusic(id, offset);
		this.requests.tts_in.setBackground_music_volume(volume);
	}

	public void setTts_req(String tts_text) {
		setTtsRequest(tts_text);
	}

	public void setTtsRequest(String tts_text) {
		if (this.requests.tts_in == null) {
			this.requests.tts_in = new NlsRequestTTS();
		}

		this.requests.tts_in.setText(tts_text);
	}

	public void setTts_req(String tts_text, String sample_rate) {
		setTtsRequest(tts_text, sample_rate);
	}

	public void setTtsRequest(String tts_text, String sample_rate) {
		setTtsRequest(tts_text);
		this.requests.tts_in.setSample_rate(sample_rate);
	}

	public String getTtsRequest() {
		if (this.requests.tts_in == null) {
			return null;
		}

		return this.requests.tts_in.getText();
	}

	public void setDeviceInfo(NlsRequestContext.DeviceInfo deviceInfo) {
		this.context.device = deviceInfo;
	}

	public void setLocation(String latitude, String longitude) {
		if (this.context.location == null) {
			this.context.location = new NlsRequestContext.LocationInfo();
		}

		this.context.location.latitude = latitude;
		this.context.location.longitude = longitude;
	}

	public void setLocationInfo(NlsRequestContext.LocationInfo locationInfo) {
		this.context.location = locationInfo;
	}

	public NlsRequestContext.LocationInfo getLocationInfo() {
		return this.context.location;
	}

	public void setExt_userData(String strName, String strContent) {
		setExtUserData(strName, strContent);
	}

	public void setExtUserData(String strName, String strContent) {
		this.requests.ext_userInName = strName;
		this.requests.ext_userIn = new RawJsonText(strContent);
	}

	public String getExtUserData(String strName) {
		if (this.requests.ext_userInName.equals(strName)) {
			return this.requests.ext_userIn.text;
		}

		return null;
	}

	public NlsRequest(NlsRequestProto proto) {
		InitRequest(proto);
	}

	public NlsRequestASR getASR_req() {
		return this.requests.asr_in;
	}

	public NlsRequest() {
		InitRequest(new NlsRequestProto());
	}

	public void InitRequest(NlsRequestProto proto) {
		this.context.application.application_id = proto.getApp_id();
		this.context.application.user_id = proto.getApp_user_id();
		this.context.application.version = proto.getApp_version();
		this.context.application.service_version = proto.getService_version();
		this.context.application.service_id = proto.getService_id();

		this.context.device.device_type = proto.getDevice_type();
		this.context.device.device_id = proto.getDevice_id();
		this.context.device.system_locale = proto.getDevice_system_locale();
		this.context.device.timezone = proto.getDevice_timezone();
		this.context.device.device_brand = proto.getDevice_brand();
		this.context.device.device_imei = proto.getDevice_imei();
		this.context.device.device_mac = proto.getDevice_mac();
		this.context.device.device_model = proto.getDevice_model();
		this.context.device.device_uuid = proto.getDevice_uuid();
		this.context.device.network_type = proto.getNetwork_type();
		this.context.device.os_type = proto.getOs_type();
		this.context.device.os_version = proto.getOs_version();
	}

	public String toJson() {
		String strReq = this.gsonCvt.toJson(this);
		return strReq;
	}

	public static class RequestSet {
		public NlsRequestASR asr_in = null;
		public NlsRequestTTS tts_in = null;
		public NlsRequestDs ds_in = null;
		public NlsRequestGds gds_in = null;
		public NlsRequestASR.out asr_fake = null;
		public RawJsonText ext_userIn = null;
		public String ext_userInName = null;

		public static class RequestSetSerializer implements JsonSerializer<NlsRequest.RequestSet> {
			public JsonElement serialize(NlsRequest.RequestSet src, Type typeOfSrc, JsonSerializationContext context) {
				JsonObject jobj = new JsonObject();
				if (src.asr_in != null) {
					jobj.add("asr_in", context.serialize(src.asr_in));
				}

				if (src.ds_in != null) {
					jobj.add("ds_in", context.serialize(src.ds_in));
				}

				if (src.gds_in != null) {
					jobj.add("gds_in", context.serialize(src.gds_in));
				}

				if (src.tts_in != null) {
					jobj.add("tts_in", context.serialize(src.tts_in));
				}

				if (src.asr_fake != null) {
					jobj.add("asr_out", context.serialize(src.asr_fake));
				}

				if ((src.ext_userIn != null) && (src.ext_userInName != null)) {
					jobj.add(src.ext_userInName, context.serialize(src.ext_userIn));
				}

				return jobj;
			}
		}
	}
}