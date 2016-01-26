package server;

/**
 *Board;
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Serverboard {
  // ------------------ Instance variables ----------------
  private int dimXp;
  private int dimXm;
  private int dimYp;
  private int dimYm;

  private Map<String, Servertile> tileLocs = new HashMap<String, Servertile>();
  private List<String> chatEntry = new ArrayList<String>();
  private List<String> consoleEntry = new ArrayList<String>();
  private Servertile[] hand = {};

  private boolean chat = false;

  /**
   * Main, mainly used for quick testing purposes.
   */

  public static void main(String[] args) {
    Serverboard board = new Serverboard(true);
    board.chatEntry("Stan", "hoi Stan", false);
    board.chatEntry("you", "hoi Steven", true);
    board.update();
  }

  /**
   * Constructor that creates an empty Board.
   */

  public Serverboard(boolean chat) {
    this.chat = chat;
  }

  /**
   * Constructor that fills the board using the map provided, useful if you want
   * to pass it around fast.
   * 
   * @param map
   *          the map to fill the board with
   */

  public Serverboard(Map<String, Servertile> map, boolean chat) {
    this.chat = chat;
    tileLocs = map;
    setDim();
  }

  /**
   * sets the tiles the player has in their possesion to be placed on the board.
   * 
   * @param tiles
   *          a array of tiles that the player has
   */

  /*
   * @requires tiles != null;
   * 
   * @ensures \old(this.hasHand()) != this.hasHand();
   * 
   * @ensures \forall(int i; i >= 0 i < hasHand().length; this.hasHand()[i] ==
   * tiles[i]);
   */
  public void setHand(Servertile[] tiles) {
    hand = tiles;
  }

  // @ pure;
  public Servertile[] hasHand() {
    return hand;
  }

  /**
   * Puts a tile in the ArrayList that controls the position of the tiles.
   * 
   * @param xcoord
   *          x Coordinate of the tile
   * @param ycoord
   *          y Coordinate of the tile
   * @param tile
   *          the tile you want to put there
   */
  /*
   * @requires tile >= 0 && tile <= 36; requires xcoord <= getdimXp() && xcoord
   * >= getdimXm(); requires ycoord <= getdimYp() && ycoord >= getdimYm();
   * 
   * @ensures getTile(xcoord, ycoord).hasColor() == new
   * Servertile(tile).hasColor();
   * 
   * @ensures getTile(xcoord, ycoord).hasShape() == new
   * Servertile(tile).hasShape();
   */
  public void putTile(int xcoord, int ycoord, int tile) {
    assert tile <= 36 && tile >= 0;
    assert xcoord <= getdimXp() && xcoord >= getdimXm();
    assert ycoord <= getdimYp() && ycoord >= getdimYm();
    tileLocs.put(xcoord + " " + ycoord, new Servertile(tile));
  }

  /**
   * returns the integer that correspondents to the tile.
   * 
   * @param tile
   *          the tile
   * @return int the Integer
   */
  /*
   * @ requires tile.hasColor() == Color.RED || tile.hasColor() == Color.ORANGE
   * || tile.hasColor() == Color.YELLOW || tile.hasColor() == Color.BLUE ||
   * tile.hasColor() == Color.GREEN || tile.hasColor == Color.PURPLE;
   * 
   * @ requires tile.hasShape() == Shape.RED || tile.hasShape() == Shape.ORANGE
   * || tile.hasShape() == Shape.YELLOW || tile.hasShape() == Shape.BLUE ||
   * tile.hasShape() == Shape.GREEN || tile.hasColor == Shape.PURPLE;
   * 
   * @ ensures \result >= 1;
   * 
   * @ ensures \result <= 36;
   */
  public int tileToInt(Servertile tile) {
    int result = 0;
    result = tile.hasColor().colorToInt() * 6 + tile.hasShape().shapeToInt();
    return result;
  }

  /**
   * sets the Dimensions of the Board based on the highest/lowest x and y
   * values, which is used in the print to determine how big it should show the
   * playing field.
   */

  // @ pure;
  public void setDim() {
    List<String> xcoords = new ArrayList<String>();
    List<String> ycoords = new ArrayList<String>();
    for (String k : tileLocs.keySet()) {
      xcoords.add((k.split("\\s+")[0]));
      ycoords.add((k.split("\\s+")[1]));
    }
    dimXp = getHighest(xcoords);
    dimYp = getHighest(ycoords);
    dimXm = getLowest(xcoords);
    dimYm = getLowest(ycoords);
  }

  /**
   * Method that returns the highest X-coordinate.
   */
  // @ pure;
  public int getdimXp() {
    return dimXp;
  }

  /**
   * Method that returns the highest Y-coordinate.
   */
  // @pure;
  public int getdimYp() {
    return dimYp;
  }

  /**
   * Method that returns the lowest X-coordinate.
   */
  // @pure;
  public int getdimXm() {
    return dimXm;
  }

  /**
   * Method that returns the lowest Y-coordinate.
   */
  // @pure;
  public int getdimYm() {
    return dimYm;
  }

  /**
   * loops through the List and finds the highest value.
   * 
   * @param list
   *          of Strings of integers
   * @return highest value in the List of Strings
   */

  // @ requires list != null;
  public int getHighest(List<String> list) {
    int highest = 0;
    for (int i = 0; i < list.size(); i++) {
      if ((new Integer(list.get(i)) > highest)) {
        highest = new Integer(list.get(i));
      }
    }
    return highest;
  }

  /**
   * loops through the List and finds the lowest value.
   * 
   * @param list
   *          the list of integers in String format
   * @return lowest value in the List of Strings
   */

  // @ ensures list != null;
  public int getLowest(List<String> list) {
    int lowest = 0;
    for (int i = 0; i < list.size(); i++) {
      if ((new Integer(list.get(i)) < lowest)) {
        lowest = new Integer(list.get(i));
      }
    }
    return lowest;
  }

  /**
   * Returns the Tile locs on the board.
   * 
   * @return tileLocs
   */
  // @pure;
  public Map<String, Servertile> getTileLocs() {
    return tileLocs;
  }

  /**
   * returns the tile that is on the given x and y values.
   * 
   * @param xcoord
   *          (x value)
   * @param ycoord
   *          (y value)
   * @return tile if it exists or null
   */

  /*
   * @requires xcoord <= getdimXp() && xcoord >= getdimXm(); requires ycoord <=
   * getdimYp() && ycoord >= getdimYm();
   */
  public Servertile getTile(int xcoord, int ycoord) {
    Servertile result = null;
    Servertile result2 = tileLocs.get(xcoord + " " + ycoord);
    if (result2 != null) {
      result = result2;
    }
    return result;
  }

  /**
   * creates a divider based on the dimensions of the Board.
   * 
   * @return divider
   */
  // @pure;
  public String createDivider() {
    String divider = "-";
    for (int i = dimXp; i >= dimXm; i--) {
      divider = divider + "---+";
    }
    divider = divider + "---+";
    return divider;
  }

  /**
   * creates an other divider, that more fits the style of the console/chatbox
   * etc.
   * 
   * @return otherDivider
   */

  // @pure;
  public String createOtherDivider() {
    String divider = "+";
    for (int i = dimXp; i >= dimXm; i--) {
      divider = divider + "----";
    }
    divider = divider + "---+";
    return divider;
  }

  /**
   * Creates the String that is is the right length to fit with the board with x
   * Coordinates.
   * 
   * @return xCoords
   */

  // @pure;
  public String createxCoords() {
    String xcoords = " y/x|";
    for (int h = dimXm; h <= dimXp; h++) {
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

  /**
   * Uses the createxCoords, createDivider, createTileLine to create the main
   * part of the Board print.
   * 
   * @return List
   */
  // @pure;
  public List<String> createBoardPrint() {
    List<String> board = new ArrayList<String>();
    board.add(createxCoords());
    for (int k = dimYp; k >= dimYm; k--) {
      board.add(createDivider());
      board.add(createTileLine(k));
    }
    board.add(createDivider());
    return board;
  }

  /**
   * Creates the Tile line based on what y value it has.
   * 
   * @param num
   *          (y value)
   * @return String of the line
   */
  // @pure;
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
    for (int g = dimXm; g <= dimXp; g++) {
      String tile = "   ";
      if (getTile(g, num) != null) {
        tile = getTile(g, num).toString();
      }
      tilesLine = tilesLine + tile + "|";
    }
    return tilesLine;
  }

  /**
   * Creates the chat box with the past 5 messages from other users.
   * 
   * @return chat
   */
  // @pure;
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

  /**
   * creates the console with the past 10 console messages.
   * 
   * @return List
   */

  // @pure;
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

  /**
   * Converts msg to a message that has the proper spacing and format.
   * 
   * @param msg
   *          Message provided that needs to be converted to be printed properly
   *          in the TUI
   * @return message
   */
  // @requires msg != "";
  // @pure;
  public String convertFormat(String msg) {
    String message = "| " + msg;
    setDim();
    int num = createOtherDivider().length() - message.length() - 2;
    for (int i = 0; i <= num; i++) {
      message = message + " ";
    }
    message = message + "|";
    return message;
  }

  /**
   * Adds the msg to the consolebacklog.
   * 
   * @param msg
   *          message to be added to the console
   */

  // @requires msg != null;
  // @ensures consoleEntry.size() == \old(consoleEntry.size()) + 1;
  public void consoleEntry(String msg) {
    consoleEntry.add("  " + msg);
  }

  /**
   * Adds the msg to the chatEntry, also uses the name where it came from and
   * who it was sent from.
   * 
   * @param msg
   *          message sent
   * @param name
   *          who the message was sent to
   * @param bool
   *          whether it was sent from or to the name
   */

  // @pure;
  public void chatEntry(String name, String msg, boolean bool) {
    String message = "";
    if (bool) {
      message = "  to " + name + ": " + msg;
    } else {

      message = "  from " + name + ": " + msg;
    }
    chatEntry.add(message);
  }

  /**
   * Prints 50 empty lines to clear the print area.
   */

  // @pure;
  public void clear() {
    for (int i = 0; i < 50; i++) {
      System.out.println();
    }
  }

  /**
   * creates the print used to display what Tiles the player has.
   * 
   * @return handPrints (String List of print of the hand
   */

  // @pure;
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

  /**
   * Uses all the methods before to create and print the whole playing field,
   * console and chatbox.
   */

  // @pure;
  public void update() {
    setDim();
    clear();
    List<String> board = createConsole();
    board.addAll(board.size(), createHandPrint());
    board.addAll(board.size(), createBoardPrint());
    if (chat) {
      board.addAll(board.size(), createChatBox());
    }
    for (int j = board.size(); j > 0; j--) {
      System.out.println(board.get(j - 1));
    }
  }
}