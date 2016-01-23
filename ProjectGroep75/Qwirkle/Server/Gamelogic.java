package Server;

import java.util.ArrayList;
import java.util.List;

public class Gamelogic {
	private Bag bag;
	private Serverboard board;
	private Servertile tile;
	private String SERVER_MOVE_TRADE = "MOVEOK_TRADE";
	private String SERVER_MOVE_PUT = "MOVEOK_PUT";
	private String SERVER_GAMEEND = "GAMEEND";
	private String SERVER_PASS = "PASS";
	private List<Player> players = new ArrayList<Player>();
	private int current = 0;

	// OK
	public Gamelogic(Serverboard board, Bag bag) {
		this.board = board;
		this.bag = bag;
	}

	// OK
	public Servertile drawTile() {
		return bag.takeTile();
	}

	// OK
	public String moveOkTrade() {
		String result = "0";

		if (!bag.emptyBag()) {
			result = SERVER_MOVE_TRADE;
		}

		return result;
	}

	// Nog naar kijken
	public String moveOkPut(int tile, int x, int y) {
		String result = "ERROR Invalid move";
		int result2 = 0;
		int result3 = 0;

		this.tile = new Servertile(tile);

		if (board.getTile(x, y) == null) {
			if (board.getTile(x + 1, y) == null) {
				result3 = result3 + 1;
			} else if (!equal(board.getTile(x + 1, y), this.tile)
					&& (board.getTile(x + 1, y).hasColor() == this.tile.hasColor()
							|| board.getTile(x + 1, y).hasShape() == this.tile.hasShape())) {
				result2 = result2 + 1;
			}
			if (board.getTile(x - 1, y) == null) {
				result3 = result3 + 1;
			} else if (!equal(board.getTile(x - 1, y), this.tile)
					&& (board.getTile(x - 1, y).hasColor() == this.tile.hasColor()
							|| board.getTile(x - 1, y).hasShape() == this.tile.hasShape())) {
				result2 = result2 + 1;
			}
			if (board.getTile(x, y + 1) == null) {
				result3 = result3 + 1;
			} else if (board.getTile(x, y + 1) != this.tile
					&& (board.getTile(x, y + 1).hasColor() == this.tile.hasColor()
							|| board.getTile(x, y + 1).hasShape() == this.tile.hasShape())) {
				result2 = result2 + 1;
			}
			if (board.getTile(x, y - 1) == null) {
				result3 = result3 + 1;
			} else if (board.getTile(x, y - 1) != this.tile
					&& (board.getTile(x, y - 1).hasColor() == this.tile.hasColor()
							|| board.getTile(x, y - 1).hasShape() == this.tile.hasShape())) {
				result2 = result2 + 1;
			}
		}

		if (result2 > 0 && result3 > 0 || (result3 == 4 && x == 0 && y == 0 && this.numberTurn() == 0)) {
			result = SERVER_MOVE_PUT;
		}

		return result;
	}

	// Ok
	public boolean equal(Servertile tile, Servertile tile2) {
		boolean result = false;

		System.out.println(tile.hasColor());
		System.out.println(tile2.hasColor());
		System.out.println(tile.hasShape());
		System.out.println(tile2.hasShape());

		if (tile.hasColor() == tile2.hasColor() && tile.hasShape() == tile2.hasShape()) {
			result = true;
		}

		return result;
	}

	// Ok
	public String gameEnd() {
		String result = "";

		if (bag.emptyBag()) {
			result = SERVER_GAMEEND;
		}

		return result;
	}

	// Nog naar kijken
	public String movePass(Player player) {
		String result = "";

		for (int i = 0; i < 6; i++) {
			if (player.hasTiles()[i] == this.tile) {
				result = SERVER_PASS;
			}
		}

		return result;
	}

	// Ok
	public void putPlayer(Player player) {
		players.add(player);
	}

	// Ok
	public List<Player> hasPlayers() {
		return players;
	}

	// Ok
	public boolean gameStart(int size) {
		boolean result = false;

		if (players.size() > size) {
			result = true;
		}

		return result;
	}

	// Ok
	public Player turn() {
		return players.get(current % 4);
	}

	// Ok
	public Player nextTurn() {
		int result = 0;

		if (current % 4 < 3) {
			result = current % 4;
		} else {
			result = 0;
		}
		return players.get(result);
	}

	// Ok
	public void movePut(int x, int y, int tile) {
		board.putTile(x, y, tile);
		this.score(x, y, tile);
	}

	public void score(int x, int y, int tile) {
		this.tile = new Servertile(tile);
		int score = 0;
		
		
		if (board.getTile(x + 1, y).hasColor() == this.tile.hasColor()) {
			score = score + 1;
			for(int i = 1; i < 5; i ++){
				if(board.getTile(x + i + 1, y).hasColor() == board.getTile(x + i, y).hasColor()){
					score = score + 1;
				}
				else{
					break;
				}
			}
		}
		if (board.getTile(x - 1, y).hasColor() == this.tile.hasColor()) {
			score = score + 1;
			for(int i = 1; i < 5; i ++){
				if(board.getTile(x - i - 1, y).hasColor() == board.getTile(x - i, y).hasColor()){
					score = score + 1;
				}
				else{
					break;
				}
			}
		}
		if (board.getTile(x, y + 1).hasColor() == this.tile.hasColor()) {
			score = score + 1;
			for(int i = 1; i < 5; i ++){
				if(board.getTile(x, y + i + 1).hasColor() == board.getTile(x, y + i).hasColor()){
					score = score + 1;
				}
				else{
					break;
				}
			}
		}
		if (board.getTile(x, y - 1).hasColor() == this.tile.hasColor()) {
			score = score + 1;
			for(int i = 1; i < 5; i ++){
				if(board.getTile(x, y - i - 1).hasColor() == board.getTile(x, y - i).hasColor()){
					score = score + 1;
				}
				else{
					break;
				}
			}
		}
		if (board.getTile(x + 1, y).hasShape() == this.tile.hasShape()) {
			score = score + 1;
			for(int i = 1; i < 5; i ++){
				if(board.getTile(x + i + 1, y).hasShape() == board.getTile(x + i, y).hasShape()){
					score = score + 1;
				}
				else{
					break;
				}
			}
		}
		if (board.getTile(x - 1, y).hasShape() == this.tile.hasShape()) {
			score = score + 1;
			for(int i = 1; i < 5; i ++){
				if(board.getTile(x - i - 1, y).hasShape() == board.getTile(x - i, y).hasShape()){
					score = score + 1;
				}
				else{
					break;
				}
			}
		}
		if (board.getTile(x, y + 1).hasShape() == this.tile.hasShape()) {
			score = score + 1;
			for(int i = 1; i < 5; i ++){
				if(board.getTile(x, y + i + 1).hasShape() == board.getTile(x, y + i).hasShape()){
					score = score + 1;
				}
				else{
					break;
				}
			}
		}
		if (board.getTile(x, y - 1).hasShape() == this.tile.hasShape()) {
			score = score + 1;
			for(int i = 1; i < 5; i ++){
				if(board.getTile(x, y - i - 1).hasShape() == board.getTile(x, y - i).hasShape()){
					score = score + 1;
				}
				else{
					break;
				}
			}
		}
		
	turn().changeScore(score);
	}

	// Ok
	public void moveTrade(int number) {
		bag.putTile(number);
		bag.takeTile();
	}

	// Ok
	public int numberTurn() {
		return current;
	}
}
