package Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Board {
	public int DIMx;
	public int DIMy;
	private Map<String, String> pieces = new HashMap<String, String>();

	public static void main(String[] args) {
		Board board = new Board(3, 2);
		board.putPiece(0, 0, "1");
		board.putPiece(1, 1, "1");
		board.putPiece(2, 2, "1");
		board.putPiece(3, 3, "1");
		board.putPiece(-1, -1, "1");
		board.putPiece(-2, -2, "1");
		board.print();
	}

	public Board(int x, int y) {
		DIMx = x;
		DIMy = y;
	}

	public void putPiece(int x, int y, String piece) {
		pieces.put(x + "," + y, piece);
	}

	public Map<String, String> getPieces() {
		return pieces;
	}

	public String getPiece(int k, int g) {
		String result = "0";
		String result2 = pieces.get(k + "," + g);
		if (result2 != null) {
			result = result2;
		}
		return result;
	}

	public void print() {
		List<String> bord = new ArrayList<String>();
		String divider = "";
		int i = DIMx;
		while (i >= -DIMx) {
			divider = divider + "---+";
			i = i - 1;
		}
		divider = divider + "---+";
		String xCoords = "   +";
		int h = -DIMx;
		while (h < DIMx) {
			if(h < 0){
			xCoords = xCoords + "" + h + " |";
			} else{
				xCoords = xCoords + " " + h + " |";
			}
			h = h + 1;
		}
		xCoords = xCoords + " " + h + " |";
		bord.add(xCoords);
		bord.add(divider);
		int k = -DIMy;
		while (k < DIMy) {
			String piecesLine;
			if(k + 1 < 0){
			piecesLine = k + 1 + ":|";
			} else{
			piecesLine = k + 1 + ": |";
			}
			int g = -DIMx;
			while (g <= DIMx) {
				piecesLine = piecesLine + " " + getPiece(k + 1, g) + " |";
				g = g + 1;
			}
			bord.add(piecesLine);
			bord.add(divider);
			k = k + 1;
		}
		int j = bord.size();
		while (j > 0) {
			System.out.println(bord.get(j - 1));
			j = j - 1;
		}
		System.out.println("Done printing");
	}
}