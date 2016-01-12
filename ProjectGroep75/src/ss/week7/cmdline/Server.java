
package ss.week7.cmdline;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Server. 
 * @author  Theo Ruys
 * @version 2005.02.21
 */
public class Server {
    private static final String USAGE
        = "usage: " + Server.class.getName() + " <name> <port>";

    /** Starts a Server-application. */
    public static void main(String[] args) {
    	if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(0);
        }

        String name = args[0];
        int port = 0;
        ServerSocket sock = null;
        Socket sock2 = null;

        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println(USAGE);
            System.out.println("ERROR: port " + args[1]
            		           + " is not an integer");
            System.exit(0);
        }

        try {
            sock = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("ERROR: could not create a socket on port" + port);
        }
        
        try {
        	sock2 = new Socket("localhost", port);
        } catch (IOException e) {
            System.out.println("ERROR: could not create a socket on port " + port);
        }

        try {
            Peer server = new Peer(name, sock2);
            Thread streamInputHandler = new Thread(server);
            streamInputHandler.start();
            server.run();
            server.handleTerminalInput();
            server.shutDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

} 
