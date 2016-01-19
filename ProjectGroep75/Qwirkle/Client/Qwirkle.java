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
	static String hostName = "192.168.1.104";
	static int portNumber = 673;

	public static void main(String[] args) {
		Serverboard board = new Serverboard();
		Peer peer = new Peer();
		ServerCommunication sc2 = new ServerCommunication(hostName, portNumber);
		Thread sc = new Thread(sc2);
		sc2.Write("Hoi Stan");
		sc2.Write("Close");
		sc.start();
		board.putTile(-10, -10, 8);
		board.putTile(10, 10, 3);
		board.putTile(-10, 10, 31);
		board.putTile(10, -10, 10);
		board.putTile(0, 0, 9);
		board.chatEntry("Steven", "Banter", true);
		board.chatEntry("Steven", "Banter", false);
		board.chatEntry("Steven", "Banter", true);
		board.chatEntry("Steven", "Banter", false);
		board.chatEntry("Steven", "Banter", true);
		board.chatEntry("Steven", "Banter", false);
		board.chatEntry("Steven", "Banter", true);
		board.update();
	}
}
