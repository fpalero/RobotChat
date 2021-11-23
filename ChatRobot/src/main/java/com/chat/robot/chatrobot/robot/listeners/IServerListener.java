package com.chat.robot.chatrobot.robot.listeners;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public interface IServerListener {

  public boolean checkEvent(SelectionKey key);

  public void apply(SelectionKey key, Selector selector) throws Exception;
}
