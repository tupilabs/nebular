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
package org.apache.commons.functor.core.algorithm;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.commons.functor.BinaryProcedure;
import org.apache.commons.functor.UnaryPredicate;

/**
 * Remove elements from left Iterator that match right UnaryPredicate.
 *
 * @param <T> the procedure argument type.
 * @version $Revision: 1187618 $ $Date: 2011-10-21 23:16:16 -0200 (Fri, 21 Oct 2011) $
 */
public final class RemoveMatching<T>
    implements BinaryProcedure<Iterator<? extends T>, UnaryPredicate<? super T>>, Serializable {
    /**
     * serialVersionUID declaration.
     */
    private static final long serialVersionUID = -8376577687898040683L;
    /**
     * A static {@link RemoveMatching} instance reference.
     */
    private static final RemoveMatching<Object> INSTANCE = new RemoveMatching<Object>();

    /**
     * {@inheritDoc}
     * @param left {@link Iterator}
     * @param right {@link UnaryPredicate}
     */
    public void run(Iterator<? extends T> left, UnaryPredicate<? super T> right) {
        while (left.hasNext()) {
            if (right.test(left.next())) {
                left.remove();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        return obj == this || obj != null && obj.getClass().equals(getClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return System.identityHashCode(INSTANCE);
    }

    /**
     * Get a static {@link RemoveMatching} instance.
     * @return {@link RemoveMatching}
     */
    public static RemoveMatching<Object> instance() {
        return INSTANCE;
    }

}
