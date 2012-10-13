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
 * {@link UnaryPredicate UnaryPredicate}
 * to the
 * {@link BinaryPredicate BinaryPredicate} interface
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
 * @version $Revision: 1365377 $ $Date: 2012-07-24 21:59:23 -0300 (Tue, 24 Jul 2012) $
 */
public final class IgnoreRightPredicate<L, R> implements BinaryPredicate<L, R>, Serializable {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = -4236624667788627722L;
    /** The {@link UnaryPredicate UnaryPredicate} I'm wrapping. */
    private final UnaryPredicate<? super L> predicate;

    /**
     * Create a new IgnoreRightPredicate.
     * @param predicate left
     */
    public IgnoreRightPredicate(UnaryPredicate<? super L> predicate) {
        this.predicate = Validate.notNull(predicate, "UnaryPredicate argument was null");
    }

    /**
     * {@inheritDoc}
     */
    public boolean test(L left, R right) {
        return predicate.test(left);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof IgnoreRightPredicate<?, ?>
                                    && equals((IgnoreRightPredicate<?, ?>) that));
    }

    /**
     * Learn whether a given IgnoreRightPredicate is equal to this.
     * @param that IgnoreRightPredicate to test
     * @return boolean
     */
    public boolean equals(IgnoreRightPredicate<?, ?> that) {
        return null != that &&  predicate.equals(that.predicate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "IgnoreRightPredicate".hashCode();
        hash ^= predicate.hashCode();
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "IgnoreRightPredicate<" + predicate + ">";
    }

    /**
     * Adapt a UnaryPredicate as an IgnoreRightPredicate.
     * @param <L> left type
     * @param <R> right type
     * @param predicate to adapt
     * @return IgnoreRightPredicate<L, R>
     */
    public static <L, R> IgnoreRightPredicate<L, R> adapt(UnaryPredicate<? super L> predicate) {
        return null == predicate ? null : new IgnoreRightPredicate<L, R>(predicate);
    }

}
