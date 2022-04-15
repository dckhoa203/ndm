package com.ndm.api.dto.port;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PortResponse {
    private String name;
    private String connector;
    private boolean state;
    private String speed;
    private String mtu;
    private String mdi;
    private String macAddress;
}
