package com.ndm.api.service;

import com.ndm.api.dto.ntpclient.NtpResponse;

public interface NtpService {
    NtpResponse getByDeviceId(final int id);
}
