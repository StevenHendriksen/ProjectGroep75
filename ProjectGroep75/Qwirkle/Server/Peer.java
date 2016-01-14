package Server;

import Client.Board;

public class Peer {
	Board board;
	Bag bag;
	Tile tile;

	
    String CLIENT_MOVE_PUT = "MOVE_PUT";
    
    public void movePut(int x, int y, int tile){
    	board.putTile(x, y, tile);
    }
    
    
    String CLIENT_MOVE_TRADE = "MOVE_TRADE";
    
    public void moveTrade(int number){
    	tile.getTile(number);
    	bag.getTile(tile);
    	bag.takeTile();
    }
    
    
}
