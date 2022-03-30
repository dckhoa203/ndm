package com.ndm.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DevicesResponse {

    private List<DeviceResponse> results;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class DeviceResponse {
        private Integer id;
        private String name;
        private String label;
        private String serial;
        private String ipAddress;
        private String version;
        private boolean isOperational;
        private boolean isResync;
        private String updatedAt;
        private int type;
    }
}
