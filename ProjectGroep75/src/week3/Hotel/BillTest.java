package week3.Hotel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BillTest {

	Bill.Item BasicItem;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void test() {
		Bill bill;
		bill = new Bill(System.out);
		BasicItem = new BasicItem("ontbijt", 6.30);
		bill.newItem(BasicItem);
		BasicItem = new BasicItem("lunch", 5.94);
		bill.newItem(BasicItem);
		assertEquals("basicItem.getAmount() is niet gelijk", BasicItem.getAmount(), 5.94, 0);
		assertTrue(Double.compare(BasicItem.getAmount(), 5.94) == 0);
		assertTrue(Double.compare(bill.getSum(), 12.24) == 0);
		bill.close();
	}

}
