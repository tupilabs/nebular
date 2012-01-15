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
package fuzzy.mf.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class PiShapedInputTest {

	protected PiShapedInput input;
	
	private final Double x = 1.0;
	private final Double a = 2.0;
	private final Double b = 3.0;
	private final Double c = 4.0;
	private final Double d = 5.0;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		input = new PiShapedInput(x, a, b, c, d);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		input = null;
	}

	/**
	 * Test method for {@link fuzzy.mf.input.PiShapedInput#PiShapedInput(java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Double)}.
	 */
	@Test
	public void testPiShapedInput() {
		assertNotNull(input);
	}

	/**
	 * Test method for {@link fuzzy.mf.input.PiShapedInput#getA()}.
	 */
	@Test
	public void testGetA() {
		assertEquals(input.getA(), a);
	}

	/**
	 * Test method for {@link fuzzy.mf.input.PiShapedInput#getB()}.
	 */
	@Test
	public void testGetB() {
		assertEquals(input.getB(), b);
	}

	/**
	 * Test method for {@link fuzzy.mf.input.PiShapedInput#getC()}.
	 */
	@Test
	public void testGetC() {
		assertEquals(input.getC(), c);
	}

	/**
	 * Test method for {@link fuzzy.mf.input.PiShapedInput#getD()}.
	 */
	@Test
	public void testGetD() {
		assertEquals(input.getD(), d);
	}

	/**
	 * Test method for {@link fuzzy.mf.input.FuzzyInput#getX()}.
	 */
	@Test
	public void testGetX() {
		assertEquals(input.getX(), x);
	}

	/**
	 * Test method for {@link java.lang.Object#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		assertEquals(input.hashCode(), new PiShapedInput(x, a, b, c, d).hashCode());
	}

	/**
	 * Test method for {@link java.lang.Object#equals(java.lang.Object)}.
	 */
	@Test
	public void testEquals() {
		assertEquals(input, new PiShapedInput(x, a, b, c, d));
	}

}
