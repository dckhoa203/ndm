package com.ndm.api.dto.ntpserver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NtpServerResponse {
    private String clockName;
    private String ipAddress;
    private boolean state;
}
