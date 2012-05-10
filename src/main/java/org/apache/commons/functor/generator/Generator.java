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
package org.apache.commons.functor.generator;

import java.util.Collection;

import org.apache.commons.functor.UnaryFunction;
import org.apache.commons.functor.UnaryProcedure;

/**
 * The Generator interface defines a number of useful actions applying UnaryFunctors
 * to each in a series of argument Objects.
 *
 * @param <E> the type of elements held in this generator.
 * @version $Revision: 1160786 $ $Date: 2011-08-23 13:40:12 -0300 (Tue, 23 Aug 2011) $
 * @author Jason Horman (jason@jhorman.org)
 * @author Rodney Waldhoff
 */
public interface Generator<E> {
    /**
     * Generators must implement this method.
     * @param proc UnaryProcedure to run
     */
    void run(UnaryProcedure<? super E> proc);

    /**
     * Stop the generator. Will stop the wrapped generator if one was set.
     */
    void stop();

    /**
     * Check if the generator is stopped.
     * @return true if stopped
     */
    boolean isStopped();

    /**
     * Transforms this generator using the passed in
     * transformer. An example transformer might turn the contents of the
     * generator into a {@link Collection} of elements.
     * @param <Z> the returned value type of the input {@link UnaryFunction}.
     * @param transformer UnaryFunction to apply to this
     * @return transformation result
     */
    <Z> Z to(UnaryFunction<Generator<? extends E>, ? extends Z> transformer);

    /**
     * Same as to(new CollectionTransformer(collection)).
     * @param collection Collection to which my elements should be added
     * @return <code>collection</code>
     */
    Collection<? super E> to(Collection<? super E> collection);

    /**
     * Same as to(new CollectionTransformer()).
     * @return Collection
     */
    Collection<? super E> toCollection();
}
