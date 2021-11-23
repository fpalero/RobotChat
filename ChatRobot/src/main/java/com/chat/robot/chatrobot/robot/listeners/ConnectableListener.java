package com.chat.robot.chatrobot.robot.listeners;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConnectableListener implements IServerListener {
  @Override public boolean checkEvent(SelectionKey key) {
    return key.isConnectable();
  }

  @Override public void apply(SelectionKey key, Selector selector) throws Exception {
    SocketChannel channel = (SocketChannel) key.channel();
    if (channel.finishConnect()) {
      // Success
      log.info("Successfully connected");
    } else {
      // fail
      log.info("Failed Connection");
    }
  }
}
