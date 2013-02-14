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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.apache.commons.functor.generator.range.DoubleRange;
import org.junit.Test;

import fuzzy.mf.GeneralizedBellShapedMembershipFunction;
import fuzzy.mf.MembershipFunction;

/**
 * Tests for Crisp Fuzzy Product.
 * 
 * @since 0.2
 * @see CrispFuzzyProduct
 */
public class TestCrispFuzzyProduct {

	@Test
	public void testCrispFuzzyProduct() {
		Collection<Double> col = new DoubleRange(0.0, 1.0, 0.1).toCollection(); // crisp
		MembershipFunction<Double> mf = new GeneralizedBellShapedMembershipFunction(0.2, 0.5, 0.8); // fuzzy
		Collection<Double> r = CrispFuzzyProduct.of(col, mf);
		assertTrue(r.size() == 11);
		assertEquals(Double.valueOf(0.0), r.iterator().next()); // 0.0 * n = 0.0
	}
	
}
