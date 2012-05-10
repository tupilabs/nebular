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

import org.apache.commons.functor.BinaryFunction;
import org.apache.commons.functor.UnaryFunction;
import org.apache.commons.lang3.Validate;

/**
 * Adapts a
 * {@link BinaryFunction BinaryFunction}
 * to the
 * {@link UnaryFunction UnaryFunction} interface
 * using a constant right-side argument.
 * <p/>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if the
 * underlying objects are.  Attempts to serialize
 * an instance whose delegates are not
 * <code>Serializable</code> will result in an exception.
 *
 * @param <A> the argument type.
 * @param <T> the returned value type.
 * @version $Revision: 1234990 $ $Date: 2012-01-23 19:18:10 -0200 (Mon, 23 Jan 2012) $
 * @author Rodney Waldhoff
 */
public final class RightBoundFunction<A, T> implements UnaryFunction<A, T>, Serializable {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = -1313775632123661422L;
    /** The {@link BinaryFunction BinaryFunction} I'm wrapping. */
    private final BinaryFunction<? super A, Object, ? extends T> function;
    /** The parameter to pass to {@code function}. */
    private final Object param;

    /**
     * @param <R> bound arg type
     * @param function the function to adapt
     * @param arg the constant argument to use
     */
    @SuppressWarnings("unchecked")
    public <R> RightBoundFunction(BinaryFunction<? super A, ? super R, ? extends T> function, R arg) {
        this.function =
            (BinaryFunction<? super A, Object, ? extends T>) Validate.notNull(
                function, "left-hand BinaryFunction argument was null");
        this.param = arg;
    }

    /**
     * {@inheritDoc}
     */
    public T evaluate(A obj) {
        return function.evaluate(obj, param);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof RightBoundFunction<?, ?> && equals((RightBoundFunction<?, ?>) that));
    }

    /**
     * Learn whether another RightBoundFunction is equal to this.
     * @param that RightBoundFunction to test
     * @return boolean
     */
    public boolean equals(RightBoundFunction<?, ?> that) {
        return null != that
                && (null == function ? null == that.function : function.equals(that.function))
                && (null == param ? null == that.param : param.equals(that.param));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "RightBoundFunction".hashCode();
        if (null != function) {
            hash <<= 2;
            hash ^= function.hashCode();
        }
        if (null != param) {
            hash <<= 2;
            hash ^= param.hashCode();
        }
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RightBoundFunction<" + function + "(?," + param + ")>";
    }

    /**
     * Adapt a BinaryFunction to the UnaryFunction interface.
     * @param <L> the left argument type.
     * @param <R> the right argument type.
     * @param <T> the returned value type.
     * @param function BinaryFunction to adapt
     * @param arg Object that will always be used for the right side of the BinaryFunction delegate.
     * @return RightBoundFunction
     */
    public static <L, R, T> RightBoundFunction<L, T> bind(BinaryFunction<? super L, ? super R, ? extends T> function,
                                                          R arg) {
        return null == function ? null : new RightBoundFunction<L, T>(function, arg);
    }

}
