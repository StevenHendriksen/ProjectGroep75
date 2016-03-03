package client;

import java.util.ArrayList;
import java.util.List;

import Shared.*;

public class tui {
  //private List<String> chatEntry = new ArrayList<String>();
  private List<String> consoleEntry = new ArrayList<String>();
  private Board board;
  //private boolean chat;
  private Tile[] hand; // todo: move

  public tui(Board board/*, boolean chat*/) {
    this.board = board;
    //this.chat = chat;
  }

  public void update() {
    board.setDim();
    clear();
    List<String> board = createConsole();
    board.addAll(board.size(), createHandPrint());
    board.addAll(board.size(), createBoardPrint());
    /*
    if (chat) {
      board.addAll(board.size(), createChatBox());
    }
    */
    for (int j = board.size(); j > 0; j--) {
      System.out.println(board.get(j - 1));
    }
  }

  public void clear() {
    for (int i = 0; i < 50; i++) {
      System.out.println();
    }
  }

  public void consoleEntry(String msg) {
    consoleEntry.add("  " + msg);
  }

  public String convertFormat(String msg) {
    String message = "| " + msg;
    int num = createOtherDivider().length() - message.length() - 2;
    for (int i = 0; i <= num; i++) {
      message = message + " ";
    }
    message = message + "|";
    return message;
  }

  public List<String> createConsole() {
    List<String> console = new ArrayList<String>();
    console.add(createOtherDivider());
    int num = 0;
    if (console.size() != 0) {
      if (consoleEntry.size() > 10) {
        num = 10;
      } else {
        num = consoleEntry.size();
      }
    }
    if (num != 0) {
      for (int i = consoleEntry.size() - 1; i >= consoleEntry.size() - num; i--) {
        console.add(convertFormat(consoleEntry.get(i)));
      }
    }
    String message = "Console:";
    console.add(convertFormat(message));
    console.add(createOtherDivider());
    return console;
  }
  
  /*
  public List<String> createChatBox() {
    List<String> chat = new ArrayList<String>();
    chat.add(createOtherDivider());
    int num = 0;
    if (chat.size() != 0) {
      if (chatEntry.size() > 5) {
        num = 5;
      } else {
        num = chatEntry.size();
      }
    }
    if (num != 0) {
      for (int i = chatEntry.size() - 1; i >= chatEntry.size() - num; i--) {
        chat.add(convertFormat(chatEntry.get(i)));
      }
    }
    String message = "Chat box:";
    chat.add(convertFormat(message));
    chat.add(createOtherDivider());
    return chat;
  }

  */

  public String createTileLine(int num) {
    String tilesLine = "";
    if (num < 0) {
      if (num < -9) {
        tilesLine = num + ":|";
      } else {
        tilesLine = num + " :|";
      }
    } else {
      if (num > 9) {
        tilesLine = num + " :|";
      } else {
        tilesLine = num + "  :|";
      }
    }
    for (int g = board.getdimXm(); g <= board.getdimXp(); g++) {
      String tile = "   ";
      if (board.getTile(g, num) != null) {
        tile = board.getTile(g, num).toString();
      }
      tilesLine = tilesLine + tile + "|";
    }
    return tilesLine;
  }

  public List<String> createBoardPrint() {
    List<String> boardPrint = new ArrayList<String>();
    boardPrint.add(createxCoords());
    for (int k = board.getdimYp(); k >= board.getdimYm(); k--) {
      boardPrint.add(createDivider());
      boardPrint.add(createTileLine(k));
    }
    boardPrint.add(createDivider());
    return boardPrint;
  }

  public String createxCoords() {
    String xcoords = " y/x|";
    for (int h = board.getdimXm(); h <= board.getdimXp(); h++) {
      if (h < 0) {
        if (h < -9) {
          xcoords = xcoords + h + "|";
        } else {
          xcoords = xcoords + h + " |";
        }
      } else {
        if (h > 9) {
          xcoords = xcoords + h + " |";
        } else {
          xcoords = xcoords + h + "  |";
        }
      }
    }
    return xcoords;
  }

  public List<String> createHandPrint() {
    List<String> handPrints = new ArrayList<String>();

    String handPrint = "Tiles:";
    for (int k = 0; k < hand.length; k++) {
      handPrint = handPrint + " " + hand[k];
    }
    handPrints.add(convertFormat(handPrint));
    handPrints.add(createOtherDivider());
    return handPrints;
  }

  public String createOtherDivider() {
    String divider = "+";
    for (int i = board.getdimXp(); i >= board.getdimXm(); i--) {
      divider = divider + "----";
    }
    divider = divider + "---+";
    return divider;
  }

  public String createDivider() {
    String divider = "-";
    for (int i = board.getdimXp(); i >= board.getdimXm(); i--) {
      divider = divider + "---+";
    }
    divider = divider + "---+";
    return divider;
  }
  
  /*
  public void chatEntry(String name, String msg, boolean bool) {
    String message = "";
    if (bool) {
      message = "  to " + name + ": " + msg;
    } else {

      message = "  from " + name + ": " + msg;
    }
    chatEntry.add(message);
  }
  */

  // todo: move
  public void setHand(Tile[] tiles) {
    hand = tiles;
  }
}
