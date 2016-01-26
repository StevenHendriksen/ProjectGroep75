package server;

import java.net.*;
import java.io.*;

public class Server extends Thread {
  // ------------------ Instance variables ----------------
  ServerSocket server = null;
  Gamelogic gamelogic;
  Serverboard board;
  Player player;
  Peer peer;
  Bag bag;
  String clientMessage = "";
  Connection connection;
  public String functions = "CHAT,LOBBY";

  // ------------------ Constructor ------------------------
  /**
   * The constructor that makes a the connection;
   * 
   * @param server
   *          the ServerSocket which is made.
   * @param bag
   *          The bag, which is used in the game
   * @param board
   *          The board, which is used in the game.
   * @param gamelogic
   *          The gamelogic, which is used in the game.
   * @param connection
   *          The connection, which is made with the clients.
   */
  public Server() {
    try {
      server = new ServerSocket(3333);
      bag = new Bag();
      board = new Serverboard(true);
      gamelogic = new Gamelogic(board, bag);
      System.out
          .println("Waiting for client on port 3223 swith IP-adress: " + Inet4Address.getLocalHost().getHostAddress());
      Connection connection = new Connection(this, gamelogic, board, bag, server);
      Thread s = new Thread(connection);
      s.start();
    } catch (Exception e) {
      System.out.println("Error while trying to make a server: " + e);
      e.printStackTrace();
    }
  }

  /**
   * The main method which makes a new Server
   */
  // @pure;
  public static void main(String[] args) {
    new Server();
  }

  /**
   * The method that makes a connection with the client.
   * 
   * @param out
   *          the Outputstream which will be writen on.
   * @param inputStream
   *          the inputstream which will be read from.
   * @param connection
   *          a new connection which is made.
   */
  // @ ensures peer != null;
  // @ ensures socket != null;
  public void connection(Peer peer, Socket socket) {
    try {
      System.out.println("Client connected: " + socket);
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      Connection connection = new Connection(this, gamelogic, board, bag, server);
      connection.read(peer, socket, out, inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // @pure;
  public void sendAll(String msg) {
    System.out.println("sendAll " + gamelogic.hasPlayers());
    for (int p = 0; p < gamelogic.hasPlayers().size(); p++) {
      gamelogic.hasPlayers().get(p).getConnection().write(msg,
          gamelogic.hasPlayers().get(p).getConnection().getOut());
    }

  }
  
  public String getfunctions() {
    return functions;
  }

}