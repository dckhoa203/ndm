package com.ndm.api.service;

import com.ndm.api.dto.ntp.NtpServerAddRequestBody;
import com.ndm.api.entity.Ntp;

public interface NtpService {
    Ntp getByDeviceId(final int id);
    void addNtpServerToDevice(final int deviceId, final NtpServerAddRequestBody requestBody);
}
