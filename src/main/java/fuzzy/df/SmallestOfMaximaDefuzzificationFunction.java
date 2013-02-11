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

import org.apache.commons.functor.UnaryProcedure;
import org.apache.commons.functor.generator.loop.IteratorToGeneratorAdapter;
import org.apache.commons.functor.generator.range.NumericRange;

import fuzzy.internal.functions.MapAbs;
import fuzzy.internal.functions.Min;
import fuzzy.mf.MembershipFunction;
import fuzzy.util.MaxMF;

/**
 * Smallest of maxima defuzzification function.
 */
public class SmallestOfMaximaDefuzzificationFunction implements
        DefuzzificationFunction<Double> {
    public Double evaluate(NumericRange<Double> x, MembershipFunction<Double> mf) {
        Collection<Double> maximumValues = MaxMF.of(x.toCollection(), mf)
                .keySet();
        IteratorToGeneratorAdapter<Double> adapter = IteratorToGeneratorAdapter
                .<Double> adapt(maximumValues.iterator());
        UnaryProcedure<Double> proc = new MapAbs();
        adapter.run(proc);
        Collection<Double> absMaximumValues = ((MapAbs) proc).getCol();
        return Min.of(absMaximumValues);
    }
}
