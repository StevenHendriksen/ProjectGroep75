package week3.Hotel;

public class Bill {
	public interface Item {
		public double getAmount();
	}

	private double sum;
	java.io.PrintStream PS;
	Format format;
	String text;
	String txt = "";

	public Bill(java.io.PrintStream theOutStream) {
		PS = theOutStream;
	}

	public void close() {
		PS.println("Totaal: " + sum);
		PS.println(txt);
	}

	public double getSum() {
		return sum;
	}

	public void newItem(Bill.Item item) {
		sum = sum + item.getAmount();
		//PS.println(item.toString());
		//PS.println(item.getAmount());
		text = Format.printLine(item.toString(), item.getAmount());
		//PS.println(text);
		txt = txt +  text;
	}

}
