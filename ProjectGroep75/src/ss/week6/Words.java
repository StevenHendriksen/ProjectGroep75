package ss.week6;

import java.util.Scanner;

public class Words {

	public Words() {
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String line = in.nextLine();
			System.out.println("Hello " + line);
		}
	}
}
