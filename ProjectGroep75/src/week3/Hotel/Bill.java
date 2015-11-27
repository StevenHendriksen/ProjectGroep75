package week3.Hotel;

public class Bill {
	public interface Item {
		public double getAmount();
	}

	private double sum;
	java.io.PrintStream PS;
	Format format;

	public Bill(java.io.PrintStream theOutStream) {
		PS = theOutStream;
	}

	public void close() {
		PS.println(sum);
	}

	public double getSum() {
		return sum;
	}

	public void newItem(Item item) {
		sum = sum + item.getAmount();
		PS.println(Format.printLine(item.toString(), item.getAmount()));
	}

}
