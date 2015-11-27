package week3.Hotel;

public class Bill {
	public interface Item {
		public double getSum();
		// public String ToString(String text, double amount);
	}

	private double sum;
	java.io.PrintStream PS;
	Format format;
	private double amount;
	private String text;
	// private String text = "Bill:\n";

	public Bill(java.io.PrintStream theOutStream) {
		PS = theOutStream;
		format = new Format();
	}

	public Bill(String text, double amount) {
		amount = this.amount;
		text = this.text;
	}

	public void close() {
		PS.println(sum);
	}

	public double getSum() {
		return sum;
	}

	public void newItem(Item item) {
		sum = sum + item.getSum();
		// item.ToString(, amount))
	}

	public String ToString(String text, double amount) {
		return text;
	}
	public double getAmount(){
		return amount;
	}

}
