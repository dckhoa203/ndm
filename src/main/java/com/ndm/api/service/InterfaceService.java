package com.ndm.api.service;

import com.ndm.api.dto.intefaces.InterfaceAddRequestBody;
import com.ndm.api.dto.intefaces.InterfaceUpdateRequestBody;
import com.ndm.api.entity.Interface;

import java.util.List;

public interface InterfaceService {
    List<Interface> getAllByDeviceId(final int deviceId);
    void addToDevice(final InterfaceAddRequestBody requestBody);
    void update(final int id, final InterfaceUpdateRequestBody requestBody);
    void delete(final int id);
}
