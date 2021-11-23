package com.chat.robot.chatrobot.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TcpClient {
    private int BUFF_SIZE = 1024;
    private SocketChannel socketChannel;
    private String ip = "0.0.0.0";
    private Integer port = 10002;
    public TcpClient() throws IOException {

        InetSocketAddress socketAddress = new InetSocketAddress(ip, port);
        socketChannel = SocketChannel.open(socketAddress);

        log.info("Connect BIOServer Service, IP: %s Port: %s", ip, port);
    }

    public void closeConnection() throws IOException {
        socketChannel.close();
    }

    public String receiveMessage() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(BUFF_SIZE);
        buffer.clear();
        socketChannel.read(buffer);
        String result = new String(buffer.array()).trim();
        log.info("Received NIOServer Message to reply:" + result);

        return result;
    }

    public void sendMessage(String message) throws IOException {
        socketChannel.write(ByteBuffer.wrap(message.getBytes()));
        log.info("Send out: " + message);
    }
}
