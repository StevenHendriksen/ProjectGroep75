package ss.week6;

import java.util.Scanner;
import java.io.*;

public class Hello {
	public Hello(java.io.InputStream InputStream) {
	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		System.out.println("Hallo, wat is jou naam?");
		Scanner in = new Scanner(System.in);
		PrintWriter writer = new PrintWriter("naam.txt", "UTF-8");
		while (in.hasNextLine()) {
			String naam = in.nextLine();
			if (naam.equals("")) {
			} else {
				if (naam.equals("stop")) {
					writer.close();
					break;
				}
				System.out.println("Hello " + naam);
				writer.println(naam);
			}
		}
	}
}
