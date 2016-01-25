package Server;

import java.net.*;
import java.io.*;

public class Server extends Thread {
	ServerSocket server = null;
	Gamelogic gamelogic;
	Serverboard board;
	Player player;
	Peer peer;
	Bag bag;
	String clientMessage = "";
	Connection connection;

	public Server() {
		try {
			server = new ServerSocket(3223);
			bag = new Bag();
			board = new Serverboard();
			gamelogic = new Gamelogic(board, bag);
			System.out.println(
					"Waiting for client on port 3223 swith IP-adress: " + Inet4Address.getLocalHost().getHostAddress());
			Connection connection = new Connection(this, gamelogic, board, bag, server);
			Thread s = new Thread(connection);
			s.start();
		} catch (Exception e) {
			System.out.println("Error while trying to make a server: " + e);
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args){
		new Server();
	}
	
	public void connection(Peer peer, Socket socket) {
		try{
			System.out.println("Client connected: " + socket);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Connection connection = new Connection(this, gamelogic, board, bag, server);
			connection.read(peer, socket, out, inputStream);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}