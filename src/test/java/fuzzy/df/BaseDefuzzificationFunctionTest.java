package fuzzy.df;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Base test for Defuzzification Functions.
 * 
 * @param <T> Type of Defuzzification Function
 * @since 0.2
 */
public abstract class BaseDefuzzificationFunctionTest<T extends DefuzzificationFunction<?>> {

	/**
	 * Creates a defuzzification function for tests.
	 * @return membership function
	 */
	protected abstract T makeDefuzzificationFunction();
	
	@Test
	public void testEqualsObject() {
		Object obj = makeDefuzzificationFunction();
		assertEquals("Object must be reflexive", obj, obj);
		assertEquals("hashCode must be reflexive", obj.hashCode(), obj.hashCode());
		assertFalse(obj.equals(null));
		
		Object other = makeDefuzzificationFunction();
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
        Object obj = makeDefuzzificationFunction();
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
