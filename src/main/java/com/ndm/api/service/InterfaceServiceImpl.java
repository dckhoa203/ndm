package com.ndm.api.service;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.entity.Device;
import com.ndm.api.entity.Interface;
import com.ndm.api.exception.DataNotFoundException;
import com.ndm.api.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterfaceServiceImpl implements InterfaceService {
    private final DeviceRepository deviceRepository;

    @Autowired
    public InterfaceServiceImpl(final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Interface> getAllByDeviceId(final int deviceId) {
        final Optional<Device> deviceOptional = deviceRepository.findById(deviceId);
        final Device device = deviceOptional.orElseThrow(() -> new DataNotFoundException(ConstantCommon.DEVICE_NOT_FOUND));
        return device.getInterfaces();
    }
}
