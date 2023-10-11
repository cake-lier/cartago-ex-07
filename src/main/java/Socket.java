import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

/** Implements a UDP socket which allows to connect to another similar socket on the local machine. */
public class Socket {
    private static final int PACKET_SIZE = 1024;

    private final DatagramSocket datagramSocket;

    /** Creates a new UDP socket using "localhost" as interface.
     *
     * @param port the port on which opening the socket
     * @throws SocketException if the socket could not be opened
     */
    public Socket(final int port) throws SocketException {
        this.datagramSocket = new DatagramSocket(port);
    }

    /** Allows sending a message through the opened socket to the socket on the local machine for which the port is given.
     *
     * @param message the message to be sent through the socket
     * @param port the port of the localhost socket to which sending the message
     * @throws IOException if the message could not be sent
     */
    public void send(final String message, final int port) throws IOException {
        this.datagramSocket.send(new DatagramPacket(
            message.getBytes(StandardCharsets.UTF_8),
            message.length(),
            new InetSocketAddress("localhost", port)
        ));
    }

    /** Blocking operation waiting for a new message to be received on the socket.
     *
     * @return a new {@link Message} containing the body of the message and the hostname of the sender
     * @throws IOException if the message could not be received
     */
    public Message receive() throws IOException {
        final var packet = new DatagramPacket(new byte[PACKET_SIZE], PACKET_SIZE);
        this.datagramSocket.receive(packet);
        return new Message(new String(packet.getData()), packet.getAddress().getHostName());
    }

    /** A message exchanged between sockets, made of its content and its sender. */
    public record Message(String content, String sender) {}
}
