package week3.Hotel;

public class BasicItem implements Bill.Item{
	private double am;
	private String txt;
	public BasicItem(String text, double amount ) {
		txt = text;
		am = amount;
	}
	public double getAmount(){
		return am;
	}
	public String toString(){
		return txt;
	}

}
