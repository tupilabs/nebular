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

import org.apache.commons.functor.BinaryPredicate;
import org.apache.commons.functor.Predicate;
import org.apache.commons.functor.UnaryPredicate;

/**
 * A predicate that returns <code>true</code>
 * the first <i>n</i> times it is invoked, and
 * <code>false</code> thereafter.
 *
 * @since 1.0
 * @version $Revision: 1188891 $ $Date: 2011-10-25 18:27:28 -0200 (Tue, 25 Oct 2011) $
 * @author Jason Horman (jason@jhorman.org)
 * @author Rodney Waldhoff
 */

public final class Limit implements Predicate, UnaryPredicate<Object>, BinaryPredicate<Object, Object> {
    // instance variables
    //---------------------------------------------------------------
    /**
     * The max number of times the predicate can be invoked.
     */
    private final int max;
    /**
     * The current number of times the predicate has been invoked.
     */
    private int current;

    /**
     * Create a new Limit.
     * @param count limit
     */
    public Limit(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Argument must be a non-negative integer.");
        }
        this.max = count;
    }

    /**
     * {@inheritDoc}
     */
    public synchronized boolean test() {
        // stop incrementing when we've hit max, so we don't loop around
        if (current < max) {
            current++;
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean test(Object obj) {
        return test();
    }

    /**
     * {@inheritDoc}
     */
    public boolean test(Object a, Object b) {
        return test();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Limit<" + max + ">";
    }

    //default == equals/hashCode due to statefulness
}
