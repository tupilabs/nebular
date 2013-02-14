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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.functor.Function;

import fuzzy.mf.MembershipFunction;

/**
 * Returns a collection with the product of each crisp value with its
 * equivalent fuzzy result.
 *
 * @param <T> numeric type
 */
public final class CrispFuzzyProduct<T extends Number & Comparable<T>> implements Function<Collection<Double>> {

	/**
	 * A collection.
	 */
    private final Collection<T> collection;

    /**
     * Membership function.
     */
    private final MembershipFunction<T> mf;

    /**
     * Constructor with arguments.
     *
     * @param col a collection
     * @param mf membership function
     */
    public CrispFuzzyProduct(Collection<T> col, MembershipFunction<T> mf) {
        this.collection = Collections.unmodifiableCollection(col);
        this.mf = mf;
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.functor.Function#evaluate()
     */
    public Collection<Double> evaluate() {
        Collection<Double> result = new ArrayList<Double>();
        for (T value : collection) {
            Double fuzzyValue = mf.evaluate(value);
            result.add(value.doubleValue() * fuzzyValue);
        }
        return result;
    }

    /**
     * Returns the crisp fuzzy product of the elements in a collection.
     *
     * @param x a collection
     * @param mf membership function
     * @return crisp fuzzy producty of the elements in a collection
     */
    public static <T extends Number & Comparable<T>> Collection<Double> of(Collection<T> x, MembershipFunction<T> mf) {
        return new CrispFuzzyProduct<T>(x, mf).evaluate();
    }

}
