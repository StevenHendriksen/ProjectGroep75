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

	private Map<String, Cube> cubeLocs = new HashMap<String, Cube>();

	public static void main(String[] args) {
		Board board = new Board();
		board.print();
	}

	public Board() {
		cubeLocs.put("0 0", new Cube(Color.RED, Shape.CIRCLE));
		cubeLocs.put("0 1", new Cube(Color.RED, Shape.CIRCLE));
		cubeLocs.put("0 2", new Cube(Color.RED, Shape.CIRCLE));
		cubeLocs.put("0 3", new Cube(Color.RED, Shape.CIRCLE));
		cubeLocs.put("0 4", new Cube(Color.RED, Shape.CIRCLE));
		cubeLocs.put("1 3", new Cube(Color.RED, Shape.CIRCLE));
		cubeLocs.put("33 43", new Cube(Color.RED, Shape.CIRCLE));
		cubeLocs.put("-40 2", new Cube(Color.RED, Shape.CIRCLE));
		cubeLocs.put("3 3", new Cube(Color.RED, Shape.CIRCLE));
		cubeLocs.put("1 -41", new Cube(Color.RED, Shape.CIRCLE));
		setDIM();
	}
	
	public Board(Map<String, Cube> map){
		cubeLocs = map;
		setDIM();
	}

	public void putCube(int x, int y, Cube cube) {
		cubeLocs.put(x + " " + y, cube);
	}

	public void setDIM() {
		List<String> x = new ArrayList<String>();
		List<String> y = new ArrayList<String>();
		for (String k : cubeLocs.keySet()) {
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

	public Map<String, Cube> getCubeLocs() {
		return cubeLocs;
	}

	public Cube getCube(int k, int g) {
		Cube result = null;
		Cube result2 = cubeLocs.get(k + " " + g);
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
		for (int k = DIMym; k < DIMyp + 1; k++) {
			board.add(createDivider());
			board.add(createCubeLine(k));
		}
		board.add(createDivider());
		board.add(createxCoords());
		return board;
	}

	public String createCubeLine(int k) {
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
			if (getCube(g, k) != null) {
				cube = getCube(g, k).toString();
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