/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.functor.core.composite;

import java.io.Serializable;

import org.apache.commons.functor.Function;
import org.apache.commons.functor.UnaryFunction;

/**
 * A Function whose result is then run through a UnaryFunction.
 *
 * @param <T> the returned value type.
 * @version $Revision: 1180207 $ $Date: 2011-10-07 17:33:34 -0300 (Fri, 07 Oct 2011) $
 * @author Matt Benson
 */
public class TransformedFunction<T> implements Function<T>, Serializable {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = 1201423110871342081L;

    /**
     * Type-remembering helper.
     *
     * @param <X> the adapted function argument type
     */
    private static final class Helper<X, T> implements Function<T>, Serializable {
        /**
         * serialVersionUID declaration.
         */
        private static final long serialVersionUID = -7177784125292465809L;
        /**
         * The preceding function.
         */
        private Function<? extends X> preceding;
        /**
         * The following function.
         */
        private UnaryFunction<? super X, ? extends T> following;

        /**
         * Create a new Helper.
         * @param preceding Function
         * @param following UnaryFunction
         */
        private Helper(Function<? extends X> preceding, UnaryFunction<? super X, ? extends T> following) {
            this.preceding = preceding;
            this.following = following;
        }

        /**
         * {@inheritDoc}
         */
        public T evaluate() {
            return following.evaluate(preceding.evaluate());
        }
    }

    /**
     * The adapted helper.
     */
    private final Helper<?, T> helper;

    /**
     * Create a new TransformedFunction.
     * @param <X> the preceding function argument type.
     * @param preceding Function
     * @param following UnaryFunction
     */
    public <X> TransformedFunction(Function<? extends X> preceding,
            UnaryFunction<? super X, ? extends T> following) {
        this.helper = new Helper<X, T>(preceding, following);
    }

    /**
     * {@inheritDoc}
     */
    public final T evaluate() {
        return helper.evaluate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(Object obj) {
        return obj == this || obj instanceof TransformedFunction<?> && equals((TransformedFunction<?>) obj);
    }

    /**
     * Learn whether another TransformedFunction is equal to <code>this</code>.
     * @param that instance to test
     * @return whether equal
     */
    public final boolean equals(TransformedFunction<?> that) {
        return that != null && that.helper.preceding.equals(this.helper.preceding)
                && that.helper.following.equals(this.helper.following);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = "TransformedFunction".hashCode();
        result <<= 2;
        result |= helper.following.hashCode();
        result <<= 2;
        result |= helper.preceding.hashCode();
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "TransformedFunction<" + helper.preceding + "; " + helper.following + ">";
    }
}
