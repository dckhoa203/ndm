package com.ndm.api.service;

import com.ndm.api.dto.port.PortAddRequestBody;
import com.ndm.api.entity.Port;

import java.util.List;

public interface PortService {
    List<Port> getAllByDeviceId(final int deviceId);
    void addToDevice(final PortAddRequestBody requestBody);
    void delete(final int id);
}
