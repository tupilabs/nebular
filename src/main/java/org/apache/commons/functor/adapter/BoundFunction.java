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

import org.apache.commons.functor.Function;
import org.apache.commons.functor.UnaryFunction;
import org.apache.commons.lang3.Validate;

/**
 * Adapts a
 * {@link UnaryFunction UnaryFunction}
 * to the
 * {@link Function Function} interface
 * using a constant unary argument.
 * <p/>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if the
 * underlying objects are.  Attempts to serialize
 * an instance whose delegates are not
 * <code>Serializable</code> will result in an exception.
 *
 * @param <T> the returned value type.
 * @version $Revision: 1234990 $ $Date: 2012-01-23 19:18:10 -0200 (Mon, 23 Jan 2012) $
 * @author Rodney Waldhoff
 */
public final class BoundFunction<T> implements Function<T>, Serializable {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = 8873081237760986490L;
    /** The {@link UnaryFunction UnaryFunction} I'm wrapping. */
    private final UnaryFunction<Object, ? extends T> function;
    /** The argument to pass to {@code function}. */
    private final Object arg;

    /**
     * Create a new BoundFunction instance.
     * @param <A> the argument value type
     * @param function the function to adapt
     * @param arg the constant argument to use
     */
    @SuppressWarnings("unchecked")
    public <A> BoundFunction(UnaryFunction<? super A, ? extends T> function, A arg) {
        this.function =
            (UnaryFunction<Object, ? extends T>) Validate.notNull(function,
                "UnaryFunction argument was null");
        this.arg = arg;
    }

    /**
     * {@inheritDoc}
     */
    public T evaluate() {
        return function.evaluate(arg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof BoundFunction<?> && equals((BoundFunction<?>) that));
    }

    /**
     * Learn whether another BoundFunction is equal to this.
     * @param that BoundFunction to test
     * @return boolean
     */
    public boolean equals(BoundFunction<?> that) {
        if (that == null) {
            return false;
        }
        if (!(that.function.equals(this.function))) {
            return false;
        }
        return that.arg == this.arg || that.arg != null && that.arg.equals(this.arg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = "BoundFunction".hashCode();
        result <<= 2;
        result |= function.hashCode();
        result <<= 2;
        return arg == null ? result : result | arg.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "BoundFunction<" + function.toString() + "(" + arg + ")>";
    }

    /**
     * Adapt the given, possibly-<code>null</code>,
     * {@link UnaryFunction UnaryFunction} to the
     * {@link Function Function} interface by binding
     * the specified <code>Object</code> as a constant
     * argument.
     * When the given <code>UnaryFunction</code> is <code>null</code>,
     * returns <code>null</code>.
     * @param <A> input type
     * @param <T> result type
     * @param function the possibly-<code>null</code>
     *        {@link UnaryFunction UnaryFunction} to adapt
     * @param arg the object to bind as a constant argument
     * @return a <code>BoundFunction</code> wrapping the given
     *         {@link UnaryFunction UnaryFunction}, or <code>null</code>
     *         if the given <code>UnaryFunction</code> is <code>null</code>
     */
    public static <A, T> BoundFunction<T> bind(UnaryFunction<? super A, ? extends T> function, A arg) {
        return null == function ? null : new BoundFunction<T>(function, arg);
    }

}
