package ss.week6;

import java.util.Scanner;

public class Hello {
	public Hello(java.io.InputStream InputStream) {
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String line = in.nextLine();
			System.out.println("Hello " + line);
		}
	}
}
