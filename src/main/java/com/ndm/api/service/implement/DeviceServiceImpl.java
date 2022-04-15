package com.ndm.api.service.implement;

import com.ndm.api.common.Command;
import com.ndm.api.common.ConstantCommon;
import com.ndm.api.dto.DtoMapper;
import com.ndm.api.dto.device.DeviceAddRequestBody;
import com.ndm.api.dto.device.DeviceResponse;
import com.ndm.api.entity.Credential;
import com.ndm.api.entity.Device;
import com.ndm.api.entity.NtpClient;
import com.ndm.api.entity.State;
import com.ndm.api.exception.DataNotFoundException;
import com.ndm.api.exception.DuplicateException;
import com.ndm.api.repository.CredentialRepository;
import com.ndm.api.repository.DeviceRepository;
import com.ndm.api.repository.NtpRepository;
import com.ndm.api.service.DeviceService;
import com.ndm.api.sshconnection.SshConnector;
import com.ndm.api.sshconnection.SshResponse;
import com.ndm.api.sshconnection.SshUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

/**
 * A class define device service implement
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final CredentialRepository credentialRepository;
    private final NtpRepository ntpRepository;
    private final DtoMapper mapper;

    @Autowired
    public DeviceServiceImpl(final DeviceRepository deviceRepository, final CredentialRepository credentialRepository, final NtpRepository ntpRepository, final DtoMapper mapper) {
        this.deviceRepository = deviceRepository;
        this.credentialRepository = credentialRepository;
        this.ntpRepository = ntpRepository;
        this.mapper = mapper;
    }

    /**
     * This method get all
     * @return List<DeviceResponse>
     */
    @Override
    public List<DeviceResponse> getAll() {
        final List<Device> devices = deviceRepository.findAll();
        return mapper.mapToDeviceResponseList(devices);
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
        return mapper.mapToDeviceResponse(device);
    }

    /**
     * This method to get device by ip address
     * @param ipAddress String
     * @return DeviceResponse
     */
    @Override
    public DeviceResponse getByIpAddress(final String ipAddress) {
        final Device device = deviceRepository.getByIpAddress(ipAddress);
        return mapper.mapToDeviceResponse(device);
    }

    /**
     * This method to get device list by type
     * @param type int
     * @return List<Device>
     */
    @Override
    public List<DeviceResponse> getByType(final int type) {
        final List<Device> devices = deviceRepository.getByType(type);
        return mapper.mapToDeviceResponseList(devices);
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
        final Device newDevice = mapper.mapToDevice(requestBody.getLabel(), requestBody.getIpAddress(), Integer.parseInt(requestBody.getPort()));
        final NtpClient newNtpClient = new NtpClient();
        newDevice.setNtpClient(newNtpClient);
        final List<Device> devices = new ArrayList<>();
        devices.add(newDevice);
        credential.setDevices(devices);

        deviceRepository.save(newDevice);
        ntpRepository.save(newNtpClient);
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

    @Override
    @Transactional
    public void managed(final int id) throws IOException {
        final Optional<Device> deviceOptional = deviceRepository.findById(id);
        final Device device = deviceOptional.orElseThrow(()-> new DataNotFoundException(ConstantCommon.DEVICE_NOT_FOUND));
        final Optional<Credential> credentialOptional = credentialRepository.findCredentialByDeviceId(device.getId());
        final Credential credential = credentialOptional.orElseThrow(() -> new DataNotFoundException(ConstantCommon.CREDENTIAL_NOT_FOUND));
        final SshConnector connector = new SshConnector(credential.getUsername(), device.getIpAddress(), credential.getPassword(), device.getPort());
        if (device.isOperational() == State.DISABLED.isState()) {
            final SshResponse sshResponse = SshUtils.runCommand(connector, Command.SESSION_WRITE_LOCK, ConstantCommon.TIMEOUT);
            device.setOperational(State.ENABLED.isState());
            deviceRepository.save(device);
        }
    }

    @Override
    @Transactional
    public void unmanaged(final int id) {
        final Optional<Device> deviceOptional = deviceRepository.findById(id);
        final Device device = deviceOptional.orElseThrow(()-> new DataNotFoundException(ConstantCommon.DEVICE_NOT_FOUND));
        if (device.isOperational() == State.ENABLED.isState()) {
            device.setOperational(State.DISABLED.isState());
            deviceRepository.save(device);
        }
    }
}
