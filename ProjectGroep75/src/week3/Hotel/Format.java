package week3.Hotel;

public class Format {
	String print;
	Format format;

	public static String printLine(String text, double amount) {
		if(text.length() < 50 && amount < 10000){
		return String.format("%-50s%15s\n",text,amount);
		}
		else
			return String.format("%-50s%15s\n","De tekst was te lang",amount);
	}
}
