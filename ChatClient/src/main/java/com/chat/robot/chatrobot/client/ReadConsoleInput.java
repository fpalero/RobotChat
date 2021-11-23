package com.chat.robot.chatrobot.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReadConsoleInput implements CommandLineRunner {

  private Client client;

  public ReadConsoleInput(Client client) {
    this.client = client;
  }

  @Override public void run(String... args) throws Exception {
    BufferedReader buffer = createInputBuffer();

    while (true) {
      System.out.print("Message: ");
      String messageSent = this.client.sendMessage(buffer);


      String messageReceived = this.client.receiveMesage();
      System.out.printf("Bot: %s\n", messageReceived);

    }
  }

  private BufferedReader createInputBuffer() {
    return new BufferedReader(
        new InputStreamReader(System.in));
  }
}
