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

import org.apache.commons.functor.Function;
import org.apache.commons.functor.Predicate;
import org.apache.commons.lang3.Validate;

/**
 * Adapts a
 * {@link Predicate Predicate}
 * to the
 * {@link Function Function} interface.
 * <p/>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if the
 * underlying predicate is.  Attempts to serialize
 * an instance whose delegate is not
 * <code>Serializable</code> will result in an exception.
 *
 * @version $Revision: 1234990 $ $Date: 2012-01-23 19:18:10 -0200 (Mon, 23 Jan 2012) $
 * @author Rodney Waldhoff
 */
public final class PredicateFunction implements Function<Boolean>, Serializable {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = -8858981355549412629L;
    /** The {@link Predicate Predicate} I'm wrapping. */
    private final Predicate predicate;

    /**
     * Create a new PredicateFunction.
     * @param predicate to adapt
     */
    public PredicateFunction(Predicate predicate) {
        this.predicate = Validate.notNull(predicate, "Predicate argument was null");
    }

    /**
     * {@inheritDoc}
     * Returns <code>Boolean.TRUE</code> (<code>Boolean.FALSE</code>)
     * when the {@link Predicate#test test} method of my underlying
     * predicate returns <code>true</code> (<code>false</code>).
     *
     * @return a non-<code>null</code> <code>Boolean</code> instance
     */
    public Boolean evaluate() {
        return Boolean.valueOf(predicate.test());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof PredicateFunction && equals((PredicateFunction) that));
    }

    /**
     * Learn whether another PredicateFunction is equal to this.
     * @param that PredicateFunction to test
     * @return boolean
     */
    public boolean equals(PredicateFunction that) {
        return null != that && (null == predicate ? null == that.predicate : predicate.equals(that.predicate));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "PredicateFunction".hashCode();
        if (null != predicate) {
            hash ^= predicate.hashCode();
        }
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "PredicateFunction<" + predicate + ">";
    }

    /**
     * Adapt a Predicate to the Function interface.
     * @param predicate to adapt
     * @return PredicateFunction
     */
    public static PredicateFunction adapt(Predicate predicate) {
        return null == predicate ? null : new PredicateFunction(predicate);
    }

}
