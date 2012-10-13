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

import org.apache.commons.functor.Procedure;
import org.apache.commons.functor.UnaryProcedure;
import org.apache.commons.lang3.Validate;

/**
 * Adapts a
 * {@link UnaryProcedure UnaryProcedure}
 * to the
 * {@link Procedure Procedure} interface
 * using a constant unary argument.
 * <p/>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if the
 * underlying objects are.  Attempts to serialize
 * an instance whose delegates are not
 * <code>Serializable</code> will result in an exception.
 *
 * @version $Revision: 1365377 $ $Date: 2012-07-24 21:59:23 -0300 (Tue, 24 Jul 2012) $
 */
public final class BoundProcedure implements Procedure, Serializable {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = -6010802933400471747L;
    /** The {@link UnaryProcedure UnaryProcedure} I'm wrapping. */
    private final UnaryProcedure<Object> procedure;
    /** The parameter to pass to {@code procedure}. */
    private final Object param;

    /**
     * Create a new BoundProcedure instance.
     * @param <A> arg type
     * @param procedure the procedure to adapt
     * @param arg the constant argument to use
     */
    @SuppressWarnings("unchecked")
    public <A> BoundProcedure(UnaryProcedure<? super A> procedure, A arg) {
        this.procedure =
            (UnaryProcedure<Object>) Validate.notNull(procedure,
                "UnaryProcedure argument was null");
        this.param = arg;
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        procedure.run(param);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof BoundProcedure && equals((BoundProcedure) that));
    }

    /**
     * Learn whether a given BoundProcedure is equal to this.
     * @param that the BoundProcedure to test
     * @return boolean
     */
    public boolean equals(BoundProcedure that) {
        return null != that
                && procedure.equals(that.procedure)
                && (null == param ? null == that.param : param.equals(that.param));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "BoundProcedure".hashCode();
        hash <<= 2;
        hash ^= procedure.hashCode();
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
        return "BoundProcedure<" + procedure + "(" + param + ")>";
    }

    /**
     * Adapt the given, possibly-<code>null</code>,
     * {@link UnaryProcedure UnaryProcedure} to the
     * {@link Procedure Procedure} interface by binding
     * the specified <code>Object</code> as a constant
     * argument.
     * When the given <code>UnaryProcedure</code> is <code>null</code>,
     * returns <code>null</code>.
     *
     * @param <A> arg type
     * @param procedure the possibly-<code>null</code>
     *        {@link UnaryProcedure UnaryProcedure} to adapt
     * @param arg the object to bind as a constant argument
     * @return a <code>BoundProcedure</code> wrapping the given
     *         {@link UnaryProcedure UnaryProcedure}, or <code>null</code>
     *         if the given <code>UnaryProcedure</code> is <code>null</code>
     */
    public static <A> BoundProcedure bind(UnaryProcedure<? super A> procedure, A arg) {
        return null == procedure ? null : new BoundProcedure(procedure, arg);
    }

}
