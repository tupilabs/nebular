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

import org.apache.commons.functor.BinaryFunction;
import org.apache.commons.functor.UnaryFunction;
import org.apache.commons.functor.adapter.RightBoundFunction;
import org.apache.commons.lang3.Validate;

/**
 * Adapts a {@link Comparator Comparator} to the
 * {@link BinaryFunction} interface.
 *
 * @param <T> the binary function arguments and return types.
 * @version $Revision: 1364676 $ $Date: 2012-07-23 12:21:25 -0300 (Mon, 23 Jul 2012) $
 */
public final class Max<T> implements BinaryFunction<T, T, T>, Serializable {

    /**
     * Basic Max instance.
     */
    public static final Max<Comparable<?>> INSTANCE = Max.<Comparable<?>>instance();

    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = 6514424464263828685L;

    /**
     * The wrapped comparator.
     */
    private final Comparator<T> comparator;

    /**
     * Create a new Max.
     */
    @SuppressWarnings("unchecked")
    public Max() {
        this((Comparator<T>) ComparableComparator.INSTANCE);
    }

    /**
     * Create a new Max.
     * @param comparator Comparator to use
     */
    public Max(Comparator<T> comparator) {
        this.comparator = Validate.notNull(comparator, "Comparator argument must not be null");
    }

    /**
     * {@inheritDoc}
     */
    public T evaluate(T left, T right) {
        return (comparator.compare(left, right) >= 0) ? left : right;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof Max<?> && equals((Max<?>) that));
    }

    /**
     * Learn whether another Max is equal to this.
     * @param that Max to test
     * @return boolean
     */
    public boolean equals(Max<?> that) {
        return null != that && comparator.equals(that.comparator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return "Max".hashCode() ^ comparator.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Max<" + comparator + ">";
    }

    /**
     * Get a Max instance.
     *
     * @param <T> the binary function arguments and return types.
     * @return Max
     */
    public static <T extends Comparable<?>> Max<T> instance() {
        return new Max<T>();
    }

    /**
     * Get a Max UnaryFunction.
     *
     * @param <T> the binary function arguments and return types.
     * @param right the right side argument of the Max function
     * @return UnaryFunction<T, T>
     */
    public static <T extends Comparable<?>> UnaryFunction<T, T> instance(T right) {
        return RightBoundFunction.bind(new Max<T>(), right);
    }

}
