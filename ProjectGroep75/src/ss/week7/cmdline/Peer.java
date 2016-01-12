package ss.week7.cmdline;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import ss.week6.Card;

/**
 * Peer for a simple client-server application
 * @author  Theo Ruys
 * @version 2005.02.21
 */
public class Peer implements Runnable {
    public static final String EXIT = "exit";

    protected String name;
    protected Socket sock;
    protected BufferedReader in;
    protected BufferedWriter out;


    /*@
       requires (nameArg != null) && (sockArg != null);
     */
    /**
     * Constructor. creates a peer object based in the given parameters.
     * @param   nameArg name of the Peer-proces
     * @param   sockArg Socket of the Peer-proces
     */
    public Peer(String nameArg, Socket sockArg) throws IOException{
    	this.name = nameArg;
    	this.sock = sockArg;
    }

    /**
     * Reads strings of the stream of the socket-connection and
     * writes the characters to the default output.
     */
    public void run() throws NullPointerException{
    	String line = "";
		try {
			line = in.readLine();
		}
		catch (IOException e){
			System.out.println("IOException: " + e);
		}
		
		if (line == ""){
			return;
		}
		Scanner message = new Scanner(line);
		while(message.hasNextLine()){
			String next = message.next();
			Scanner spatie = new Scanner(next);
			while (spatie.hasNext()){
				String woord = spatie.next();
				System.out.println(woord);
			}
			spatie.close();
		}
		message.close();
    } 


    /**
     * Reads a string from the console and sends this string over
     * the socket-connection to the Peer process.
     * On Peer.EXIT the method ends
     */
    public void handleTerminalInput() {
    	String line = "";
    	try {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    System.in));
            line = in.readLine();
        } catch (IOException e) {
        }
		while(true){
			Scanner spatie = new Scanner(line);
			while (spatie.hasNext()){
				String woord = spatie.next();
				if (woord == EXIT){
					spatie.close();
					return;
				}
				try{
					out.write(woord);
				}
				catch(IOException e){
		    		System.out.println("IOException: " + e);
		    	}
			}
			spatie.close();
		}
    }

    /**
     * Closes the connection, the sockets will be terminated
     */
    public void shutDown() {
    	try{
    		sock.close();
    	}
    	catch(IOException e){
    		System.out.println("IOException: " + e);
    	}
    }

    /**  returns name of the peer object*/
    public String getName() {
        return name;
    }

    /** read a line from the default input */
    static public String readString(String tekst) {
        System.out.print(tekst);
        String antw = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    System.in));
            antw = in.readLine();
        } catch (IOException e) {
        }

        return (antw == null) ? "" : antw;
    }
}
