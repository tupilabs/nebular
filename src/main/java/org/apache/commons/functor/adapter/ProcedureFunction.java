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
import org.apache.commons.functor.Procedure;
import org.apache.commons.lang3.Validate;

/**
 * Adapts a
 * {@link Procedure Procedure}
 * to the
 * {@link Function Function} interface
 * by always returning <code>null</code>.
 * <p/>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if the
 * underlying procedure is.  Attempts to serialize
 * an instance whose delegate is not
 * <code>Serializable</code> will result in an exception.
 *
 * @param <T> the returned value type.
 * @version $Revision: 1365377 $ $Date: 2012-07-24 21:59:23 -0300 (Tue, 24 Jul 2012) $
 */
public final class ProcedureFunction<T> implements Function<T>, Serializable {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = -655207616317672341L;
    /** The {@link Procedure Procedure} I'm wrapping. */
    private final Procedure procedure;

    /**
     * Create a new ProcedureFunction.
     * @param procedure to adapt
     */
    public ProcedureFunction(Procedure procedure) {
        this.procedure = Validate.notNull(procedure, "Procedure argument was null");
    }

    /**
     * {@inheritDoc}
     */
    public T evaluate() {
        procedure.run();
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof ProcedureFunction<?> && equals((ProcedureFunction<?>) that));
    }

    /**
     * Learn whether another ProcedureFunction is equal to this.
     * @param that ProcedureFunction to test
     * @return boolean
     */
    public boolean equals(ProcedureFunction<?> that) {
        return null != that && procedure.equals(that.procedure);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "ProcedureFunction".hashCode();
        hash ^= procedure.hashCode();
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ProcedureFunction<" + procedure + ">";
    }

    /**
     * Adapt a Procedure as a Function.
     * @param <T> the returned value type.
     * @param procedure to adapt
     * @return ProcedureFunction<T>
     */
    public static <T> ProcedureFunction<T> adapt(Procedure procedure) {
        return null == procedure ? null : new ProcedureFunction<T>(procedure);
    }

}
