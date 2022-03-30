package com.ndm.api.dto;

import com.ndm.api.entity.Device;
import com.ndm.api.entity.Ntp;
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

    public DevicesResponse toDevicesResponse(final List<Device> devices) {
        final List<DevicesResponse.DeviceResponse> deviceResponses = devices.stream().map(device -> modelMapper.map(device, DevicesResponse.DeviceResponse.class)).collect(Collectors.toList());
        return new DevicesResponse(deviceResponses);
    }
}
