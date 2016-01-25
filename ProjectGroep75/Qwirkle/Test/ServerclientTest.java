package Test;

import Server.Server;

import Client.Qwirkle;

public class ServerclientTest {
	public static void main(String[] args){
		Thread serverThread = new Thread(new Runnable() {
			@Override
			public void run() {
				new Server();
			}
		});
		
		Thread clientThread = new Thread(new Runnable() {
			@Override
			public void run() {
				new Qwirkle("130.89.88.133", 3222, "Steven");
			}
		});
		
		Thread clientThread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				new Qwirkle("130.89.88.133", 3222, "Stan");
			}
		});
		System.out.println("1");
		serverThread.start();
		System.out.println("2");
		clientThread.start();
		System.out.println("3");
		clientThread2.start();
		System.out.println("4");
	}
}
