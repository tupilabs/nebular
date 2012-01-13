package fuzzy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import fuzzy.mf.MembershipFunction;

/**
 * Simple Fuzzy Set, that implements java.util.Set. It contains a membership 
 * function and a collection of elements. 
 * 
 * <p>
 * The membership function of the Fuzzy Set indicates the degree of an element 
 * in this set. The degree is given in a value between [0, 1]. The higher the 
 * value, more the element is member of this set.
 * 
 * <p>
 * For instance, if for an element x, the membership function returns 0.8, and 
 * for another element, say y, it returns 0.6, then we can say that x is more 
 * member of this Fuzzy Set than y.
 * 
 * <p>
 * The membership function is a UnaryFunctor, built using classes of Apache 
 * Functor. While the collection of elements is a simple ArrayList.
 * 
 * <p>
 * Each element is stored in the collection of elements wrapped in another 
 * objects that stores the original value, plus the membership degree.
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class FuzzySet<A> implements Set<Element<A>>, Serializable {

	private static final long serialVersionUID = -4847002337204078159L;

	private final MembershipFunction<A> membershipFunction;
	
	private final Collection<Element<A>> elements;
	
	public FuzzySet(MembershipFunction<A> membershipFunction) {
		this.membershipFunction = membershipFunction;
		elements = new ArrayList<Element<A>>();
	}
	
	/**
	 * Returns the membership function. 
	 * 
	 * @return membership function
	 */
	public MembershipFunction<A> getMembershipFunction() {
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean contains(Object o) {
		try {
			A anotherA = (A)o;
			return elements.contains(new Element(o, this.membershipFunction.evaluate(anotherA)));
		} catch (ClassCastException cce) {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Set#iterator()
	 */
	public Iterator<Element<A>> iterator() {
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
	public boolean add(Element<A> e) {
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
	public boolean addAll(Collection<? extends Element<A>> c) {
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

}

/**
 * A Fuzzy Set element. This is a wrapper for a fuzzy set element.  
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
class Element<A> implements Serializable {

	private static final long serialVersionUID = -2610505335269904685L;
	
	private final A entry;
	private final Double degree;
	
	/**
	 * @param entry a fuzzy set element
	 * @param degree the membership degree of the element
	 */
	public Element(A entry, Double degree) {
		this.entry = entry;
		this.degree = degree;
	}
	
	/**
	 * @return the entry
	 */
	public A getEntry() {
		return entry;
	}
	
	/**
	 * @return the degree
	 */
	public Double getDegree() {
		return degree;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return this.entry == null ? false : this.entry.equals(obj);
	}
	
}
