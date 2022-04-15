package com.ndm.api.sshconnection;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SshConnector {
    private String username;
    private String hostname;
    private String password;
    private int port;

    public SshConnector(final String username, final String hostname, final String password) {
        this.username = username;
        this.hostname = hostname;
        this.password = password;
        this.port = 22;
    }
}
