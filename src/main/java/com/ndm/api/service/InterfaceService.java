package com.ndm.api.service;

import com.ndm.api.dto.intefaces.InterfaceAddRequestBody;
import com.ndm.api.dto.intefaces.InterfaceResponse;
import com.ndm.api.dto.intefaces.InterfaceUpdateRequestBody;

import java.util.List;

public interface InterfaceService {
    List<InterfaceResponse> getAll(final int deviceId);
    void add(final InterfaceAddRequestBody requestBody);
    void update(final int id, final InterfaceUpdateRequestBody requestBody);
    void delete(final int id);
}
