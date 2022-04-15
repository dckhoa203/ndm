package com.ndm.api.service;

import com.jcraft.jsch.JSchException;
import com.ndm.api.dto.device.DeviceAddRequestBody;
import com.ndm.api.dto.device.DeviceResponse;

import java.io.IOException;
import java.util.List;

public interface DeviceService {
    List<DeviceResponse> getAll();
    DeviceResponse findById(final int id);
    DeviceResponse getByIpAddress(final String ipAddress);
    List<DeviceResponse> getByType(final int type);
    void add(final DeviceAddRequestBody requestBody);
    void delete(final int id);
    void managed(final int id) throws IOException;
    void unmanaged(final int id);
}
