package com.ndm.api.dto.device;

import com.ndm.api.entity.Device;
import com.ndm.api.dto.device.DeviceListResponse.DeviceResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class define deviceMapper
 */
@Component
public class DeviceMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public DeviceMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * This is a method convert Devices to DeviceListResponse
     * @param devices List<Device>
     * @return DeviceListResponse
     */
    public DeviceListResponse mapToDeviceListResponse(final List<Device> devices) {
        if (devices.isEmpty()) {
            return new DeviceListResponse(Collections.emptyList());
        }
        final List<DeviceListResponse.DeviceResponse> deviceResponses = devices.stream().map(device -> modelMapper.map(device, DeviceResponse.class)).collect(Collectors.toList());
        return new DeviceListResponse(deviceResponses);
    }

    /**
     * This is a method convert Device to DeviceResponse
     * @param device Device
     * @return DeviceResponse
     */
    public DeviceResponse mapToDeviceResponse(final Device device) {
        if (ObjectUtils.isEmpty(device)) {
            return null;
        }
        return modelMapper.map(device, DeviceResponse.class);
    }

    /**
     * This is a method convert DeviceAddRequestBody to Device
     * @param requestBody DeviceAddRequestBody
     * @return Device
     */
    public Device mapToDevice(final DeviceAddRequestBody requestBody) {
        if (ObjectUtils.isEmpty(requestBody)) {
            return null;
        }
        return modelMapper.map(requestBody, Device.class);
    }
}
