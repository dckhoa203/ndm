package com.ndm.api.service;

import com.ndm.api.entity.Port;

import java.util.List;

public interface PortService {
    List<Port> getAllByDeviceId(final int deviceId);
    void delete(final int id);
}
