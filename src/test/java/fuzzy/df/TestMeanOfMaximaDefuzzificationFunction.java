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

import java.text.DecimalFormat;

import org.apache.commons.functor.generator.range.DoubleRange;
import org.junit.Test;

import fuzzy.mf.MembershipFunction;
import fuzzy.mf.TrapezoidalMembershipFunction;


/**
 * Tests for Smallest Of Maxima Defuzzification Function.
 *
 * @since 0.2
 * @see SmallestOfMaximaDefuzzificationFunction
 */
public class TestMeanOfMaximaDefuzzificationFunction extends BaseDefuzzificationFunctionTest<MeanOfMaximaDefuzzificationFunction<Double>>{

	@Override
	protected MeanOfMaximaDefuzzificationFunction<Double> makeDefuzzificationFunction() {
		final MeanOfMaximaDefuzzificationFunction<Double> df = new MeanOfMaximaDefuzzificationFunction<Double>();
		return df;
	}

	@Test
	public void testDefuzzification() {
	    MeanOfMaximaDefuzzificationFunction<Double> df = makeDefuzzificationFunction();
		DoubleRange range = new DoubleRange(-10.0, 10.0, 0.1);
		MembershipFunction<Double> mf = new TrapezoidalMembershipFunction(-10.0, -8.0, -4.0, 7.0);
        Double d = df.apply(range, mf);
		assertEquals(Integer.valueOf(-6).toString(), new DecimalFormat("#.#").format(d));
	}

}
