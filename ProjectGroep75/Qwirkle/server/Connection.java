package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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

  /**
   * Constructor that makes sure all the proper objects are availible.
   * 
   * @param server
   *          (Link to server)
   * @param gamelogic
   *          (link to gamelogic)
   * @param board
   *          (link to the board)
   * @param bag
   *          (link to the bag)
   * @param serversock
   *          (link to the serversocket)
   */

  public Connection(Server server, Gamelogic gamelogic,
      Serverboard board, Bag bag, ServerSocket serversock) {
    this.server = server;
    this.gamelogic = gamelogic;
    this.board = board;
    this.bag = bag;
    this.serversock = serversock;
  }

  /**
   * Used to make sure the socket input keeps being read, runs on its own thread
   * (connectionThread).
   * 
   * @param peer
   *          (peer to make sure it gets there)
   * @param connection
   *          (the socket that belongs with this input)
   * @param out
   *          (the place to output to send things back)
   * @param inputStream
   *          (stream to read from)
   */

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
          System.out.println("clientMessage " + clientMessage);
          // write(handledCommand, out);
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

  /**
   * Closes the socket.
   * 
   * @param connection
   *          (the socket to be closed)
   */

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
        ConnectionThread connectionThread = new ConnectionThread(
            socket, gamelogic, board, bag, server, out,inputStream, this);
        connectionThread.start();
        System.out.println("Awaiting next connection");
      }
    } catch (IOException e1) {
      e1.printStackTrace();
    }

  }

  public PrintWriter getOut() {
    return out;
  }

  public Server getServer() {
    return server;
  }
}