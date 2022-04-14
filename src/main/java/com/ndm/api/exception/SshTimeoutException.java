package com.ndm.api.exception;

import com.ndm.api.common.ConstantCommon;

public class SshTimeoutException extends RuntimeException {
    public SshTimeoutException(final String command, final String host, final int timeout) {
        super(String.format(ConstantCommon.SSH_TIMEOUT_MESSAGE, command, host, timeout));
    }
}
