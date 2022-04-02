package com.ndm.api.dto.ntp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * A class define NtpResponse
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NtpResponse {
    private boolean ntpClient;
    private int dscp;
    private int vlanPriority;
    private boolean syncStatus;
    private int timeInterval;
    private int numberMessages;
    private List<NtpServerResponse> ntpServers;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class NtpServerResponse {
        private String clockName;
        private String ipAddress;
        private boolean state;
    }
}
