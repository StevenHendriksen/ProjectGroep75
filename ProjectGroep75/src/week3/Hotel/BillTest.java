package week3.Hotel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BillTest {
	Bill bill;
	Bill.Item BasicItem;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void test() {
		BasicItem = new BasicItem("ontbijt", 6.30);
		System.out.println(BasicItem.getAmount());
		System.out.println(BasicItem.toString());
	}

}
