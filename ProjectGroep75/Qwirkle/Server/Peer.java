package Server;

import Client.Board;

public class Peer {
	Board board;
	Bag bag;
	Tile tile;
	/**
     * <p>Sent by the client to put tiles on the board as a move.</p>
     * <p>The player must own the tiles.<code>{@link nl.utwente.ewi.qwirkle.net.IProtocol.Error#MOVE_TILES_UNOWNED }</code></p>
     * <p>The move must be valid. <code>{@link nl.utwente.ewi.qwirkle.net.IProtocol.Error#MOVE_INVALID }</code></p>
     *
     * <dl>
     *     <dt>Parameters:</dt>
     *     <dd><code>tile@x,y</code> - list of tilecode at coordinate</dd>
     * </dl>
     * <dl>
     *     <dt>Example:</dt>
     *     <dd><code><strong>MOVE_PUT</strong> 0@0,0 6@0,1 12@0,2</code></dd>
     * </dl>
     */
    String CLIENT_MOVE_PUT = "MOVE_PUT";
    
    public void movePut(int x, int y, Tile tile){
    	board.putTile(x, y, tile);
    }
    
    /**
     * <p>Sent by the client to trade tiles as a move.</p>
     * <p>The player must own the tiles. <code>{@link nl.utwente.ewi.qwirkle.net.IProtocol.Error#MOVE_TILES_UNOWNED }</code></p>
     * <p>The deck contain at least as many tiles as traded. <code>{@link nl.utwente.ewi.qwirkle.net.IProtocol.Error#DECK_EMPTY }</code></p>
     * <p>The player cannot trade if the board is empty. <code>{@link nl.utwente.ewi.qwirkle.net.IProtocol.Error#TRADE_FIRST_TURN }</code></p>
     *
     * <dl>
     *     <dt>Parameters:</dt>
     *     <dd><code>tiles</code> - list of tiles</dd>
     * </dl>
     * <dl>
     *     <dt>Example:</dt>
     *     <dd><code><strong>MOVE_TRADE</strong> 23 18 7</code></dd>
     * </dl>
     */
    String CLIENT_MOVE_TRADE = "MOVE_TRADE";
    
    public void moveTrade(int number){
    	tile.getTile(number);
    	bag.getTile(tile);
    	bag.takeTile();
    }
    
    
}
