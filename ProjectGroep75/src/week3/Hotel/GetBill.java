package week3.Hotel;

public class GetBill extends Hotel {

	java.io.PrintStream PS;
	Bill bill;
	Bill.Item item;
	PricedRoom room;
	private double sum;
	Guest guest;
	private int i = 0;
	
	public GetBill(String guestName, int nights, java.io.PrintStream theOutStream) {
		super("Fawlty Towers");
		PS = theOutStream;
		bill = new Bill(PS);
		room = (PricedRoom) super.getRoom(guestName);
		guest = room.getGuest();
		sum = room.getAmount();
		while (i <= nights){
			bill.newItem(new BasicItem("Kamer en Safe dag" + i + ": ", sum));
			i++;
		}
		bill.close();
	}
}