package com.ndm.api.dto.device;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A class define DeviceListResponse
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceResponse {
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
