package week3.Hotel;

public class Bill {
	public interface Item {
		public double getSum();
		public void newItem(Item item);
		public String ToString();
		public double getAmount();
	}

	private double sum;
	java.io.PrintStream PS;
	Format format;
	private double am;
	private String txt;
	// private String text = "Bill:\n";

	public Bill(java.io.PrintStream theOutStream) {
		PS = theOutStream;
	}

	public Bill(String text, double amount) {
		am = amount;
		txt = text;
	}

	public void close() {
		PS.println(sum);
	}

	public double getSum() {
		return sum;
	}

	public void newItem(Item item) {
		sum = sum + item.getSum();
		PS.println(Format.printLine(Bill.ToString(), Bill.getAmount()));
	}

	public String ToString() {
		return txt;
	}

	public double getAmount() {
		return am;
	}

}
