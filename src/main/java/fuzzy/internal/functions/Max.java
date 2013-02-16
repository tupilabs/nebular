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
 * Returns the maximum value in a collection. In case the collection is empty it
 * returns 0.0 (double).
 *
 * @param <T> numeric type
 * @since 0.2
 */
public class Max<T extends Number & Comparable<T>> implements Function<Double> {

	/**
	 * Collection.
	 */
    private final Collection<T> col;
    /**
     * Whether use the absolute value of elements or not.
     */
    private final boolean abs;

    /**
     * Constructor with arguments.
     *
     * @param col a collection
     * @param abs whether to use absolute values or not
     */
    public Max(Collection<T> col, boolean abs) {
        this.col = col;
        this.abs = abs;
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.functor.Function#evaluate()
     */
    public Double evaluate() {
        double out = 0.0;
        double max = 0.0;
        boolean first = true;
        for (T elem : col) {
            final double value;
            if (abs) {
                value = Math.abs(elem.doubleValue());
            } else {
                value = elem.doubleValue();
            }
            if (first || value > max) {
                max = value;
                out = elem.doubleValue();
                first = false;
            }
        }
        return out;
    }

    /**
     * Returns the maximum value found in a collection.
     *
     * @param col a collection
     * @param abs whether to use absolute values or not
     * @return maximum value
     */
    public static <T extends Number & Comparable<T>> double of(Collection<T> col, boolean abs) {
        return new Max<T>(col, abs).evaluate();
    }

}
