package Client;

/**
 * ServerCommunication;
 * 
 * @author Stan Peters en Steven hendriksen
 * @version 1.0
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerCommunication extends Thread {
	// ------------------ Instance variables ----------------
	Socket echoSocket = null;
	PrintWriter out = null;
	String hostName;
	int portNumber;
	Peer peer = null;

	// ------------------ Constructor ------------------------

	/**
	 * The constructor that sets up the connection with the server
	 * 
	 * @param hostName
	 *            String of the ip to connect to
	 * @param portNumber
	 *            int of the port to connect to
	 * @param board
	 *            the board that this connection will be working with
	 * @param peer
	 *            the peer that this connection will be working with
	 */

	public ServerCommunication(String hostName, int portNumber, Board board, Peer peer) {
		this.hostName = hostName;
		this.portNumber = portNumber;
		this.peer = peer;
		try {
			System.out.println("Conecting Socket " + hostName + ":" + portNumber);
			echoSocket = new Socket(hostName, portNumber);
			System.out.println("Connected " + echoSocket);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
		} catch (IOException e2) {
			System.out.println("Failed to connect");
		}
	}

	/**
	 * The thread that makes sure the client constantly reads the data from the
	 * server
	 */

	public void run() {
		System.out.println("Reading");
		Read();
	}

	/**
	 * Writes to the server
	 * 
	 * @param str
	 *            String to be sent to the server
	 */

	public void Write(String str) {
		out.println(str);
		System.out.println("Sent to Server: " + str);
	}

	/**
	 * Closes the connection
	 */

	public void Close() {
		try {
			echoSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * used to read from the server, runs on a seperate thread and keeps reading
	 */

	public void Read() {
		try {
			while (true) {
				BufferedReader inputStream = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

				String serverMessage = "";
				while (!serverMessage.equals("Close")) {
					serverMessage = inputStream.readLine();
					if (!serverMessage.equals("") && !serverMessage.equals("Close")) {
						peer.handleCommand(serverMessage);
						System.out.println(serverMessage);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
