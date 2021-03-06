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
 * Tests for Trapezoidal Membership Function.
 *
 * @since 0.1
 * @see TrapezoidalMembershipFunction
 */
public class TestTrapezoidalMembershipFunction extends BaseMembershipFunctionTest<TrapezoidalMembershipFunction> {

	protected TrapezoidalMembershipFunction mf;

	private final double a = 1.0;
	private final double b = 5.0;
	private final double c = 7.0;
	private final double d = 8.0;

	private final double[][] expected = new double[101][2];

	@Override
	protected TrapezoidalMembershipFunction makeMembershipFunction() {
		return new TrapezoidalMembershipFunction(a, b, c, d);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mf = makeMembershipFunction();

		/*
		 * Results from Matlab trapmf.
		 *
		 * x=0:0.1:10;
		 * y=trapmf(x,[1 5 7 8]);
		 */
		expected[0] = new double[]{0.0000, 0.0000};
		expected[1] = new double[]{0.1000, 0.0000};
		expected[2] = new double[]{0.2000, 0.0000};
		expected[3] = new double[]{0.3000, 0.0000};
		expected[4] = new double[]{0.4000, 0.0000};
		expected[5] = new double[]{0.5000, 0.0000};
		expected[6] = new double[]{0.6000, 0.0000};
		expected[7] = new double[]{0.7000, 0.0000};
		expected[8] = new double[]{0.8000, 0.0000};
		expected[9] = new double[]{0.9000, 0.0000};
		expected[10] = new double[]{1.0000, 0.0000};
		expected[11] = new double[]{1.1000, 0.0250};
		expected[12] = new double[]{1.2000, 0.0500};
		expected[13] = new double[]{1.3000, 0.0750};
		expected[14] = new double[]{1.4000, 0.1000};
		expected[15] = new double[]{1.5000, 0.1250};
		expected[16] = new double[]{1.6000, 0.1500};
		expected[17] = new double[]{1.7000, 0.1750};
		expected[18] = new double[]{1.8000, 0.2000};
		expected[19] = new double[]{1.9000, 0.2250};
		expected[20] = new double[]{2.0000, 0.2500};
		expected[21] = new double[]{2.1000, 0.2750};
		expected[22] = new double[]{2.2000, 0.3000};
		expected[23] = new double[]{2.3000, 0.3250};
		expected[24] = new double[]{2.4000, 0.3500};
		expected[25] = new double[]{2.5000, 0.3750};
		expected[26] = new double[]{2.6000, 0.4000};
		expected[27] = new double[]{2.7000, 0.4250};
		expected[28] = new double[]{2.8000, 0.4500};
		expected[29] = new double[]{2.9000, 0.4750};
		expected[30] = new double[]{3.0000, 0.5000};
		expected[31] = new double[]{3.1000, 0.5250};
		expected[32] = new double[]{3.2000, 0.5500};
		expected[33] = new double[]{3.3000, 0.5750};
		expected[34] = new double[]{3.4000, 0.6000};
		expected[35] = new double[]{3.5000, 0.6250};
		expected[36] = new double[]{3.6000, 0.6500};
		expected[37] = new double[]{3.7000, 0.6750};
		expected[38] = new double[]{3.8000, 0.7000};
		expected[39] = new double[]{3.9000, 0.7250};
		expected[40] = new double[]{4.0000, 0.7500};
		expected[41] = new double[]{4.1000, 0.7750};
		expected[42] = new double[]{4.2000, 0.8000};
		expected[43] = new double[]{4.3000, 0.8250};
		expected[44] = new double[]{4.4000, 0.8500};
		expected[45] = new double[]{4.5000, 0.8750};
		expected[46] = new double[]{4.6000, 0.9000};
		expected[47] = new double[]{4.7000, 0.9250};
		expected[48] = new double[]{4.8000, 0.9500};
		expected[49] = new double[]{4.9000, 0.9750};
		expected[50] = new double[]{5.0000, 1.0000};
		expected[51] = new double[]{5.1000, 1.0000};
		expected[52] = new double[]{5.2000, 1.0000};
		expected[53] = new double[]{5.3000, 1.0000};
		expected[54] = new double[]{5.4000, 1.0000};
		expected[55] = new double[]{5.5000, 1.0000};
		expected[56] = new double[]{5.6000, 1.0000};
		expected[57] = new double[]{5.7000, 1.0000};
		expected[58] = new double[]{5.8000, 1.0000};
		expected[59] = new double[]{5.9000, 1.0000};
		expected[60] = new double[]{6.0000, 1.0000};
		expected[61] = new double[]{6.1000, 1.0000};
		expected[62] = new double[]{6.2000, 1.0000};
		expected[63] = new double[]{6.3000, 1.0000};
		expected[64] = new double[]{6.4000, 1.0000};
		expected[65] = new double[]{6.5000, 1.0000};
		expected[66] = new double[]{6.6000, 1.0000};
		expected[67] = new double[]{6.7000, 1.0000};
		expected[68] = new double[]{6.8000, 1.0000};
		expected[69] = new double[]{6.9000, 1.0000};
		expected[70] = new double[]{7.0000, 1.0000};
		expected[71] = new double[]{7.1000, 0.9000};
		expected[72] = new double[]{7.2000, 0.8000};
		expected[73] = new double[]{7.3000, 0.7000};
		expected[74] = new double[]{7.4000, 0.6000};
		expected[75] = new double[]{7.5000, 0.5000};
		expected[76] = new double[]{7.6000, 0.4000};
		expected[77] = new double[]{7.7000, 0.3000};
		expected[78] = new double[]{7.8000, 0.2000};
		expected[79] = new double[]{7.9000, 0.1000};
		expected[80] = new double[]{8.0000, 0.0000};
		expected[81] = new double[]{8.1000, 0.0000};
		expected[82] = new double[]{8.2000, 0.0000};
		expected[83] = new double[]{8.3000, 0.0000};
		expected[84] = new double[]{8.4000, 0.0000};
		expected[85] = new double[]{8.5000, 0.0000};
		expected[86] = new double[]{8.6000, 0.0000};
		expected[87] = new double[]{8.7000, 0.0000};
		expected[88] = new double[]{8.8000, 0.0000};
		expected[89] = new double[]{8.9000, 0.0000};
		expected[90] = new double[]{9.0000, 0.0000};
		expected[91] = new double[]{9.1000, 0.0000};
		expected[92] = new double[]{9.2000, 0.0000};
		expected[93] = new double[]{9.3000, 0.0000};
		expected[94] = new double[]{9.4000, 0.0000};
		expected[95] = new double[]{9.5000, 0.0000};
		expected[96] = new double[]{9.6000, 0.0000};
		expected[97] = new double[]{9.7000, 0.0000};
		expected[98] = new double[]{9.8000, 0.0000};
		expected[99] = new double[]{9.9000, 0.0000};
		expected[100] = new double[]{10.0000, 0.0000};

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		mf = null;
	}

	/**
	 * Test method for {@link fuzzy.mf.TrapezoidalMembershipFunction#evaluate(java.lang.Double)}.
	 */
	@Test
	public void testEvaluate() {
		final NumberFormat nf = NumberFormat.getInstance(Locale.US);
		nf.setMaximumFractionDigits(4);
		nf.setRoundingMode(RoundingMode.HALF_UP);
		int i = 0;
		for(double x = 0.0 ; x <= 10.0 ; x+=0.1) {
			double y = Double.parseDouble(nf.format(mf.apply(x)));
			assertEquals(Double.valueOf(expected[i][1]), Double.valueOf(y));
			i++;
		}
	}

}
