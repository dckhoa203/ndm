package com.ndm.api.dto.intefaces;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InterfaceListResponse {
    private List<InterfaceResponse> results;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class InterfaceResponse {
        private String name;
        private boolean state;
        private boolean dhcp;
        private String ipAddress;
        private String netmask;
        private String gateway;
        private String info;
    }
}
