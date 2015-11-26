package week3.Hotel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FormatTest {
    private Format format;
    @Before
    public void setUp() throws Exception {
        format = new Format();
    }
    @Test
    public void testPrintLine(){
    	format.printLine("lang verhaal over wat iemand zijn uitgave", 5.22);
    	format.printLine("veel te lang verhaal over wat iemand allemaal heeft uitgegeven", 5.22);
    	format.printLine("avond eten", 15.22);
    	format.printLine("ontbijt", 1150.22);
    	
    }

}
