package com.ndm.api.service;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.dto.ntpserver.NtpServerAddRequestBody;
import com.ndm.api.dto.ntpserver.NtpServerMapper;
import com.ndm.api.dto.ntpserver.NtpServerUpdateRequestBody;
import com.ndm.api.entity.Device;
import com.ndm.api.entity.Ntp;
import com.ndm.api.entity.NtpServer;
import com.ndm.api.exception.DataNotFoundException;
import com.ndm.api.exception.DuplicateException;
import com.ndm.api.repository.DeviceRepository;
import com.ndm.api.repository.NtpRepository;
import com.ndm.api.repository.NtpServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class NtpServerServiceImpl
 */
@Service
@Transactional
public class NtpServerServiceImpl implements NtpServerService{
    private final NtpRepository ntpRepository;
    private final NtpServerRepository ntpServerRepository;
    private final NtpServerMapper ntpServerMapper;
    private final DeviceRepository deviceRepository;

    @Autowired
    public NtpServerServiceImpl(final NtpRepository ntpRepository, NtpServerRepository ntpServerRepository, final NtpServerMapper ntpServerMapper, final DeviceRepository deviceRepository) {
        this.ntpRepository = ntpRepository;
        this.ntpServerRepository = ntpServerRepository;
        this.ntpServerMapper = ntpServerMapper;
        this.deviceRepository = deviceRepository;
    }

    /**
     * This is a method of adding ntp server
     * @param requestBody NtpServerAddRequestBody
     */
    @Override
    public void add(final NtpServerAddRequestBody requestBody) {
        if (ntpServerRepository.existsByIpAddress(requestBody.getIpAddress())) {
            throw new DuplicateException(String.format(ConstantCommon.DUPLICATE_IP_ADDRESS, requestBody.getIpAddress()));
        }
        final Optional<Device> deviceOptional = deviceRepository.findById(Integer.parseInt(requestBody.getDeviceId()));
        final Device device = deviceOptional.orElseThrow(() -> new DataNotFoundException(ConstantCommon.DEVICE_NOT_FOUND));
        final Ntp ntp = device.getNtp();
        final List<NtpServer> ntpServers = new ArrayList<>();
        NtpServer ntpServer = ntpServerMapper.mapToNtpServer(requestBody.getClockName(), requestBody.getIpAddress());
        ntpServers.add(ntpServer);
        ntp.setNtpServers(ntpServers);

        ntpServerRepository.save(ntpServer);
        ntpRepository.save(ntp);
    }

    /**
     * This is a method of updating ntp server
     * @param id int
     * @param requestBody NtpServerUpdateRequestBody
     */
    @Override
    public void update(final int id, final NtpServerUpdateRequestBody requestBody) {
        final Optional<NtpServer> ntpServerOptional = ntpServerRepository.findById(id);
        final NtpServer ntpServer = ntpServerOptional.orElseThrow(() -> new DataNotFoundException(ConstantCommon.NTP_SERVER_NOT_FOUND));

        if (ntpServerRepository.existsByIpAddress(requestBody.getIpAddress())) {
            throw new DuplicateException(String.format(ConstantCommon.DUPLICATE_IP_ADDRESS, requestBody.getIpAddress()));
        }

        ntpServer.setClockName(requestBody.getClockName());
        ntpServer.setIpAddress(requestBody.getIpAddress());

        ntpServerRepository.save(ntpServer);
    }
}
