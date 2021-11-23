package com.chat.robot.chatrobot.robot.listeners;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import org.springframework.stereotype.Component;

@Component
public class WritableListener implements IServerListener {
  private static int BUFF_SIZE = 1024;

  @Override public boolean checkEvent(SelectionKey key) {
    return key.isWritable();
  }

  @Override public void apply(SelectionKey key, Selector selector) throws Exception {
    ByteBuffer byteBuffer = ByteBuffer.allocate(BUFF_SIZE);
    byteBuffer.flip();
    SocketChannel clientChannel = (SocketChannel) key.channel();
    while (byteBuffer.hasRemaining()) {
      clientChannel.write(byteBuffer);
    }
    byteBuffer.compact();
  }
}
