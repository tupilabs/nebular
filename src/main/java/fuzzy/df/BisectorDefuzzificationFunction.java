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
package fuzzy.df;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.functor.generator.range.NumericRange;

import fuzzy.internal.functions.Sum;
import fuzzy.mf.MembershipFunction;

/**
 * Bisector defuzzification function. Equivalent to Matlab
 * <a href="http://www.mathworks.com/products/demos/shipping/fuzzy/defuzzdm.html#3">bisector</a>
 * defuzzification function.
 *
 * <p>
 * The bisector is the vertical line that will divide the region into two
 * sub-regions of equal area. It is sometimes, but not always coincident with
 * the centroid line.
 * </p>
 *
 * <p>
 * This class is <strong>thread safe</strong>.
 * </p>
 *
 * @param <T> numeric type used in this defuzzification function
 * @since 0.2
 */
public class BisectorDefuzzificationFunction<T extends Number & Comparable<T>>
        implements DefuzzificationFunction<T> {

    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException if total area is zero
     */
	@Override
    public Double apply(NumericRange<T> x, MembershipFunction<T> mf) {
        ArrayList<T> values = new ArrayList<T>(x.toCollection());
        Collection<Double> fuzzyValues = new ArrayList<Double>();
        for (T crispValue : values) {
            fuzzyValues.add(mf.apply(crispValue));
        }
        double totalArea = Sum.of(fuzzyValues);
        if (totalArea == 0)
            throw new IllegalArgumentException(
                    "Total area is zero in bisector defuzzification!");
        double result = 0.0;
        double temp = 0.0;
        for (int i = 0; i < values.size(); ++i) {
            T value = values.get(i);
            result = value.doubleValue();
            temp = temp + mf.apply(value);
            if (temp >= (totalArea / 2)) {
                break;
            }
        }
        return result;
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
		if(!(obj instanceof BisectorDefuzzificationFunction)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = "BisectorDefuzzificationFunction".hashCode();
		return hash;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bisector Defuzzification Function";
	}

}
