package com.ndm.api.sshconnection;

import com.jcraft.jsch.*;
import com.ndm.api.common.ConstantCommon;
import com.ndm.api.entity.Credential;
import com.ndm.api.entity.Device;

import java.io.*;
import java.util.Objects;

public class SshConnector {
    private static final String STRICT_HOST_KEY_CHECK_IN_KEY = "StrictHostKeyChecking";
    private static final String STRICT_HOST_KEY_CHECK_IN_VALUE = "no";
    private static final String CHANNEL_TYPE = "exec";
    private static final int SIZE = 1024;
    private static final int MILLIS = 100;

    private final Credential credential;
    private final Device device;
    private Session session;
    private ChannelExec channel;
    private ByteArrayOutputStream responseStream;

    public SshConnector(final Credential credential, final Device device) {
        this.credential = credential;
        this.device = device;
    }

    public void open() throws JSchException {
        connectSession();
    }

    public String execute(final String command) throws JSchException, InterruptedException, IOException {
        if (!session.isConnected()) {
            open();
        }
        channel = createChannel(command);
        final String result = result();
        close();
        return result;
    }

    public void close() throws IOException {
        if (session.isConnected()) {
            session.disconnect();
        }
        if (channel.isConnected()) {
            channel.disconnect();
        }
        if (Objects.nonNull(responseStream)) {
            responseStream.close();
        }
    }

    public boolean isDisconnected() {
        return !session.isConnected() && !channel.isConnected();
    }

    private void connectSession() throws JSchException {
        final JSch jSch = new JSch();
        session = jSch.getSession(credential.getUsername(), device.getIpAddress(), device.getPort());
        session.setPassword(credential.getPassword());
        session.setTimeout(ConstantCommon.SECTION_TIMEOUT);
        session.setConfig(STRICT_HOST_KEY_CHECK_IN_KEY, STRICT_HOST_KEY_CHECK_IN_VALUE);
    }

    private ChannelExec createChannel(final String command) throws JSchException {
        final ChannelExec channel = (ChannelExec) session.openChannel(CHANNEL_TYPE);
        channel.setCommand(command);
        return channel;
    }

    private String result() throws JSchException, InterruptedException {
        responseStream = new ByteArrayOutputStream(SIZE);
        channel.setOutputStream(responseStream);
        channel.connect(ConstantCommon.CHANNEL_TIMEOUT);
        while (channel.isConnected()) {
            Thread.sleep(MILLIS);
        }
        return responseStream.toString();
    }
}
