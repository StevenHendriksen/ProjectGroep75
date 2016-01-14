package Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Server.Tile;

import java.util.HashMap;

public class Board {
	public int DIMxp;
	public int DIMxm;
	public int DIMyp;
	public int DIMym;

	private Map<String, Tile> tileLocs = new HashMap<String, Tile>();

	public static void main(String[] args) {
		Board board = new Board();
		board.print();
	}

	public Board() {
		tileLocs.put("0 0", new Tile(1));
		tileLocs.put("0 1", new Tile(1));
		tileLocs.put("0 2", new Tile(1));
		tileLocs.put("0 3", new Tile(1));
		tileLocs.put("0 4", new Tile(1));
		tileLocs.put("1 3", new Tile(1));
		tileLocs.put("33 43", new Tile(1));
		tileLocs.put("-40 2", new Tile(1));
		tileLocs.put("3 3", new Tile(1));
		tileLocs.put("1 -41", new Tile(1));
		setDIM();
	}

	public Board(Map<String, Tile> map) {
		tileLocs = map;
		setDIM();
	}

	public void putTile(int x, int y, Tile tile) {
		tileLocs.put(x + " " + y, tile);
	}

	public int tileToInt(Tile tile){
		int result = 0;
		result = tile.hasColor().colorToInt() * 6 + tile.hasShape().shapeToInt();
		return result;
	}
	
	public Tile intToTile(int i){
		int color = i % 6;
		int shape = i - color;
		return new Tile(intToColor(color), intToShape(shape));
	}
	
	public Shape intToShape(int i) {
        if (i == 1) {
            return Shape.CIRCLE;
        } else if (i == 2) {
            return Shape.CRISSCROSS;
        } else if (i == 3) {
            return Shape.DIAMOND;
        } else if (i == 4) {
            return Shape.SQUARE;
        } else if (i == 5) {
            return Shape.PLUS;
        } else if (i == 6) {
            return Shape.STAR;
        } else {
            return null;
        }
    }
	
	public Color intToColor(int i){
        if (i == 1) {
            return Color.RED;
        } else if (i == 2) {
            return Color.ORANGE;
        } else if (i == 3) {
            return Color.YELLOW;
        } else if (i == 4) {
            return Color.GREEN;
        } else if (i == 5) {
            return Color.BLUE;
        } else if (i == 6) {
            return Color.PURPLE;
        } else {
        	return null;
        }
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

	public String createxCoords() {
		String xCoords = "    |";
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
		for (int k = DIMyp; k >= DIMym - 1; k--) {
			board.add(createDivider());
			board.add(createTileLine(k));
		}
		board.add(createDivider());
		board.add(createxCoords());
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
			System.out.println(g + " " + k);
			String cube = "   ";
			if (getTile(g, k) != null) {
				cube = getTile(g, k).toString();
			}
			cubesLine = cubesLine + cube + "|";
		}
		return cubesLine;
	}

	public void print() {
		List<String> board = createBoardPrint();
		for (int j = board.size(); j > 0; j--) {
			System.out.println(board.get(j - 1));
		}
		System.out.println("Done printing");
	}
}