package com.ndm.api.service;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.dto.ntp.NtpMapper;
import com.ndm.api.dto.ntp.NtpServerAddRequestBody;
import com.ndm.api.entity.Device;
import com.ndm.api.entity.Ntp;
import com.ndm.api.entity.NtpServer;
import com.ndm.api.exception.DataNotFoundException;
import com.ndm.api.repository.DeviceRepository;

import com.ndm.api.repository.NtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * A class define ntp service implement
 */
@Service
public class NtpServiceImpl implements NtpService {
    private final DeviceRepository deviceRepository;
    private final NtpRepository ntpRepository;

    @Autowired
    private NtpMapper ntpMapper;

    @Autowired
    public NtpServiceImpl(DeviceRepository deviceRepository, final NtpRepository ntpRepository) {
        this.deviceRepository = deviceRepository;
        this.ntpRepository = ntpRepository;
    }

    /**
     * This method to get ntp by device id
     * @param id int
     * @return Ntp
     */
    @Override
    public Ntp getByDeviceId(final int id) {
        final Optional<Device> optionalDevice = deviceRepository.findById(id);
        final Device device = optionalDevice.orElseThrow(() -> new DataNotFoundException(ConstantCommon.DEVICE_NOT_FOUND));
        return device.getNtp();
    }

    /**
     * This method to add ntp to device
     * @param deviceId int
     * @param requestBody NtpServerAddRequestBody
     */
    @Override
    public void addNtpServerToDevice(final int deviceId, final NtpServerAddRequestBody requestBody) {
        final Optional<Device> deviceOptional = deviceRepository.findById(deviceId);
        final Device device = deviceOptional.orElseThrow(() -> new DataNotFoundException(ConstantCommon.DEVICE_NOT_FOUND));
        final Ntp ntp = device.getNtp();
        final Set<NtpServer> ntpServers = new HashSet<>();
        final NtpServer ntpServer = ntpMapper.mapToNtpServer(requestBody);
        ntpServers.add(ntpServer);
        ntp.setNtpServers(ntpServers);
        ntpRepository.save(ntp);
    }
}
