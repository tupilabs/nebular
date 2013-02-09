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

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.functor.Function;

import fuzzy.mf.MembershipFunction;

/**
 * Product function.
 *
 * @param <T> numeric type used in this function
 */
public final class Product<T extends Number & Comparable<T>> implements Function<Collection<Double>> {

    private Collection<T> x;
    private MembershipFunction<T> mf;

    public Product(Collection<T> x, MembershipFunction<T> mf) {
        this.x = x;
        this.mf = mf;
    }

    public Collection<Double> evaluate() {
        Collection<Double> result = new ArrayList<Double>();
        for (T value : x) {
            Double fuzzyValue = mf.evaluate(value);
            result.add(value.doubleValue() * fuzzyValue);
        }
        return result;
    }

    public static <T extends Number & Comparable<T>> Collection<Double> of(Collection<T> x, MembershipFunction<T> mf) {
        return new Product<T>(x, mf).evaluate();
    }

}
