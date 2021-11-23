package com.chat.robot.chatrobot;

import com.chat.robot.chatrobot.client.Client;
import com.chat.robot.chatrobot.client.TcpClient;
import java.io.BufferedReader;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ClientTest {

  @Test
  @DisplayName("Sanity Test")
  public void test_00() {
    TcpClient tcpClient = Mockito.mock(TcpClient.class);
    Client client = new Client(tcpClient);

    Assertions.assertNotNull(client);
  }

  @Test
  @DisplayName("Test sendMessage method: expected return the user input")
  public void test_01() throws IOException {

    String message = "Hola";

    // Create instance
    TcpClient tcpClient = Mockito.mock(TcpClient.class);
    Client client = new Client(tcpClient);

    // Mock Buffer Reader to return a message
    BufferedReader buffer = Mockito.mock(BufferedReader.class);
    Mockito.doReturn(message).when(buffer).readLine();

    // Call method sendMessage to test the call
    String messageSent = client.sendMessage(buffer);

    Assertions.assertEquals(message, messageSent);
  }

  @Test
  @DisplayName("Test receiveMessage method: expected message from the chatbot")
  public void test_02() throws IOException {
    String message = "Hola";
    // Create instance
    TcpClient tcpClient = Mockito.mock(TcpClient.class);
    Client client = new Client(tcpClient);

    // Mock TcpReceiveMessage
    Mockito.doReturn(message).when(tcpClient).receiveMessage();

    // recover message from chat
    Assertions.assertEquals(message, client.receiveMesage());
  }

}
