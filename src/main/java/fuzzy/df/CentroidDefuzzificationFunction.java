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
import java.util.Collections;

import org.apache.commons.functor.generator.range.NumericRange;

import fuzzy.internal.functions.Sum;
import fuzzy.mf.MembershipFunction;
import fuzzy.util.CrispFuzzyProduct;

/**
 * <p>
 * Also known as <strong>center of gravity</strong> or <strong>center of
 * area</strong> defuzzication function, created by <strong>Sugeno</strong> in
 * 1985, is the most used technique and very accurate.
 * </p>
 *
 * <p>
 * The resulting value is computed using the formula:
 * {@literal sum ( product ( x, mf ) ) / total_area } where x is the input crisp
 * range, mf a membership function and total_area is the sum of all the fuzzy
 * values (evaluated range).
 * </p>
 *
 * <p>
 * This class is <strong>thread safe</strong>.
 * </p>
 *
 * @param <T> numeric type used in this defuzzification function
 */
public class CentroidDefuzzificationFunction<T extends Number & Comparable<T>>
		implements DefuzzificationFunction<T> {
	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalArgumentException if total area is zero
	 */
	public Double evaluate(NumericRange<T> x, MembershipFunction<T> mf) {
		Collection<T> values = Collections.unmodifiableCollection(x.toCollection());
		Collection<Double> fuzzyValues = new ArrayList<Double>();
		for (T crispValue : values) {
			fuzzyValues.add(mf.evaluate(crispValue));
		}
		double totalArea = Sum.of(fuzzyValues);
		if (totalArea == 0)
			throw new IllegalArgumentException(
					"Total area is zero in centroid defuzzification!");
		Collection<Double> product = CrispFuzzyProduct.of(x.toCollection(), mf);
		double sum2 = Sum.of(product);
		double out = sum2 / totalArea;
		return out;
	}
}
