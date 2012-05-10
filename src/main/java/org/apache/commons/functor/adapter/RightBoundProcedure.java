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

import org.apache.commons.functor.BinaryProcedure;
import org.apache.commons.functor.UnaryProcedure;
import org.apache.commons.lang3.Validate;

/**
 * Adapts a
 * {@link BinaryProcedure BinaryProcedure}
 * to the
 * {@link UnaryProcedure UnaryProcedure} interface
 * using a constant left-side argument.
 * <p/>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if the
 * underlying objects are.  Attempts to serialize
 * an instance whose delegates are not
 * <code>Serializable</code> will result in an exception.
 *
 * @param <A> the argument type.
 * @version $Revision: 1234990 $ $Date: 2012-01-23 19:18:10 -0200 (Mon, 23 Jan 2012) $
 * @author Rodney Waldhoff
 */
public final class RightBoundProcedure<A> implements UnaryProcedure<A>, Serializable {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = 3267188080481758226L;
    /** The {@link BinaryProcedure BinaryProcedure} I'm wrapping. */
    private final BinaryProcedure<? super A, Object> procedure;
    /** The parameter to pass to {@code procedure}. */
    private final Object param;

    /**
     * Create a new RightBoundProcedure.
     * @param <R> bound arg type
     * @param procedure the procedure to adapt
     * @param arg the constant argument to use
     */
    @SuppressWarnings("unchecked")
    public <R> RightBoundProcedure(BinaryProcedure<? super A, ? super R> procedure, R arg) {
        this.procedure =
            (BinaryProcedure<? super A, Object>) Validate.notNull(procedure,
                "BinaryProcedure argument was null");
        this.param = arg;
    }

    /**
     * {@inheritDoc}
     */
    public void run(A obj) {
        procedure.run(obj, param);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof RightBoundProcedure<?> && equals((RightBoundProcedure<?>) that));
    }

    /**
     * Learn whether another RightBoundProcedure is equal to this.
     * @param that RightBoundProcedure to test
     * @return boolean
     */
    public boolean equals(RightBoundProcedure<?> that) {
        return null != that
                && (null == procedure ? null == that.procedure : procedure.equals(that.procedure))
                && (null == param ? null == that.param : param.equals(that.param));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "RightBoundProcedure".hashCode();
        if (null != procedure) {
            hash <<= 2;
            hash ^= procedure.hashCode();
        }
        if (null != param) {
            hash <<= 2;
            hash ^= param.hashCode();
        }
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RightBoundProcedure<" + procedure + "(?," + param + ")>";
    }

    /**
     * Get a UnaryProcedure from <code>procedure</code>.
     * @param <L> the left argument type.
     * @param <R> the right argument type.
     * @param procedure to adapt
     * @param arg right side argument
     * @return RightBoundProcedure
     */
    public static <L, R> RightBoundProcedure<L> bind(BinaryProcedure<? super L, ? super R> procedure, R arg) {
        return null == procedure ? null : new RightBoundProcedure<L>(procedure, arg);
    }

}
