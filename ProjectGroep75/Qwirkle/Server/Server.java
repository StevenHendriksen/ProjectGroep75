package Server;

import java.net.*;
import java.io.*;

public class Server extends Thread {
	ServerSocket server = null;
	Socket connection = null;
	PrintWriter out;

	public Server(){
		try {
			server = new ServerSocket(673);
			System.out.println(
					"Waiting for client on port 673 swith IP-adress: " + Inet4Address.getLocalHost().getHostAddress());
			connection = server.accept();
			System.out.println("Connection established.");
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
					clientMessage = inputStream.readLine();
					if (!clientMessage.equals("") && !clientMessage.equals("Close")) {
						System.out.println(clientMessage);
					}
				}

				write("IDENTIFYOK");
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
		System.out.println("shit geprint");
	}
}