package com.ndm.api.dto.device;

import com.ndm.api.entity.Device;
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
     * @return List<DeviceResponse>
     */
    public List<DeviceResponse> mapToDeviceResponseList(final List<Device> devices) {
        if (devices.isEmpty()) {
            return Collections.emptyList();
        }
        return devices.stream().map(device -> modelMapper.map(device, DeviceResponse.class)).collect(Collectors.toList());
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

    /**
     *
     * @param label String
     * @param ipAddress String
     * @param port int
     * @return Device
     */
    public Device mapToDevice(final String label, final String ipAddress, final int port) {
        return Device.builder().label(label).ipAddress(ipAddress).port(port).build();
    }
}
