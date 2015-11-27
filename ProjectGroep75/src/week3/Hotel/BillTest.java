package week3.Hotel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BillTest {
	Bill bill;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void test() {
		bill = new Bill("ontbijt", 6.30);
		System.out.print(bill.getAmount());
		System.out.print(Double.compare(bill.getAmount(), (6.30)));
        assertTrue(Double.compare(bill.getAmount(), (6.30)) == 0);
		System.out.print(bill.getAmount());        
        assertTrue(bill.ToString());
	}

}
