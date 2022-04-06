package com.ndm.api.service;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.dto.ntpclient.NtpMapper;
import com.ndm.api.dto.ntpclient.NtpResponse;
import com.ndm.api.entity.Device;
import com.ndm.api.entity.Ntp;
import com.ndm.api.exception.DataNotFoundException;
import com.ndm.api.repository.DeviceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * A class define ntp service implement
 */
@Service
public class NtpServiceImpl implements NtpService {
    private final DeviceRepository deviceRepository;
    private final NtpMapper ntpMapper;

    @Autowired
    public NtpServiceImpl(final DeviceRepository deviceRepository, final NtpMapper ntpMapper) {
        this.deviceRepository = deviceRepository;
        this.ntpMapper = ntpMapper;
    }

    /**
     * This method to get ntp by device id
     * @param id int
     * @return Ntp
     */
    @Override
    public NtpResponse getByDeviceId(final int id) {
        final Optional<Device> optionalDevice = deviceRepository.findById(id);
        final Device device = optionalDevice.orElseThrow(() -> new DataNotFoundException(ConstantCommon.DEVICE_NOT_FOUND));
        return ntpMapper.mapToNtpResponse(device.getNtp());
    }
}
