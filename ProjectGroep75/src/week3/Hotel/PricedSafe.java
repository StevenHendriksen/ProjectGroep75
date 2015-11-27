package week3.Hotel;

public class PricedSafe extends week3.Hotel.Safe implements Bill.Item {
	private double sum;


	public PricedSafe(double price) {
		sum = price;
	}

	public double getAmount() {
		return sum;
	}

	public String toString() {
		return "Prijs van de Safe is: €" + sum;
	}

}