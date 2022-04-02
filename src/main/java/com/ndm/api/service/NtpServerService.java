package com.ndm.api.service;

import com.ndm.api.dto.ntpserver.NtpServerAddRequestBody;
import com.ndm.api.dto.ntpserver.NtpServerUpdateRequestBody;

public interface NtpServerService {
    void add(final NtpServerAddRequestBody requestBody);
    void update(final int id, final NtpServerUpdateRequestBody requestBody);
}
