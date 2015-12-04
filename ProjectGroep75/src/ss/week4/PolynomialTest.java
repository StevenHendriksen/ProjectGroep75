package ss.week4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PolynomialTest {
	double [] as = {1.0,2.0,3.0,4.0};
	double [] saai = {2.0,3.0,4.0,5.0};
	Polynomial pol;
	
	@Before
	public void setUp(){
		pol = new Polynomial(as, saai);
	}
	
	@Test
	public void applyTest(){
		System.out.println(pol.apply(2));
	}

}
