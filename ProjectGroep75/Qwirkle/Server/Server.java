package Server;

import java.net.*;
import java.io.*;

public class Server {
	ServerSocket server = null;
	Socket connection = null;

	public Server() {
		try {
			server = new ServerSocket(666);
			System.out.println("Waiting for client on port 666 swith IP-adress: " + Inet4Address.getLocalHost().getHostAddress());
		} catch (Exception e) {
			System.out.println("Error while trying to make a server: " + e);
		}
	}

	public static void main(String[] args) throws java.net.SocketException {
		Server server = new Server();

		server.communicate();
	}

	public void communicate() throws java.net.SocketException{
		try {
			while (true) {
				connection = server.accept();
				System.out.println("Connection established.");
				PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
				BufferedReader inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				String clientMessage = "";
				
				while (!clientMessage.equals("Close")) {
					clientMessage = inputStream.readLine();
					if(!clientMessage.equals("") && !clientMessage.equals("Close")){
						System.out.println(clientMessage);
						out.println("Hallo Henk");
					}
				}
				
				close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close(){
		try{
			connection.close();
			System.out.println("The connection has been closed.");
		}
		catch(IOException e){
			System.out.println("Error while closing: " + e);
		}
	}
}