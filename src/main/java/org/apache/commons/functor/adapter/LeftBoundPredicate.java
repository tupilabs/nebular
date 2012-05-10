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

import org.apache.commons.functor.BinaryPredicate;
import org.apache.commons.functor.UnaryPredicate;
import org.apache.commons.lang3.Validate;

/**
 * Adapts a
 * {@link BinaryPredicate BinaryPredicate}
 * to the
 * {@link UnaryPredicate UnaryPredicate} interface
 * using a constant left-side argument.
 * <p/>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if the
 * underlying objects are.  Attempts to serialize
 * an instance whose delegates are not
 * <code>Serializable</code> will result in an exception.
 *
 * @param <A> the argument type.
 * @version $Revision: 1234990 $ $Date: 2012-01-23 19:18:10 -0200 (Mon, 23 Jan 2012) $
 * @author Rodney Waldhoff
 */
public final class LeftBoundPredicate<A> implements UnaryPredicate<A>, Serializable {

    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = 3851481216909573294L;
    /** The {@link BinaryPredicate BinaryPredicate} I'm wrapping. */
    private final BinaryPredicate<Object, ? super A> predicate;
    /** The parameter to pass to {@code predicate}. */
    private final Object param;

    /**
     * Create a new LeftBoundPredicate.
     * @param <L> the left argument type.
     * @param predicate the predicate to adapt
     * @param arg the constant argument to use
     */
    @SuppressWarnings("unchecked")
    public <L> LeftBoundPredicate(BinaryPredicate<? super L, ? super A> predicate, L arg) {
        this.predicate =
            (BinaryPredicate<Object, ? super A>) Validate.notNull(predicate,
                "BinaryPredicate argument was null");
        this.param = arg;
    }

    /**
     * {@inheritDoc}
     */
    public boolean test(A obj) {
        return predicate.test(param, obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof LeftBoundPredicate<?> && equals((LeftBoundPredicate<?>) that));
    }

    /**
     * Learn whether another LeftBoundPredicate is equal to this.
     * @param that LeftBoundPredicate to test
     * @return boolean
     */
    public boolean equals(LeftBoundPredicate<?> that) {
        return null != that
                && (null == predicate ? null == that.predicate : predicate.equals(that.predicate))
                && (null == param ? null == that.param : param.equals(that.param));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "LeftBoundPredicate".hashCode();
        if (null != predicate) {
            hash <<= 2;
            hash ^= predicate.hashCode();
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
        return "LeftBoundPredicate<" + predicate + "(" + param + ",?)>";
    }

    /**
     * Adapt a BinaryPredicate to the UnaryPredicate interface.
     * @param <L> the left argument type.
     * @param <R> the right argument type.
     * @param predicate to adapt
     * @param arg Object argument to always send as the left operand to the wrapped function
     * @return LeftBoundPredicate
     */
    public static <L, R> LeftBoundPredicate<R> bind(BinaryPredicate<? super L, ? super R> predicate, L arg) {
        return null == predicate ? null : new LeftBoundPredicate<R>(predicate, arg);
    }
}
