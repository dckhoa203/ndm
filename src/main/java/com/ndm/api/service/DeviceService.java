package com.ndm.api.service;

import com.ndm.api.dto.device.DeviceAddRequestBody;
import com.ndm.api.dto.device.DeviceResponse;

import java.util.List;

public interface DeviceService {
    List<DeviceResponse> getAll();
    DeviceResponse findById(final int id);
    DeviceResponse getByIpAddress(final String ipAddress);
    List<DeviceResponse> getByType(final int type);
    void add(final DeviceAddRequestBody requestBody);
    void delete(final int id);
}
