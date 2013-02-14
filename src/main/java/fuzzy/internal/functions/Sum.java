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

import java.util.Collection;

import org.apache.commons.functor.Function;

/**
 * Returns the sum of all elements of a collection. In case the collection is empty it
 * returns 0.0 (double).
 *
 * @param <T> numeric type
 */
public final class Sum<T extends Number & Comparable<T>> implements Function<Double> {

	/**
	 * A collection.
	 */
    private Collection<T> col;

    /**
     * Constructor with arguments.
     *
     * @param col a collection
     */
    public Sum(Collection<T> col) {
        this.col = col;
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.functor.Function#evaluate()
     */
    public Double evaluate() {
        double result = 0.0;
        for (T value : col) {
            result += value.doubleValue();
        }
        return result;
    }

    /**
     * Returns the sum of all elements of a collection.
     *
     * @param col a collection
     * @return sum
     */
    public static <T extends Number & Comparable<T>> double of(Collection<T> x) {
        return new Sum<T>(x).evaluate();
    }
}
