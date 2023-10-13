package io.github.cakelier;

import cartago.*;

import java.net.SocketException;

public class Port extends Artifact {
    private Socket socket;

    private void init(final int port) {
        try {
            this.socket = new Socket(port);
        } catch (final SocketException e) {
            failed(e.getMessage());
        }
    }

    @OPERATION
    public void send(final String message, final int port) {
        // Add your implementation for the "send" operation here
    }

    @OPERATION
    public void receive(final OpFeedbackParam<String> content, final OpFeedbackParam<String> sender) {
        // Add your implementation for the "receive" command here
    }

    @OPERATION
    public void startReceiving() {
        // Add your implementation for the "startReceiving" operation here
    }

    @OPERATION
    public void stopReceiving() {
        // Add your implementation for the "stopReceiving" operation here
    }

    @INTERNAL_OPERATION
    private void receiving() {
        // Add your implementation for the "receiving" internal operation here
    }

    private class ReceiveCommand implements IBlockingCmd {
        @Override
        public void exec() {
            // Add your implementation for the blocking command here
        }
    }
}
