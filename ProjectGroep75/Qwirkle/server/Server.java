package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import Shared.*;

public class Server extends Thread {
  // ------------------ Instance variables ----------------
  ServerSocket server = null;
  private Gamelogic gamelogic;
  private Board board;
  private Peer peer;
  private Bag bag;
  private String functions = "CHAT,LOBBY";

  // ------------------ Constructor ------------------------

  /**
   * Constructor that creates all the objects needed for maintaining the server.
   */
  
  public Server() {
    Start();
  }
  
  private void Start() {
    Scanner in = new Scanner(System.in);
    while (server == null) {
      try {
        System.out.println("Port:");
        String port = "";
        port = in.nextLine();
        while (port.equals("")) {
          System.out.println("Re-enter port: ");
          port = in.nextLine();
        }
        server = new ServerSocket(new Integer(port));
        bag = new Bag(108);
        bag.fillBag();
        board = new Board();
        gamelogic = new Gamelogic(board, bag);
        System.out.println(
              "Waiting for client on port " + port + " with IP-adress: " 
                    + Inet4Address.getLocalHost().getHostAddress());
        Connection connection = new Connection(this, gamelogic, board, bag, server);
        Thread connectionThread = new Thread(connection);
        connectionThread.start();
        in.close();
      } catch (Exception e) {
        System.out.println("Error while trying to make a server, Try another port");
      }
    }
    in.close();
  }

  /**
   * The main method which makes a new Server, only used to call the constructor.
   */
  // @pure;
  public static void main(String[] args) {
    new Server();
  }

  /**
   * The method that creates a new connection for each client.
   * @param peer (the peer used to handle the client)
   * @param socket (the socket of the client)
   */
  // @ ensures peer != null;
  // @ ensures socket != null;
  public void connection(Peer peer, Socket socket) {
    try {
      System.out.println("Client connected: " + socket);
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader inputStream = new BufferedReader(
          new InputStreamReader(socket.getInputStream()));
      Connection connection = new Connection(this, gamelogic, board, bag, server);
      connection.read(peer, socket, out, inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * used to send a message to all the clients connected.
   * @param msg (message to send to the clients)
   */
  
  // @pure;
  public void sendAll(String msg) {
    System.out.println("sendAll: " + msg);
    for (int p = 0; p < gamelogic.hasPlayers().size(); p++) {
      gamelogic.hasPlayers().get(p).getConnection().write(
          msg, gamelogic.hasPlayers().get(p).getConnection().getOut());
    }

  }

  public String getFunctions() {
    return functions;
  }
  
  public Board getBoard(){
    return board;
  }
  
  public Gamelogic getGamelogic() {
    return gamelogic;
  }
  
  public Peer getPeer() {
    return peer;
  }
  
  public Bag getBag() {
    return bag;
  }
  
  

}