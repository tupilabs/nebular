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
 * {@link UnaryFunction UnaryFunction}
 * to the
 * {@link BinaryFunction BinaryFunction} interface
 * by ignoring the second binary argument.
 * <p/>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if the
 * underlying functor is.  Attempts to serialize
 * an instance whose delegate is not
 * <code>Serializable</code> will result in an exception.
 *
 * @param <L> the left argument type.
 * @param <R> the right argument type.
 * @param <T> the returned value type.
 * @version $Revision: 1234990 $ $Date: 2012-01-23 19:18:10 -0200 (Mon, 23 Jan 2012) $
 * @author Rodney Waldhoff
 */
public final class IgnoreRightFunction<L, R, T> implements BinaryFunction<L, R, T>, Serializable {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = -1564814716024791395L;
    /** The {@link UnaryFunction UnaryFunction} I'm wrapping. */
    private final UnaryFunction<? super L, ? extends T> function;

    /**
     * Create a new IgnoreRightFunction.
     * @param function UnaryFunction to wrap
     */
    public IgnoreRightFunction(UnaryFunction<? super L, ? extends T> function) {
        this.function = Validate.notNull(function, "UnaryFunction argument was null");
    }

    /**
     * {@inheritDoc}
     */
    public T evaluate(L left, R right) {
        return function.evaluate(left);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof IgnoreRightFunction<?, ?, ?>
                                    && equals((IgnoreRightFunction<?, ?, ?>) that));
    }

    /**
     * Learn whether another IgnoreRightFunction is equal to this.
     * @param that IgnoreRightFunction to test
     * @return boolean
     */
    public boolean equals(IgnoreRightFunction<?, ?, ?> that) {
        return null != that && (null == function ? null == that.function : function.equals(that.function));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "IgnoreRightFunction".hashCode();
        if (null != function) {
            hash ^= function.hashCode();
        }
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "IgnoreRightFunction<" + function + ">";
    }

    /**
     * Adapt a UnaryFunction to the BinaryFunction interface.
     * @param <L> left type
     * @param <R> right type
     * @param <T> result type
     * @param function UnaryFunction to adapt
     * @return IgnoreRightFunction
     */
    public static <L, R, T> IgnoreRightFunction<L, R, T> adapt(UnaryFunction<? super L, ? extends T> function) {
        return null == function ? null : new IgnoreRightFunction<L, R, T>(function);
    }

}
