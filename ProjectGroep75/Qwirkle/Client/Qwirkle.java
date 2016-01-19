package Client;

import Server.Serverboard;

/**
 * 
 * Executable client class for Qwirkle
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

public class Qwirkle {
	public static void main(String[] args){
		Serverboard board = new Serverboard();
		board.putTile(-10, -10, 8);
		board.putTile(10,10,3);
		board.putTile(-10,10,31);
		board.putTile(10,-10,10);
		board.putTile(0,0,9);
		board.chatEntry("Steven", "What do you think?", true);
		board.chatEntry("Steven", "Quite fancy", false);
		board.chatEntry("Steven", "it's actually quite amazing", true);
		board.chatEntry("Steven", "yeah agreed", false);
		board.chatEntry("Steven", "What else should we add?", true);
		board.chatEntry("Steven", "leaderboards or lobby maybe?", false);
		board.chatEntry("Steven", "pretty good idea", true);
		board.update();	
	}
}
