package server;

import java.util.ArrayList;
import java.util.List;

import Shared.*;

public class Gamelogic {
	// ------------------ Instance variables ----------------
	private Bag bag;
	private Board board;
	private Tile tile;
	private int current = 0;
	private int pass = 0;

	// @ private invariant players.length >= 0;
	private List<Player> players = new ArrayList<Player>();

	// ------------------ Constructor ------------------------
	/**
	 * The constructor that concerns all the rules of the game.
	 * 
	 * @param board
	 *            The board, which is used in the game
	 * @param bag
	 *            The bag, which is used in the game
	 */
	public Gamelogic(Board board, Bag bag) {
		this.board = board;
		this.bag = bag;
	}

	/**
	 * The method that takes a tile from the bag.
	 */
	public Tile drawTile(Player player) {
		Tile tile = bag.takeTile();
		try {
			player.getConnection().write("DRAWTILE " + tile.tileToInt(tile), player.getConnection().getOut());
		} catch (NullPointerException e) {
			String sendall = "GAMEEND";
			for (int p = 0; p < players.size(); p++) {
				sendall = sendall + " " + players.get(p).hasScore() + "," + players.get(p).hasName();
			}
			player.getConnection().getServer().sendAll(sendall);
		}
		return bag.takeTile();
	}

	/**
	 * The method that checks whether you can do a trade or not. For this the
	 * bag must not be empty. Current has to be higher than the number of
	 * players. This means all players have played a move.
	 */
	// @ ensures \result == (!bag.emptyBag());
	public String moveOkTrade() {
		String result = "0";

		if (!bag.emptyBag() && current >= players.size()) {
			result = "MOVEOK_TRADE";
		}

		return result;
	}

	/**
	 * This method checks if your move is correct or not.
	 * 
	 * @param tile
	 *            (the tile to put)
	 * @param xcoord
	 *            (the x coordinate to put it)
	 * @param ycoord
	 *            (the y coordinate to put it)
	 * @return (boolean of whether it's allowed)
	 */
	/*
	 * requires tile >= 0 && tile <= 36; requires x <= board.getdimXp() && x >=
	 * board.getdimXm(); requires y <= board.getdimYp() && y >=
	 * board.getdimYm();
	 * 
	 * ensures \result == (board.getTile(x,y) == null && (
	 */
	public String moveOkPut(int tile, int xcoord, int ycoord) {
		assert tile <= 36 && tile >= 0;
		assert xcoord <= board.getdimXp() && xcoord >= board.getdimXm();
		assert ycoord <= board.getdimYp() && ycoord >= board.getdimYm();

		String result = "ERROR Invalid move";

		this.tile = new Tile(tile);

		int empty = 0;
		int max_x = 0;
		int min_x = 0;
		int max_y = 0;
		int min_y = 0;

		if (board.getTile(xcoord, ycoord) == null) {
			if (xcoord == 0 && ycoord == 0) {
				result = "MOVEOK_PUT";
			}

			if (board.getTile(xcoord + 1, ycoord) == null) {
				empty = empty + 1;
			}

			if (board.getTile(xcoord - 1, ycoord) == null) {
				empty = empty + 1;
			}

			if (board.getTile(xcoord, ycoord + 1) == null) {
				empty = empty + 1;
			}

			if (board.getTile(xcoord, ycoord - 1) == null) {
				empty = empty + 1;
			}

			if (empty > 0) {
				for (int i = 0; i < 6; i++) {
					if ((board.getTile(xcoord + i, ycoord).getColor() != this.tile.getColor()
							|| equal(board.getTile(xcoord + i, ycoord), this.tile)) && max_x == 0) {
						break;
					} else if (board.getTile(xcoord + i, ycoord) == null) {
						max_x = i;
					}

					if ((board.getTile(xcoord - i, ycoord).getColor() != this.tile.getColor()
							|| equal(board.getTile(xcoord - i, ycoord), this.tile)) && min_x == 0) {
						break;
					} else if (board.getTile(xcoord - i, ycoord) == null) {
						min_x = i;
					}

					if ((board.getTile(xcoord, ycoord + i).getColor() != this.tile.getColor()
							|| equal(board.getTile(xcoord, ycoord + i), this.tile)) && max_y == 0) {
						break;
					} else if (board.getTile(xcoord, ycoord + i) == null) {
						max_y = i;
					}

					if ((board.getTile(xcoord, ycoord - i).getColor() != this.tile.getColor()
							|| equal(board.getTile(xcoord, ycoord - i), this.tile)) && min_y == 0) {
						break;
					} else if (board.getTile(xcoord, ycoord - 1) == null) {
						min_y = i;
					}
				}

				result = "MOVEOK_PUT";
			}
		}

		return result;
	}

