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

import java.util.Collection;

import org.apache.commons.functor.Function;

public class Max<T extends Number & Comparable<T>> implements Function<Double> {

    private final Collection<T> col;

    public Max(Collection<T> col) {
        this.col = col;
    }
    
    public Double evaluate() {
        double max = 0.0;
        boolean first = true;
        for (T value : col) {
            if (first || value.doubleValue() > max) {
                max = value.doubleValue();
                first = false;
            }
        }
        return max;
    }
    
    public static <T extends Number & Comparable<T>> double of(Collection<T> col) {
        return new Max<T>(col).evaluate();
    }
    
}
