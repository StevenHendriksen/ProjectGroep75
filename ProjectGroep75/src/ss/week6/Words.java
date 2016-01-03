package ss.week6;

import java.util.Scanner;

public class Words {

	public Words() {
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Dump je zin hier");
		String line = in.nextLine();
		Scanner spatie = new Scanner(line);
		int i = 1;
		System.out.println("Line (or 'end'): " + line);
		while (spatie.hasNext()) {
			String woord = spatie.next();
			if (woord.equals("end")) {
				break;
			} else {
				System.out.println("Word " + i + ": " + woord);
				i++;
			}
		}
		System.out.println("Line (or 'end'): end");
		System.out.println("End of programme.");

	}
}
