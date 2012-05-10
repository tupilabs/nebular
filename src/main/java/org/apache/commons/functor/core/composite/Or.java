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

import org.apache.commons.functor.Predicate;

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
 * @version $Revision: 1187618 $ $Date: 2011-10-21 23:16:16 -0200 (Fri, 21 Oct 2011) $
 * @author Rodney Waldhoff
 */
public final class Or extends BasePredicateList {

    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = -1636233158061690073L;

    // constructor
    // ------------------------------------------------------------------------
    /**
     * Create a new Or.
     */
    public Or() {
        super();
    }

    /**
     * Create a new Or instance.
     *
     * @param predicates predicates have to be put in or condition.
     */
    public Or(Iterable<Predicate> predicates) {
        super(predicates);
    }

    /**
     * Create a new Or instance.
     *
     * @param predicates predicates have to be put in or condition.
     */
    public Or(Predicate... predicates) {
        super(predicates);
    }

    /**
     * Fluently add a Predicate.
     * @param p Predicate to add
     * @return this
     */
    public Or or(Predicate p) {
        super.addPredicate(p);
        return this;
    }

    // predicate interface
    // ------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    public boolean test() {
        for (Predicate p : getPredicateList()) {
            if (p.test()) {
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
        return that == this || (that instanceof Or && equals((Or) that));
    }

    /**
     * Learn whether another Or is equal to this.
     * @param that Or to test
     * @return boolean
     */
    public boolean equals(Or that) {
        return getPredicateListEquals(that);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return "Or".hashCode() ^ getPredicateListHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Or<" + getPredicateListToString() + ">";
    }

}
