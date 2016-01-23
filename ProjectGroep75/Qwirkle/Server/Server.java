package Server;

import java.net.*;
import java.io.*;

public class Server extends Thread {
	ServerSocket server = null;
	Socket connection = null;
	PrintWriter out;
	Gamelogic gamelogic;
	Serverboard board;
	Player player;
	Peer peer;
	Bag bag;

	public Server() {
		try {
			server = new ServerSocket(667);
			bag = new Bag();
			board = new Serverboard();
			gamelogic = new Gamelogic(board, bag);
			peer = new Peer(gamelogic, board);
			System.out.println(
					"Waiting for client on port 666 swith IP-adress: " + Inet4Address.getLocalHost().getHostAddress());
			connection = server.accept();
			out = new PrintWriter(connection.getOutputStream(), true);
		} catch (Exception e) {
			System.out.println("Error while trying to make a server: " + e);
		}
	}

	public static void main(String[] args) throws java.net.SocketException {
		Server server = new Server();
		Thread s = new Thread(server);
		s.start();
	}

	public void run() {
		this.read();
	}

	public void read() {
		try {
			while (true) {
				BufferedReader inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				String clientMessage = "";

				while (!clientMessage.equals("Close")) {
					if(!clientMessage.equals("")){
						System.out.println(clientMessage);
						write(peer.handleCommand(clientMessage));
						if(peer.handleCommand(clientMessage) == "SERVER_GAMEEND"){
							this.close();
						}
					}

					clientMessage = inputStream.readLine();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			connection.close();
			System.out.println("The connection has been closed.");
		} catch (IOException e) {
			System.out.println("Error while closing: " + e);
		}
	}

	public void write(String str) {
		out.println(str);
	}
}