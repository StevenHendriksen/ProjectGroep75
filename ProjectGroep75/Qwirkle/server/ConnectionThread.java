package server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import Shared.*;

public class ConnectionThread extends Thread {
  Socket socket;
  Gamelogic gamelogic;
  Board board;
  Bag bag;
  Server server;
  PrintWriter out;
  BufferedReader inputStream;
  Connection connection;
  
  /**
   * The constructor to create a proper thread for reading.
   * @param socket (socket)
   * @param gamelogic (gamelogic)
   * @param board (board)
   * @param bag (bag)
   * @param server (server)
   * @param out (output)
   * @param inputStream (input)
   * @param connection (connection)
   */

  public ConnectionThread(Socket socket, Gamelogic gamelogic,
      Board board, Bag bag, Server server,
      PrintWriter out, BufferedReader inputStream, Connection connection) {
    this.socket = socket;
    this.gamelogic = gamelogic;
    this.board = board;
    this.bag = bag;
    this.server = server;
    this.out = out;
    this.inputStream = inputStream;
    this.connection = connection;
  }

  @Override
  public void run() {
    Peer peer = new Peer(gamelogic, board, bag, server);
    server.connection(peer, socket);
    System.out.println("test");
    connection.read(peer, socket, out, inputStream);
  }
}
