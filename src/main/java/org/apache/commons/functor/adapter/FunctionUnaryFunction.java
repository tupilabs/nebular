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
 * {@link Function Function}
 * to the
 * {@link UnaryFunction UnaryFunction} interface
 * by ignoring the unary argument.
 * <p/>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if the
 * underlying functor is.  Attempts to serialize
 * an instance whose delegate is not
 * <code>Serializable</code> will result in an exception.
 *
 * @param <A> the argument type.
 * @param <T> the returned value type.
 * @version $Revision: 1234990 $ $Date: 2012-01-23 19:18:10 -0200 (Mon, 23 Jan 2012) $
 * @author Rodney Waldhoff
 */
public final class FunctionUnaryFunction<A, T> implements UnaryFunction<A, T>, Serializable {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = 1993899041200996524L;
    /** The {@link Function Function} I'm wrapping. */
    private final Function<? extends T> function;

    /**
     * Create a new FunctionUnaryFunction.
     * @param function to adapt
     */
    public FunctionUnaryFunction(Function<? extends T> function) {
        this.function = Validate.notNull(function, "Function argument was null");
    }

    /**
     * {@inheritDoc}
     */
    public T evaluate(A obj) {
        return function.evaluate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof FunctionUnaryFunction<?, ?>
                && equals((FunctionUnaryFunction<?, ?>) that));
    }

    /**
     * Learn whether another FunctionUnaryFunction is equal to this.
     * @param that FunctionUnaryFunction to test
     * @return boolean
     */
    public boolean equals(FunctionUnaryFunction<?, ?> that) {
        return null != that && (null == function ? null == that.function : function.equals(that.function));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "FunctionUnaryFunction".hashCode();
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
        return "FunctionUnaryFunction<" + function + ">";
    }

    /**
     * Adapt a Function to the UnaryFunction interface.
     * @param <A> arg type
     * @param <T> result type
     * @param function to adapt
     * @return FunctionUnaryFunction
     */
    public static <A, T> FunctionUnaryFunction<A, T> adapt(Function<? extends T> function) {
        return null == function ? null : new FunctionUnaryFunction<A, T>(function);
    }

}
