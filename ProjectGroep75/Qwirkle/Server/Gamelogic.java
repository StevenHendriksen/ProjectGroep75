package Server;

public class Gamelogic {
	private Bag bag;
	private Serverboard board;
	private Servertile tile;
	private String SERVER_MOVE_TRADE = "MOVEOK_TRADE";
	private String SERVER_MOVE_PUT = "MOVEOK_PUT";
	private String SERVER_DRAWTILE = "DRAWTILE";

	public String drawTile() {
		bag.takeTile();
		return SERVER_DRAWTILE;
	}

	public String moveOkTrade(Servertile tile) {
		String result = "0";

		this.tile = tile;

		if (!bag.emptyBag()) {
			result = SERVER_MOVE_TRADE;
		}

		return result;
	}

	public String moveOkPut(int tile, int x, int y) {
		String result = "0";

		this.tile = new Servertile(tile);

		if (board.getTile(x, y) == null) {
			if (board.getTile(x + 1, y) != this.tile && (board.getTile(x + 1, y).hasColor() == this.tile.hasColor()
					|| board.getTile(x + 1, y).hasShape() == this.tile.hasShape())) {

			}
		}

		return result;
	}
}
