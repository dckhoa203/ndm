package com.ndm.api.service;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.dto.device.DeviceAddRequestBody;
import com.ndm.api.dto.device.DeviceMapper;
import com.ndm.api.entity.Credential;
import com.ndm.api.entity.Device;
import com.ndm.api.exception.DataNotFoundException;
import com.ndm.api.repository.CredentialRepository;
import com.ndm.api.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * A class define device service implement
 */
@Service
public class DeviceServiceImpl implements DeviceService{

    private final DeviceRepository deviceRepository;
    private final CredentialRepository credentialRepository;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    public DeviceServiceImpl(final DeviceRepository deviceRepository, final CredentialRepository credentialRepository) {
        this.deviceRepository = deviceRepository;
        this.credentialRepository = credentialRepository;
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
        final Device device = deviceMapper.mapToDevice(requestBody);
        final Optional<Credential> credentialOptional = credentialRepository.findById(device.getId());
        final Credential credential = credentialOptional.orElseThrow(() -> new DataNotFoundException(ConstantCommon.CREDENTIAL_NOT_FOUND));
        final Set<Device> devices = new HashSet<>();
        devices.add(device);
        credential.setDevices(devices);
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
