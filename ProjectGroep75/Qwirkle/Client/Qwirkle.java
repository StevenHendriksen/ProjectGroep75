package Client;

import java.util.Scanner;

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
	private Board board;
	public Peer peer; // change to private in final version
	private ServerCommunication sc = null;
	private Thread scThread = null;
	private static final String functions = "CHAT";
	private lobby lobby;
	private Player player;

	public static void main(String[] args) {
		Qwirkle qwirkle = new Qwirkle();
		qwirkle.help();
		Tile[] tiles2 = new Tile[2];
		tiles2[0] = new Tile(2);
		tiles2[1] = new Tile(3);
		qwirkle.board.setHand(tiles2);
		qwirkle.test();
		qwirkle.sc.Write("CLIENT_QUEUE 1");
		qwirkle.sc.Write("CLIENT_MOVE_PUT 1@0,0 7@0,1 13@0,2");

	}

	public void test() {
		peer.handleCommand("GAMESTART");
		board.putTile(-5, -5, 8);
		board.putTile(5, 5, 3);
		board.putTile(-5, 5, 31);
		board.putTile(5, -5, 10);
		board.putTile(5, -5, 10);
		board.putTile(0, 0, 9);
		board.consoleEntry("Test");
		board.consoleEntry("Test");
		board.consoleEntry("Test");
		board.consoleEntry("Test");
		board.consoleEntry("Test");
		board.chatEntry("Steven", "Banter", true);
		board.chatEntry("Steven", "Banter", false);
		board.chatEntry("Steven", "Banter", true);
		board.chatEntry("Steven", "Banter", false);
		board.chatEntry("Steven", "Banter", true);
		board.chatEntry("Steven", "Banter", false);
		board.chatEntry("Steven", "Banter", true);
		board.update();
		board.putTile(-5, 5, 10);
		board.chatEntry("Steven", "Banter", true);
		board.chatEntry("Steven", "Banter", false);
		board.chatEntry("Steven", "Banter", true);
		board.update();
	}

	public Qwirkle() {
		// A little interface for the start to ask for the ip/port and to start
		// up any neccesary things
		System.out.println("Welcome to Qwirkle, to connect to a server, enter the following");

		Scanner in = new Scanner(System.in);
		System.out.println("Ip:");
		String ip = "";
		ip = in.nextLine();
		while (ip.equals("")) {
			System.out.println("Re-enter IP: ");
			ip = in.nextLine();
		}
		System.out.println("port:");
		String portEntry = "";
		portEntry = in.nextLine();
		while (portEntry.equals("")) {
			System.out.println("Re-enter Port: ");
			portEntry = in.nextLine();
		}
		int port = new Integer(portEntry);
		System.out.println("Playing alias:");
		String name = "";
		name = in.nextLine();
		while (name.equals("")) {
			System.out.println("Re-enter name: ");
			name = in.nextLine();
		}
		board = new Board();
		player = new Player(name);
		peer = new Peer(board, this, player);
		lobby = new lobby();
		System.out.println(ip + port);
		sc = new ServerCommunication(ip, port, board, peer);
		if (sc.echoSocket == null) {
		} else {
			scThread = new Thread(sc);
			scThread.start();
			sc.Write("CLIENT_IDENTIFY " + name + " " + functions);
		}
		lobby.Connected(ip, port);
		in.close();
	}

	public void Start() {
		// creates the board and does the main thing
		board.update();
	}

	public void End(String str) {
		// End screen
		board.clear();
		System.out.println(str);
	}

	public void connected() {
	}

	public void turn(String name) {
		System.out.println("Turn: " + name);
	}

	public void pass(String name) {
		System.out.println("Pass: " + name);
	}

	public void help() {
		System.out.println("Available Commands:");
		System.out.println("CHALLENGE [player]");
		System.out.println("CHALLENGE_ACCEPT [player]");
		System.out.println("CHALLENGE_DECLINE [player]");
		System.out.println("LEADERBOARDS");
		System.out.println("LOBBY");
		System.out.println("QUIT");
		System.out.println("QUEUE [int, int, ....]");
		System.out.println("MOVE_PUT 1@0,0 2@0,1 etc");
		System.out.println("MOVE_TRADE int int ...");

	}
}
