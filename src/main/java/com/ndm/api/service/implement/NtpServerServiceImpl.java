package com.ndm.api.service.implement;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.dto.DtoMapper;
import com.ndm.api.dto.ntpserver.NtpServerAddRequestBody;
import com.ndm.api.dto.ntpserver.NtpServerResponse;
import com.ndm.api.dto.ntpserver.NtpServerUpdateRequestBody;
import com.ndm.api.entity.Device;
import com.ndm.api.entity.NtpServer;
import com.ndm.api.exception.DataNotFoundException;
import com.ndm.api.exception.DuplicateException;
import com.ndm.api.repository.DeviceRepository;
import com.ndm.api.repository.NtpServerRepository;
import com.ndm.api.service.NtpServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * This class NtpServerServiceImpl
 */
@Service
public class NtpServerServiceImpl implements NtpServerService {
    private final NtpServerRepository ntpServerRepository;
    private final DeviceRepository deviceRepository;
    private final DtoMapper mapper;

    @Autowired
    public NtpServerServiceImpl(final NtpServerRepository ntpServerRepository, final DeviceRepository deviceRepository, final DtoMapper mapper) {
        this.ntpServerRepository = ntpServerRepository;
        this.deviceRepository = deviceRepository;
        this.mapper = mapper;
    }

    /**
     * This method of get all ntp server
     * @param deviceId int
     * @return List<NtpServerResponse>
     */
    @Override
    public List<NtpServerResponse> getAll(final int deviceId) {
        final Optional<Device> deviceOptional = deviceRepository.findById(deviceId);
        final Device device = deviceOptional.orElseThrow(() -> new DataNotFoundException(ConstantCommon.DEVICE_NOT_FOUND));
        return mapper.mapToNtpServerResponseList(device.getNtpServers());
    }

    /**
     * This method of adding ntp server
     * @param requestBody NtpServerAddRequestBody
     */
    @Override
    @Transactional
    public void add(final NtpServerAddRequestBody requestBody) {
        final Optional<Device> deviceOptional = deviceRepository.findById(Integer.parseInt(requestBody.getDeviceId()));
        final Device device = deviceOptional.orElseThrow(() -> new DataNotFoundException(ConstantCommon.DEVICE_NOT_FOUND));
        final List<NtpServer> ntpServers = device.getNtpServers();
        final NtpServer newNtpServer = ntpServerRepository.existsByIpAddress(requestBody.getIpAddress()) ? ntpServerRepository.getByIpAddress(requestBody.getIpAddress()) : mapper.mapToNtpServer(requestBody.getClockName(), requestBody.getIpAddress());
        if (ntpServers.stream().anyMatch(ntpServer -> Objects.equals(newNtpServer.getId(), ntpServer.getId()))) {
            throw new DuplicateException(String.format(ConstantCommon.DUPLICATE_IP_ADDRESS, newNtpServer.getIpAddress()));
        }
        ntpServers.add(newNtpServer);
        device.setNtpServers(ntpServers);
        ntpServerRepository.save(newNtpServer);
        deviceRepository.save(device);
    }

    /**
     * This is a method of updating ntp server
     * @param deviceId int
     * @param clockName String
     * @param requestBody NtpServerUpdateRequestBody
     */
    @Override
    @Transactional
    public void update(final int deviceId, final String clockName, final NtpServerUpdateRequestBody requestBody) {
        final Optional<Device> deviceOptional = deviceRepository.findById(deviceId);
        final Device device = deviceOptional.orElseThrow(() -> new DataNotFoundException(ConstantCommon.DEVICE_NOT_FOUND));
        final List<NtpServer> ntpServers = device.getNtpServers();
        if (ntpServers.stream().anyMatch(ntpServer -> !clockName.equals(ntpServer.getClockName()))) {
            throw new DataNotFoundException(ConstantCommon.NTP_SERVER_NOT_FOUND);
        }

        final NtpServer ntpServer = ntpServerRepository.getByClockName(clockName);

        if (!requestBody.taiOffsetIsEmpty()) {
            ntpServer.setTaiOffset(Integer.parseInt(requestBody.getTaiOffset()));
        }

        if (!requestBody.dscpIsEmpty()) {
            ntpServer.setDscp(Integer.parseInt(requestBody.getDscp()));
        }

        if (!requestBody.vlanPriorityIsEmpty()) {
            ntpServer.setVlanPriority(Integer.parseInt(requestBody.getVlanPriority()));
        }

        if (!requestBody.timeIntervalIsEmpty()) {
            ntpServer.setTimeInterval(Integer.parseInt(requestBody.getTimeInterval()));
        }

        if (!requestBody.numberMessagesIsEmpty()) {
            ntpServer.setNumberMessages(Integer.parseInt(requestBody.getNumberMessages()));
        }

        if (!requestBody.isState()) {
            ntpServer.setState(requestBody.isState());
        }

        ntpServerRepository.save(ntpServer);
    }
}