	/**
	 * The method that checks whether tile and tile2 are equal.
	 * 
	 * @return (boolean whether or not they are equal)
	 */
	// @ requires tile != null;
	// @ requires tile2 != null;
	// @ ensures \result == (this.hasColor() == tile2.hasColor() &&
	// tile.hasShape() == tile2.hasShape());
	public boolean equal(Tile tile, Tile tile2) {
		boolean result = false;

		if (tile.getColor() == tile2.getColor() && tile.getShape() == tile2.getShape()) {
			result = true;
		}

		return result;
	}

	/**
	 * The method that checks whether the game is over or not.
	 * 
	 * @return (returns GAMEEND if bag is empty and all players have passed)
	 * @return (returns GAMEEND if bag is empty and a players doesn't have tiles
	 *         anymore)
	 */
	// @ \result == (bag.emptyBag());
	public String gameEnd() {
		String result = "";
		
		if (bag.emptyBag()) {
			if (pass == players.size()) {
				result = "GAMEEND";
			}

			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).hasTiles().length == 0) {
					players.get(i).changeScore(6);
					result = "GAMEEND";
				}
			}
		}

		return result;
	}

	/**
	 * The method that makes the player pass.
	 */
	// @ requires (\exists int i; i <= 0 & i < player.size();
	// this.hasPlayers().get(i) == player)
	public String movePass(Player player) {
		this.nextTurn();
		pass = pass + 1;
		return "PASS";
	}

	/**
	 * The method that puts the player to a list.
	 * 
	 * @param player
	 *            (the player to be added)
	 */
	// ensures \old(this.hasPlayers().size()) == this.hasPlayers().size() - 1;
	public void putPlayer(Player player) {
		boolean result = true;

		if (players.size() != 0) {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).hasName().equals(player.hasName())) {
					result = false;
					break;
				}
			}
			if (result && players.size() > 0) {
				players.add(player);
			}
		} else {
			players.add(player);
		}
	}

	/**
	 * The method that returns a list of all players.
	 */
	/* @ pure */
	public List<Player> hasPlayers() {
		return players;
	}

	/**
	 * The method that starts the game.
	 * 
	 * @return (returns true if succesful checks whether there are enough
	 *         players (more than size) or not and whether size is greater than
	 *         1 and smaller than 5.)
	 */
	// @requires size > 1 && size < 5;
	// @ensures \result == (players.size() >= size);
	public boolean gameStart(int size) {
		assert size > 1 && size < 5;

		boolean result = false;
		if (size > 1 && size < 5) {
			if (players.size() >= size) {
				result = true;
			}

			if (result) {
				for (int i = 0; i < size; i++) {
					players.get(i).getTiles();
				}
			}
		}
		return result;
	}

	/**
	 * The method that returns the turn of the current player.
	 */
	/* @ pure */
	public Player turn() {
		return players.get(current % players.size());
	}

	/**
	 * The method that returns the turn of the next player and makes it move a
	 * turn further.
	 * 
	 * @returns (the player that has the bext turn)
	 */
	// @ ensures this.numberTurn() == \old(this.numberTurn()) + 1;
	public Player nextTurn() {
		int result = 0;
		current = current + 1;

		result = current % players.size();

		return players.get(result);
	}

	/**
	 * The method that puts the tile on the board.
	 * 
	 * @param xcoord
	 *            (the xcoord where the tile will be put)
	 * @param ycoord
	 *            (the ycoord where the tile will be put)
	 * @param tile
	 *            (the tile that will be put there)
	 */
	/*
	 * @requires tile >= 0 && tile <= 36; requires x <= board.getdimXp() && x >=
	 * board.getdimXm(); requires y <= board.getdimYp() && y >=
	 * board.getdimYm(); ensures board.getTile(x , y).hasColor() == new
	 * Servertile(tile).hasColor(); ensures board.getTile(x , y).hasShape() ==
	 * new Servertile(tile).hasShape();
	 * 
	 * @
	 */
	public void movePut(int xcoord, int ycoord, int tile) {
		assert tile <= 36 && tile >= 0;
		assert xcoord <= board.getdimXp() + 1 && xcoord >= board.getdimXm() - 1;
		assert ycoord <= board.getdimYp() + 1 && ycoord >= board.getdimYm() - 1;
		if (moveOkPut(tile, xcoord, ycoord).equals("MOVEOK_PUT")) {
			board.putTile(xcoord, ycoord, tile);
		}

		pass = 0;
	}

	/**
	 * The method that calculates the score.
	 * 
	 * @param score
	 *            checks the score of the current move.
	 * @param qwirkle1
	 *            checks whether there is qwirkle horizontale in color.
	 * @param qwirkle2
	 *            checks whether there is qwirkle horizontale in shape. `
	 *            * @param qwirkle3 checks whether there is qwirkle verticale in
	 *            color.
	 * @param qwirkle4
	 *            checks whether there is qwirkle verticale in shape.
	 */
	/*
	 * @requires tile >= 0 && tile <= 36; requires x <= board.getdimXp() && x >=
	 * board.getdimXm(); requires y <= board.getdimYp() && y >=
	 * board.getdimYm();
	 * 
	 * ensures \old(turn().hasScore()) != turn().hasScore();
	 * 
	 * @
	 */
	public void score(int xcoord, int ycoord, int tile, Player player) {
		assert tile <= 36 && tile >= 0;
		assert xcoord <= board.getdimXp() && xcoord >= board.getdimXm();
		assert ycoord <= board.getdimYp() && ycoord >= board.getdimYm();
		this.tile = new Tile(tile);
		int score = 1;
		int qwirkle1 = 1;
		int qwirkle2 = 1;
		int qwirkle3 = 1;
		int qwirkle4 = 1;

		if (board.getTile(xcoord + 1, ycoord) != null) {
			if (board.getTile(xcoord + 1, ycoord).getColor().equals(this.tile.getColor())) {
				score = score + 1;
				qwirkle1 = qwirkle1 + 1;
				for (int i = 1; i < 5; i++) {
					if (board.getTile(xcoord + i + 1, ycoord) != null && board.getTile(xcoord + i + 1, ycoord)
							.getColor() == board.getTile(xcoord + i, ycoord).getColor()) {
						score = score + 1;
						qwirkle1 = qwirkle1 + 1;
					} else {
						break;
					}
				}
			} else if (board.getTile(xcoord + 1, ycoord).getShape() == this.tile.getShape()) {
				score = score + 1;
				qwirkle2 = qwirkle2 + 1;
				for (int i = 1; i < 5; i++) {
					if (board.getTile(xcoord + i + 1, ycoord) != null && board.getTile(xcoord + i + 1, ycoord)
							.getShape() == board.getTile(xcoord + i, ycoord).getShape()) {
						score = score + 1;
						qwirkle2 = qwirkle2 + 1;
					} else {
						break;
					}
				}
			}
			if (qwirkle1 == 6 || qwirkle2 == 6) {
				score = score + 6;
			}
		}

		if (board.getTile(xcoord - 1, ycoord) != null) {
			if (board.getTile(xcoord - 1, ycoord).getColor() == this.tile.getColor()) {
				score = score + 1;
				qwirkle1 = qwirkle1 + 1;
				for (int i = 1; i < 5; i++) {
					if (board.getTile(xcoord - i - 1, ycoord) != null && board.getTile(xcoord - i - 1, ycoord)
							.getColor() == board.getTile(xcoord - i, ycoord).getColor()) {
						score = score + 1;
						qwirkle1 = qwirkle1 + 1;
					} else {
						break;
					}
				}
			} else if (board.getTile(xcoord - 1, ycoord).getShape() == this.tile.getShape()) {
				score = score + 1;
				qwirkle2 = qwirkle2 + 1;
				for (int i = 1; i < 5; i++) {
					if (board.getTile(xcoord - i - 1, ycoord) != null && board.getTile(xcoord - i - 1, ycoord)
							.getShape() == board.getTile(xcoord - i, ycoord).getShape()) {
						score = score + 1;
						qwirkle2 = qwirkle2 + 1;
					} else {
						break;
					}
				}
			}
			if (qwirkle1 == 6 || qwirkle2 == 6) {
				score = score + 6;
			}
		}

		if (board.getTile(xcoord, ycoord + 1) != null) {
			if (board.getTile(xcoord, ycoord + 1).getColor() == this.tile.getColor()) {
				score = score + 1;
				qwirkle3 = qwirkle3 + 1;
				for (int i = 1; i < 5; i++) {
					if (board.getTile(xcoord, ycoord + i + 1) != null && board.getTile(xcoord, ycoord + i + 1)
							.getColor() == board.getTile(xcoord, ycoord + i).getColor()) {
						score = score + 1;
						qwirkle3 = qwirkle3 + 1;
					} else {
						break;
					}
				}
			} else if (board.getTile(xcoord, ycoord + 1).getShape() == this.tile.getShape()) {
				score = score + 1;
				qwirkle4 = qwirkle4 + 1;
				for (int i = 1; i < 5; i++) {
					if (board.getTile(xcoord, ycoord + i + 1) != null && board.getTile(xcoord, ycoord + i + 1)
							.getShape() == board.getTile(xcoord, ycoord + i).getShape()) {
						score = score + 1;
						qwirkle4 = qwirkle4 + 1;
					} else {
						break;
					}
				}
			}
			if (qwirkle3 == 6 || qwirkle4 == 6) {
				score = score + 6;
			}
		}

		if (board.getTile(xcoord, ycoord - 1) != null) {
			if (board.getTile(xcoord, ycoord - 1).getColor() == this.tile.getColor()) {
				score = score + 1;
				qwirkle3 = qwirkle3 + 1;
				for (int i = 1; i < 5; i++) {
					if (board.getTile(xcoord, ycoord - i - 1) != null && board.getTile(xcoord, ycoord - i - 1)
							.getColor() == board.getTile(xcoord, ycoord - i).getColor()) {
						score = score + 1;
						qwirkle3 = qwirkle3 + 1;
					} else {
						break;
					}
				}
			} else if (board.getTile(xcoord, ycoord - 1).getShape() == this.tile.getShape()) {
				score = score + 1;
				qwirkle4 = qwirkle4 + 1;
				for (int i = 1; i < 5; i++) {
					if (board.getTile(xcoord, ycoord - i - 1) != null && board.getTile(xcoord, ycoord - i - 1)
							.getShape() == board.getTile(xcoord, ycoord - i).getShape()) {
						score = score + 1;
						qwirkle4 = qwirkle4 + 1;
					} else {
						break;
					}
				}
			}

			if (qwirkle3 == 6 || qwirkle4 == 6) {
				score = score + 6;
			}
		}
		player.changeScore(score);
	}

	/**
	 * The method that does the trade.
	 * 
	 * @param number
	 *            (the number of the tile that gets traded)
	 */
	/*
	 * @ requires number <= 36 && number >= 0; ensures \old(turn().hasTiles())
	 * != turn().hasTiles();
	 * 
	 * @
	 */
	public void moveTrade(int number) {
		bag.putTile(number);

		Tile instancetile = new Tile(number);

		for (int i = 0; i < 6; i++) {
			if (equal(turn().hasTiles()[i], instancetile)) {
				turn().changeTiles(bag.takeTile(), i);
			}
		}

		pass = 0;
	}

	/**
	 * The method that returns the current turn.
	 */
	// @ pure;
	public int numberTurn() {
		return current;
	}

	/**
	 * returns the player that has the connection.
	 * 
	 * @param connection
	 *            (the connection)
	 * @return (player that has connection)
	 */

	public Player getPlayer(Connection connection) {
		Player result = null;

		for (int p = 0; p < players.size(); p++) {
			if (players.get(p).getConnection().equals(connection)) {
				result = players.get(p);
			}
		}
		return result;
	}
}
