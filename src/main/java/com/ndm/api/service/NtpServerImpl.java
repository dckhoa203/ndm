package com.ndm.api.service;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.entity.Device;
import com.ndm.api.entity.Ntp;
import com.ndm.api.exception.DataNotFoundException;
import com.ndm.api.repository.DeviceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NtpServerImpl implements NtpService {
    private final DeviceRepository deviceRepository;

    @Autowired
    public NtpServerImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Ntp getByDeviceId(final int id) {
        final Optional<Device> optionalDevice = deviceRepository.findById(id);
        final Device device = optionalDevice.orElseThrow(() -> new DataNotFoundException(ConstantCommon.DEVICE_NOT_FOUND));
        return device.getNtp();
    }
}
