package com.ndm.api.dto.intefaces;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InterfaceResponse {
    private String name;
    private boolean state;
    private boolean dhcp;
    private String ipAddress;
    private String netmask;
    private String gateway;
    private String info;
}
