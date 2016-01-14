package Server;

import Client.Board;

public class Gamelogic {
	private Bag bag;
	private Board board;
	private Tile tile;
	private String SERVER_MOVE_TRADE = "MOVEOK_TRADE";
    private String SERVER_MOVE_PUT = "MOVEOK_PUT";
    private String SERVER_DRAWTILE = "DRAWTILE";
    
    public String drawTile(){
    	bag.takeTile();
    	return "SERVER_DRAWTILE";
    }
    
	//The if loop needs to be done again.
	public String moveOkTrade(Tile tile){		
		String result = "0";
		
		if(bag.emptyBag()){
			result = SERVER_MOVE_TRADE;
		}
		
		return result;
	}

    public String moveOkPut(int tile, int x, int y){
    	String result = "0";
    	
    	this.tile = new Tile(tile);
    	
    	int intcolor = tile / 6 + 1;
    	int intshape = tile % 6;
    	if (intshape == 0){
    		intshape = 6;
    	}
    	
    	this.tile.intToColor(intcolor);
    	Color color = this.tile.hasColor();
    	this.tile.intToShape(intshape);
    	Shape shape = this.tile.hasShape();
    	
    	int result2 = 0;
    	int result3 = 0;
    	
    	if(board.getTile(x+1, y) != null && (board.getTile(x+1, y).hasColor() == color || board.getTile(x+1, y).hasShape() == shape || !(board.getTile(x+1, y)).hasColor() == color && board.getTile(x+1, y).hasShape() == shape)){
    		result2 = result2 + 1;
    	}
    	else if(board.getTile(x+1, y) == null){
    		result3 = result3 + 1;
    	}
    	
    	if(board.getTile(x-1, y) != null && (board.getTile(x-1, y).hasColor() == color || board.getTile(x-1, y).hasShape() == shape || !(board.getTile(x-1, y)).hasColor() == color && board.getTile(x-1, y).hasShape() == shape)){
    		result2 = result2 + 1;
    	}
    	else if(board.getTile(x-1, y) == null){
    		result3 = result3 + 1;
    	}
    	
    	if(board.getTile(x, y+1) != null && (board.getTile(x, y+1).hasColor() == color || board.getTile(x, y+1).hasShape() == shape || !(board.getTile(x, y+1)).hasColor() == color && board.getTile(x, y+1).hasShape() == shape)){
    		result2 = result2 + 1;
    	}
    	else if(board.getTile(x, y+1) == null){
    		result3 = result3 + 1;
    	}
    	
    	if(board.getTile(x, y-1) != null && (board.getTile(x, y-1).hasColor() == color || board.getTile(x, y-1).hasShape() == shape || !(board.getTile(x, y-1)).hasColor() == color && board.getTile(x, y-1).hasShape() == shape)){
    		result2 = result2 + 1;
    	}
    	else if(board.getTile(x, y-1) == null){
    		result3 = result3 + 1;
    	}
    	
    	if(result2 > 0 && result3 < 4){
    		result = SERVER_MOVE_PUT;
    	}
    	
    	return result;
    }
}
