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
import org.apache.commons.functor.UnaryProcedure;
import org.apache.commons.lang3.Validate;

/**
 * Adapts a {@link UnaryFunction UnaryFunction}
 * to the {@link UnaryProcedure UnaryProcedure}
 * interface by ignoring the value returned
 * by the function.
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
public final class UnaryFunctionUnaryProcedure<A> implements UnaryProcedure<A>, Serializable {

    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = -3578673875995684811L;
    /** The {@link UnaryFunction UnaryFunction} I'm wrapping. */
    private final UnaryFunction<? super A, ?> function;

    /**
     * Create an {@link UnaryProcedure UnaryProcedure} wrapping
     * the given {@link UnaryFunction UnaryFunction}.
     * @param function the {@link UnaryFunction UnaryFunction} to wrap
     */
    public UnaryFunctionUnaryProcedure(UnaryFunction<? super A, ?> function) {
        this.function = Validate.notNull(function, "UnaryFunction argument was null");
    }

    /**
     * {@link UnaryFunction#evaluate Evaluate} my function, but
     * ignore its returned value.
     * {@inheritDoc}
     */
    public void run(A obj) {
        function.evaluate(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this
                || (that instanceof UnaryFunctionUnaryProcedure<?> && equals((UnaryFunctionUnaryProcedure<?>) that));
    }

    /**
     * Learn whether a specified UnaryFunctionUnaryPredicate is equal to this.
     * @param that the UnaryFunctionUnaryPredicate to test
     * @return boolean
     */
    public boolean equals(UnaryFunctionUnaryProcedure<?> that) {
        return null != that && function.equals(that.function);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "UnaryFunctionUnaryProcedure".hashCode();
        hash ^= function.hashCode();
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "UnaryFunctionUnaryProcedure<" + function + ">";
    }

    /**
     * Adapt the given, possibly-<code>null</code>,
     * {@link UnaryFunction UnaryFunction} to the
     * {@link UnaryProcedure UnaryProcedure} interface.
     * When the given <code>UnaryFunction</code> is <code>null</code>,
     * returns <code>null</code>.
     *
     * @param <A> the argument type.
     * @param function the possibly-<code>null</code>
     *        {@link UnaryFunction UnaryFunction} to adapt
     * @return a {@link UnaryProcedure UnaryProcedure} wrapping the given
     *         {@link UnaryFunction UnaryFunction}, or <code>null</code>
     *         if the given <code>UnaryFunction</code> is <code>null</code>
     */
    public static <A> UnaryFunctionUnaryProcedure<A> adapt(UnaryFunction<? super A, ?> function) {
        return null == function ? null : new UnaryFunctionUnaryProcedure<A>(function);
    }

}
