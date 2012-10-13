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

import org.apache.commons.functor.BinaryPredicate;
import org.apache.commons.lang3.Validate;

/**
 * Transposes (swaps) the arguments to some other
 * {@link BinaryPredicate predicate}.
 * For example, given a predicate <i>p</i>
 * and the ordered pair of arguments <i>a</i>,
 * <i>b</i>.
 * {@link #test tests}
 * <code>p.test(b,a)</code>.
 * <p>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if the
 * underlying functor is.  Attempts to serialize
 * an instance whose delegate is not
 * <code>Serializable</code> will result in an exception.
 * </p>
 * @param <L> the left argument type.
 * @param <R> the right argument type.
 * @version $Revision: 1365329 $ $Date: 2012-07-24 19:34:23 -0300 (Tue, 24 Jul 2012) $
 */
public class TransposedPredicate<L, R> implements BinaryPredicate<L, R>, Serializable {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = 3441209087576289240L;
    // attributes
    // ------------------------------------------------------------------------
    /**
     * The adapted predicate.
     */
    private final BinaryPredicate<? super R, ? super L> predicate;

    // constructor
    // ------------------------------------------------------------------------
    /**
     * Create a new TransposedPredicate.
     * @param predicate the BinaryPredicate to transpose
     */
    public TransposedPredicate(BinaryPredicate<? super R, ? super L> predicate) {
        this.predicate = Validate.notNull(predicate, "BinaryPredicate argument must not be null");
    }

    // functor interface
    // ------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    public final boolean test(L left, R right) {
        return predicate.test(right, left);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof TransposedPredicate<?, ?> && equals((TransposedPredicate<?, ?>) that));
    }

    /**
     * Learn whether another TransposedPredicate is equal to this.
     * @param that the TransposedPredicate to test
     * @return boolean
     */
    public boolean equals(TransposedPredicate<?, ?> that) {
        return null != that && predicate.equals(that.predicate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "TransposedPredicate".hashCode();
        hash ^= predicate.hashCode();
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "TransposedPredicate<" + predicate + ">";
    }

    // static
    // ------------------------------------------------------------------------
    /**
     * Return the transposition of <code>p</code>.
     *
     * @param <L> the left argument type.
     * @param <R> the right argument type.
     * @param p BinaryPredicate to transpose
     * @return TransposedPredicate
     */
    public static <L, R> TransposedPredicate<R, L> transpose(BinaryPredicate<? super L, ? super R> p) {
        return null == p ? null : new TransposedPredicate<R, L>(p);
    }

}
