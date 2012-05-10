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
 * at least one of its children test <code>true</code>.
 * Note that by this definition, the "or" of
 * an empty collection of predicates tests <code>false</code>.
 * <p>
 * Note that although this class implements
 * {@link java.io.Serializable Serializable}, a given instance will
 * only be truly <code>Serializable</code> if all the
 * underlying functors are.  Attempts to serialize
 * an instance whose delegates are not all
 * <code>Serializable</code> will result in an exception.
 * </p>
 * @param <A> the predicate argument type.
 * @version $Revision: 1187618 $ $Date: 2011-10-21 23:16:16 -0200 (Fri, 21 Oct 2011) $
 * @author Rodney Waldhoff
 */
public final class UnaryOr<A> extends BaseUnaryPredicateList<A> {

    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = -4072936447932983645L;

    // constructor
    // ------------------------------------------------------------------------
    /**
     * Create a new UnaryOr.
     */
    public UnaryOr() {
        super();
    }

    /**
     * Create a new UnaryOr instance.
     *
     * @param predicates the predicates to put in unary or.
     */
    public UnaryOr(Iterable<UnaryPredicate<? super A>> predicates) {
        super(predicates);
    }

    /**
     * Create a new UnaryOr instance.
     *
     * @param predicates the predicates to put in unary or.
     */
    public UnaryOr(UnaryPredicate<? super A>... predicates) {
        super(predicates);
    }

    // modifiers
    // ------------------------------------------------------------------------
    /**
     * Fluently add a Predicate.
     * @param p Predicate to add
     * @return this
     */
    public UnaryOr<A> or(UnaryPredicate<? super A> p) {
        super.addUnaryPredicate(p);
        return this;
    }

    // predicate interface
    // ------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    public boolean test(A a) {
        for (UnaryPredicate<? super A> p : getUnaryPredicateList()) {
            if (p.test(a)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof UnaryOr<?> && equals((UnaryOr<?>) that));
    }

    /**
     * Learn whether another UnaryOr is equal to this.
     * @param that UnaryOr to test
     * @return boolean
     */
    public boolean equals(UnaryOr<?> that) {
        return getUnaryPredicateListEquals(that);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return "UnaryOr".hashCode() ^ getUnaryPredicateListHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "UnaryOr<" + getUnaryPredicateListToString() + ">";
    }

}
