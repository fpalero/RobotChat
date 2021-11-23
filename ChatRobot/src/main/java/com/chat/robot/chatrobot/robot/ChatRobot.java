package com.chat.robot.chatrobot.robot;

import com.chat.robot.chatrobot.robot.listeners.ServerListeners;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChatRobot {
  private InetAddress addr = null;
  private int port = 10002;
  private Selector selector;
  private ServerListeners serverListeners;

  public ChatRobot(ServerListeners serverListeners) throws Exception {

    this.serverListeners = serverListeners;

    configureSocket();
    listenEvents();
  }

  private void listenEvents() throws Exception {
    while (true) {
      log.info("The server waits for new connections and selector Choose...");
      this.selector.select();

      // Select key job
      Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();
      while (keys.hasNext()) {
        SelectionKey key = keys.next();

        // Prevent duplicate keys and remove them when finished
        keys.remove();

        // Apply different behaviour depending on the event received
        this.serverListeners.applyListener(key, this.selector);
      }
    }
  }

  private void configureSocket() throws IOException {
    this.selector = Selector.open();
    ServerSocketChannel serverChannel = ServerSocketChannel.open();
    serverChannel.configureBlocking(false);

    // Binding Address and Port
    InetSocketAddress listenAddr = new InetSocketAddress(this.addr, this.port);
    serverChannel.socket().bind(listenAddr);
    serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);
  }

}
