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
import org.apache.commons.functor.UnaryFunction;
import org.apache.commons.lang3.Validate;

/**
 * A {@link BinaryPredicate BinaryPredicate} composed of
 * one binary predicate, <i>p</i>, and two unary
 * functions, <i>f</i> and <i>g</i>,
 * evaluating the ordered parameters <i>x</i>, <i>y</i>
 * to <code><i>p</i>(<i>f</i>(<i>x</i>),<i>g</i>(<i>y</i>))</code>.
 * <p>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if all the
 * underlying functors are.  Attempts to serialize
 * an instance whose delegates are not all
 * <code>Serializable</code> will result in an exception.
 * </p>
 * @param <L> the left argument type.
 * @param <R> the right argument type.
 * @version $Revision: 1345136 $ $Date: 2012-06-01 09:47:06 -0300 (Fri, 01 Jun 2012) $
 */
public class UnaryCompositeBinaryPredicate<L, R> implements BinaryPredicate<L, R>, Serializable {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = 3841123079006929493L;

    /** Base hash integer used to shift hash. */
    private static final int HASH_SHIFT = 4;

    /**
     * Internal helper.
     *
     * @param <G> right function type.
     * @param <H> right function type.
     * @param <L> left function type.
     * @param <R> left function type.
     */
    private static class Helper<G, H, L, R> implements BinaryPredicate<L, R>, Serializable {
        /**
         * serialVersionUID declaration.
         */
        private static final long serialVersionUID = -3463108273324567825L;
        /**
         * BinaryPredicate to test <i>output(</i><code>f</code><i>), output(</i><code>g</code><i>)</i>.
         */
        private BinaryPredicate<? super G, ? super H> f;
        /**
         * left UnaryFunction.
         */
        private UnaryFunction<? super L, ? extends G> g;
        /**
         * right UnaryFunction.
         */
        private UnaryFunction<? super R, ? extends H> h;

        /**
         * Create a new Helper.
         * @param f BinaryPredicate to test <i>output(</i><code>f</code><i>), output(</i><code>g</code><i>)</i>
         * @param g left UnaryFunction
         * @param h right UnaryFunction
         */
        public Helper(BinaryPredicate<? super G, ? super H> f, UnaryFunction<? super L, ? extends G> g,
                UnaryFunction<? super R, ? extends H> h) {
            this.f = f;
            this.g = g;
            this.h = h;
        }

        /**
         * {@inheritDoc}
         */
        public boolean test(L left, R right) {
            return f.test(g.evaluate(left), h.evaluate(right));
        }
    }

    // attributes
    // ------------------------------------------------------------------------
    /**
     * The adapted helper.
     */
    private final Helper<?, ?, L, R> helper;

    // constructor
    // ------------------------------------------------------------------------
    /**
     * Create a new UnaryCompositeBinaryPredicate.
     *
     * @param <G> right function type.
     * @param <H> right function type.
     * @param f BinaryPredicate to test <i>output(</i><code>f</code><i>), output(</i><code>g</code><i>)</i>
     * @param g left UnaryFunction
     * @param h right UnaryFunction
     */
    public <G, H> UnaryCompositeBinaryPredicate(final BinaryPredicate<? super G, ? super H> f,
            final UnaryFunction<? super L, ? extends G> g, final UnaryFunction<? super R, ? extends H> h) {
        helper = new Helper<G, H, L, R>(
                Validate.notNull(f, "BinaryPredicate must not be null"),
                Validate.notNull(g, "left UnaryFunction must not be null"),
                Validate.notNull(h, "right UnaryFunction must not be null")
        );
    }

    // function interface
    // ------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    public boolean test(L left, R right) {
        return helper.test(left, right);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof UnaryCompositeBinaryPredicate<?, ?>
                                    && equals((UnaryCompositeBinaryPredicate<?, ?>) that));
    }

    /**
     * Learn whether another UnaryCompositeBinaryPredicate is equal to this.
     * @param that UnaryCompositeBinaryPredicate to test
     * @return boolean
     */
    public boolean equals(UnaryCompositeBinaryPredicate<?, ?> that) {
        return null != that && helper.f.equals(that.helper.f) && helper.g.equals(that.helper.g)
                && helper.h.equals(that.helper.h);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "UnaryCompositeBinaryPredicate".hashCode();
        hash <<= HASH_SHIFT;
        hash ^= helper.f.hashCode();
        hash <<= HASH_SHIFT;
        hash ^= helper.g.hashCode();
        hash <<= HASH_SHIFT;
        hash ^= helper.h.hashCode();
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "UnaryCompositeBinaryPredicate<" + helper.f + ";" + helper.g + ";" + helper.h + ">";
    }

}
