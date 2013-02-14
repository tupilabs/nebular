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
import org.apache.commons.math3.stat.descriptive.moment.Mean;

import fuzzy.mf.MembershipFunction;
import fuzzy.util.MaxMF;

/**
 * <p>
 * Uses the mean of maximum fuzzy value.
 * </p>
 *
 * <p>
 * This class is <strong>thread safe</strong>.
 * </p>
 *
 * @param <T> numeric type used in this defuzzification function
 */
public class MeanOfMaximaDefuzzificationFunction implements
        DefuzzificationFunction<Double> {
    public Double evaluate(NumericRange<Double> x, MembershipFunction<Double> mf) {
        Collection<Double> maximumValues = MaxMF.of(x.toCollection(), mf)
                .keySet();
        double mean = new Mean().evaluate(Doubles.toArray(maximumValues));
        return mean;
    }

    private static class Doubles {
        public static double[] toArray(Collection<Double> col) {
            col.remove(null);
            double[] r = new double[col.size()];
            int i = 0;
            for (Double value : col) {
                r[i] = value;
                i++;
            }
            return r;
        }
    }
}
