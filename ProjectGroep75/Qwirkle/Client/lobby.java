package Client;

/**
 * lobby
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

import java.util.ArrayList;
import java.util.List;

public class lobby {
	// ------------------ Instance variables ----------------
	private List<String> players = new ArrayList<String>();
	private String connectedIp = "Not connected";
	private int port = 0;
	private int lobbySize;

	/**
	 * Main used for testing purposes
	 * @param args
	 */
	
	public static void main(String[] args) {
		lobby lobby = new lobby(3);
		lobby.addPlayer("Steven");
		lobby.addPlayer("Stan");
		lobby.Print();
	}
	
	/**
	 * Creates a lobby with size
	 * @param size int of the lobby size
	 */

	public lobby(int size) {
		lobbySize = size;
	}
	
	/**
	 * Constructor for a lobby without a size limit
	 */
	
	public lobby(){
		
	}
	
	// ------------------ Commands ------------------------
	
	/**
	 * creates a lobby that shows the people that are currently in the lobby
	 * @return lobby, print of lobby with the players in
	 */
	
	public List<String> createLobby() {
		List<String> lobby = new ArrayList<String>();
		lobby.add("==================================================");
		for (int i = lobbySize - 1; i >= 0; i--) {
			if (i > lobby.size() - 1) {
				lobby.add("| " + (i + 1) + ". " + "~~~");
			} else {
				lobby.add("| " + (i + 1) + ". " + players.get(i));
			}
		}
		lobby.add("==================================================");
		lobby.add("Connected to: " + connectedIp + ":" + port);
		return lobby;
	}
	
	/**
	 * Adds a player into the lobby
	 * @param player
	 */

	public void addPlayer(String player) {
		players.add(player);
	}
	
	/**
	 * Clears and prints to the console
	 */

	public void Print() {
		for (int i = 0; i < 50; i++){
			System.out.println();
		}
		List<String> lobby = createLobby();
		for (int j = lobby.size(); j > 0; j--) {
			System.out.println(lobby.get(j - 1));
		}
	}
	
	/**
	 * Called when connected to a server
	 * @param ip String of the server ip
	 * @param port int of the server port
	 */

	public void Connected(String ip, int port) {
		connectedIp = ip;
		this.port = port;
		Print();
	}
}
