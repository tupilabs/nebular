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
 * {@link Procedure Procedure}
 * to the
 * {@link UnaryProcedure UnaryProcedure} interface
 * by ignoring the arguments.
 * <p/>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if the
 * underlying functor is.  Attempts to serialize
 * an instance whose delegate is not
 * <code>Serializable</code> will result in an exception.
 *
 * @param <A> the argument type.
 * @version $Revision: 1365377 $ $Date: 2012-07-24 21:59:23 -0300 (Tue, 24 Jul 2012) $
 */
public final class ProcedureUnaryProcedure<A> implements UnaryProcedure<A>, Serializable {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = 501530698794315412L;
    /** The {@link Procedure Procedure} I'm wrapping. */
    private final Procedure procedure;

    /**
     * Create a new ProcedureUnaryProcedure.
     * @param procedure to adapt
     */
    public ProcedureUnaryProcedure(Procedure procedure) {
        this.procedure = Validate.notNull(procedure, "Procedure argument was null");
    }

    /**
     * {@inheritDoc}
     */
    public void run(A obj) {
        procedure.run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof ProcedureUnaryProcedure<?>
                                    && equals((ProcedureUnaryProcedure<?>) that));
    }

    /**
     * Learn whether another ProcedureUnaryProcedure is equal to this.
     * @param that ProcedureUnaryProcedure to test
     * @return boolean
     */
    public boolean equals(ProcedureUnaryProcedure<?> that) {
        return null != that && procedure.equals(that.procedure);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "ProcedureUnaryProcedure".hashCode();
        hash ^= procedure.hashCode();
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ProcedureUnaryProcedure<" + procedure + ">";
    }

    /**
     * Adapt a Procedure to the UnaryProcedure interface.
     * @param <A> the argument type.
     * @param procedure to adapt
     * @return ProcedureUnaryProcedure<A>
     */
    public static <A> ProcedureUnaryProcedure<A> adapt(Procedure procedure) {
        return null == procedure ? null : new ProcedureUnaryProcedure<A>(procedure);
    }

}
