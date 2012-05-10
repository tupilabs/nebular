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
 * {@link UnaryProcedure UnaryProcedure}
 * to the
 * {@link BinaryProcedure BinaryProcedure} interface
 * by ignoring the first binary argument.
 * <p/>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if the
 * underlying functor is.  Attempts to serialize
 * an instance whose delegate is not
 * <code>Serializable</code> will result in an exception.
 *
 * @param <L> the left argument type.
 * @param <R> the right argument type.
 * @version $Revision: 1234990 $ $Date: 2012-01-23 19:18:10 -0200 (Mon, 23 Jan 2012) $
 * @author Rodney Waldhoff
 */
public final class IgnoreLeftProcedure<L, R> implements BinaryProcedure<L, R>, Serializable {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = 513435556181843298L;
    /** The {@link UnaryProcedure UnaryProcedure} I'm wrapping. */
    private final UnaryProcedure<? super R> procedure;

    /**
     * Create a new IgnoreLeftProcedure.
     * @param procedure to adapt
     */
    public IgnoreLeftProcedure(UnaryProcedure<? super R> procedure) {
        this.procedure = Validate.notNull(procedure, "UnaryProcedure argument was null");
    }

    /**
     * {@inheritDoc}
     */
    public void run(L left, R right) {
        procedure.run(right);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof IgnoreLeftProcedure<?, ?> && equals((IgnoreLeftProcedure<?, ?>) that));
    }

    /**
     * Learn whether another IgnoreLeftProcedure is equal to this.
     * @param that IgnoreLeftProcedure to test
     * @return boolean
     */
    public boolean equals(IgnoreLeftProcedure<?, ?> that) {
        return null != that && (null == procedure ? null == that.procedure : procedure.equals(that.procedure));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "IgnoreLeftProcedure".hashCode();
        if (null != procedure) {
            hash ^= procedure.hashCode();
        }
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "IgnoreLeftProcedure<" + procedure + ">";
    }

    /**
     * Adapt a UnaryProcedure to the BinaryProcedure interface.
     * @param <L> left type
     * @param <R> right type
     * @param procedure to adapt
     * @return IgnoreLeftProcedure<L, R>
     */
    public static <L, R> IgnoreLeftProcedure<L, R> adapt(UnaryProcedure<? super R> procedure) {
        return null == procedure ? null : new IgnoreLeftProcedure<L, R>(procedure);
    }

}
