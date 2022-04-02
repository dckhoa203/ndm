package com.ndm.api.service;

import com.ndm.api.entity.Ntp;

public interface NtpService {
    Ntp getByDeviceId(final int id);
}
