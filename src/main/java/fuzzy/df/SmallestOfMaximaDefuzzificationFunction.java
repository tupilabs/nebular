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

import java.util.Collection;

import org.apache.commons.functor.generator.range.NumericRange;

import fuzzy.internal.functions.Min;
import fuzzy.mf.MembershipFunction;
import fuzzy.util.MaxMF;

/**
 * Smallest Of Maxima defuzzification function. Equivalent to Matlab
 * <a href="http://www.mathworks.com/products/demos/shipping/fuzzy/defuzzdm.html#4">som</a>
 * defuzzification function.
 *
 * <p>
 * Uses the smallest maximum fuzzy value.
 * </p>
 *
 * <p>
 * This class is <strong>thread safe</strong>.
 * </p>
 *
 * @param <T> numeric type used in this defuzzification function
 * @since 0.2
 */
public class SmallestOfMaximaDefuzzificationFunction<T extends Number & Comparable<T>> implements
        DefuzzificationFunction<T> {

    /*
     * (non-Javadoc)
     * @see fuzzy.df.DefuzzificationFunction#evaluate(org.apache.commons.functor.generator.range.NumericRange, fuzzy.mf.MembershipFunction)
     */
    public Double evaluate(NumericRange<T> x, MembershipFunction<T> mf) {
        Collection<Double> maximumValues = MaxMF.of(x.toCollection(), mf)
                .keySet();
        return Min.of(maximumValues, true);
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
		if(!(obj instanceof SmallestOfMaximaDefuzzificationFunction)) {
			return false;
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override	
	public int hashCode() {
		int hash = "SmallestOfMaximaDefuzzificationFunction".hashCode();
		return hash;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Smallest Of Maxima Defuzzification Function";
	}

}
