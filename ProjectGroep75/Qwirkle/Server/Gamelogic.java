package Server;

public class Gamelogic {
	private Bag bag;
	private Serverboard board;
	private Servertile tile;
	private String SERVER_MOVE_TRADE = "MOVEOK_TRADE";
	private String SERVER_MOVE_PUT = "MOVEOK_PUT";
	private String SERVER_DRAWTILE = "DRAWTILE";
	private String SERVER_GAMEEND = "GAMEEND";
	private String SERVER_PASS = "PASS";

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
		String result = "ERROR Invalid move";
		int result2 = 0;
		int result3 = 0;
		
		this.tile = new Servertile(tile);

		if (board.getTile(x, y) == null) {
			if (board.getTile(x + 1, y) != this.tile && (board.getTile(x + 1, y).hasColor() == this.tile.hasColor()
					|| board.getTile(x + 1, y).hasShape() == this.tile.hasShape())) {
				result2 = result2 + 1;
			}
			else if(board.getTile(x + 1, y) == null){
				result3 = result3 + 1;
			}
			if (board.getTile(x - 1, y) != this.tile && (board.getTile(x - 1, y).hasColor() == this.tile.hasColor()
					|| board.getTile(x - 1, y).hasShape() == this.tile.hasShape())) {
				result2 = result2 + 1;
			}
			else if(board.getTile(x - 1, y) == null){
				result3 = result3 + 1;
			}
			if (board.getTile(x, y + 1) != this.tile && (board.getTile(x, y + 1).hasColor() == this.tile.hasColor()
					|| board.getTile(x, y + 1).hasShape() == this.tile.hasShape())) {
				result2 = result2 + 1;
			}
			else if(board.getTile(x, y + 1) == null){
				result3 = result3 + 1;
			}
			if (board.getTile(x, y - 1) != this.tile && (board.getTile(x, y - 1).hasColor() == this.tile.hasColor()
					|| board.getTile(x, y - 1).hasShape() == this.tile.hasShape())) {
				result2 = result2 + 1;
			}
			else if(board.getTile(x, y - 1) == null){
				result3 = result3 + 1;
			}
		}
		
		if(result2 > 0 && result3 > 0){
			result = SERVER_MOVE_PUT;
		}

		return result;
	}
	
	public String gameEnd(){
		String result = "";
		
		if(bag.emptyBag()){
			result = SERVER_GAMEEND;
		}
		
		return result;
	}
	
	public String movePass(Player player){
		String result = "";
		
		for(int i = 0; i < 6; i++){
			if(player.hasTiles()[i] == this.tile){
				result = SERVER_PASS;
			}
		}
		
		return result;
	}
}
