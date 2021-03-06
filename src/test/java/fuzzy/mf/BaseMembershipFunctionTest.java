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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.function.Function;

import org.junit.Test;

/**
 * Base test for Membership Functions.
 * 
 * @param <T> Type of Membership Function
 * @since 0.1
 */
public abstract class BaseMembershipFunctionTest<T extends Function<?, ?>> {

	/**
	 * Creates a membership function for tests.
	 * @return membership function
	 */
	protected abstract T makeMembershipFunction();
	
	@Test
	public void testEqualsObject() {
		Object obj = makeMembershipFunction();
		assertEquals("Object must be reflexive", obj, obj);
		assertEquals("hashCode must be reflexive", obj.hashCode(), obj.hashCode());
		assertFalse(obj.equals(null));
		
		Object other = makeMembershipFunction();
		if(obj.equals(other)) {
			assertEquals("equals implies hash equals", obj.hashCode(), other.hashCode());
			assertEquals("equals must be symmetric", other, obj);
		} else {
			assertFalse("equals must be symmetric", other.equals(obj));
		}
		
		assertFalse(obj.equals(Boolean.FALSE));
	}
	
	@Test
    public void testToStringIsOverridden() throws Exception {
        Object obj = makeMembershipFunction();
        assertNotNull("toString should never return null",obj.toString());
        assertTrue(
            obj.getClass().getName()  + " should override toString(), found \"" + obj.toString() + "\"",
            !obj.toString().equals(objectToString(obj)));
    }
	
	// private utils
    // ------------------------------------------------------------------------
    private String objectToString(Object obj) {
        return obj.getClass().getName() + "@" + Integer.toHexString(obj.hashCode());
    }
	
}
