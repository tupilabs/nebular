/*
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language 
 * governing permissions and limitations under the License.
 */
package fuzzy.mf;

import static org.junit.Assert.assertEquals;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Gaussian Membership Function.
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class GaussianMembershipFunctionTest extends BaseMembershipFunctionTest<GaussianMembershipFunction> {

	protected GaussianMembershipFunction mf;
	
	private final double sigma = 2.0;
	private final double c = 5.0;
	
	private final double[][] expected = new double[101][2];
	
	@Override
	protected GaussianMembershipFunction makeMembershipFunction() {
		return new GaussianMembershipFunction(sigma, c);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mf = makeMembershipFunction();
		
		/*
		 * Results from Matlab psigmf.
		 * 
		 * x=0:0.1:10;
		 * y=gaussmf(x,[2 5]);
		 */
		// TODO test data
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		mf = null;
	}

	/**
	 * Test method for {@link fuzzy.mf.GaussianMembershipFunction#evaluate(java.lang.Double)}.
	 */
	@Test
	public void testEvaluate() {
		final NumberFormat nf = NumberFormat.getInstance(Locale.US);
		nf.setMaximumFractionDigits(4);
		nf.setRoundingMode(RoundingMode.HALF_UP);
		int i = 0;
		for(double x = 0.0 ; x <= 10.0 ; x+=0.1) {
			double y = Double.parseDouble(nf.format(mf.evaluate(x)));
			assertEquals(Double.valueOf(y), Double.valueOf(expected[i][1]));
			i++;
		}
	}

}
