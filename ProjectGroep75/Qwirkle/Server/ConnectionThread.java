package Server;

import java.net.*;
import java.io.*;

public class ConnectionThread extends Thread {
	Socket socket;
	Gamelogic gamelogic;
	Serverboard board;
	Bag bag;
	Server server;
	PrintWriter out;
	BufferedReader inputStream;
	Connection connection;

	public ConnectionThread(Socket socket, Gamelogic gamelogic, Serverboard board, Bag bag, Server server,
			PrintWriter out, BufferedReader inputStream, Connection connection) {
		this.socket = socket;
		this.gamelogic = gamelogic;
		this.board = board;
		this.bag = bag;
		this.server = server;
		this.out = out;
		this.inputStream = inputStream;
		this.connection = connection;
	}

	public void run() {
		Peer peer = new Peer(gamelogic, board, bag);
		server.connection(peer, socket);
		System.out.println("test");
		connection.read(peer, socket, out, inputStream);
	}
}
