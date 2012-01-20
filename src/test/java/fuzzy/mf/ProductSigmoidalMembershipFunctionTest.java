/*
 * The MIT License
 *
 * Copyright (c) <2011> <Bruno P. Kinoshita>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fuzzy.mf;

import static org.junit.Assert.assertEquals;

import java.math.RoundingMode;
import java.text.NumberFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Product Sigmoidal Membership Function.
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class ProductSigmoidalMembershipFunctionTest {

	protected ProductSigmoidalMembershipFunction mf;
	
	private final double a1 = 2.0;
	private final double c1 = 3.0;
	private final double a2 = -5.0;
	private final double c2 = 8.0;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mf = new ProductSigmoidalMembershipFunction(a1, c1, a2, c2);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		mf = null;
	}

	/**
	 * Test method for {@link fuzzy.mf.ProductSigmoidalMembershipFunction#evaluate(java.lang.Double)}.
	 */
	@Test
	public void testEvaluate() {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		nf.setRoundingMode(RoundingMode.HALF_UP);
		Double r1 = Double.parseDouble(nf.format(mf.evaluate(3.0)));
		Double r2 = Double.parseDouble(nf.format(mf.evaluate(8.0)));
		assertEquals(r1, r2);
	}

	/**
	 * Test method for {@link fuzzy.mf.ProductSigmoidalMembershipFunction#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		assertEquals(mf, new ProductSigmoidalMembershipFunction(a1, c1, a2, c2));
	}
	
	/**
	 * Test method for {@link fuzzy.mf.ProductSigmoidalMembershipFunction#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		assertEquals(mf.hashCode(), new ProductSigmoidalMembershipFunction(a1, c1, a2, c2).hashCode());
	}

}
