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
package fuzzy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Fuzzy Set Element.
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class FuzzySetElementTest {

	protected FuzzySet.Element element;
	
	private final Object entry = new String("fuzzy");
	private final double degree = 1.984;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		element = new FuzzySet.Element(entry, degree);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		element = null;
	}

	/**
	 * Test method for {@link fuzzy.FuzzySet.Element#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		assertEquals(element.hashCode(), new FuzzySet.Element(entry, degree).hashCode());
	}

	/**
	 * Test method for {@link fuzzy.FuzzySet.Element#getEntry()}.
	 */
	@Test
	public void testGetEntry() {
		assertEquals(element.getEntry(), entry);
	}

	/**
	 * Test method for {@link fuzzy.FuzzySet.Element#getDegree()}.
	 */
	@Test
	public void testGetDegree() {
		assertEquals(Double.valueOf(element.getDegree()), Double.valueOf(degree));
	}
	
	@Test
	public void testSerializeDeserializeThenCompare() throws Exception {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(buffer);
        out.writeObject(element);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
        Object dest = in.readObject();
        in.close();
        assertEquals("obj != deserialize(serialize(obj))",element,dest);
        assertEquals("obj.hash != deserialize(serialize(obj.hash))",element.hashCode(),dest.hashCode());
	}
	
	@Test
	public void testEqualsObject() {
		Object obj = element;
		assertEquals("Object must be reflexive", obj, obj);
		assertEquals("hashCode must be reflexive", obj.hashCode(), obj.hashCode());
		assertFalse(obj.equals(null));
		
		Object other = new FuzzySet.Element(entry, degree);
		if(obj.equals(other)) {
			assertEquals("equals implies hash equals", obj.hashCode(), other.hashCode());
			assertEquals("equals must be symmetric", other, obj);
		} else {
			assertFalse("equals must be symmetric", other.equals(obj));
		}
	}
	
	@Test
	public void testEqualsWithNullEntry() {
		Object obj = new FuzzySet.Element(null, degree);
		Object other = new FuzzySet.Element(null, degree);
		
		assertEquals(obj, other);
		assertEquals("hashCode must be reflexive", obj.hashCode(), obj.hashCode());
	}
	
	@Test
	public void testEqualsWidhDifferentDegrees() {
		Object obj = new FuzzySet.Element(null, degree);
		Object other = new FuzzySet.Element(null, .9);
		
		assertFalse(obj.equals(other));
		assertFalse(obj.hashCode() == other.hashCode());
	}

	@Test
	public void testEqualsWithDifferentClassType() {
		Object obj = new FuzzySet.Element(null, degree);
		
		assertFalse(obj.equals(new String("fuzzy")));
	}
	
}
