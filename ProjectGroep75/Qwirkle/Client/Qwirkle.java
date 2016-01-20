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
	public Peer peer;
	private ServerCommunication sc = null;
	private Thread scThread = null;
	private static final String functions = "CHAT";

	public static void main(String[] args) {
		Qwirkle qwirkle = new Qwirkle();


	}
	
	public void test(){
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
		board = new Board();
		peer = new Peer(board, this);
		Scanner in = new Scanner(System.in);
		System.out.println("Ip:");
		String ip = "192.168.1.105";
		while(ip.equals("")){
			System.out.println("Re-enter IP: ");
			ip = in.nextLine();
		}
		System.out.println("port:");
		String portEntry = " ";
		while(portEntry.equals("")){
			System.out.println("Re-enter Port: ");
			portEntry = in.nextLine();
		}
		int port = 673;
		System.out.println("Playing alias:");
		String name = "Steven";
		while(name.equals("")){
			System.out.println("Re-enter name: ");
			name = in.nextLine();
		}
		System.out.println(ip + port);
		sc = new ServerCommunication(ip, port, board, peer);
		if (sc.echoSocket == null) {
		} else {
			scThread = new Thread(sc);
			scThread.start();
			sc.Write("CLIENT_IDENTIFY " + name + " " + functions);
		}
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
}
