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
package fuzzy.mf;

import org.apache.commons.functor.UnaryFunction;

/**
 * Membership Function marker interface. All provided membership functions 
 * implement this interface.
 * <p>
 * Implementors are encouraged but not required to make their membership 
 * functions {@link java.io.Serializable Serializable}.
 * </p>
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 * @param <T> unary function parameter value
 */
public interface MembershipFunction<T> extends UnaryFunction<T, Double> {

    /**
     * Fuzzify the given crisp variable.
     *
     * @param x crisp variable
     */
    Double evaluate(T x);

}
