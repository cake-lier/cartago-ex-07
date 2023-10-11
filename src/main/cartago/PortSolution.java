import cartago.*;

import java.io.IOException;
import java.net.SocketException;
import java.util.Optional;

public class PortSolution extends Artifact {
    Socket socket;
    boolean isReceiving;

    void init(final int port) {
        this.isReceiving = false;
        try {
            this.socket = new Socket(port);
        } catch (final SocketException e) {
            failed(e.getMessage());
        }
    }

    @OPERATION
    void send(final String message, final int port) {
        try {
            this.socket.send(message, port);
        } catch (final IOException e) {
            failed(e.getMessage());
        }
    }

    @OPERATION
    void receive(final OpFeedbackParam<String> content, final OpFeedbackParam<String> sender) {
        final var receiveCommand = new ReceiveCommand();
        await(receiveCommand);
        receiveCommand.getMessage().ifPresentOrElse(
            m -> {
                content.set(m.content());
                sender.set(m.sender());
            },
            () -> {
                content.set("");
                sender.set("");
            }
        );
    }

    @OPERATION
    void startReceiving() {
        if (!this.isReceiving) {
            this.isReceiving = true;
            execInternalOp("receiving");
        }
    }

    @OPERATION
    void stopReceiving() {
        this.isReceiving = false;
    }

    @INTERNAL_OPERATION
    void receiving() {
        while (this.isReceiving) {
            final var receiveCommand = new ReceiveCommand();
            await(receiveCommand);
            receiveCommand.getMessage().ifPresent(m -> signal("new_message", m.content(), m.sender()));
        }
    }

    private class ReceiveCommand implements IBlockingCmd {
        private Optional<Socket.Message> message = Optional.empty();

        @Override
        public void exec() {
            try {
                this.message = Optional.of(PortSolution.this.socket.receive());
            } catch (final IOException ignored) {}
        }

        public Optional<Socket.Message> getMessage() {
            return this.message;
        }
    }
}
