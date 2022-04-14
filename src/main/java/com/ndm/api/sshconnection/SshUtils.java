package com.ndm.api.sshconnection;

import com.ndm.api.exception.SshTimeoutException;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ChannelExec;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.future.ConnectFuture;
import org.apache.sshd.client.session.ClientSession;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SshUtils {
    private SshUtils() {

    }

    public static SshResponse runCommand(final SshConnector connector, final String command, final int timeout) throws IOException {
        final SshClient client = SshClient.setUpDefaultClient();
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final ByteArrayOutputStream error = new ByteArrayOutputStream();

        try {
            client.start();

            final ClientSession session = connectServer(client, connector, timeout);

            final ChannelExec channelExec = createChannel(session, command, output, error);

            final Set<ClientChannelEvent> events = execute(session,channelExec, timeout);

            if (events.contains(ClientChannelEvent.TIMEOUT)) {
                throw new SshTimeoutException(output.toString(), error.toString(), channelExec.getExitStatus());
            }

            return new SshResponse(output.toString(), error.toString(), channelExec.getExitStatus());
        } finally {
            client.stop();
        }
    }

    private static ClientSession connectServer(final SshClient client, final SshConnector connector, final int timeout) throws IOException {
        final ConnectFuture connectFuture = client.connect(connector.getUsername(), connector.getHostname(), connector.getPort());
        final ClientSession session = connectFuture.verify().getClientSession();
        session.addPasswordIdentity(connector.getPassword());
        session.auth().verify(TimeUnit.SECONDS.toMillis(timeout));
        return session;
    }

    private static ChannelExec createChannel(final ClientSession session, final String command, final ByteArrayOutputStream output, final ByteArrayOutputStream error) throws IOException {
        final ChannelExec channelExec = session.createExecChannel(command);
        channelExec.setOut(output);
        channelExec.setErr(error);
        return channelExec;
    }

    private static Set<ClientChannelEvent> execute(final ClientSession session, final ChannelExec channelExec, final int timeout) throws IOException {
        channelExec.open();
        final Set<ClientChannelEvent> events = channelExec.waitFor(EnumSet.of(ClientChannelEvent.CLOSED), TimeUnit.SECONDS.toMillis(timeout));
        session.close();
        return events;
    }
}

