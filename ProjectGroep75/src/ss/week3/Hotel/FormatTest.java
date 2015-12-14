package ss.week3.Hotel;

import org.junit.Before;
import org.junit.Test;

public class FormatTest {
    @Before
    public void setUp() throws Exception {
    }
    @Test
    public void testPrintLine(){
    	System.out.println(Format.printLine("lang verhaal over wat iemand zijn uitgave", 5.22));
    	System.out.println(Format.printLine("veel te lang verhaal over wat iemand allemaal heeft uitgegeven", 5.22));
    	System.out.println(Format.printLine("avond eten", 15.22));
    	System.out.println(Format.printLine("ontbijt", 1150.22));
    	
    }

}
