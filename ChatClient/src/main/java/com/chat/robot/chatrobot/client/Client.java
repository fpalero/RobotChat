package com.chat.robot.chatrobot.client;


import java.io.BufferedReader;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class Client {

  TcpClient tcpClient;

  public Client(TcpClient tcpClient) {
    this.tcpClient = tcpClient;
  }

  public String sendMessage(BufferedReader reader) throws IOException {
    // Enter data using BufferReader

    // Reading data using readLine
    String message = reader.readLine();

    this.tcpClient.sendMessage(message);

    // Printing the read line
    return message;
  }

  public String receiveMesage() throws IOException {
    return tcpClient.receiveMessage();
  }

  public void endConnection() throws IOException {
    this.tcpClient.closeConnection();
  }

}
