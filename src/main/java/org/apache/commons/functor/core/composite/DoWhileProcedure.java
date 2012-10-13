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

import org.apache.commons.functor.Predicate;
import org.apache.commons.functor.Procedure;


/**
 * A {@link Procedure} implementation of a while loop. Given a {@link Predicate}
 * <i>c</i> and an {@link Procedure} <i>p</i>, {@link #run runs}
 * <code>do { p.run(); } while(c.test())</code>.
 * <p>
 * Note that although this class implements
 * {@link java.io.Serializable}, a given instance will
 * only be truly <code>Serializable</code> if all the
 * underlying functors are.  Attempts to serialize
 * an instance whose delegates are not all
 * <code>Serializable</code> will result in an exception.
 * </p>
 * @version $Revision: 1345136 $ $Date: 2012-06-01 09:47:06 -0300 (Fri, 01 Jun 2012) $
 */
public class DoWhileProcedure extends AbstractLoopProcedure {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = -6064417600588553892L;

    /**
     * Create a new DoWhileProcedure.
     * @param action to do
     * @param condition while true
     */
    public DoWhileProcedure(Procedure action, Predicate condition) {
        super(condition, action);
    }

    /**
     * {@inheritDoc}
     */
    public final void run() {
        do {
            getAction().run();
        } while (getCondition().test());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "DoWhileProcedure<do(" + getAction() + ") while(" + getCondition() + ")>";
    }
}
