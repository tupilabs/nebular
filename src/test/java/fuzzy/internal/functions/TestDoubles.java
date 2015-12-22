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

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

/**
 * Tests for Doubles utility class.
 *
 * @since 0.2
 * @see Doubles
 */
public class TestDoubles {

	@Test
	public void testDoubles() {
		Collection<Double> list = Arrays.asList(-1.0, 1.0, 2.0, 3.5);
		assertTrue(Arrays.equals(new double[]{-1.0, 1.0, 2.0, 3.5}, Doubles.toArray(list)));
	}

	@Test
    public void testDoublesEmpty() {
        Collection<Double> list = Arrays.asList();
        assertTrue(Arrays.equals(new double[0], Doubles.toArray(list)));
    }

}
