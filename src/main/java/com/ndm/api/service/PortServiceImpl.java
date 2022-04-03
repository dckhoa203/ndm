package com.ndm.api.service;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.dto.port.PortAddRequestBody;
import com.ndm.api.dto.port.PortMapper;
import com.ndm.api.entity.Device;
import com.ndm.api.entity.Port;
import com.ndm.api.exception.DataNotFoundException;
import com.ndm.api.repository.DeviceRepository;
import com.ndm.api.repository.PortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PortServiceImpl implements PortService {
    private final DeviceRepository deviceRepository;
    private final PortRepository portRepository;
    private final PortMapper portMapper;

    @Autowired
    public PortServiceImpl(final DeviceRepository deviceRepository, final PortRepository portRepository, final PortMapper portMapper) {
        this.deviceRepository = deviceRepository;
        this.portRepository = portRepository;
        this.portMapper = portMapper;
    }

    @Override
    public List<Port> getAllByDeviceId(final int deviceId) {
        final Optional<Device> deviceOptional = deviceRepository.findById(deviceId);
        final Device device = deviceOptional.orElseThrow(() -> new DataNotFoundException(ConstantCommon.DEVICE_NOT_FOUND));
        return device.getPorts();
    }

    @Override
    public void addToDevice(final PortAddRequestBody requestBody) {
        final Port port = portMapper.mapToPort(requestBody);
        final Optional<Device> deviceOptional = deviceRepository.findById(Integer.parseInt(requestBody.getDeviceId()));
        final Device device = deviceOptional.orElseThrow(() -> new DataNotFoundException(ConstantCommon.DEVICE_NOT_FOUND));
        final List<Port> ports = new ArrayList<>();
        ports.add(port);
        device.setPorts(ports);

        portRepository.save(port);
        deviceRepository.save(device);
    }

    @Override
    public void delete(final int id) {
        final Optional<Port> portOptional = portRepository.findById(id);
        final Port port = portOptional.orElseThrow(() -> new DataNotFoundException(ConstantCommon.PORT_NOT_FOUND));
        portRepository.delete(port);
    }
}
