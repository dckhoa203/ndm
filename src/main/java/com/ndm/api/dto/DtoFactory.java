package com.ndm.api.dto;

import com.ndm.api.entity.Device;
import com.ndm.api.entity.Ntp;
import com.ndm.api.dto.DeviceListResponse.DeviceResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoFactory {

    private final ModelMapper modelMapper;

    @Autowired
    public DtoFactory(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public NtpResponse toNtpResponse(final Ntp ntp){
        return modelMapper.map(ntp, NtpResponse.class);
    }

    public DeviceListResponse toDeviceListResponse(final List<Device> devices) {
        final List<DeviceResponse> deviceResponses = devices.stream().map(device -> modelMapper.map(device, DeviceResponse.class)).collect(Collectors.toList());
        return new DeviceListResponse(deviceResponses);
    }

    public DeviceResponse toDeviceResponse(final Device device) {
        return modelMapper.map(device, DeviceResponse.class);
    }
}
