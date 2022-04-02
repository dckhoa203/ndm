package com.ndm.api.dto.port;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PortListResponse {

    private List<PortResponse> results;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class PortResponse {
        private String name;
        private String connector;
        private boolean state;
        private String speed;
        private String mtu;
        private String mdi;
        private String macAddress;
    }
}
