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
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fuzzy.mf.TriangularMembershipFunction;

/**
 * Tests for Fuzzy Set.
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class FuzzySetTest {

	protected FuzzySet fuzzySet;
	protected TriangularMembershipFunction mf;
	
	private final double a = 1.0;
	private final double b = 2.0;
	private final double c = 3.0;
	
	private final double[] values = new double[101];
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mf = new TriangularMembershipFunction(a, b, c);
		fuzzySet = new FuzzySet(mf);
		
		int index = 0;
		for(double i = 0.0 ; i <= 10.0 ; i += 0.1) {
			values[index] = i;
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		fuzzySet = null;
		mf = null;
	}

	/**
	 * Test method for {@link fuzzy.FuzzySet#getMembershipFunction()}.
	 */
	@Test
	public void testGetMembershipFunction() {
		assertEquals(fuzzySet.getMembershipFunction(), new TriangularMembershipFunction(a, b, c));
	}

	/**
	 * Test method for {@link fuzzy.FuzzySet#size()}.
	 */
	@Test
	public void testSize() {
		assertEquals(Integer.valueOf(fuzzySet.size()), Integer.valueOf(0));
		
		for(int i = 0; i < 5; ++i) {
			FuzzySet.Element element = new FuzzySet.Element(new Integer(i), 1.2);
			fuzzySet.add(element);
		}
		
		assertEquals(Integer.valueOf(fuzzySet.size()), Integer.valueOf(5));
	}

	/**
	 * Test method for {@link fuzzy.FuzzySet#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(fuzzySet.isEmpty());
		
		FuzzySet.Element element = new FuzzySet.Element(new Integer(1), 1.2);
		fuzzySet.add(element);
		
		assertFalse(fuzzySet.isEmpty());
	}

	/**
	 * Test method for {@link fuzzy.FuzzySet#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains() {
		FuzzySet.Element element = new FuzzySet.Element(new Integer(1), 1.2);
		assertFalse(fuzzySet.contains(element));
		fuzzySet.add(element);
		assertTrue(fuzzySet.contains(element));
		
		assertFalse("fuzzy set must cotnain only fuzzy set elements", fuzzySet.contains(new String("fuzzy")));
	}

	/**
	 * Test method for {@link fuzzy.FuzzySet#iterator()}.
	 */
	@Test
	public void testIterator() {
		for(int i = 0; i < 5; ++i) {
			FuzzySet.Element element = new FuzzySet.Element(new Integer(i), 1.2);
			fuzzySet.add(element);
		}
		
		Iterator<FuzzySet.Element> it = fuzzySet.iterator();
		int count = 0;
		while(it.hasNext()) {
			it.next();
			count += 1;
		}
		
		assertEquals(Integer.valueOf(count), Integer.valueOf(5));
	}

	/**
	 * Test method for {@link fuzzy.FuzzySet#toArray()}.
	 */
	@Test
	public void testToArray() {
		FuzzySet.Element element = new FuzzySet.Element(new Integer(1), 1.2);
		fuzzySet.add(element);
		
		Object[] array = fuzzySet.toArray();
		assertEquals(array[0], element);
	}

	/**
	 * Test method for {@link fuzzy.FuzzySet#toArray(T[])}.
	 */
	@Test
	public void testToArrayTArray() {
		FuzzySet.Element element = new FuzzySet.Element(new Integer(1), 1.2);
		fuzzySet.add(element);
		
		FuzzySet.Element[] array = fuzzySet.toArray(new FuzzySet.Element[0]);
		assertEquals(array[0], element);
	}

	/**
	 * Test method for {@link fuzzy.FuzzySet#add(fuzzy.Element)}.
	 */
	@Test
	public void testAdd() {
		assertTrue(fuzzySet.isEmpty());
		FuzzySet.Element element = new FuzzySet.Element(new Integer(1), 1.2);
		fuzzySet.add(element);
		assertFalse(fuzzySet.isEmpty());
	}

	/**
	 * Test method for {@link fuzzy.FuzzySet#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemove() {
		assertTrue(fuzzySet.isEmpty());
		FuzzySet.Element element = new FuzzySet.Element(new Integer(1), 1.2);
		fuzzySet.add(element);
		fuzzySet.remove(element);
		assertTrue(fuzzySet.isEmpty());
	}

	/**
	 * Test method for {@link fuzzy.FuzzySet#containsAll(java.util.Collection)}.
	 */
	@Test
	public void testContainsAll() {
		Collection<FuzzySet.Element> elements = new ArrayList<FuzzySet.Element>();
		
		for(int i = 0; i < 5; ++i) {
			FuzzySet.Element element = new FuzzySet.Element(new Integer(i), 1.2);
			elements.add(element);
		}
		
		assertFalse(fuzzySet.containsAll(elements));
		
		fuzzySet.addAll(elements);
		
		assertTrue(fuzzySet.containsAll(elements));
	}

	/**
	 * Test method for {@link fuzzy.FuzzySet#addAll(java.util.Collection)}.
	 */
	@Test
	public void testAddAll() {
		Collection<FuzzySet.Element> elements = new ArrayList<FuzzySet.Element>();
		
		for(int i = 0; i < 5; ++i) {
			FuzzySet.Element element = new FuzzySet.Element(new Integer(i), 1.2);
			elements.add(element);
		}
		
		assertEquals(Integer.valueOf(fuzzySet.size()), Integer.valueOf(0));
		
		fuzzySet.addAll(elements);
		
		assertEquals(Integer.valueOf(fuzzySet.size()), Integer.valueOf(5));
	}

	/**
	 * Test method for {@link fuzzy.FuzzySet#retainAll(java.util.Collection)}.
	 */
	@Test
	public void testRetainAll() {
		Collection<FuzzySet.Element> elements = new ArrayList<FuzzySet.Element>();
		
		FuzzySet.Element onlyElement = null;
		
		for(int i = 0; i < 5; ++i) {
			FuzzySet.Element element = new FuzzySet.Element(new Integer(i), 1.2);
			if(i > 3) {
				elements.add(element);
				onlyElement = element;
			}
			fuzzySet.add(element);
		}
		
		fuzzySet.retainAll(elements);
		
		assertEquals(Integer.valueOf(fuzzySet.size()), Integer.valueOf(1));
		
		assertTrue(fuzzySet.contains(onlyElement));
	}

	/**
	 * Test method for {@link fuzzy.FuzzySet#removeAll(java.util.Collection)}.
	 */
	@Test
	public void testRemoveAll() {
		Collection<FuzzySet.Element> elements = new ArrayList<FuzzySet.Element>();
		
		for(int i = 0; i < 5; ++i) {
			FuzzySet.Element element = new FuzzySet.Element(new Integer(i), 1.2);
			// Add to both lists
			elements.add(element);
			fuzzySet.add(element);
		}
		
		assertEquals(Integer.valueOf(fuzzySet.size()), Integer.valueOf(5));
		
		fuzzySet.removeAll(elements);
		
		assertEquals(Integer.valueOf(fuzzySet.size()), Integer.valueOf(0));
	}

	/**
	 * Test method for {@link fuzzy.FuzzySet#clear()}.
	 */
	@Test
	public void testClear() {
		assertTrue(fuzzySet.isEmpty());
		FuzzySet.Element element = new FuzzySet.Element(new Integer(1), 1.2);
		fuzzySet.add(element);
		assertFalse(fuzzySet.isEmpty());
		fuzzySet.clear();
		assertTrue(fuzzySet.isEmpty());
	}
	
	@Test
	public void testSerializeDeserializeThenCompare() throws Exception {
		for(int i = 0; i < 5; ++i) {
			FuzzySet.Element element = new FuzzySet.Element(new Integer(i), 1.2);
			fuzzySet.add(element);
		}
		
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(buffer);
        out.writeObject(fuzzySet);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
        Object dest = in.readObject();
        in.close();
        assertEquals("obj != deserialize(serialize(obj))",fuzzySet,dest);
        assertEquals("obj.hash != deserialize(serialize(obj.hash))",fuzzySet.hashCode(),dest.hashCode());
	}
	
	@Test
	public void testEqualsObject() {
		Object obj = fuzzySet;
		assertEquals("Object must be reflexive", obj, obj);
		assertEquals("hashCode must be reflexive", obj.hashCode(), obj.hashCode());
		assertFalse(obj.equals(null));
		
		Object other = new FuzzySet(mf);
		if(obj.equals(other)) {
			assertEquals("equals implies hash equals", obj.hashCode(), other.hashCode());
			assertEquals("equals must be symmetric", other, obj);
		} else {
			assertFalse("equals must be symmetric", other.equals(obj));
		}
	}

}
