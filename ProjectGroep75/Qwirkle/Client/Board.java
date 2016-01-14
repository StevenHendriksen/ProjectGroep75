package Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Board {
	public int DIMxp;
	public int DIMxm;
	public int DIMyp;
	public int DIMym;

	private Map<String, Tile> tileLocs = new HashMap<String, Tile>();
	List<String> chatEntry = new ArrayList<String>();

	public static void main(String[] args) {
		Board board = new Board();
		board.chatEntry("Stan", "hoi Stan", false);
		board.chatEntry("you", "hoi Steven", true);
		board.update();
	}

	public Board() {
		setDIM();
		update();
	}

	public Board(Map<String, Tile> map) {
		tileLocs = map;
		setDIM();
	}

	public void putTile(int x, int y, int tile) {
		tileLocs.put(x + " " + y, new Tile(tile));
	}

	public int tileToInt(Tile tile) {
		int result = 0;
		result = tile.hasColor().colorToInt() * 6 + tile.hasShape().shapeToInt();
		return result;
	}

	

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
		DIMym = getLowest(x);
	}

	public int getHighest(List<String> list) {
		int highest = 0;
		for (int i = 0; i < list.size(); i++) {
			if ((new Integer(list.get(i)) > highest)) {
				highest = new Integer(list.get(i));
			}
		}
		return highest;
	}

	public int getLowest(List<String> list) {
		int lowest = 0;
		for (int i = 0; i < list.size(); i++) {
			if ((new Integer(list.get(i)) < lowest)) {
				lowest = new Integer(list.get(i));
			}
		}
		return lowest;
	}

	public Map<String, Tile> getCubeLocs() {
		return tileLocs;
	}

	public Tile getTile(int k, int g) {
		Tile result = null;
		Tile result2 = tileLocs.get(k + " " + g);
		if (result2 != null) {
			result = result2;
		}
		return result;
	}

	public String createDivider() {
		String divider = "-";
		for (int i = DIMxp; i >= DIMxm; i--) {
			divider = divider + "---+";
		}
		divider = divider + "---+";
		return divider;
	}

	public String createOtherDivider() {
		String divider = "+";
		for (int i = DIMxp; i >= DIMxm; i--) {
			divider = divider + "----";
		}
		divider = divider + "---+";
		return divider;
	}

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

	public String createTileLine(int k) {
		String cubesLine = "";
		if (k < 0) {
			if (k < -9) {
				cubesLine = k + ":|";
			} else {
				cubesLine = k + " :|";
			}
		} else {
			if (k > 9) {
				cubesLine = k + " :|";
			} else {
				cubesLine = k + "  :|";
			}
		}
		for (int g = DIMxm; g <= DIMxp; g++) {
			String cube = "   ";
			if (getTile(g, k) != null) {
				cube = getTile(g, k).toString();
			}
			cubesLine = cubesLine + cube + "|";
		}
		return cubesLine;
	}

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
				chat.add(chatEntry.get(i));
				chat.add(createOtherDivider());
			}
		}
		chat.add("| Chat Box:                                                                             |");
		chat.add(createOtherDivider());
		return chat;
	}

	public void chatEntry(String name, String msg, boolean bool) {
		if (bool) {
			String message = "| to " + name + ": " + msg;
			setDIM();
			System.out.println(createOtherDivider().length());
			for(int i = 0; i <= createOtherDivider().length() - 9 - name.length() - msg.length() ; i++){
				message = message + " ";
			}
			message = message + "|";
			chatEntry.add(message);
		} else {
			String message = "| from " + name + ": " + msg;
			setDIM();
			System.out.println(createOtherDivider().length());
			for(int i = 0; i <= createOtherDivider().length() - 11 - name.length() - msg.length() ; i++){
				message = message + " ";
			}
			message = message + "|";
			chatEntry.add(message);
		}
	}

	public void clear() {
		for (int i = 0; i < 50; i++)
			System.out.println();
	}

	public void update() {
		setDIM();
		clear();
		List<String> board = createBoardPrint();
		board.addAll(board.size(), createChatBox());
		for (int j = board.size(); j > 0; j--) {
			System.out.println(board.get(j - 1));
		}
	}
}