package week3.Hotel;

public class BasicItem implements Bill.Item{
	private double am;
	private String txt;
	Bill bill;
	
	public BasicItem(String text, double amount ) {
		bill = new Bill(System.out);
		am = amount;
		txt = text;
	}
	public double getAmount(){
		return am;
	}
	public String toString(){
		return txt;
	}

}
