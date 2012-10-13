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
package org.apache.commons.functor.core.comparator;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.commons.functor.BinaryPredicate;
import org.apache.commons.functor.UnaryPredicate;
import org.apache.commons.functor.adapter.RightBoundPredicate;
import org.apache.commons.lang3.Validate;

/**
 * A {@link BinaryPredicate BinaryPredicate} that {@link #test tests}
 * <code>true</code> iff the left argument is less than or equal to the
 * right argument under the specified {@link Comparator}.
 * When no (or a <code>null</code> <code>Comparator</code> is specified,
 * a {@link Comparable Comparable} <code>Comparator</code> is used.
 *
 * @param <T> the binary predicate input types
 * @version $Revision: 1365328 $ $Date: 2012-07-24 19:19:23 -0300 (Tue, 24 Jul 2012) $
 */
public final class IsLessThanOrEqual<T> implements BinaryPredicate<T, T>, Serializable {

    /**
     * Basic IsLessThanOrEqual instance.
     */
    public static final IsLessThanOrEqual<Comparable<?>> INSTANCE = IsLessThanOrEqual.<Comparable<?>>instance();

    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = -5791636848361450563L;

    /**
     * The wrapped comparator.
     */
    private final Comparator<? super T> comparator;

    /**
     * Construct a <code>IsLessThanOrEqual</code> {@link BinaryPredicate predicate}
     * for {@link Comparable Comparable}s.
     */
    @SuppressWarnings("unchecked")
    public IsLessThanOrEqual() {
        this((Comparator<? super T>) ComparableComparator.INSTANCE);
    }

    /**
     * Construct a <code>IsLessThanOrEqual</code> {@link BinaryPredicate predicate}
     * for the given {@link Comparator Comparator}.
     *
     * @param comparator the {@link Comparator Comparator}, when <code>null</code>,
     *        a <code>Comparator</code> for {@link Comparable Comparable}s will
     *        be used.
     */
    public IsLessThanOrEqual(Comparator<? super T> comparator) {
        this.comparator = Validate.notNull(comparator, "Comparator argument must not be null");
    }

    /**
     * {@inheritDoc}
     * Return <code>true</code> iff the <i>left</i> parameter is
     * less than or equal to the <i>right</i> parameter under my current
     * {@link Comparator Comparator}.
     */
    public boolean test(T left, T right) {
        return comparator.compare(left, right) <= 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof IsLessThanOrEqual<?> && equals((IsLessThanOrEqual<?>) that));
    }

    /**
     * Learn whether another IsLessThanOrEqual is equal to this.
     * @param that the IsLessThanOrEqual to test.
     * @return boolean
     */
    public boolean equals(IsLessThanOrEqual<?> that) {
        if (null != that) {
            return comparator.equals(that.comparator);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "IsLessThanOrEqual".hashCode();
        // by construction, comparator is never null
        hash ^= comparator.hashCode();
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "IsLessThanOrEqual<" + comparator + ">";
    }

    /**
     * Get a typed IsLessThanOrEqual instance.
     *
     * @param <T> the binary predicate input types
     * @return IsLessThanOrEqual<T>
     */
    public static <T extends Comparable<?>> IsLessThanOrEqual<T> instance() {
        return new IsLessThanOrEqual<T>();
    }

    /**
     * Get an IsLessThanOrEqual UnaryPredicate.
     *
     * @param <T> the binary predicate input types
     * @param right the right side object of the comparison.
     * @return UnaryPredicate
     */
    public static <T extends Comparable<?>> UnaryPredicate<T> instance(T right) {
        return RightBoundPredicate.bind(new IsLessThanOrEqual<T>(), right);
    }

}
