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

import org.apache.commons.functor.Predicate;
import org.apache.commons.lang3.Validate;

/**
 * A {@link Predicate Predicate}
 * similiar to Java's "ternary"
 * or "conditional" operator (<code>&#x3F; &#x3A;</code>).
 * Given three {@link Predicate predicates}
 * <i>p</i>, <i>q</i>, <i>r</i>,
 * {@link #test tests}
 * to
 * <code>p.test() ? q.test() : r.test()</code>.
 * <p>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if all the
 * underlying functors are.  Attempts to serialize
 * an instance whose delegates are not all
 * <code>Serializable</code> will result in an exception.
 * </p>
 * @version $Revision: 1234990 $ $Date: 2012-01-23 19:18:10 -0200 (Mon, 23 Jan 2012) $
 * @author Rodney Waldhoff
 */
public final class ConditionalPredicate implements Predicate, Serializable {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = 7333505000745854098L;

    /** Base hash integer used to shift hash. */
    private static final int HASH_SHIFT = 4;
    // attributes
    // ------------------------------------------------------------------------
    /**
     * the condition to be evaluated.
     */
    private final Predicate ifPred;
    /**
     * the predicate executed if the condition is satisfied.
     */
    private final Predicate thenPred;
    /**
     * the predicate executed if the condition is not satisfied.
     */
    private final Predicate elsePred;

    // constructor
    // ------------------------------------------------------------------------
    /**
     * Create a new ConditionalPredicate.
     * @param ifPred if
     * @param thenPred then
     * @param elsePred else
     */
    public ConditionalPredicate(Predicate ifPred, Predicate thenPred, Predicate elsePred) {
        this.ifPred = Validate.notNull(ifPred, "'if' Predicate argument was null");
        this.thenPred = Validate.notNull(thenPred, "'then' Predicate argument was null");
        this.elsePred = Validate.notNull(elsePred, "'else' Predicate argument was null");
    }

    // predicate interface
    // ------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    public boolean test() {
        return ifPred.test() ? thenPred.test() : elsePred.test();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof ConditionalPredicate && equals((ConditionalPredicate) that));
    }

    /**
     * Learn whether another ConditionalPredicate is equal to this.
     * @param that ConditionalPredicate to test
     * @return boolean
     */
    public boolean equals(ConditionalPredicate that) {
        return null != that
                && (null == ifPred ? null == that.ifPred : ifPred.equals(that.ifPred))
                && (null == thenPred ? null == that.thenPred : thenPred.equals(that.thenPred))
                && (null == elsePred ? null == that.elsePred : elsePred.equals(that.elsePred));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "ConditionalPredicate".hashCode();
        if (null != ifPred) {
            hash <<= HASH_SHIFT;
            hash ^= ifPred.hashCode();
        }
        if (null != thenPred) {
            hash <<= HASH_SHIFT;
            hash ^= thenPred.hashCode();
        }
        if (null != elsePred) {
            hash <<= HASH_SHIFT;
            hash ^= elsePred.hashCode();
        }
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ConditionalPredicate<" + ifPred + "?" + thenPred + ":" + elsePred + ">";
    }

}
