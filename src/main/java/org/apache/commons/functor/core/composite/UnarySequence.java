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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.functor.UnaryProcedure;

/**
 * A {@link UnaryProcedure UnaryProcedure}
 * that {@link UnaryProcedure#run runs} an ordered
 * sequence of {@link UnaryProcedure UnaryProcedures}.
 * When the sequence is empty, this procedure is does
 * nothing.
 * <p>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if all the
 * underlying functors are.  Attempts to serialize
 * an instance whose delegates are not all
 * <code>Serializable</code> will result in an exception.
 * </p>
 * @param <A> the argument type.
 * @version $Revision: 1187618 $ $Date: 2011-10-21 23:16:16 -0200 (Fri, 21 Oct 2011) $
 * @author Rodney Waldhoff
 */
public class UnarySequence<A> implements UnaryProcedure<A>, Serializable {

    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = 9194268249717820246L;
    // attributes
    // ------------------------------------------------------------------------
    /**
     * The data structure to store the procedure sequence.
     */
    private final List<UnaryProcedure<? super A>> list = new ArrayList<UnaryProcedure<? super A>>();

    // constructor
    // ------------------------------------------------------------------------
    /**
     * Create a new UnarySequence.
     */
    public UnarySequence() {
        super();
    }

    /**
     * Create a new UnarySequence instance.
     *
     * @param procedures to run sequentially
     */
    public UnarySequence(UnaryProcedure<? super A>... procedures) {
        this();
        if (procedures != null) {
            for (UnaryProcedure<? super A> p : procedures) {
                then(p);
            }
        }
    }

    /**
     * Create a new UnarySequence instance.
     *
     * @param procedures to run sequentially
     */
    public UnarySequence(Iterable<UnaryProcedure<? super A>> procedures) {
        this();
        if (procedures != null) {
            for (UnaryProcedure<? super A> p : procedures) {
                then(p);
            }
        }
    }

    // modifiers
    // ------------------------------------------------------------------------
    /**
     * Fluently add a UnaryProcedure to the sequence.
     * @param p UnaryProcedure to add
     * @return this
     */
    public UnarySequence<A> then(UnaryProcedure<? super A> p) {
        list.add(p);
        return this;
    }

    // predicate interface
    // ------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    public void run(A obj) {
        for (Iterator<UnaryProcedure<? super A>> iter = list.iterator(); iter.hasNext();) {
            iter.next().run(obj);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that == this || (that instanceof UnarySequence<?> && equals((UnarySequence<?>) that));
    }

    /**
     * Learn whether another UnarySequence is equal to this.
     * @param that UnarySequence to test
     * @return boolean
     */
    public boolean equals(UnarySequence<?> that) {
        // by construction, list is never null
        return null != that && list.equals(that.list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // by construction, list is never null
        return "UnarySequence".hashCode() ^ list.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "UnarySequence<" + list + ">";
    }

}
