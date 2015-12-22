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
package fuzzy.df;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.DecimalFormat;

import org.apache.commons.functor.generator.range.DoubleRange;
import org.junit.Test;

import fuzzy.mf.MembershipFunction;
import fuzzy.mf.SigmoidalMembershipFunction;


/**
 * Tests for Bisector Defuzzification Function.
 *
 * @since 0.2
 * @see BisectorDefuzzificationFunction
 */
public class TestBisectorDefuzzificationFunction extends BaseDefuzzificationFunctionTest<BisectorDefuzzificationFunction<Double>>{

	@Override
	protected BisectorDefuzzificationFunction<Double> makeDefuzzificationFunction() {
		final BisectorDefuzzificationFunction<Double> df = new BisectorDefuzzificationFunction<Double>();
		return df;
	}

	@Test
	public void testDefuzzification() {
		BisectorDefuzzificationFunction<Double> df = makeDefuzzificationFunction();
		DoubleRange range = new DoubleRange(-10.0, 10.0, 0.1);
		MembershipFunction<Double> mf = new SigmoidalMembershipFunction(-10.0, 10.0);
		Double d = df.apply(range, mf);
		assertEquals(Integer.valueOf(0).toString(), new DecimalFormat("#.#").format(Math.abs(d)));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testDefuzzificationEmptySet() {
		BisectorDefuzzificationFunction<Double> df = makeDefuzzificationFunction();
		DoubleRange range = new DoubleRange(0.0, 0.0);
		MembershipFunction<Double> mf = new SigmoidalMembershipFunction(-10.0, 10.0);
		df.apply(range, mf);
		fail("Not supposed to get here");
	}

}
