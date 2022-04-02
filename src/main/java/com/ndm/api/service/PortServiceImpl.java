package com.ndm.api.service;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.entity.Device;
import com.ndm.api.entity.Port;
import com.ndm.api.exception.DataNotFoundException;
import com.ndm.api.repository.DeviceRepository;
import com.ndm.api.repository.PortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PortServiceImpl implements PortService {
    private final DeviceRepository deviceRepository;
    private final PortRepository portRepository;

    @Autowired
    public PortServiceImpl(final DeviceRepository deviceRepository, final PortRepository portRepository) {
        this.deviceRepository = deviceRepository;
        this.portRepository = portRepository;
    }

    @Override
    public List<Port> getAllByDeviceId(final int deviceId) {
        final Optional<Device> deviceOptional = deviceRepository.findById(deviceId);
        final Device device = deviceOptional.orElseThrow(() -> new DataNotFoundException(ConstantCommon.DEVICE_NOT_FOUND));
        return device.getPorts();
    }

    @Override
    public void delete(final int id) {
        final Optional<Port> portOptional = portRepository.findById(id);
        final Port port = portOptional.orElseThrow(() -> new DataNotFoundException(ConstantCommon.PORT_NOT_FOUND));
        portRepository.delete(port);
    }
}
