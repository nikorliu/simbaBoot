package com.simba.interfaces;

import com.simba.model.wxHardware.receive.BindRequestEvent;
import com.simba.model.wxHardware.receive.BindResponseEvent;
import com.simba.model.wxHardware.receive.DeviceRequestMsg;
import com.simba.model.wxHardware.receive.DeviceResponseMsg;

public interface HardwareDealInterface {

	DeviceResponseMsg text(DeviceRequestMsg request, String json);

	BindResponseEvent bind(BindRequestEvent request, String json);

	BindResponseEvent unbind(BindRequestEvent request, String json);
}
