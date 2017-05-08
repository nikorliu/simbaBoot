package com.simba.model.wx.kf;

import java.util.List;

import com.simba.model.wx.ErrMsg;

public class KfSearchResult extends ErrMsg {

	private List<KfSearch> kf_list;

	public List<KfSearch> getKf_list() {
		return kf_list;
	}

	public void setKf_list(List<KfSearch> kf_list) {
		this.kf_list = kf_list;
	}

}
