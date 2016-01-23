package Client;

import java.util.ArrayList;
import java.util.List;

public class lobby {
	private List<String> players = new ArrayList<String>();
	private String connectedIp = "Not connected";
	private int port = 0;
	private int lobbySize;

	public static void main(String[] args) {
		lobby lobby = new lobby(3);
		lobby.addPlayer("Steven");
		lobby.addPlayer("Stan");
		lobby.Print();
	}

	public lobby(int size) {
		lobbySize = size;
	}
	
	public lobby(){
		
	}
	
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

	public void addPlayer(String player) {
		players.add(player);
	}

	public void Print() {
		List<String> lobby = createLobby();
		for (int j = lobby.size(); j > 0; j--) {
			System.out.println(lobby.get(j - 1));
		}
	}

	public void Connected(String ip, int port) {
		connectedIp = ip;
		this.port = port;
	}
}
