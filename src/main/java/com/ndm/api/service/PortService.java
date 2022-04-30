package com.ndm.api.service;

import com.ndm.api.dto.port.PortAddRequestBody;
import com.ndm.api.dto.port.PortResponse;

import java.util.List;

public interface PortService {
    List<PortResponse> getAll(final int deviceId);
    void add(final PortAddRequestBody requestBody);
    void delete(final int id);
}
