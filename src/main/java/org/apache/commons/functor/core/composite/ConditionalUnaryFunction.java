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

import org.apache.commons.functor.UnaryFunction;
import org.apache.commons.functor.UnaryPredicate;
import org.apache.commons.lang3.Validate;

/**
 * A {@link UnaryFunction UnaryFunction}
 * similiar to Java's "ternary"
 * or "conditional" operator (<code>&#x3F; &#x3A;</code>).
 * Given a {@link UnaryPredicate predicate}
 * <i>p</i> and {@link UnaryFunction functions}
 * <i>f</i> and <i>g</i>, {@link #evaluate evalautes}
 * to
 * <code>p.test(x) ? f.evaluate(x) : g.evaluate(x)</code>.
 * <p>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if all the
 * underlying functors are.  Attempts to serialize
 * an instance whose delegates are not all
 * <code>Serializable</code> will result in an exception.
 * </p>
 * @param <A> the argument type.
 * @param <T> the returned value type.
 * @version $Revision: 1234990 $ $Date: 2012-01-23 19:18:10 -0200 (Mon, 23 Jan 2012) $
 * @author Rodney Waldhoff
 */
public final class ConditionalUnaryFunction<A, T> implements UnaryFunction<A, T>, Serializable {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = -8152490481969255068L;

    /** Base hash integer used to shift hash. */
    private static final int HASH_SHIFT = 4;
    // attributes
    // ------------------------------------------------------------------------
    /**
     * the condition to be evaluated.
     */
    private final UnaryPredicate<? super A> ifPred;
    /**
     * the function executed if the condition is satisfied.
     */
    private final UnaryFunction<? super A, ? extends T> thenFunc;
    /**
     * the function executed if the condition is not satisfied.
     */
    private final UnaryFunction<? super A, ? extends T> elseFunc;

    // constructor
    // ------------------------------------------------------------------------
    /**
     * Create a new ConditionalUnaryFunction.
     * @param ifPred if
     * @param thenFunc then
     * @param elseFunc else
     */
    public ConditionalUnaryFunction(UnaryPredicate<? super A> ifPred, UnaryFunction<? super A, ? extends T> thenFunc,
        UnaryFunction<? super A, ? extends T> elseFunc) {
        this.ifPred = Validate.notNull(ifPred, "UnaryPredicate argument was null");
        this.thenFunc = Validate.notNull(thenFunc, "'then' UnaryFunction argument was null");
        this.elseFunc = Validate.notNull(elseFunc, "'else' UnaryFunction argument was null");
    }

    // predicate interface
    // ------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    public T evaluate(A obj) {
        if (ifPred.test(obj)) {
            return thenFunc.evaluate(obj);
        } else {
            return elseFunc.evaluate(obj);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof ConditionalUnaryFunction<?, ?>
                                    && equals((ConditionalUnaryFunction<?, ?>) that));
    }

    /**
     * Learn whether another ConditionalUnaryFunction is equal to this.
     * @param that ConditionalUnaryFunction to test
     * @return boolean
     */
    public boolean equals(ConditionalUnaryFunction<?, ?> that) {
        return null != that
                && (null == ifPred ? null == that.ifPred : ifPred.equals(that.ifPred))
                && (null == thenFunc ? null == that.thenFunc : thenFunc.equals(that.thenFunc))
                && (null == elseFunc ? null == that.elseFunc : elseFunc.equals(that.elseFunc));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "ConditionalUnaryFunction".hashCode();
        if (null != ifPred) {
            hash <<= HASH_SHIFT;
            hash ^= ifPred.hashCode();
        }
        if (null != thenFunc) {
            hash <<= HASH_SHIFT;
            hash ^= thenFunc.hashCode();
        }
        if (null != elseFunc) {
            hash <<= HASH_SHIFT;
            hash ^= elseFunc.hashCode();
        }
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ConditionalUnaryFunction<" + ifPred + "?" + thenFunc + ":" + elseFunc + ">";
    }

}
