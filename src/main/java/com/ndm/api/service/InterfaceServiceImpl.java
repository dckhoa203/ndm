package com.ndm.api.service;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.dto.intefaces.InterfaceAddRequestBody;
import com.ndm.api.dto.intefaces.InterfaceMapper;
import com.ndm.api.entity.Device;
import com.ndm.api.entity.Interface;
import com.ndm.api.exception.DataNotFoundException;
import com.ndm.api.repository.DeviceRepository;
import com.ndm.api.repository.InterfaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InterfaceServiceImpl implements InterfaceService {
    private final DeviceRepository deviceRepository;
    private final InterfaceRepository interfaceRepository;
    private final InterfaceMapper interfaceMapper;

    @Autowired
    public InterfaceServiceImpl(final DeviceRepository deviceRepository, final InterfaceRepository interfaceRepository, final InterfaceMapper interfaceMapper) {
        this.deviceRepository = deviceRepository;
        this.interfaceRepository = interfaceRepository;
        this.interfaceMapper = interfaceMapper;
    }

    @Override
    public List<Interface> getAllByDeviceId(final int deviceId) {
        final Optional<Device> deviceOptional = deviceRepository.findById(deviceId);
        final Device device = deviceOptional.orElseThrow(() -> new DataNotFoundException(ConstantCommon.DEVICE_NOT_FOUND));
        return device.getInterfaces();
    }

    @Override
    public void addToDevice(final InterfaceAddRequestBody requestBody) {
        final Interface anInterface = interfaceMapper.mapToInterface(requestBody);
        final Optional<Device> deviceOptional = deviceRepository.findById(Integer.parseInt(requestBody.getDeviceId()));
        final Device device = deviceOptional.orElseThrow(() -> new DataNotFoundException(ConstantCommon.DEVICE_NOT_FOUND));
        final List<Interface> interfaces = new ArrayList<>();
        interfaces.add(anInterface);
        device.setInterfaces(interfaces);

        interfaceRepository.save(anInterface);
        deviceRepository.save(device);
    }

    @Override
    public void delete(final int id) {
        final Optional<Interface> optionalInterface = interfaceRepository.findById(id);
        final Interface anInterface = optionalInterface.orElseThrow(() -> new DataNotFoundException(ConstantCommon.INTERFACE_NOT_FOUND));
        interfaceRepository.delete(anInterface);
    }
}
