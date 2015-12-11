package ss.week4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PolynomialTest {
    private static final double DELTA = 1e-15;
    private static final double  [] newConstant = {1.0,2.0,3.0,4.0};
    private static final double  [] newExponent = {2.0,3.0,4.0,5.0};
	private Polynomial polynomial;
	
	@Before
	public void setUp() {
        polynomial = new Polynomial(new double[newConstant.length], new double[newExponent.length]);
        polynomial = new Polynomial(newConstant, newExponent);
    }
	
	@Test
	public void applyTest(){
        assertEquals(0.0, polynomial.apply(0), DELTA);
        assertEquals(10.0, polynomial.apply(1), DELTA);
        assertEquals(-2.0, polynomial.apply(-1), DELTA);
	}

	@Test
    public void testDerivative() {
        assertTrue(polynomial.derivative() instanceof Polynomial);
		assertEquals(0.0, polynomial.derivative().apply(0), DELTA);
		assertEquals(40.0, polynomial.derivative().apply(1), DELTA);
    }

}
