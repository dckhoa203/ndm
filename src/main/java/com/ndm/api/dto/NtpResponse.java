package com.ndm.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class NtpResponse {
    private int id;
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
        private Integer id;
        private String clockName;
        private String ipAddress;
        private boolean state;
    }
}
