package client;

import java.util.Scanner;

/**
 * Executable client class for Qwirkle
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

public class Qwirkle {
  // ------------------Instance variables----------------
  static String hostName = "192.168.0.1";
  static int portNumber = 3215;
  private Board board;
  public Peer peer; // change to private in final version
  private ServerCommunication sc = null;
  private Thread scThread = null;
  private static final String functions = "CHAT,LOBBY";
  private Lobby lobby;
  private Player player;
  private Ai ai;
  private boolean manual;

  /**
   * Main used for testing purposes mainly.
   * 
   * @param args
   *          String[] of arguments needed, currently unused
   */

  public static void main(String[] args) {
    new Qwirkle(false);
  }

  /**
   * Test method used to quickly insert a lot of commands that would normally
   * come from the server.
   */

  public void test() {
    peer.handleCommand("GAMESTART");
    board.putTile(-5, -5, 8);
    board.putTile(5, 5, 3);
    board.putTile(-5, 5, 31);
    board.putTile(5, -5, 10);
    board.putTile(5, -5, 10);
    board.putTile(0, 0, 9);
    board.consoleEntry("Test");
    board.consoleEntry("Test");
    board.consoleEntry("Test");
    board.consoleEntry("Test");
    board.consoleEntry("Test");
    board.chatEntry("Steven", "Banter", true);
    board.chatEntry("Steven", "Banter", false);
    board.chatEntry("Steven", "Banter", true);
    board.chatEntry("Steven", "Banter", false);
    board.chatEntry("Steven", "Banter", true);
    board.chatEntry("Steven", "Banter", false);
    board.chatEntry("Steven", "Banter", true);
    board.update();
    board.putTile(-5, 5, 10);
    board.chatEntry("Steven", "Banter", true);
    board.chatEntry("Steven", "Banter", false);
    board.chatEntry("Steven", "Banter", true);
    board.update();
  }

  // ------------------ Constructor ------------------------

  /**
   * Qwirkle constructor, used to start an instance of Qwirkle, asks for some
   * basic information like ip of server, port, alias.
   */

  public Qwirkle(boolean manual) {
    System.out.println("Welcome to Qwirkle, to connect to a server, enter the following");

    Scanner in = new Scanner(System.in);
    System.out.println("Ip:");
    String ip = "";
    ip = in.nextLine();
    while (ip.equals("")) {
      System.out.println("Re-enter IP: ");
      ip = in.nextLine();
    }
    System.out.println("port:");
    String portEntry = in.nextLine();
    int port = new Integer(portEntry);
    while (portEntry.equals("")) {
      System.out.println("Re-enter Port: ");
      portEntry = in.nextLine();
    }
    port = new Integer(portEntry);
    System.out.println("Playing alias:");
    String name = "";
    name = in.nextLine();
    while (name.equals("")) {
      System.out.println("Re-enter name: ");
      name = in.nextLine();
    }

    if (functions.contains("CHAT")) {
      board = new Board(true);
    } else {
      board = new Board(false);
    }
    player = new Player(name);
    ai = new Ai(player);
    peer = new Peer(board, this, player);
    System.out.println(ip + port);
    sc = new ServerCommunication(ip, port, board, peer);
    if (sc.echoSocket != null) {
      scThread = new Thread(sc);
      scThread.start();
      sc.write("IDENTIFY " + name + " " + functions);
      // sc.write("CLIENT_LOBBY");
    }
    if (manual) {
      while (true) {
        String typedMessage = in.nextLine();
        if (typedMessage.equals("HINT")) {
          System.out.println(ai.smartMove(board));
        } else {
          sc.write(typedMessage);
        }
      }
    } else {
      while (true) {
      }
    }
  }

  /**
   * Used to quickly use predefined ip, port and name for repeated testing ot if
   * they stay the same.
   * 
   * @param ip
   *          (ip to connect to)
   * @param port
   *          (port to connect to)
   * @param name
   *          (name to use)
   */

  public Qwirkle(String ip, int port, String name) {
    if (functions.contains("CHAT")) {
      board = new Board(true);
    } else {
      board = new Board(false);
    }
    player = new Player(name);
    peer = new Peer(board, this, player);
    System.out.println(ip + port);
    sc = new ServerCommunication(ip, port, board, peer);
    if (sc.echoSocket != null) {
      scThread = new Thread(sc);
      scThread.start();
      sc.write("CLIENT_IDENTIFY " + name + " " + functions);
      sc.write("CLIENT_LOBBY");
    }
    while (true) {
      Scanner systemIn = new Scanner(System.in);
      String typedMessage = systemIn.nextLine();
      if (typedMessage.equals("HINT")) {
        System.out.println(ai.smartMove(board));
      } else {
        peer.handleCommand(typedMessage);
      }
    }
  }

  /**
   * Starts the actual game instead of the start up screen.
   */

  public void start() {
    // creates the board and does the main thing
    board.update();
  }

  /**
   * Called when the game ends and shows the scores of all the players.
   */

  public void end(String str) {
    // End screen
    board.clear();
    System.out.println(str);
  }

  /**
   * sets the turn of the player to the name provided.
   * 
   * @param name
   *          (name of the next person)
   */

  public void turn(String name) {
    System.out.println("Turn: " + name);
  }

  /**
   * Used to indicate that the player passed.
   * 
   * @param name
   *          (the name of the player that passed)
   */

  public void pass(String name) {
    System.out.println("Pass: " + name);
  }

  /**
   * returns the instance of lobby.
   * 
   * @return (lobby instance of this client)
   */

  public Lobby getLobby() {
    return lobby;
  }
  /**
   * returns if the client is manual.
   * 
   * @return (boolean)
   */
  
  public boolean getManual() {
    return manual;
  }
  
  /**
   * returns the communication
   * 
   * @return (ServerCommunication)
   */
  
  public ServerCommunication getConnection() {
    return sc;
  }
  
  /**
   * returns the ai
   * 
   * @return (ai)
   */
  
  public Ai getai() {
    return ai;
  }
  
  

  /**
   * prints all the commands you can use.
   */

  public void help() {
    System.out.println("Available Commands:");
    System.out.println("CHALLENGE [player]");
    System.out.println("CHALLENGE_ACCEPT [player]");
    System.out.println("CHALLENGE_DECLINE [player]");
    System.out.println("LEADERBOARDS");
    System.out.println("LOBBY");
    System.out.println("QUIT");
    System.out.println("QUEUE [int, int, ....]");
    System.out.println("MOVE_PUT 1@0,0 2@0,1 etc");
    System.out.println("MOVE_TRADE int int ...");

  }
}
