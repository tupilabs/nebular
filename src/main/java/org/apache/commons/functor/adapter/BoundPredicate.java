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

import org.apache.commons.functor.Predicate;
import org.apache.commons.functor.UnaryPredicate;
import org.apache.commons.lang3.Validate;

/**
 * Adapts a
 * {@link UnaryPredicate UnaryPredicate}
 * to the
 * {@link Predicate Predicate} interface
 * using a constant unary argument.
 * <p/>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if the
 * underlying objects are.  Attempts to serialize
 * an instance whose delegates are not
 * <code>Serializable</code> will result in an exception.
 *
 * @version $Revision: 1365377 $ $Date: 2012-07-24 21:59:23 -0300 (Tue, 24 Jul 2012) $
 */
public final class BoundPredicate implements Predicate, Serializable {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = -5721164265625291834L;
    /** The {@link UnaryPredicate UnaryPredicate} I'm wrapping. */
    private final UnaryPredicate<Object> predicate;
    /** The parameter to pass to {@code predicate}. */
    private final Object param;

    /**
     * Create a new BoundPredicate instance.
     * @param <A> input type
     * @param predicate the predicate to adapt
     * @param arg the constant argument to use
     */
    @SuppressWarnings("unchecked")
    public <A> BoundPredicate(UnaryPredicate<? super A> predicate, A arg) {
        this.predicate =
            (UnaryPredicate<Object>) Validate.notNull(predicate,
                "UnaryPredicate argument was null");
        this.param = arg;
    }

    /**
     * {@inheritDoc}
     */
    public boolean test() {
        return predicate.test(param);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof BoundPredicate && equals((BoundPredicate) that));
    }

    /**
     * Learn whether another BoundPredicate is equal to this.
     * @param that BoundPredicate to test
     * @return boolean
     */
    public boolean equals(BoundPredicate that) {
        return null != that
                && predicate.equals(that.predicate)
                && (null == param ? null == that.param : param.equals(that.param));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "BoundPredicate".hashCode();
        hash <<= 2;
        hash ^= predicate.hashCode();
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
        return "BoundPredicate<" + predicate + "(" + param + ")>";
    }

    /**
     * Adapt the given, possibly-<code>null</code>,
     * {@link UnaryPredicate UnaryPredicate} to the
     * {@link Predicate Predicate} interface by binding
     * the specified <code>Object</code> as a constant
     * argument.
     * When the given <code>UnaryPredicate</code> is <code>null</code>,
     * returns <code>null</code>.
     *
     * @param <A> input type
     * @param predicate the possibly-<code>null</code>
     *        {@link UnaryPredicate UnaryPredicate} to adapt
     * @param arg the object to bind as a constant argument
     * @return a <code>BoundPredicate</code> wrapping the given
     *         {@link UnaryPredicate UnaryPredicate}, or <code>null</code>
     *         if the given <code>UnaryPredicate</code> is <code>null</code>
     */
    public static <A> BoundPredicate bind(UnaryPredicate<? super A> predicate, A arg) {
        return null == predicate ? null : new BoundPredicate(predicate, arg);
    }

}
