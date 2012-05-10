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
package org.apache.commons.functor.core;

import java.io.Serializable;

import org.apache.commons.functor.BinaryPredicate;
import org.apache.commons.functor.UnaryPredicate;
import org.apache.commons.functor.adapter.RightBoundPredicate;

/**
 * {@link #test Tests} the reference (==) equality of its arguments.
 *
 * @param <L> the left argument type.
 * @param <R> the right argument type.
 * @version $Revision: 1187618 $ $Date: 2011-10-21 23:16:16 -0200 (Fri, 21 Oct 2011) $
 * @author Matt Benson
 */
public final class IsSame<L, R> implements BinaryPredicate<L, R>, Serializable {
    // static attributes
    // ------------------------------------------------------------------------
    /**
     * Basic IsSame<Object, Object> instance.
     */
    public static final IsSame<Object, Object> INSTANCE = IsSame.<Object, Object>instance();
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = 7024585699909734072L;

    // constructor
    // ------------------------------------------------------------------------
    /**
     * Create a new IsSame.
     */
    public IsSame() {
    }

    // predicate interface
    // ------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    public boolean test(L left, R right) {
        return left == right;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that instanceof IsSame<?, ?>;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return "IsSame".hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "IsSame";
    }

    // static methods
    // ------------------------------------------------------------------------
    /**
     * Get an IsSame instance.
     * @param <L> the left argument type.
     * @param <R> the right argument type.
     * @return IsSame
     */
    public static <L, R> IsSame<L, R> instance() {
        return new IsSame<L, R>();
    }

    /**
     * Get an IsSame UnaryPredicate.
     * @param <L> the left argument type.
     * @param <R> the right argument type.
     * @param object bound comparison object
     * @return UnaryPredicate<L>
     */
    public static <L, R> UnaryPredicate<L> as(R object) {
        return new RightBoundPredicate<L>(new IsSame<L, R>(), object);
    }
}
