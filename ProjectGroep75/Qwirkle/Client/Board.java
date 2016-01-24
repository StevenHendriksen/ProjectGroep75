package Client;

/**
 *Board;
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
	// ------------------ Instance variables ----------------
	private int DIMxp;
	private int DIMxm;
	private int DIMyp;
	private int DIMym;

	private Map<String, Tile> tileLocs = new HashMap<String, Tile>();
	private List<String> chatEntry = new ArrayList<String>();
	private List<String> consoleEntry = new ArrayList<String>();
	private Tile[] hand = {};

	private boolean chat = false;

	/**
	 * Main, mainly used for quick testing purposes
	 */

	public static void main(String[] args) {
		Board board = new Board(true);
		board.chatEntry("Stan", "hoi Stan", false);
		board.chatEntry("you", "hoi Steven", true);
		board.update();
	}

	/**
	 * Constructor that creates an empty Board
	 */

	public Board(boolean chat) {
		this.chat = chat;
	}

	/**
	 * Constructor that fills the board using the map provided, useful if you
	 * want to pass it around fast.
	 * 
	 * @param map the map to fill the board with
	 */

	public Board(Map<String, Tile> map, boolean chat) {
		this.chat = chat;
		tileLocs = map;
		setDIM();
	}

	/**
	 * sets the tiles the player has in their possesion to be placed on the
	 * board
	 * 
	 * @param tiles
	 */

	public void setHand(Tile[] tiles) {
		hand = tiles;
	}

	/**
	 * Puts a tile in the ArrayList that controls the position of the tiles
	 * 
	 * @param x
	 * @param y
	 * @param tile
	 */

	public void putTile(int x, int y, int tile) {
		tileLocs.put(x + " " + y, new Tile(tile));
	}

	/**
	 * returns the integer that correspondents to the tile
	 * 
	 * @param tile
	 * @return int
	 */

	public int tileToInt(Tile tile) {
		int result = 0;
		result = tile.hasColor().colorToInt() * 6 + tile.hasShape().shapeToInt();
		return result;
	}

	/**
	 * sets the Dimensions of the Board based on the highest/lowest x and y
	 * values, which is used in the print to determine how big it should show
	 * the playing field.
	 */

	public void setDIM() {
		List<String> x = new ArrayList<String>();
		List<String> y = new ArrayList<String>();
		for (String k : tileLocs.keySet()) {
			x.add((k.split("\\s+")[0]));
			y.add((k.split("\\s+")[1]));
		}
		DIMxp = getHighest(x);
		DIMyp = getHighest(y);
		DIMxm = getLowest(x);
		DIMym = getLowest(y);
	}

	/**
	 * loops through the List and finds the highest value
	 * 
	 * @param list
	 * @return highest value in the List<String>
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
	 * loops through the List and finds the lowest value
	 * 
	 * @param list
	 * @return lowest value in the List<String>
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
	 * Returns the Tile locs on the board
	 * 
	 * @return tileLocs
	 */
	public Map<String, Tile> getTileLocs() {
		return tileLocs;
	}

	/**
	 * returns the tile that is on the given x and y values
	 * 
	 * @param k
	 *            (x value)
	 * @param g
	 *            (y value)
	 * @return tile if it exists or null
	 */

	public Tile getTile(int k, int g) {
		Tile result = null;
		Tile result2 = tileLocs.get(k + " " + g);
		if (result2 != null) {
			result = result2;
		}
		return result;
	}

	/**
	 * creates a divider based on the dimensions of the Board
	 * 
	 * @return divider
	 */

	public String createDivider() {
		String divider = "-";
		for (int i = DIMxp; i >= DIMxm; i--) {
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

	public String createOtherDivider() {
		String divider = "+";
		for (int i = DIMxp; i >= DIMxm; i--) {
			divider = divider + "----";
		}
		divider = divider + "---+";
		return divider;
	}

	/**
	 * Creates the String that is is the right length to fit with the board with
	 * x Coordinates
	 * 
	 * @return xCoords
	 */

	public String createxCoords() {
		String xCoords = " y/x|";
		for (int h = DIMxm; h <= DIMxp; h++) {
			if (h < 0) {
				if (h < -9) {
					xCoords = xCoords + h + "|";
				} else {
					xCoords = xCoords + h + " |";
				}
			} else {
				if (h > 9) {
					xCoords = xCoords + h + " |";
				} else {
					xCoords = xCoords + h + "  |";
				}
			}
		}
		return xCoords;
	}

	/**
	 * Uses the createxCoords, createDivider, createTileLine to create the main
	 * part of the Board print
	 * 
	 * @return List<String> of the print
	 */

	public List<String> createBoardPrint() {
		List<String> board = new ArrayList<String>();
		board.add(createxCoords());
		for (int k = DIMyp; k >= DIMym; k--) {
			board.add(createDivider());
			board.add(createTileLine(k));
		}
		board.add(createDivider());
		return board;
	}

	/**
	 * Creates the Tile line based on what y value it has
	 * 
	 * @param k
	 *            (y value)
	 * @return String of the line
	 */

	public String createTileLine(int k) {
		String tilesLine = "";
		if (k < 0) {
			if (k < -9) {
				tilesLine = k + ":|";
			} else {
				tilesLine = k + " :|";
			}
		} else {
			if (k > 9) {
				tilesLine = k + " :|";
			} else {
				tilesLine = k + "  :|";
			}
		}
		for (int g = DIMxm; g <= DIMxp; g++) {
			String tile = "   ";
			if (getTile(g, k) != null) {
				tile = getTile(g, k).toString();
			}
			tilesLine = tilesLine + tile + "|";
		}
		return tilesLine;
	}

	/**
	 * Creates the chat box with the past 5 messages from other users.
	 * 
	 * @return
	 */

	public List<String> createChatBox() {
		List<String> chat = new ArrayList<String>();
		chat.add(createOtherDivider());
		int j = 0;
		if (chat.size() == 0) {
		} else {
			if (chatEntry.size() > 5) {
				j = 5;
			} else {
				j = chatEntry.size();
			}
		}
		if (j != 0) {
			for (int i = chatEntry.size() - 1; i >= chatEntry.size() - j; i--) {
				chat.add(convertFormat(chatEntry.get(i)));
			}
		}
		String message = "Chat box:";
		chat.add(convertFormat(message));
		chat.add(createOtherDivider());
		return chat;
	}

	/**
	 * creates the console with the past 10 console messages
	 * 
	 * @return List<String> of console
	 */

	public List<String> createConsole() {
		List<String> console = new ArrayList<String>();
		console.add(createOtherDivider());
		int j = 0;
		if (console.size() == 0) {
		} else {
			if (consoleEntry.size() > 10) {
				j = 10;
			} else {
				j = consoleEntry.size();
			}
		}
		if (j != 0) {
			for (int i = consoleEntry.size() - 1; i >= consoleEntry.size() - j; i--) {
				console.add(convertFormat(consoleEntry.get(i)));
			}
		}
		String message = "Console:";
		console.add(convertFormat(message));
		console.add(createOtherDivider());
		return console;
	}

	/**
	 * Converts msg to a message that has the proper spacing and format
	 * 
	 * @param msg
	 * @return message
	 */

	public String convertFormat(String msg) {
		String message = "| " + msg;
		setDIM();
		int j = createOtherDivider().length() - message.length() - 2;
		for (int i = 0; i <= j; i++) {
			message = message + " ";
		}
		message = message + "|";
		return message;
	}

	/**
	 * Adds the msg to the consolebacklog
	 * 
	 * @param msg
	 */

	public void consoleEntry(String msg) {
		consoleEntry.add("  " + msg);
	}

	/**
	 * Adds the msg to the chatEntry, also uses the name where it came from and
	 * who it was sent from.
	 * 
	 * @param msg
	 */

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
	 * Prints 50 empty lines to clear the print area
	 */

	public void clear() {
		for (int i = 0; i < 50; i++)
			System.out.println();
	}

	/**
	 * creates the print used to display what Tiles the player has
	 * 
	 * @return List<String>
	 */

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

	public void update() {
		setDIM();
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