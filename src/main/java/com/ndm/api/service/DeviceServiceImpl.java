package com.ndm.api.service;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.dto.device.DeviceAddRequestBody;
import com.ndm.api.dto.device.DeviceMapper;
import com.ndm.api.dto.device.DeviceResponse;
import com.ndm.api.entity.Credential;
import com.ndm.api.entity.Device;
import com.ndm.api.entity.Ntp;
import com.ndm.api.exception.DataNotFoundException;
import com.ndm.api.exception.DuplicateException;
import com.ndm.api.repository.CredentialRepository;
import com.ndm.api.repository.DeviceRepository;
import com.ndm.api.repository.NtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * A class define device service implement
 */
@Service
public class DeviceServiceImpl implements DeviceService{

    private final DeviceRepository deviceRepository;
    private final CredentialRepository credentialRepository;
    private final NtpRepository ntpRepository;
    private final DeviceMapper deviceMapper;

    @Autowired
    public DeviceServiceImpl(final DeviceRepository deviceRepository, final CredentialRepository credentialRepository, final NtpRepository ntpRepository, final DeviceMapper deviceMapper) {
        this.deviceRepository = deviceRepository;
        this.credentialRepository = credentialRepository;
        this.ntpRepository = ntpRepository;
        this.deviceMapper = deviceMapper;
    }

    /**
     * This method get all
     * @return List<DeviceResponse>
     */
    @Override
    public List<DeviceResponse> getAll() {
        final List<Device> devices = deviceRepository.findAll();
        return deviceMapper.mapToDeviceResponseList(devices);
    }

    /**
     * This method to find device by id
     * @param id int
     * @return DeviceResponse
     */
    @Override
    public DeviceResponse findById(final int id) {
        final Optional<Device> deviceOptional = deviceRepository.findById(id);
        final Device device = deviceOptional.orElseThrow(() -> new DataNotFoundException((ConstantCommon.DEVICE_NOT_FOUND)));
        return deviceMapper.mapToDeviceResponse(device);
    }

    /**
     * This method to get device by ip address
     * @param ipAddress String
     * @return DeviceResponse
     */
    @Override
    public DeviceResponse getByIpAddress(final String ipAddress) {
        final Device device = deviceRepository.getByIpAddress(ipAddress);
        return deviceMapper.mapToDeviceResponse(device);
    }

    /**
     * This method to get device list by type
     * @param type int
     * @return List<Device>
     */
    @Override
    public List<DeviceResponse> getByType(final int type) {
        final List<Device> devices = deviceRepository.getByType(type);
        return deviceMapper.mapToDeviceResponseList(devices);
    }

    /**
     * This method to add new device
     * @param requestBody DeviceAddRequestBody
     */
    @Override
    @Transactional
    public void add(final DeviceAddRequestBody requestBody) {
        if (deviceRepository.existsByIpAddress(requestBody.getIpAddress())) {
            throw new DuplicateException(String.format(ConstantCommon.DUPLICATE_IP_ADDRESS, requestBody.getIpAddress()));
        }
        final Optional<Credential> credentialOptional = credentialRepository.findById(Integer.parseInt(requestBody.getCredentialId()));
        final Credential credential = credentialOptional.orElseThrow(() -> new DataNotFoundException(ConstantCommon.CREDENTIAL_NOT_FOUND));
        final Device newDevice = deviceMapper.mapToDevice(requestBody.getLabel(), requestBody.getIpAddress(), Integer.parseInt(requestBody.getPort()));
        final Ntp newNtp = new Ntp();
        newDevice.setNtp(newNtp);
        final List<Device> devices = new ArrayList<>();
        devices.add(newDevice);
        credential.setDevices(devices);

        deviceRepository.save(newDevice);
        ntpRepository.save(newNtp);
        credentialRepository.save(credential);
    }

    /**
     * This method to remove a device
     * @param id int
     */
    @Override
    @Transactional
    public void delete(final int id) {
        final Optional<Device> deviceOptional = deviceRepository.findById(id);
        final Device device = deviceOptional.orElseThrow(()-> new DataNotFoundException(ConstantCommon.DEVICE_NOT_FOUND));
        deviceRepository.delete(device);
    }
}
