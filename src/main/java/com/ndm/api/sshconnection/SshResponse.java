package com.ndm.api.sshconnection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SshResponse {
    private String result;
    private String error;
    private int code;
}
