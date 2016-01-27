package server;

import java.net.*;

import java.io.*;

public class Connection extends Thread {
  Server server;
  ServerSocket serversock;
  Gamelogic gamelogic;
  Serverboard board;
  Bag bag;
  String clientMessage = "";
  Socket connection;
  PrintWriter out;
  BufferedReader inputStream;
  Peer peer;

  public Connection(Server server, Gamelogic gamelogic, Serverboard board, Bag bag, ServerSocket serversock) {
    this.server = server;
    this.gamelogic = gamelogic;
    this.board = board;
    this.bag = bag;
    this.serversock = serversock;
  }

  public void read(Peer peer, Socket connection, PrintWriter out, BufferedReader inputStream) {
    this.peer = peer;
    this.connection = connection;
    this.out = out;
    this.inputStream = inputStream;

    System.out.println("Reading: " + connection);
    try {
      while (!clientMessage.equals("Close")) {
        if (!clientMessage.equals("")) {
          System.out.println("clientMessage: " + clientMessage);
          String handledCommand = peer.handleCommand(clientMessage, this);
          System.out.println("clientMessage " +  clientMessage);
          //write(handledCommand, out);
          if (handledCommand == "SERVER_GAMEEND") {
            this.close(connection);
          }
        }

        clientMessage = inputStream.readLine();
      }
      this.close(connection);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void close(Socket connection) {
    try {
      connection.close();
      System.out.println("The connection has been closed.");
    } catch (IOException e) {
      System.out.println("Error while closing: " + e);
    }
  }

  public void write(String str, PrintWriter out) {
    out.println(str);
  }

  @Override
  public void run() {
    try {
      while (true) {
        Socket socket = serversock.accept();
        ConnectionThread connectionThread = new ConnectionThread(socket, gamelogic, board, bag, server, out,
            inputStream, this);
        connectionThread.start();
        System.out.println("Awaiting next connection");
      }
    } catch (IOException e1) {
      e1.printStackTrace();
    }

  }
  
  public PrintWriter getOut(){
    return out;
  }
}