package Server;

import java.net.*;
import java.io.*;

public class Server {

    /**
     * Opens a ServerSocket on port 666 and waits for a response from the Python code and the Arduino.
     * Returns "Connection failed" when the connection fails.
     *
     * @return a string containing the card ID and location ID
     */
	public static void main(String[] args){
		Server server = new Server();
		
		server.communicate();
	}
	
	
    public static String communicate() {

        try {
            ServerSocket server = new ServerSocket(3658);
            
            System.out.println("Waiting for client on port 3658 with IP-adress: " + Inet4Address.getLocalHost().getHostAddress());

            while (true) {
                Socket connection = server.accept();
                System.out.println("Connection established.");

                BufferedReader inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                while (true) {
                    String clientMessage = inputStream.readLine();
                    if(clientMessage != null) {
                        String encryptedId = clientMessage.substring(1).replaceAll("'", "");
                        connection.close();
                        server.close();
                        return encryptedId;
                    } else {
                        connection.close();
                        server.close();
                        return "Connection failed";
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error while reading. Please try again." + e);
        }

        return "Connection failed";
    }

}