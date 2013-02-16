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

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
 * @since 0.2
 */
public class MaxMF<T extends Number & Comparable<T>> implements BinaryFunction<Collection<T>, MembershipFunction<T>, Map<Double, Double>> {

    public static final int DEFAULT_PRECISION = 4; // same as Matlab
    public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;
    
    private final int precision;
    private final RoundingMode roundingMode;
    
    public MaxMF() {
        precision = DEFAULT_PRECISION;
        roundingMode = DEFAULT_ROUNDING_MODE;
    }
    
    public MaxMF(int precision, RoundingMode roundingMode) {
        this.precision = precision;
        this.roundingMode = roundingMode;
    }
    
	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.functor.BinaryFunction#evaluate(java.lang.Object, java.lang.Object)
	 */
	public Map<Double, Double> evaluate(Collection<T> x,
            MembershipFunction<T> mf) {
        Map<Double, Double> max = new HashMap<Double, Double>();
        BigDecimal maxValue = BigDecimal.valueOf(0.0);
        boolean first = true;
        for(T value : x) {
            BigDecimal temp = new BigDecimal(mf.evaluate(value), new MathContext(precision, roundingMode));
            if (first || temp.compareTo(maxValue) > 0) {
                first = false;
                maxValue = temp;
                max.clear();
                max.put(value.doubleValue(), temp.doubleValue());
            } else if (temp.compareTo(maxValue) == 0) {
                max.put(value.doubleValue(), temp.doubleValue());
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