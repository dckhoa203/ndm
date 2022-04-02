package com.ndm.api.service;

import com.ndm.api.entity.Interface;

import java.util.List;

public interface InterfaceService {
    List<Interface> getAllByDeviceId(final int deviceId);
    void delete(final int id);
}
