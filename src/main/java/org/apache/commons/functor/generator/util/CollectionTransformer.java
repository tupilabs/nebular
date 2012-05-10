/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.functor.generator.util;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.functor.UnaryFunction;
import org.apache.commons.functor.UnaryProcedure;
import org.apache.commons.functor.generator.Generator;

/**
 * Transforms a generator into a collection. If a collection is not passed into
 * the constructor an ArrayList will be returned from the transform method.
 *
 * @param <E> the type of elements held in the adapted collection.
 * @since 1.0
 * @version $Revision: 1160793 $ $Date: 2011-08-23 13:48:32 -0300 (Tue, 23 Aug 2011) $
 * @author Jason Horman (jason@jhorman.org)
 */
public class CollectionTransformer<E> implements UnaryFunction<Generator<? extends E>, Collection<? super E>> {
    /*
     * TODO revisit this class... it could stand a more-descriptive name.  Also, it's a little
     * hard to say whether, for an instance constructed without a specific target collection,
     * #evaluate() should return a new ArrayList for each call, or continue adding to
     * a single ArrayList instance (the current behavior).
     * Perhaps this is more a documentation issue than anything.
     */

    // instance methods
    //---------------------------------------------------
    /**
     * The adapted collection has to be filled.
     */
    private final Collection<? super E> toFill;

    // constructors
    //---------------------------------------------------
    /**
     * Create a new CollectionTransformer.
     */
    public CollectionTransformer() {
        this(null);
    }

    /**
     * Create a new CollectionTransformer.
     * @param toFill Collection to fill
     */
    public CollectionTransformer(Collection<? super E> toFill) {
        Collection<? super E> coll;
        if (toFill == null) {
            coll = new ArrayList<E>();
        } else {
            coll = toFill;
        }
        this.toFill = coll;
    }

    // instance methods
    //---------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public Collection<E> evaluate(Generator<? extends E> generator) {
        generator.run(new UnaryProcedure<E>() {
            public void run(E obj) {
                toFill.add(obj);
            }
        });
        return (Collection<E>) toFill;
    }
}
