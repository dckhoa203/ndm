package com.ndm.api.service;

import com.ndm.api.entity.Device;
//
import com.ndm.api.entity.Ntp;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NtpService {
    Ntp getByDeviceId(final int id);
}
