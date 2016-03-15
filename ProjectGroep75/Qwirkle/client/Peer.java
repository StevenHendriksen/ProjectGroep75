package client;

/**
 * Peer for the command send by the server
 * 
 * @author Stan peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

import java.util.Scanner;

import Shared.*;

public class Peer {

  private Board board = null;
  private Qwirkle game = null;
  private Player player = null;
  private tui tui = null;

  /**
   * main, used to test peer with a few commands before we started doing it by
   * letting the server send the messages.
   * 
   * @param args
   *          (args provided in run config, not used in the actual method)
   */

  public static void main(String[] args) {
    Qwirkle game = new Qwirkle(true);
    game.peer.handleCommand("IDENTIFYOK");
    game.peer.handleCommand("GAMESTART");
    game.peer.handleCommand("TURN Steven");
    game.peer.handleCommand("PASS");
    game.peer.handleCommand("DRAWTILE 33 32 12");
    game.peer.handleCommand("MOVEOK_PUT 12@0,0 4@0,33 3@1,32 0@3,12");
    game.peer.handleCommand("MOVEOK_TRADE 3");
    game.peer.handleCommand("ERROR Invalid move");
    game.peer.handleCommand("GAMEEND");
  }

  /**
   * Constructor of Peer, making sure it has a direct link to the other objects
   * it needs.
   * 
   * @param board
   *          (the board)
   * @param game
   *          (the client instance)
   * @param player
   *          (the player that is playing)
   */

  public Peer(Qwirkle game) {
    this.game = game;
    board = game.getBoard();
    player = game.getPlayer();
    tui = game.getTui();
  }

  /**
   * Used to handle the commands sent by the server.
   * @param cmd (String of the command sent by the server)
   */

  public void handleCommand(String cmd) {
    System.out.println("handlingCommand: " + cmd);
    Scanner scan = new Scanner(cmd);
    String str = scan.nextLine();
    try {

      Scanner fullCommand = new Scanner(str);

      String command = fullCommand.next();
      command = command.replace("", ""); //To fix a weird bug where it would place 's in front of messages from the server
      if (command.equals("IDENTIFYOK")) {
        System.out.println("Succesfully connected");
      }
      if (command.equals("GAMESTART")) {
        System.out.println("Game is starting");
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
        if (name.equals(player.hasName()) && !game.getManual()) {
          System.out.println("move :" + game.getai().smartMove(board));
          game.getConnection().write(game.getai().smartMove(board));
        }
        System.out.println("Turn: " + name);
        game.turn(name);
      }
      if (command.equals("PASS")) {
        String name = fullCommand.next();
        System.out.println("Player Passed: " + name);
        game.pass(name);
      }
      //check protocol
      if (command.equals("DRAWTILE")) {
        while (fullCommand.hasNext()) {
          // adds the previously mentioned
          System.out.println("Adding tile");
          player.addTile(new Tile(new Integer(fullCommand.next())));
          /*
          if (player.getTiles().emptySpots() == 0) {
            int position = 0;
            for (int i = 0; i < player.getTiles(). - 1; i++) {
              if (player.hasTiles()[i] == null) {
                position = i;
                break;
              }
            }
            player.changeTiles(new Tile(new Integer(fullCommand.next())), position);
          } else {
            player.changeTiles(new Tile(new Integer(fullCommand.next())), 
                player.hasTiles().length - 1);
          }
          */

        }
        player.getTiles().setBag(player.getTiles().getBag());
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
          if (game.getTurn().equals(player.hasName())) {
            for (int i = 0; i < 6; i++) {
              if (equal(player.getTiles().getBag()[i], tileInt)) {
                Tile[] tiles = player.getTiles().getBag();
                tiles[i] = null;
                player.getTiles().setBag(tiles);
              }
            }
            board.putTile(xvalue, yvalue, tileInt);
            tui.consoleEntry("added: " + tile);
          } else {
            board.putTile(xvalue, yvalue, tileInt);
            tui.consoleEntry("added: " + tile);
          }
          in.close();
        }
        System.out.println("tiles places");
        fullCommandTiles.close();
      }
      if (command.equals("MOVEOK_TRADE")) {
        System.out.println("Tiles traded: " + fullCommand.next());
      }
      if (command.equals("ERROR")) {
        System.out.println("Error: " + fullCommand.next());
      }
      /*if (command.equals("CHATOK")) {
        String name = fullCommand.next();
        String output = name + " " + fullCommand.next();
        while (fullCommand.hasNext()) {
          output = output + " " + fullCommand.next();
        }
        System.out.println(output);
        tui.chatEntry(name, output, true);
      }*/
      /*
      if (command.equals("LOBBY")) {
        while (fullCommand.hasNext()) {
          game.getLobby().addPlayer(fullCommand.next());
        }
        game.getLobby().print();
      }
      */
      scan.close();
      fullCommand.close();
      tui.update();
    } catch (java.util.NoSuchElementException e) {
      System.out.println("Invalid Server command: " + str);
      scan.close();
      tui.update();

    }
  }
  /**
   * Used to check if the tile provided is equal to the int.
   * @param tile (the tile provided)
   * @param tileInt (the int provided)
   * @return (boolean of whether they are equal)
   */

  public boolean equal(Tile tile, int tileInt) {
    boolean result = false;

    Tile tile2 = new Tile(tileInt);

    if (tile == null || tile2 == null || tile.getColor() == tile2.getColor() && tile.getShape() 
        == tile2.getShape()) {
      result = true;
    }

    return result;
  }
}
