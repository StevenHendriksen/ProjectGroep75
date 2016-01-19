package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerCommunication extends Thread {
	Socket echoSocket = null;
	PrintWriter out = null;
	String hostName;
	int portNumber;
	Peer peer = null;

	public static void main(String[] args) {
		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);
		String input = args[2];
		ServerCommunication sc = new ServerCommunication(hostName, portNumber);
		sc.Write(input);
		sc.Write("Close");
		while (true) {

		}
	}

	public ServerCommunication(String hostName, int portNumber) {
		this.hostName = hostName;
		this.portNumber = portNumber;
		peer = new Peer();
		try {
			System.out.println("Conect Socket " + hostName + ":" + portNumber);
			echoSocket = new Socket(hostName, portNumber);
			System.out.println("Connected " + echoSocket);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void run() {
		System.out.println("Reading");
		Read();
	}

	public void Write(String str) {
		out.println(str);
		System.out.println("Sent to Server: " + str);
	}

	public void Close() {
		try {
			echoSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Read() {
		try {
			while (true) {
				BufferedReader inputStream = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

				String serverMessage = "";
				while (!serverMessage.equals("Close")) {
					serverMessage = inputStream.readLine();
					if (!serverMessage.equals("") && !serverMessage.equals("Close")) {
						peer.handleCommand(serverMessage);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ReadAndWrite() {
		System.out.println("Naar de Server toe:");
		try {

			while (true) {
				Scanner scanin = null;
				String output = "";
				while (output.equals("")) {
					scanin = new Scanner(System.in);
					String line = scanin.nextLine();
					output = output + line;
				}
				System.out.println("printing to Server: " + output);
				out.println(" " + output);
				BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("From Server: " + in.readLine());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
