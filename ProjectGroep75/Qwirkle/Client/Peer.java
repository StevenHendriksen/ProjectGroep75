package Client;

import java.util.Scanner;

public class Peer {

	private Board board = null;
	private Qwirkle game = null;

	public static void main(String[] args) {
		Qwirkle game = new Qwirkle();
		game.peer.handleCommand("IDENTIFYOK");
		game.peer.handleCommand("GAMESTART");
		game.peer.handleCommand("TURN Steven");
		game.peer.handleCommand("PASS");
		game.peer.handleCommand("DRAWTILE 33 32 12");
		game.peer.handleCommand("MOVEOK_PUT 12@0,0 4@0,33 3@1,32 0@3,12");
		game.peer.handleCommand("MOVEOK_TRADE 3");
		game.peer.handleCommand("ERROR Invalid move");
		game.peer.handleCommand("CHAT global Alice Hello World!");
		game.peer.handleCommand("CHATOK global Alice Hello World!");
		game.peer.handleCommand("GAMEEND");
	}

	public Peer(Board board, Qwirkle game) {
		this.board = board;
		this.game = game;
	}

	public void handleCommand(String cmd) {
		Scanner scan = new Scanner(cmd);
		String str = scan.nextLine();
		try {

			Scanner fullCommand = new Scanner(str);

			String command = fullCommand.next();
			if (command.equals("IDENTIFYOK")) {
				System.out.println("Succesfully connected");
				game.connected();
			}
			if (command.equals("GAMESTART")) {
				System.out.println("Game is starting");
				game.Start();
			}
			if (command.equals("GAMEEND")) {
				System.out.println("Game has ended");
				String name = fullCommand.next();
				String output = name + " " + fullCommand.next();
				while (fullCommand.hasNext()) {
					output = output + " " + fullCommand.next();
				}
				game.End(output);
			}
			if (command.equals("TURN")) {
				String name = fullCommand.next();
				System.out.println("Turn: " + name);
				game.turn(name);
			}
			if (command.equals("PASS")) {
				String name = fullCommand.next();
				System.out.println("Player Passed: " + name);
				game.pass(name);
			}
			if (command.equals("DRAWTILE")) {
				String output = "Tiles Drawn:";
				while (fullCommand.hasNext()) {
					output = output + " " + fullCommand.next();
				}
				System.out.println(output);
			}
			if (command.equals("MOVEOK_PUT")) {
				String output = "Tiles placed:";
				Scanner fullCommandTiles = new Scanner(cmd);
				fullCommandTiles.next();
				while (fullCommandTiles.hasNext()) {
					String tile = fullCommandTiles.next();
					String tile2 = tile.replaceAll("[^\\dA-Za-z ]", " ");
					Scanner in = new Scanner(tile2);
					int tileInt = new Integer(in.next());
					int x = new Integer(in.next());
					int y = new Integer(in.next());
					System.out.println("putTile: " + x + y + tileInt);
					board.putTile(x, y, tileInt);
					in.close();
					

				}
				System.out.println("tiles put");
				
				fullCommandTiles.close();
			}
			if (command.equals("MOVEOK_TRADE")) {
				System.out.println("Tiles traded: " + fullCommand.next());
			}
			if (command.equals("ERROR")) {
				System.out.println("Error: " + fullCommand.next());
			}
			if (command.equals("CHATOK")) {
				String name = fullCommand.next();
				String output = name + " " + fullCommand.next();
				while (fullCommand.hasNext()) {
					output = output + " " + fullCommand.next();
				}
				System.out.println(output);
				board.chatEntry(name, output, true);
			}
			scan.close();
			fullCommand.close();
			board.update();
		} catch (

		java.util.NoSuchElementException e) {
			System.out.println("Invalid Server command: " + str);
			e.printStackTrace();
			scan.close();
			board.update();
			
		}
	}
}
