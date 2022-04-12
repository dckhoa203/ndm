package com.ndm.api.sshconnection;

import com.jcraft.jsch.*;
import com.ndm.api.common.ConstantCommon;
import com.ndm.api.entity.Credential;
import com.ndm.api.entity.Device;

import java.io.*;

public class SshConnector {
    private static final String STRICT_HOST_KEY_CHECK_IN_KEY = "StrictHostKeyChecking";
    private static final String STRICT_HOST_KEY_CHECK_IN_VALUE = "no";
    private static final String CHANNEL_TYPE = "exec";

    private final Credential credential;
    private final Device device;
    private Session session;
    private ChannelExec channel;

    public SshConnector(final Credential credential, final Device device) {
        this.credential = credential;
        this.device = device;
    }

    public void open() throws JSchException {
        session = connectSession();
    }

    public String execute(final String command) throws JSchException, InterruptedException {
        channel = createChannel(command);
        return result();
    }

    public void close() {
        if (session.isConnected()) {
            session.disconnect();
        }
        if (channel.isConnected()) {
            channel.disconnect();
        }
    }

    private Session connectSession() throws JSchException {
        final JSch jSch = new JSch();
        final Session session = jSch.getSession(credential.getUsername(), device.getIpAddress(), device.getPort());
        session.setPassword(credential.getPassword());
        session.setTimeout(ConstantCommon.SECTION_TIMEOUT);
        session.setConfig(STRICT_HOST_KEY_CHECK_IN_KEY, STRICT_HOST_KEY_CHECK_IN_VALUE);
        return session;
    }

    private ChannelExec createChannel(final String command) throws JSchException {
        final ChannelExec channel = (ChannelExec) session.openChannel(CHANNEL_TYPE);
        channel.setCommand(command);
        return channel;
    }

    private String result() throws JSchException, InterruptedException {
        final ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
        channel.setOutputStream(responseStream);
        channel.connect(ConstantCommon.CHANNEL_TIMEOUT);
        while (channel.isConnected()) {
            Thread.sleep(100);
        }
        return responseStream.toString();
    }
}
