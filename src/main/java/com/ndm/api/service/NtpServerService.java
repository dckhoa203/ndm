package com.ndm.api.service;

import com.ndm.api.dto.ntpserver.NtpServerAddRequestBody;
import com.ndm.api.dto.ntpserver.NtpServerResponse;
import com.ndm.api.dto.ntpserver.NtpServerUpdateRequestBody;

import java.util.List;

public interface NtpServerService {
    List<NtpServerResponse> getAll(final int deviceId);
    void add(final NtpServerAddRequestBody requestBody);
    void update(final int id, final String clocName, final NtpServerUpdateRequestBody requestBody);
}
