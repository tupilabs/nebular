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
package org.apache.commons.functor.core.collection;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;

import org.apache.commons.functor.BinaryPredicate;
import org.apache.commons.functor.UnaryPredicate;
import org.apache.commons.functor.adapter.RightBoundPredicate;
import org.apache.commons.lang3.Validate;

/**
 * A {@link BinaryPredicate} that checks to see if the
 * specified object is an element of the specified
 * Collection.
 *
 * @param <L> the left argument type.
 * @param <R> the right argument type.
 * @since 1.0
 * @version $Revision: 1345136 $ $Date: 2012-06-01 09:47:06 -0300 (Fri, 01 Jun 2012) $
 */
public final class IsElementOf<L, R> implements BinaryPredicate<L, R>, Serializable {
    // static members
    //---------------------------------------------------------------

    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = -7639051806015321070L;
    /**
     * A static {@link IsElementOf} instance reference.
     */
    private static final IsElementOf<Object, Object> INSTANCE = new IsElementOf<Object, Object>();

    // constructors
    //---------------------------------------------------------------
    /**
     * Create a new IsElementOf.
     */
    public IsElementOf() {
    }

    // instance methods
    //---------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    public boolean test(L obj, R col) {
        Validate.notNull(col, "Right side argument must not be null.");
        if (col instanceof Collection<?>) {
            return testCollection(obj, (Collection<?>) col);
        }
        if (col.getClass().isArray()) {
            return testArray(obj, col);
        }
        throw new IllegalArgumentException("Expected Collection or Array, found " + col.getClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof IsElementOf<?, ?>);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return "IsElementOf".hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "IsElementOf";
    }

    /**
     * Test a collection.
     * @param obj to find
     * @param col to search
     * @return boolean
     */
    private boolean testCollection(Object obj, Collection<?> col) {
        return col.contains(obj);
    }

    /**
     * Test an array.
     * @param obj to find
     * @param array to search
     * @return boolean
     */
    private boolean testArray(Object obj, Object array) {
        for (int i = 0, m = Array.getLength(array); i < m; i++) {
            Object value = Array.get(array, i);
            if (obj == value) {
                return true;
            }
            if (obj != null && obj.equals(value)) {
                return true;
            }
        }
        return false;
    }

    // static methods
    //---------------------------------------------------------------
    /**
     * Get an IsElementOf instance.
     * @return IsElementOf
     */
    public static IsElementOf<Object, Object> instance() {
        return INSTANCE;
    }

    /**
     * Get an IsElementOf(collection|array) UnaryPredicate.
     *
     * @param <A> the UnaryPredicate argument generic type
     * @param obj collection/array to search
     * @return UnaryPredicate
     */
    public static <A> UnaryPredicate<A> instance(Object obj) {
        if (null == obj) {
            throw new NullPointerException("Argument must not be null");
        } else if (obj instanceof Collection<?>) {
            return new RightBoundPredicate<A>(new IsElementOf<A, Object>(), obj);
        } else if (obj.getClass().isArray()) {
            return new RightBoundPredicate<A>(new IsElementOf<A, Object>(), obj);
        } else {
            throw new IllegalArgumentException("Expected Collection or Array, found " + obj.getClass());
        }
    }

}
