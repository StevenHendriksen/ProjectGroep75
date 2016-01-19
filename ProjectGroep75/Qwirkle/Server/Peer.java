package Server;

import Client.Board;

public class Peer {
	Serverboard board;
	Bag bag;
	Servertile tile;

	
    String CLIENT_MOVE_PUT = "MOVE_PUT";
    
    public void movePut(int x, int y, int tile){
    	board.putTile(x, y, tile);
    }
    
    
    String CLIENT_MOVE_TRADE = "MOVE_TRADE";
    
    public void moveTrade(int number){
    	bag.getTile(tile);
    	bag.takeTile();
    }
    
    
}
