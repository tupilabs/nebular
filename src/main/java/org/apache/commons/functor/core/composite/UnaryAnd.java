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

import org.apache.commons.functor.UnaryPredicate;

/**
 * {@link #test Tests} <code>true</code> iff
 * none of its children test <code>false</code>.
 * Note that by this definition, the "and" of
 * an empty collection of predicates tests <code>true</code>.
 * <p>
 * Note that although this class implements
 * {@link java.io.Serializable Serializable}, a given instance will
 * only be truly <code>Serializable</code> if all the
 * underlying functors are.  Attempts to serialize
 * an instance whose delegates are not all
 * <code>Serializable</code> will result in an exception.
 * </p>
 * @param <A> the predicate argument type.
 * @version $Revision: 1345136 $ $Date: 2012-06-01 09:47:06 -0300 (Fri, 01 Jun 2012) $
 */
public final class UnaryAnd<A> extends BaseUnaryPredicateList<A> {

    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = 8324861737107307302L;

    // constructor
    // ------------------------------------------------------------------------
    /**
     * Create a new UnaryAnd.
     */
    public UnaryAnd() {
        super();
    }

    /**
     * Create a new UnaryAnd instance.
     *
     * @param predicates the predicates to put in unary and.
     */
    public UnaryAnd(Iterable<UnaryPredicate<? super A>> predicates) {
        super(predicates);
    }

    /**
     * Create a new UnaryAnd instance.
     *
     * @param predicates the predicates to put in unary and.
     */
    public UnaryAnd(UnaryPredicate<? super A>... predicates) {
        super(predicates);
    }

    // modifiers
    // ------------------------------------------------------------------------
    /**
     * Fluently add a UnaryPredicate.
     * @param p UnaryPredicate to add
     * @return this
     */
    public UnaryAnd<A> and(UnaryPredicate<? super A> p) {
        super.addUnaryPredicate(p);
        return this;
    }

    // predicate interface
    // ------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    public boolean test(A obj) {
        for (UnaryPredicate<? super A> p : getUnaryPredicateList()) {
            if (!p.test(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof UnaryAnd<?> && equals((UnaryAnd<?>) that));
    }

    /**
     * Learn whether another UnaryAnd is equal to this.
     * @param that UnaryAnd to test
     * @return boolean
     */
    public boolean equals(UnaryAnd<?> that) {
        return getUnaryPredicateListEquals(that);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return "UnaryAnd".hashCode() ^ getUnaryPredicateListHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "UnaryAnd<" + getUnaryPredicateListToString() + ">";
    }

}
