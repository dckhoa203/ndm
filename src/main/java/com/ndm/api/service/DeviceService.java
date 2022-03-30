package com.ndm.api.service;

import com.ndm.api.entity.Device;

import java.util.List;

public interface DeviceService {
    List<Device> getAll();
    Device findById(final int id);
}
