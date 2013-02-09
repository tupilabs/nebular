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

public class BisectorDefuzzificationFunction<T extends Number & Comparable<T>> implements DefuzzificationFunction<T> {

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException if total area is zero
     */
    public Double evaluate(NumericRange<T> x, MembershipFunction<T> mf) {
        ArrayList<T> values = new ArrayList<T>(x.toCollection());
        Collection<Double> fuzzyValues = new ArrayList<Double>();
        for (T crispValue : x.toCollection()) {
            fuzzyValues.add(mf.evaluate(crispValue));
        }
        double totalArea = Sum.of(fuzzyValues);
        if (totalArea == 0)
            throw new IllegalArgumentException(
                    "Total area is zero in bisector defuzzification!");
        double result = 0.0;
        double temp = 0.0;
        for (int i = 0; i < values.size() ; ++i) {
            T value = values.get(i);
            result = value.doubleValue();
            temp = temp + mf.evaluate(value);
            if(temp >= (totalArea/2)) {
                break;
            }
        }
        return result;
    }

}
