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
package org.apache.commons.functor.adapter;

import java.io.Serializable;

import org.apache.commons.functor.UnaryFunction;
import org.apache.commons.functor.UnaryPredicate;
import org.apache.commons.lang3.Validate;

/**
 * Adapts a <code>Boolean</code>-valued
 * {@link UnaryFunction UnaryFunction}
 * to the {@link UnaryPredicate UnaryPredicate}
 * interface.
 * <p/>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if the
 * underlying function is.  Attempts to serialize
 * an instance whose delegate is not
 * <code>Serializable</code> will result in an exception.
 *
 * @param <A> the argument type.
 * @version $Revision: 1365377 $ $Date: 2012-07-24 21:59:23 -0300 (Tue, 24 Jul 2012) $
 */
public final class UnaryFunctionUnaryPredicate<A> implements UnaryPredicate<A>, Serializable {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = -9211927278252224707L;
    /** The {@link UnaryFunction UnaryFunction} I'm wrapping. */
    private final UnaryFunction<? super A, Boolean> function;

    /**
     * Create an {@link UnaryPredicate UnaryPredicate} wrapping
     * the given {@link UnaryFunction UnaryFunction}.
     * @param function the {@link UnaryFunction UnaryFunction} to wrap
     */
    public UnaryFunctionUnaryPredicate(UnaryFunction<? super A, Boolean> function) {
        this.function = Validate.notNull(function, "UnaryFunction argument was null");
    }

    /**
     * {@inheritDoc}
     * Returns the <code>boolean</code> value of the non-<code>null</code>
     * <code>Boolean</code> returned by the {@link UnaryFunction#evaluate evaluate}
     * method of my underlying function.
     */
    public boolean test(A obj) {
        return function.evaluate(obj).booleanValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this
                || (that instanceof UnaryFunctionUnaryPredicate<?> && equals((UnaryFunctionUnaryPredicate<?>) that));
    }

    /**
     * Learn whether another UnaryFunctionUnaryPredicate is equal to this.
     * @param that UnaryFunctionUnaryPredicate to test
     * @return boolean
     */
    public boolean equals(UnaryFunctionUnaryPredicate<?> that) {
        return null != that && function.equals(that.function);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "UnaryFunctionUnaryPredicate".hashCode();
        hash ^= function.hashCode();
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "UnaryFunctionUnaryPredicate<" + function + ">";
    }

    /**
     * Adapt the given, possibly-<code>null</code>,
     * {@link UnaryFunction UnaryFunction} to the
     * {@link UnaryPredicate UnaryPredicate} interface.
     * When the given <code>UnaryFunction</code> is <code>null</code>,
     * returns <code>null</code>.
     *
     * @param <A> the argument type.
     * @param function the possibly-<code>null</code>
     *        {@link UnaryFunction UnaryFunction} to adapt
     * @return a {@link UnaryPredicate UnaryPredicate} wrapping the given
     *         {@link UnaryFunction UnaryFunction}, or <code>null</code>
     *         if the given <code>UnaryFunction</code> is <code>null</code>
     */
    public static <A> UnaryFunctionUnaryPredicate<A> adapt(UnaryFunction<? super A, Boolean> function) {
        return null == function ? null : new UnaryFunctionUnaryPredicate<A>(function);
    }

}
