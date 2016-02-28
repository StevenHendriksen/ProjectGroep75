package server;

import java.util.Scanner;

import Shared.*;

public class Peer {
  Gamelogic gamelogic;
  Player player;
  Board board;
  Bag bag;
  Server server;
  int queueSize = 4;

  /**
   * The constructor for peer making sure it has all objects it need.
   * @param gamelogic (gamelogic)
   * @param board (board)
   * @param bag (bag)
   * @param server (server)
   */
  
  public Peer(Gamelogic gamelogic, Board board, Bag bag, Server server) {
    this.gamelogic = gamelogic;
    this.bag = bag;
    this.board = board;
    this.server = server;
  }
  
  /**
   * handles the command provided and the return gets send to the right person.
   * @param cmd (String to handle)
   * @param connection (connection it came from)
   * @return (the String to reply to the command)
   */

  public String handleCommand(String cmd, Connection connection) {
    try {
      String result = "";
      Scanner scan = new Scanner(cmd);
      String str = scan.nextLine();
      Scanner fullCommand = new Scanner(str);

      String command = fullCommand.next();

      if (command.equals("IDENTIFY")) {
        this.player = new Player(fullCommand.next(), connection);
        gamelogic.putPlayer(player);

        result = "IDENTIFYOK ";
        while (fullCommand.hasNext()) {
          String next = fullCommand.next();
          if (server.functions.contains(next)) {
            result = result + next;
          }
        }
        String sendall = "LOBBY";
        for (int player = 0; player < gamelogic.hasPlayers().size(); player++) {
          sendall = sendall + " " + gamelogic.hasPlayers().get(player).hasName();
        }
        server.sendAll(sendall);
        if (gamelogic.hasPlayers().size() >= queueSize) {
          if (gamelogic.gameStart(queueSize)) {
            server.sendAll("GAMESTART");
            for (int j = 0; j < gamelogic.hasPlayers().size(); j++) {
              Player player = gamelogic.hasPlayers().get(j);
              for (int i = 0; i < 6; i++) {
                player.changeTiles(gamelogic.drawTile(player), i);
              }
            }
            server.sendAll("TURN " + gamelogic.turn().hasName());
          }
        }
      } else if (command.equals("DRAWTILE")) {
        for (int i = 0; i < 6; i++) {
          if (player.hasTiles()[i] == null) {
            player.changeTiles(gamelogic.drawTile(gamelogic.getPlayer(connection)), i);
          }
        }
        result = "DRAWTILE";
      } else if (command.equals("QUIT")) {
        result = "GAMEEND";
      } else if (command.equals("QUEUE")) {
        int numberplayers = new Integer(fullCommand.next());

        if (gamelogic.gameStart(numberplayers)) {
          result = "GAMESTART";

          for (int i = 0; i < numberplayers; i++) {
            result = result + " " + gamelogic.hasPlayers().get(i).hasName();
          }
        }
      } else if (command.equals("MOVE_PUT")) {
        result = "MOVEOK_PUT";
        Scanner fullCommandTiles = new Scanner(cmd);
        fullCommandTiles.next();
        String sendall = "MOVEOK_PUT";
        int[] xcoords = new int[6];
        int[] ycoords = new int[6];

        while (fullCommandTiles.hasNext()) {
          String tile = fullCommandTiles.next();
          String tile2 = tile.replaceAll("[^\\dA-Za-z ]", " ");
          Scanner in = new Scanner(tile2);
          int tileInt = new Integer(in.next());
          int xcoord = new Integer(in.next());
          int ycoord = new Integer(in.next());
          xcoords[xcoords.length - 1] = xcoord;
          ycoords[ycoords.length - 1] = ycoord;
          if (gamelogic.getPlayer(connection).hasName().equals(gamelogic.turn().hasName()) 
              && (straightline(xcoords, xcoord) || straightline(ycoords, ycoord) 
                  || gamelogic.moveOkPut(tileInt, xcoord, ycoord).equals("MOVEOK_PUT"))) {
            board.putTile(xcoord, ycoord, tileInt);
            result = result + " " + tileInt + "@" + xcoord + "," + ycoord;
            sendall = sendall + " " + tileInt + "@" + xcoord + "," + ycoord;
            Tile drawnTile = gamelogic.drawTile(gamelogic.getPlayer(connection));
            connection.getOut().write(drawnTile.tileToInt(drawnTile));
            gamelogic.score(xcoord, ycoord, tileInt, gamelogic.getPlayer(connection));
          } else {
            result = "ERROR: INVALID MOVE";
          }
          in.close();
        }
        if (!sendall.equals("MOVEOK_PUT")) {
          server.sendAll(sendall);
        } else {
          connection.getOut().write("Error");
        }

        gamelogic.nextTurn();
        server.sendAll("TURN " + gamelogic.turn().hasName());

        fullCommandTiles.close();
      } else if (command.equals("MOVE_TRADE")) {
        result = "MOVEOK_TRADE";

        while (fullCommand.hasNext()) {
          int inttile = new Integer(fullCommand.next());
          if (gamelogic.moveOkTrade() == "MOVEOK_TRADE") {
            gamelogic.moveTrade(inttile);
          } else {
            result = "ERROR: INVALID TRADE";
          }
        }
      } else if (command.equals("QUEUE")) {
        queueSize = new Integer(fullCommand.next());
      }

      scan.close();
      fullCommand.close();
      return result;
    } catch (java.util.NoSuchElementException e) {
      String result = "Invalid Server command";
      e.printStackTrace();
      return result;
    }
  }

  /**
   * returns a boolean based on whether or not the moves done are in a straight line.
   * @param coords (int[] coords to check)
   * @param value (new value to add)
   * @return (boolean of whether or not straight line)
   */
  
  public boolean straightline(int[] coords, int value) {
    boolean result = true;
    for (int i = 0; i < coords.length; i++) {
      if (coords[i] != value) {
        result = false;
      }
    }
    System.out.println("straightline " + result);
    return result;
  }

}
