package ss.week6;

import java.util.Scanner;

public class Hello {
	public Hello(java.io.InputStream InputStream) {
	}

	public static void main(String[] args) {
		System.out.println("Omdat noel een bitch is staat dit hier");
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String naam = in.nextLine();
			if (naam.equals("")) {
			} else {
				System.out.println("Hello " + naam);
			}
		}
	}
}
