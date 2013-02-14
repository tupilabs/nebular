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
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for Map Absolute values procedure.
 * 
 * @since 0.2
 * @see MapAbs
 */
public class TestMapAbs {

	@Test
	public void testMapAbs() {
		MapAbs<Double> mapAbs = new MapAbs<Double>();
		assertTrue(mapAbs.getCol().size() == 0);
		mapAbs.run(-1.0);
		assertEquals(Double.valueOf(1.0), mapAbs.getCol().iterator().next());
	}
	
}
