package Client;

import java.util.Scanner;

public class Peer {

	public static void main(String[] args) {
		Peer peer = new Peer();
		peer.handleCommand("IDENTIFYOK");
		peer.handleCommand("GAMESTART");
		peer.handleCommand("GAMEEND");
		peer.handleCommand("TURN Steven");
		peer.handleCommand("PASS");
		peer.handleCommand("DRAWTILE 33 32 12");
		peer.handleCommand("MOVEOK_PUT 4@0,33 3@1,32 0@3,12");
		peer.handleCommand("MOVEOK_TRADE 3");
		peer.handleCommand("ERROR Invalid move");
		peer.handleCommand("CHAT global Alice Hello World!");
		peer.handleCommand("CHATOK global Alice Hello World!");
	}

	public void handleCommand(String cmd) {
		try {
			Scanner scan = new Scanner(cmd);
			String str = scan.nextLine();
			Scanner fullCommand = new Scanner(str);

			String command = fullCommand.next();
			if (command.equals("IDENTIFYOK")) {
				System.out.println("Succesfully connected");
			}
			if (command.equals("GAMESTART")) {
				System.out.println("Game is starting");
			}
			if (command.equals("GAMEEND")) {
				System.out.println("Game has ended");
			}
			if (command.equals("TURN")) {
				System.out.println("Turn: " + fullCommand.next());
			}
			if (command.equals("PASS")) {
				System.out.println("Player Passed: " + fullCommand.next());
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
				while (fullCommand.hasNext()) {
					output = output + " " + fullCommand.next();
				}
				System.out.println(output);
			}
			if (command.equals("MOVEOK_TRADE")) {
				System.out.println("Tiles traded: " + fullCommand.next());
			}
			if (command.equals("ERROR")) {
				System.out.println("Error: " + fullCommand.next());
			}
			if (command.equals("CHAT")) {
				String output = fullCommand.next() + " " + fullCommand.next();
				while (fullCommand.hasNext()) {
					output = output + " " + fullCommand.next();
				}
				System.out.println(output);
			}
			if (command.equals("CHATOK")) {
				String output = fullCommand.next() + " " + fullCommand.next();
				while (fullCommand.hasNext()) {
					output = output + " " + fullCommand.next();
				}
				System.out.println(output);
			}
			scan.close();
			fullCommand.close();
		} catch (

		java.util.NoSuchElementException e)

		{
			System.out.println("Invalid Server command");
		}
	}
}
