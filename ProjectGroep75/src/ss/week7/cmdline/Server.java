package ss.week7.cmdline;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server. 
 * @author  Theo Ruys
 * @version 2005.02.21
 */
public class Server {
    private static final String USAGE = "usage: " + Server.class.getName() + " <name> <port>";

    /** Starts a Server-application. */
    public static void main(String[] args) {     
    	int port = -1;
        ServerSocket serversock = null;
        Socket csock;
        
    	try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println(USAGE);
            System.out.println("ERROR: port " + args[1] + " is not an integer");
            System.exit(0);
        }
    	
    	try {
			serversock = new ServerSocket(port);
			System.out.println("server created (name: " + args[0] + ", adress: " + serversock.getInetAddress() + ", port: " + port + ")");
		} catch (IOException e) {
			System.out.println(USAGE);
			System.out.println("ERROR: could not create a ServerSocket on port " + port); 
			System.exit(0);
		}
    	
		try {
			csock = serversock.accept();
			System.out.println("client connected");
			Peer peer = new Peer("client1peer", csock);
			Thread clientThread = new Thread(peer);
			clientThread.start();
			clientThread.join();
			peer.shutDown();
			System.out.println("client disconnected");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			serversock.close();
			System.out.println("server disconnected");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }

} // end of class Server
