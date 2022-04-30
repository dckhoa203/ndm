package com.ndm.api.service;

import com.ndm.api.dto.ntpclient.NtpResponse;

public interface NtpClientService {
    NtpResponse getByDeviceId(final int id);
}
