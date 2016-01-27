package server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Peer {
  Gamelogic gamelogic;
  Player player;
  Serverboard board;
  Bag bag;
  Server server;

  public Peer(Gamelogic gamelogic, Serverboard board, Bag bag, Server server) {
    this.gamelogic = gamelogic;
    this.bag = bag;
    this.board = board;
    this.server = server;
  }

  public String handleCommand(String cmd, Connection connection) {
    try {
      String result = "";
      Scanner scan = new Scanner(cmd);
      String str = scan.nextLine();
      Scanner fullCommand = new Scanner(str);

      String command = fullCommand.next();

      if (command.equals("IDENTIFY")) {
        this.player = new Player(fullCommand.next(), bag, connection);
        gamelogic.putPlayer(player);

        result = "IDENTIFYOK ";
        while (fullCommand.hasNext()) {
          String next = fullCommand.next();
          if (server.functions.contains(next)) {
            result = result + next;
          }
        }
        if (gamelogic.hasPlayers().size() >= 2) {
          if (gamelogic.gameStart(2)) {
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
        int xCoords[] = new int[6];
        int yCoords[] = new int[6];

        while (fullCommandTiles.hasNext()) {
          String tile = fullCommandTiles.next();
          String tile2 = tile.replaceAll("[^\\dA-Za-z ]", " ");
          Scanner in = new Scanner(tile2);
          int tileInt = new Integer(in.next());
          int x = new Integer(in.next());
          int y = new Integer(in.next());
          xCoords[xCoords.length - 1] = x;
          yCoords[yCoords.length - 1] = y;
          if (gamelogic.getPlayer(connection).hasName().equals(gamelogic.turn().hasName()) && (straightline(xCoords, x)
              || straightline(yCoords, y) || gamelogic.moveOkPut(tileInt, x, y).equals("MOVEOK_PUT"))) {
            board.putTile(x, y, tileInt);
            result = result + " " + tileInt + "@" + x + "," + y;
            sendall = sendall + " " + tileInt + "@" + x + "," + y;
            Servertile drawnTile = gamelogic.drawTile(gamelogic.getPlayer(connection));
            connection.getOut().write(drawnTile.tileToInt(drawnTile));
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
      }

      scan.close();
      fullCommand.close();
      return result;
    }

    catch (

    java.util.NoSuchElementException e)

    {
      String result = "Invalid Server command";
      e.printStackTrace();
      return result;
    }
  }

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
