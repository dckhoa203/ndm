package com.ndm.api.service;

import com.ndm.api.dto.device.DeviceAddRequestBody;
import com.ndm.api.entity.Device;

import java.util.List;

public interface DeviceService {
    List<Device> getAll();
    Device findById(final int id);
    Device getByIpAddress(final String ipAddress);
    List<Device> getByType(final int type);
    void add(final DeviceAddRequestBody requestBody);
    void delete(final int id);
}
