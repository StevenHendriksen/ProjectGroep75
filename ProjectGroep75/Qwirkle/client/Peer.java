package client;

/**
 * Peer for the command send by the server
 * 
 * @author Stan peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

import java.util.Scanner;

public class Peer {

  private Board board = null;
  private Qwirkle game = null;
  private Player player = null;

  public static void main(String[] args) {
    Qwirkle game = new Qwirkle();
    game.peer.handleCommand("IDENTIFYOK");
    game.peer.handleCommand("GAMESTART");
    game.peer.handleCommand("TURN Steven");
    game.peer.handleCommand("PASS");
    game.peer.handleCommand("DRAWTILE 33 32 12");
    game.peer.handleCommand("MOVEOK_PUT 12@0,0 4@0,33 3@1,32 0@3,12");
    game.peer.handleCommand("MOVEOK_TRADE 3");
    game.peer.handleCommand("ERROR Invalid move");
    game.peer.handleCommand("CHAT global Alice Hello World!");
    game.peer.handleCommand("CHATOK global Alice Hello World!");
    game.peer.handleCommand("GAMEEND");
  }

  public Peer(Board board, Qwirkle game, Player player) {
    this.board = board;
    this.game = game;
    this.player = player;
  }

  public Peer() {
    board = new Board(false);
    player = new Player("GenericName");
  }

  public void handleCommand(String cmd) {
    System.out.println("handlingCommand: " + cmd);
    Scanner scan = new Scanner(cmd);
    String str = scan.nextLine();
    try {

      Scanner fullCommand = new Scanner(str);

      String command = fullCommand.next();
      if (command.equals("IDENTIFYOK")) {
        System.out.println("Succesfully connected");
      }
      if (command.equals("GAMESTART")) {
        System.out.println("Game is starting");
        game.start();
      }
      if (command.equals("GAMEEND")) {
        System.out.println("Game has ended");
        String name = fullCommand.next();
        String output = name + " " + fullCommand.next();
        while (fullCommand.hasNext()) {
          output = output + " " + fullCommand.next();
        }
        game.end(output);
      }
      if (command.equals("TURN")) {
        String name = fullCommand.next();
        System.out.println("Turn: " + name);
        game.turn(name);
        board.update();
      }
      if (command.equals("PASS")) {
        String name = fullCommand.next();
        System.out.println("Player Passed: " + name);
        game.pass(name);
        board.update();
      }
      if (command.equals("DRAWTILE")) {
        while (fullCommand.hasNext()) {
          // adds the previously mentioned
          System.out.println("Adding tile");
          player.addTile(new Servertile(new Integer(fullCommand.next())));
        }
        board.setHand(player.hasTiles());
        board.update();
      }
      if (command.equals("MOVEOK_PUT")) {
        Scanner fullCommandTiles = new Scanner(cmd);
        fullCommandTiles.next();
        while (fullCommandTiles.hasNext()) {
          String tile = fullCommandTiles.next();
          String tile2 = tile.replaceAll("[^\\dA-Za-z ]", " ");
          Scanner in = new Scanner(tile2);
          int tileInt = new Integer(in.next());
          int xvalue = new Integer(in.next());
          int yvalue = new Integer(in.next());
          System.out.println("putTile: " + xvalue + yvalue + tileInt);
          board.putTile(xvalue, yvalue, tileInt);
          in.close();
          board.consoleEntry("added: " + tile);
        }
        System.out.println("tiles places");
        fullCommandTiles.close();
        board.update();
      }
      if (command.equals("MOVEOK_TRADE")) {
        System.out.println("Tiles traded: " + fullCommand.next());
      }
      if (command.equals("ERROR")) {
        System.out.println("Error: " + fullCommand.next());
      }
      if (command.equals("CHATOK")) {
        String name = fullCommand.next();
        String output = name + " " + fullCommand.next();
        while (fullCommand.hasNext()) {
          output = output + " " + fullCommand.next();
        }
        System.out.println(output);
        board.chatEntry(name, output, true);
      }
      if (command.equals("LOBBY")) {
        while (fullCommand.hasNext()) {
          game.getLobby().addPlayer(fullCommand.next());
        }
        game.getLobby().print();
      }
      scan.close();
      fullCommand.close();

    } catch (java.util.NoSuchElementException e) {
      System.out.println("Invalid Server command: " + str);
      e.printStackTrace();
      scan.close();
      board.update();

    }
  }
}
