package ss.week4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExponentTest {

    public static final int CONSTANT_VALUE = 35;
    private static final double DELTA = 1e-15;

    @Test
    public void testApply() {
        assertEquals(1, Math.pow(CONSTANT_VALUE, new Exponent(0).apply(CONSTANT_VALUE)), DELTA);
        assertEquals(35, Math.pow(CONSTANT_VALUE, new Exponent(1).apply(CONSTANT_VALUE)), DELTA);
        assertEquals(1225, Math.pow(CONSTANT_VALUE, new Exponent(2).apply(CONSTANT_VALUE)), DELTA);
    }

    @Test
    public void testDerivative() {
        Exponent exponent = new Exponent(3);
        assertTrue(exponent.derivative() instanceof LinearProduct);
        assertEquals(12, exponent.derivative().apply(2), DELTA);
    }
}
