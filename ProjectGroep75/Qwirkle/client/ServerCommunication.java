package client;

/**
 * ServerCommunication;
 * 
 * @author Stan Peters en Steven hendriksen
 * @version 1.0
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Shared.Board;

public class ServerCommunication extends Thread {
  // ------------------ Instance variables ----------------
  Socket echoSocket = null;
  PrintWriter out = null;
  String hostName;
  int portNumber;
  Peer peer = null;

  // ------------------ Constructor ------------------------

  /**
   * The constructor that sets up the connection with the server.
   * 
   * @param hostName
   *          String of the ip to connect to
   * @param portNumber
   *          int of the port to connect to
   * @param board
   *          the board that this connection will be working with
   * @param peer
   *          the peer that this connection will be working with
   */

  public ServerCommunication(String hostName, int portNumber, Board board, Peer peer) {
    this.hostName = hostName;
    this.portNumber = portNumber;
    this.peer = peer;
    try {
      echoSocket = new Socket(hostName, portNumber);
      System.out.println("Connected " + echoSocket);
      out = new PrintWriter(echoSocket.getOutputStream(), true);
      System.out.println("Conecting Socket " + hostName + ":" + portNumber);
    } catch (IOException e2) {
      System.out.println("Failed to connect");
    }
  }

  /**
   * The thread that makes sure the client constantly reads the data from the
   * server.
   */

  public void run() {
    System.out.println("Reading");
    read();
  }

  /**
   * Writes to the server.
   * 
   * @param str
   *          String to be sent to the server
   */

  public void write(String str) {
    out.println(str);
    System.out.println("Sent to Server: " + str);
  }

  /**
   * Closes the connection with the server.
   */

  public void close() {
    try {
      echoSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * used to read from the server, runs on a seperate thread and keeps reading.
   */

  public void read() {
    try {
      while (true) {
        InputStreamReader inputReader = new InputStreamReader(echoSocket.getInputStream());
        BufferedReader inputStream = new BufferedReader(inputReader);

        String serverMessage = "";
        while (!serverMessage.equals("Close")) { 
          if (!serverMessage.equals("") && !serverMessage.equals("Close")) {
            peer.handleCommand(serverMessage);
            System.out.println(serverMessage);
          }
          serverMessage = inputStream.readLine();
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
