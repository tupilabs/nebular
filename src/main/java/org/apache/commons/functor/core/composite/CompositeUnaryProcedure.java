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
import org.apache.commons.functor.UnaryProcedure;
import org.apache.commons.functor.adapter.UnaryProcedureUnaryFunction;
import org.apache.commons.lang3.Validate;

/**
 * A {@link UnaryProcedure UnaryProcedure}
 * representing the composition of
 * {@link UnaryFunction UnaryFunctions},
 * "chaining" the output of one to the input
 * of another.  For example,
 * <pre>new CompositeUnaryProcedure(p).of(f)</pre>
 * {@link #run runs} to
 * <code>p.run(f.evaluate(obj))</code>, and
 * <pre>new CompositeUnaryProcedure(p).of(f).of(g)</pre>
 * {@link #run runs}
 * <code>p.run(f.evaluate(g.evaluate(obj)))</code>.
 * <p>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if all the
 * underlying functors are.  Attempts to serialize
 * an instance whose delegates are not all
 * <code>Serializable</code> will result in an exception.
 * </p>
 * @param <A> the procedure argument type.
 * @version $Revision: 1345136 $ $Date: 2012-06-01 09:47:06 -0300 (Fri, 01 Jun 2012) $
 */
public final class CompositeUnaryProcedure<A> implements UnaryProcedure<A>, Serializable {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = -7496282561355676509L;
    // attributes
    // ------------------------------------------------------------------------
    /**
     * The adapted composite procedure.
     */
    private final CompositeUnaryFunction<? super A, Object> function;

    // constructor
    // ------------------------------------------------------------------------
    /**
     * Create a new CompositeUnaryProcedure.
     * @param procedure final UnaryProcedure to run
     */
    public CompositeUnaryProcedure(UnaryProcedure<? super A> procedure) {
        this.function =
            new CompositeUnaryFunction<A, Object>(
                new UnaryProcedureUnaryFunction<A, Object>(Validate.notNull(
                    procedure, "procedure must not be null")));
    }

    /**
     * Create a new CompositeUnaryProcedure.
     * @param function final CompositeUnaryFunction to run
     */
    private CompositeUnaryProcedure(CompositeUnaryFunction<? super A, Object> function) {
        this.function = function;
    }

    // modifiers
    // ------------------------------------------------------------------------
    /**
     * Fluently obtain a CompositeUnaryProcedure that runs our procedure against the result of the preceding function.
     * @param <T> the input function left argument and output procedure argument type
     * @param preceding UnaryFunction
     * @return CompositeUnaryProcedure<P>
     */
    public <T> CompositeUnaryProcedure<T> of(UnaryFunction<? super T, ? extends A> preceding) {
        return new CompositeUnaryProcedure<T>(function.of(preceding));
    }

    // predicate interface
    // ------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    public void run(A obj) {
        function.evaluate(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof CompositeUnaryProcedure<?>
                                    && equals((CompositeUnaryProcedure<?>) that));
    }

    /**
     * Learn whether another CompositeUnaryProcedure is equal to this.
     * @param that CompositeUnaryProcedure to test
     * @return boolean
     */
    public boolean equals(CompositeUnaryProcedure<?> that) {
        return null != that && function.equals(that.function);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "CompositeUnaryProcedure".hashCode();
        hash <<= 2;
        hash ^= function.hashCode();
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "CompositeUnaryProcedure<" + function + ">";
    }

}
