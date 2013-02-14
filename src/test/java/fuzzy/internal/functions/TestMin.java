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
package fuzzy.internal.functions;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.Test;

/**
 * Tests for Min function.
 * 
 * @since 0.2
 * @see Min
 */
public class TestMin {

	@Test
	public void testMin() {
		Collection<Double> list = Arrays.asList(-1.0, 1.0, 2.0, 3.5);
		Double r = Min.of(list);
		assertEquals(Double.valueOf(-1.0), r);
	}
	
	@Test
	public void testEmptyMin() {
		Double r = Min.of(Collections.<Double>emptyList());
		assertEquals(Double.valueOf(0.0), r);
	}
	
}
