package week3.Hotel;

import java.util.Formatter;

public class Format {
	String print;
	Format format;

	public Format() {
		StringBuilder sb = new StringBuilder();

	}

	public void printLine(String text, double amount) {
		if(text.length() < 50 && amount < 10000){
		System.out.print(String.format("%-50s%s%15s\n",text,"",amount));
		}
		else
			System.out.print(String.format("%-50s%s%15s\n","De tekst was te lang","",amount));
				
		

	}
}
