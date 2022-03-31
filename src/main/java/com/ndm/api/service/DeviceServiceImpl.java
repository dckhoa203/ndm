package com.ndm.api.service;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.entity.Device;
import com.ndm.api.exception.DataNotFoundException;
import com.ndm.api.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService{

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceServiceImpl(final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Device> getAll() {
        return deviceRepository.findAll();
    }

    @Override
    public Device findById(final int id) {
        final Optional<Device> deviceOptional = deviceRepository.findById(id);
        return deviceOptional.orElseThrow(() -> new DataNotFoundException((ConstantCommon.DEVICE_NOT_FOUND)));
    }

    @Override
    public Device getByIpAddress(final String ipAddress) {
        return deviceRepository.getByIpAddress(ipAddress);
    }

    @Override
    public List<Device> getByType(final int type) {
        return deviceRepository.getByType(type);
    }
}
