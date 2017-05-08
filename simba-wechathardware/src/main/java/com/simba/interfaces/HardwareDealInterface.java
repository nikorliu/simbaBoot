package com.simba.interfaces;

import com.simba.model.wxHardware.receive.BindRequestEvent;
import com.simba.model.wxHardware.receive.BindResponseEvent;
import com.simba.model.wxHardware.receive.DeviceRequestMsg;
import com.simba.model.wxHardware.receive.DeviceResponseMsg;

public interface HardwareDealInterface {

	DeviceResponseMsg text(DeviceRequestMsg request);

	BindResponseEvent bind(BindRequestEvent request);

	BindResponseEvent unbind(BindRequestEvent request);
}
