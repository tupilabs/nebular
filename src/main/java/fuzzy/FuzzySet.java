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

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import fuzzy.mf.MembershipFunction;

/**
 * Simple Fuzzy Set, that implements java.util.Set. It contains a membership 
 * function and a collection of elements. 
 * <p>
 * The membership function of the Fuzzy Set indicates the degree of an element 
 * in this set. The degree is given in a value between [0, 1]. The higher the 
 * value, more the element is member of this set.
 * </p>
 * <p>
 * For instance, if for an element x, the membership function returns 0.8, and 
 * for another element, say y, it returns 0.6, then we can say that x is more 
 * member of this Fuzzy Set than y.
 * </p>
 * <p>
 * The membership function is a UnaryFunctor, built using classes of Apache 
 * Functor. While the collection of elements is a simple ArrayList.
 * </p>
 * <p>
 * Each element is stored in the collection of elements wrapped in another 
 * objects that stores the original value, plus the membership degree.
 * </p>
 * @since 0.1
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 */
public class FuzzySet extends AbstractSet<FuzzySet.Element> implements Serializable {

	private static final long serialVersionUID = -4847002337204078159L;

	private transient MembershipFunction membershipFunction;
	
	private transient Collection<Element> elements;
	
	/**
     * Save the state of this <tt>HashSet</tt> instance to a stream (that is,
     * serialize it).
     *
     * @serialData The capacity of the backing <tt>HashMap</tt> instance
     *             (int), and its load factor (float) are emitted, followed by
     *             the size of the set (the number of elements it contains)
     *             (int), followed by all of its elements (each an Object) in
     *             no particular order.
     */
    private void writeObject(java.io.ObjectOutputStream s)
        throws java.io.IOException {
        s.defaultWriteObject();

        s.writeInt(elements.size());

        s.writeObject(membershipFunction);
        
        // Write out all elements in the proper order.
        Iterator<Element> it = elements.iterator();
        while(it.hasNext()) {
        	s.writeObject(it.next());
        }
    }

    /**
     * Reconstitute the <tt>HashSet</tt> instance from a stream (that is,
     * deserialize it).
     */
    private void readObject(java.io.ObjectInputStream s)
        throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();

        int size = s.readInt();
        elements = new ArrayList<Element>(size);

        membershipFunction = (MembershipFunction) s.readObject();
        
        // Read in all elements in the proper order.
        for (int i=0; i<size; i++) {
            Element element = (Element)s.readObject();
            elements.add(element);
        }
    }
	
	public FuzzySet(MembershipFunction membershipFunction) {
		this.membershipFunction = membershipFunction;
		elements = new ArrayList<Element>();
	}
	
	/**
	 * Returns the membership function. 
	 * 
	 * @return membership function
	 */
	public MembershipFunction getMembershipFunction() {
		return this.membershipFunction;
	}

	/* (non-Javadoc)
	 * @see java.util.Set#size()
	 */
	public int size() {
		return elements.size();
	}

	/* (non-Javadoc)
	 * @see java.util.Set#isEmpty()
	 */
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	/* (non-Javadoc)
	 * @see java.util.Set#contains(java.lang.Object)
	 */
	public boolean contains(Object o) {
		if(o instanceof Element) {
			Element anotherElement = (Element)o;
			return elements.contains(anotherElement);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Set#iterator()
	 */
	public Iterator<Element> iterator() {
		return elements.iterator();
	}

	/* (non-Javadoc)
	 * @see java.util.Set#toArray()
	 */
	public Object[] toArray() {
		return elements.toArray();
	}

	/* (non-Javadoc)
	 * @see java.util.Set#toArray(T[])
	 */
	public <T> T[] toArray(T[] a) {
		return elements.toArray(a);
	}

	/* (non-Javadoc)
	 * @see java.util.Set#add(java.lang.Object)
	 */
	public boolean add(Element e) {
		return elements.add(e);
	}

	/* (non-Javadoc)
	 * @see java.util.Set#remove(java.lang.Object)
	 */
	public boolean remove(Object o) {
		return elements.remove(o);
	}

	/* (non-Javadoc)
	 * @see java.util.Set#containsAll(java.util.Collection)
	 */
	public boolean containsAll(Collection<?> c) {
		return elements.containsAll(c);
	}

	/* (non-Javadoc)
	 * @see java.util.Set#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends Element> c) {
		return elements.addAll(c);
	}

	/* (non-Javadoc)
	 * @see java.util.Set#retainAll(java.util.Collection)
	 */
	public boolean retainAll(Collection<?> c) {
		return elements.retainAll(c);
	}

	/* (non-Javadoc)
	 * @see java.util.Set#removeAll(java.util.Collection)
	 */
	public boolean removeAll(Collection<?> c) {
		return elements.removeAll(c);
	}

	/* (non-Javadoc)
	 * @see java.util.Set#clear()
	 */
	public void clear() {
		elements.clear();
	}
	
	/**
	 * A Fuzzy Set element. This is a wrapper for a fuzzy set element.  
	 * 
	 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
	 * @since 0.1
	 */
	public static class Element implements Serializable {

		private static final long serialVersionUID = -2610505335269904685L;
		
		private final Object entry;
		private final double degree;
		
		/**
		 * @param entry a fuzzy set element
		 * @param degree the membership degree of the element
		 */
		public Element(Object entry, double degree) {
			this.entry = entry;
			this.degree = degree;
		}
		
		/**
		 * @return the entry
		 */
		public Object getEntry() {
			return entry;
		}
		
		/**
		 * @return the degree
		 */
		public double getDegree() {
			return degree;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if(obj == null) {
				return false;
			}
			if(obj == this) {
				return true;
			}
			if(!(obj instanceof Element)) {
				return false;
			}
			final Element that = (Element)obj;
			if(this.degree == that.degree) {
				if(this.entry != null) {
					return this.entry.equals(that.entry);
				}
				return true;
			}
			return false;
		}
		
		public int hashCode() {
			int hash = "Element".hashCode();
			hash <<= 2;
			hash ^= (int)this.degree;
			if(this.entry != null) {
				hash <<= 2;
				hash ^= this.entry.hashCode();
			}
			return hash;
		};
		
	}

}
