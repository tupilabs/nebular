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
package fuzzy.util;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.functor.generator.range.DoubleRange;
import org.junit.Test;

import fuzzy.mf.MembershipFunction;
import fuzzy.mf.TrapezoidalMembershipFunction;

/**
 * Tests for {@link MaxMF}
 */
public class TestMaxMF {

	@Test
	public void testMaxMF() {
		Collection<Double> values = new DoubleRange(-10.0, 30.0, 0.1).toCollection();
		MembershipFunction<Double> mf = new TrapezoidalMembershipFunction(-5.0,  -2.0, 10.0, 20.0);
		Map<Double, Double> r = MaxMF.of(values, mf);
		
		assertTrue(r.containsValue(Double.valueOf(1.0)));
	}
	
}
