package week3.Hotel;

public class PricedRoom extends Room implements week3.Hotel.Bill.Item {
	private double rPrice;
	private double sPrice;
	PricedRoom room;
	Safe safe;
	
	public PricedRoom(int no, double roomPrice, double safePrice) {
		super(no);
		safe = new PricedSafe(safePrice);
		rPrice = roomPrice;
		sPrice = safePrice;
	}

	public double getAmount() {
		return rPrice + sPrice;
	}
	
	public String toString() {
		return "Total price: " + (rPrice + sPrice);
	}

}
