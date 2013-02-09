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

import fuzzy.internal.functions.Product;
import fuzzy.internal.functions.Sum;
import fuzzy.mf.MembershipFunction;

/**
 * Centroid defuzzification function.
 * <p>
 * Also known as <strong>center of gravity</strong> or <strong>center of
 * area</p> defuzzication function. Created by <strong>Sugeno</strong> in
 * 1985, is the most used technique and very accurate.
 * <p>
 * TODO: add details about implementation, serialization and thread-safety
 * TODO: add example
 * @param <T> numeric type used in this defuzzification function
 */
public class CentroidDefuzzificationFunction<T extends Number & Comparable<T>> implements DefuzzificationFunction<T> {

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException if the area is zero
     */
    public double defuzzify(NumericRange<T> x, MembershipFunction<T> mf) {
        Collection<Double> fuzzyValues = new ArrayList<Double>();
        for (T crispValue : x.toCollection()) {
            fuzzyValues.add(mf.evaluate(crispValue));
        }
        double totalArea = Sum.of(fuzzyValues);
        if (totalArea == 0)
            throw new IllegalArgumentException(
                    "Total area is zero in centroid defuzzification!");
        Collection<Double> product = Product.of(x.toCollection(), mf);
        double sum2 = Sum.of(product);
        double out = sum2 / totalArea;
        return out;
    }

}
