package com.chat.robot.chatrobot.robot.listeners;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ServerListeners {
  List<IServerListener> listeners;

  public ServerListeners(List<IServerListener> listeners) {
    this.listeners = listeners;
  }

  public void applyListener(SelectionKey key, Selector selector) throws Exception {

    //Invalid direct skip
    if (!key.isValid()) {
      return;
    }

    // Look for the appropriate event listener
    for (IServerListener listener : this.listeners) {
      if(listener.checkEvent(key)) {
        listener.apply(key, selector);
        break;
      }
    }

  }
}
