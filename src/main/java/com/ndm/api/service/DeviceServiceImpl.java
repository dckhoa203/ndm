package com.ndm.api.service;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.dto.DeviceAddRequestBody;
import com.ndm.api.entity.Device;
import com.ndm.api.exception.DataNotFoundException;
import com.ndm.api.exception.DuplicateException;
import com.ndm.api.repository.DeviceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService{

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceServiceImpl(final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Device> getAll() {
        return deviceRepository.findAll();
    }

    @Override
    public Device findById(final int id) {
        final Optional<Device> deviceOptional = deviceRepository.findById(id);
        return deviceOptional.orElseThrow(() -> new DataNotFoundException((ConstantCommon.DEVICE_NOT_FOUND)));
    }

    @Override
    public Device getByIpAddress(final String ipAddress) {
        return deviceRepository.getByIpAddress(ipAddress);
    }

    @Override
    public List<Device> getByType(final int type) {
        return deviceRepository.getByType(type);
    }

    @Override
    public void add(final DeviceAddRequestBody requestBody) {
        final Device device = toDevice(requestBody);
        if (deviceRepository.existsByIpAddress(requestBody.getIpAddress())) {
            throw new DuplicateException(String.format(ConstantCommon.DUPLICATE_IP_ADDRESS, requestBody.getIpAddress()));
        }
        deviceRepository.save(device);
    }

    @Override
    public void delete(final int id) {
        final Optional<Device> deviceOptional = deviceRepository.findById(id);
        final Device device = deviceOptional.orElseThrow(()-> new DataNotFoundException(ConstantCommon.DEVICE_NOT_FOUND));
        deviceRepository.delete(device);
    }

    private Device toDevice(final DeviceAddRequestBody requestBody) {
        if (ObjectUtils.isEmpty(requestBody)) {
            return new Device();
        }
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(requestBody, Device.class);
    }
}
