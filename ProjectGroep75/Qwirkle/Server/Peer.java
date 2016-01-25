package Server;

import java.util.Scanner;

public class Peer {
	Gamelogic gamelogic;
	Player player;
	Serverboard board;
	Bag bag;

	public Peer(Gamelogic gamelogic, Serverboard board, Bag bag) {
		this.gamelogic = gamelogic;
		this.bag = bag;
		this.board = board;
	}

	public String handleCommand(String cmd) {
		try {
			String result = "";
			Scanner scan = new Scanner(cmd);
			String str = scan.nextLine();
			Scanner fullCommand = new Scanner(str);

			String command = fullCommand.next();

			if (command.equals("CLIENT_IDENTIFY")) {
				this.player = new Player(fullCommand.next(), bag);
				System.out.println("Player put: " + player.hasName() + "    " + this);
				gamelogic.putPlayer(player);
				result = "SERVER_IDENTIFYOK";
			} 
			else if (command.equals("CLIENT_LOBBY")) {
				result = "LOBBYOK";

				for(int i = 1; i < gamelogic.hasPlayers().size(); i++){
					System.out.println(gamelogic.hasPlayers().size());
					System.out.println("HENK      " + gamelogic.hasPlayers().get(i).hasName());
					result = result + " " + gamelogic.hasPlayers().get(i).hasName();
				}
				
				if(gamelogic.hasPlayers().size() > 4){
					if(gamelogic.gameStart(4)){
						result = result + " GAMEHASSTARTED";
					}
				}
			}
			else if (command.equals("CLIENT_DRAWTILE")) {
				gamelogic.turn();
				for (int i = 0; i < 6; i++) {
					if (player.tiles[i] == null) {
						player.tiles[i] = gamelogic.drawTile();
					}
				}
				result = "SERVER_DRAWTILE";
			} 
			else if (command.equals("CLIENT_QUIT")) {
				result = "SERVER_GAMEEND";
			} 
			else if (command.equals("CLIENT_QUEUE")) {
				int numberplayers = new Integer(fullCommand.next());

				if (gamelogic.gameStart(numberplayers)) {
					result = "SERVER_GAMESTART";

					for (int i = 0; i < numberplayers; i++) {
						result = result + " " + gamelogic.hasPlayers().get(i).hasName();
					}
				}
			} 
			else if (command.equals("CLIENT_MOVE_PUT")) {
				result = "SERVER_MOVEOK_PUT";
				Scanner fullCommandTiles = new Scanner(cmd);
				fullCommandTiles.next();
				while (fullCommandTiles.hasNext()) {
					String tile = fullCommandTiles.next();
					String tile2 = tile.replaceAll("[^\\dA-Za-z ]", " ");
					Scanner in = new Scanner(tile2);
					int tileInt = new Integer(in.next());
					int x = new Integer(in.next());
					int y = new Integer(in.next());
					if (gamelogic.moveOkPut(tileInt, x, y).equals("MOVEOK_PUT")) {
						board.putTile(x, y, tileInt);
						result = result + " " + tileInt + "@" + x + "," + y;
					} else {
						result = "SERVER_ERROR: INVALID MOVE";
					}
					in.close();
				}
				fullCommandTiles.close();
			} 
			else if (command.equals("CLIENT_MOVE_TRADE")) {
				result = "SERVER_MOVEOK_TRADE";

				while (fullCommand.hasNext()) {
					int inttile = new Integer(fullCommand.next());
					if (gamelogic.moveOkTrade() == "MOVEOK_TRADE") {
						gamelogic.moveTrade(inttile);
					} else {
						result = "SERVER_ERROR: INVALID TRADE";
					}
				}
			}

			scan.close();
			fullCommand.close();
			return result;
		} 
		
		catch (java.util.NoSuchElementException e){
			String result = "Invalid Server command";
			e.printStackTrace();
			return result;
		}
	}
}
