package com.ndm.api.service;

import com.ndm.api.entity.Device;
import com.ndm.api.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
