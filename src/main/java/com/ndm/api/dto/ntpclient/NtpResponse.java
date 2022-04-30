package com.ndm.api.dto.ntpclient;

import com.ndm.api.dto.ntpserver.NtpServerResponse;
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
}
