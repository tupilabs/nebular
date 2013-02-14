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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.functor.BinaryFunction;

import fuzzy.mf.MembershipFunction;

/**
 * Gets the maximum values of a membership function applied over a collection.
 * Returns a map which the key is the the crisp value and the value its
 * fuzzy value.
 *
 * @param <T> numeric type
 */
public class MaxMF<T extends Number & Comparable<T>> implements BinaryFunction<Collection<T>, MembershipFunction<T>, Map<Double, Double>> {

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.functor.BinaryFunction#evaluate(java.lang.Object, java.lang.Object)
	 */
	public Map<Double, Double> evaluate(Collection<T> x,
            MembershipFunction<T> mf) {
        Map<Double, Double> max = new HashMap<Double, Double>();
        double maxValue = 0.0;
        boolean first = true;
        for(T value : x) {
            double temp = mf.evaluate(value);
            if (first || temp > maxValue) {
                first = false;
                maxValue = temp;
                max.clear();
                max.put(value.doubleValue(), temp);
            } else if (temp == maxValue) {
                max.put(value.doubleValue(), temp);
            } // else ignore since it's less than the maximum value
        }
        return max;
    }

	/**
	 * Returns the maximum values of a membership function applied over a collection.
	 *
	 * @param col a collection
	 * @param mf membership function
	 * @return maximum values of a membership function applied over a collection
	 */
    public static <T extends Number & Comparable<T>> Map<Double, Double> of(Collection<T> col,
            MembershipFunction<T> mf) {
        return new MaxMF<T>().evaluate(col, mf);
    }

}