package com.ndm.api.service;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.dto.device.DeviceAddRequestBody;
import com.ndm.api.dto.device.DeviceMapper;
import com.ndm.api.entity.Credential;
import com.ndm.api.entity.Device;
import com.ndm.api.entity.Ntp;
import com.ndm.api.exception.DataNotFoundException;
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
@Transactional
public class DeviceServiceImpl implements DeviceService{

    private final DeviceRepository deviceRepository;
    private final CredentialRepository credentialRepository;
    private final NtpRepository ntpRepository;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    public DeviceServiceImpl(final DeviceRepository deviceRepository, final CredentialRepository credentialRepository, final NtpRepository ntpRepository) {
        this.deviceRepository = deviceRepository;
        this.credentialRepository = credentialRepository;
        this.ntpRepository = ntpRepository;
    }

    @Override
    public List<Device> getAll() {
        return deviceRepository.findAll();
    }

    /**
     * This method to find device by id
     * @param id int
     * @return Device
     */
    @Override
    public Device findById(final int id) {
        final Optional<Device> deviceOptional = deviceRepository.findById(id);
        return deviceOptional.orElseThrow(() -> new DataNotFoundException((ConstantCommon.DEVICE_NOT_FOUND)));
    }

    /**
     * This method to get device by ip address
     * @param ipAddress String
     * @return Device
     */
    @Override
    public Device getByIpAddress(final String ipAddress) {
        return deviceRepository.getByIpAddress(ipAddress);
    }

    /**
     * This method to get device list by type
     * @param type int
     * @return List<Device>
     */
    @Override
    public List<Device> getByType(final int type) {
        return deviceRepository.getByType(type);
    }

    /**
     * This method to add new device
     * @param requestBody DeviceAddRequestBody
     */
    @Override
    public void add(final DeviceAddRequestBody requestBody) {
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
    public void delete(final int id) {
        final Optional<Device> deviceOptional = deviceRepository.findById(id);
        final Device device = deviceOptional.orElseThrow(()-> new DataNotFoundException(ConstantCommon.DEVICE_NOT_FOUND));
        deviceRepository.delete(device);
    }
}
