package com.chat.robot.chatrobot.robot.listeners;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AcceptableListner implements IServerListener{
  @Override public boolean checkEvent(SelectionKey key) {
    return key.isAcceptable();
  }

  @Override
  public void apply(SelectionKey key, Selector selector) throws Exception {
    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
    SocketChannel channel = serverChannel.accept();
    channel.configureBlocking(false);
    channel.register(selector, SelectionKey.OP_READ);

    Socket socket = channel.socket();
    SocketAddress remoteAddr = socket.getRemoteSocketAddress();
    log.info("connection to: " + remoteAddr);
  }
}
