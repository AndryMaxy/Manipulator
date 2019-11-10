package by.akulich.manipulator.broadcast;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

@Slf4j
@Component
public class BroadcastingServer implements Runnable {

    private static final int PORT = 4445;
    private static final String RECEIVED = "manipulator";
    private static final byte[] RECEIVED_BYTES = RECEIVED.getBytes();
    private DatagramSocket socket;
    private boolean running = true;

    @PostConstruct
    public void init() throws Exception {
        socket = new DatagramSocket(new InetSocketAddress(PORT));
        socket.setReuseAddress(true);
    }

    @Override
    public void run() {
        while (running) {
            try {
                log.info("BroadcastingServer is running...");
                DatagramPacket packet = new DatagramPacket(RECEIVED_BYTES, RECEIVED_BYTES.length);
                socket.receive(packet);
                SocketAddress socketAddress = packet.getSocketAddress();
                String received = new String(packet.getData(), 0, packet.getLength());
                log.info("!!!!!!!!!! --> "  + received);
                if (received.equals("manipulator")) {
                    byte[] response = getResponse();
                    packet = new DatagramPacket(response, response.length, socketAddress);
                    socket.send(packet);
                }
            } catch (IOException e) {
                log.error("BroadcastingServer failed", e);
                running = false;
            }
        }
        closeSocket();
    }

    @PreDestroy
    public void closeSocket() {
        running = false;
        socket.close();
    }

    private byte[] getResponse() throws IOException {
        InetAddress address = InetAddress.getLocalHost();
        String hostName = address.getHostName();
        String ipAddress = address.getHostAddress();
        String response = String.format("name=%s&ip=%s", hostName, ipAddress);
        return response.getBytes();
    }
}
