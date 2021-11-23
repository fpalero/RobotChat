package com.chat.robot.chatrobot.robot.listeners;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReadableListener implements IServerListener{

  private static int BUFF_SIZE = 1024;

  @Override public boolean checkEvent(SelectionKey key) {
    return key.isReadable();
  }

  @Override public void apply(SelectionKey key, Selector selector) throws IOException {
    SocketChannel channel = (SocketChannel) key.channel();

    ByteBuffer buffer = ByteBuffer.allocate(BUFF_SIZE);
    int numRead = channel.read(buffer);
    if (numRead == -1) {
      log.info("Close Client Connection: " + channel.socket().getRemoteSocketAddress());
      channel.close();
      return;
    }
    String msg = new String(buffer.array()).trim();
    log.info("Got: " + msg);

    // Reply Client
    String reMsg = "Hi !!!";
    channel.write(ByteBuffer.wrap(reMsg.getBytes()));
  }
}
