package ss.week6;

import java.util.Scanner;

public class Hello {
	Scanner in;
	public Hello(java.io.InputStream InputStream){
		in = new Scanner(System.in);

		}
	
	public static void main(String[] args) {
		while (in.hasNextLine()) {  
			String line = in.nextLine();    
			System.out.println(line);
			}
	}
}
