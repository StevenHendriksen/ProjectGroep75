package week3.Hotel;

import org.junit.Before;
import org.junit.Test;

public class FormatTest {
    private Format format;
    @Before
    public void setUp() throws Exception {
        format = new Format();
    }
    @Test
    public void testPrintLine(){
    	System.out.println(format.printLine("lang verhaal over wat iemand zijn uitgave", 5.22));
    	System.out.println(format.printLine("veel te lang verhaal over wat iemand allemaal heeft uitgegeven", 5.22));
    	System.out.println(format.printLine("avond eten", 15.22));
    	System.out.println(format.printLine("ontbijt", 1150.22));
    	
    }

}
