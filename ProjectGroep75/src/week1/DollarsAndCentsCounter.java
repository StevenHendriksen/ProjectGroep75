package week1;

public class DollarsAndCentsCounter {
	int dollars = 0;
	int cents = 0;
	public int dollars(){
		return dollars;
	}
	public int cents(){
		
		return cents;
	}
	public void add(int addeddollars, int addedcents){
		dollars = dollars + addeddollars;
		cents = cents + addedcents;
		if (cents >= 100){
			cents = cents - 100;
			dollars = dollars + 1;
		}
	}
	public int reset(){
		cents = 0;
		dollars = 0;
		return cents + dollars;
	}
	public int substract(int substractedDollars, int substractedCents){
		dollars = dollars - substractedDollars;
		cents = cents - substractedCents;
		if (cents < 0){
			cents = cents + 100;
			dollars = dollars - 1;
		}
		if (dollars < 0){
			System.out.println("ja, en nu");
		}
		return dollars + cents;
	}
}
