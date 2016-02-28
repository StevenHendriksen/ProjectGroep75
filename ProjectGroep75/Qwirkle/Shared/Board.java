package Shared;

/**
 *Board
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import client.tui;

public class Board {
  // ------------------ Instance variables ----------------
  private int dimXp;
  private int dimXm;
  private int dimYp;
  private int dimYm;

  private Map<String, Tile> tileLocs = new HashMap<String, Tile>();

  /**
   * Main, mainly used for quick testing purposes.
   */

  public static void main(String[] args) {
    Board board = new Board();
    tui tui = new tui(board, true);
    tui.chatEntry("Stan", "hoi Stan", false);
    board.putTile(0, 0, 5);
    board.putTile(5, 5, 5);
    board.putTile(-5, -5, 5);
    board.putTile(-5, 5, 5);
    board.putTile(5, -5, 5);
    tui.chatEntry("you", "hoi Steven", true);
    tui.update();
  }

  /**
   * Constructor that creates an empty Board.
   */

  public Board() {
  }

  /**
   * Constructor that fills the board using the map provided, useful if you want
   * to pass it around fast.
   * 
   * @param map
   *          the map to fill the board with
   */

  public Board(Map<String, Tile> map, boolean chat) {
    tileLocs = map;
    setDim();
  }

  /**
   * sets the tiles the player has in their possesion to be placed on the board.
   * 
   * @param tiles
   *          a array of tiles that the player has
   */

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

  public void putTile(int xcoord, int ycoord, int tile) {
    tileLocs.put(xcoord + " " + ycoord, new Tile(tile));
  }

  /**
   * returns the integer that correspondents to the tile.
   * 
   * @param tile
   *          the tile
   * @return int the Integer
   */

  public int tileToInt(Tile tile) {
    int result = 0;
    result = tile.getColor().colorToInt() * 6 + tile.getShape().shapeToInt();
    return result;
  }

  /**
   * sets the Dimensions of the Board based on the highest/lowest x and y
   * values, which is used in the print to determine how big it should show the
   * playing field.
   */

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
   * loops through the List and finds the highest value.
   * 
   * @param list
   *          of Strings of integers
   * @return highest value in the List of Strings
   */

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
  public Map<String, Tile> getTileLocs() {
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

  public Tile getTile(int xcoord, int ycoord) {
    Tile result = null;
    Tile result2 = tileLocs.get(xcoord + " " + ycoord);
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

  /**
   * creates an other divider, that more fits the style of the console/chatbox
   * etc.
   * 
   * @return otherDivider
   */

  /**
   * Creates the String that is is the right length to fit with the board with x
   * Coordinates.
   * 
   * @return xCoords
   */

  /**
   * Uses the createxCoords, createDivider, createTileLine to create the main
   * part of the Board print.
   * 
   * @return List
   */

  /**
   * Creates the Tile line based on what y value it has.
   * 
   * @param num
   *          (y value)
   * @return String of the line
   */

  /**
   * Creates the chat box with the past 5 messages from other users.
   * 
   * @return chat
   */

  /**
   * creates the console with the past 10 console messages.
   * 
   * @return List
   */


  /**
   * Converts msg to a message that has the proper spacing and format.
   * 
   * @param msg
   *          Message provided that needs to be converted to be printed properly
   *          in the TUI
   * @return message
   */

  /**
   * Adds the msg to the consolebacklog.
   * 
   * @param msg
   *          message to be added to the console
   */

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

  /**
   * Prints 50 empty lines to clear the print area.
   */

  /**
   * creates the print used to display what Tiles the player has.
   * 
   * @return handPrints (String List of print of the hand
   */


  /**
   * Uses all the methods before to create and print the whole playing field,
   * console and chatbox.
   */


  
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
}