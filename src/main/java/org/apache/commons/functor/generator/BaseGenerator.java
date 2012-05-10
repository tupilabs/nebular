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
import org.apache.commons.functor.generator.util.CollectionTransformer;

/**
 * Base class for generators. Adds support for all of the Algorithms to
 * each subclass.
 *
 * @param <E> the type of elements held in this generator.
 * @since 1.0
 * @version $Revision: 1188397 $ $Date: 2011-10-24 19:40:54 -0200 (Mon, 24 Oct 2011) $
 * @author  Jason Horman (jason@jhorman.org)
 */
public abstract class BaseGenerator<E> implements Generator<E> {

    /** A generator can wrap another generator. */
    private final Generator<?> wrappedGenerator;

    /** Set to true when the generator is {@link #stop stopped}. */
    private boolean stopped = false;

    /** Create a new generator. */
    public BaseGenerator() {
        this(null);
    }

    /**
     * A generator can wrap another generator. When wrapping generators you
     * should use probably this constructor since doing so will cause the
     * {@link #stop} method to stop the wrapped generator as well.
     * @param generator Generator to wrap
     */
    public BaseGenerator(Generator<?> generator) {
        this.wrappedGenerator = generator;
    }

    /**
     * Get the generator that is being wrapped.
     * @return Generator
     */
    protected Generator<?> getWrappedGenerator() {
        return wrappedGenerator;
    }

    /**
     * {@inheritDoc}
     * Stop the generator. Will stop the wrapped generator if one was set.
     */
    public void stop() {
        if (wrappedGenerator != null) {
            wrappedGenerator.stop();
        }
        stopped = true;
    }

    /**
     * {@inheritDoc}
     * Check if the generator is stopped.
     */
    public boolean isStopped() {
        return stopped;
    }

    /**
     * {@inheritDoc}
     * Transforms this generator using the passed in
     * UnaryFunction. An example function might turn the contents of the
     * generator into a {@link Collection} of elements.
     */
    public final <T> T to(UnaryFunction<Generator<? extends E>, ? extends T> transformer) {
        return transformer.evaluate(this);
    }

    /**
     * {@inheritDoc}
     * Same as to(new CollectionTransformer(collection)).
     */
    public final Collection<? super E> to(Collection<? super E> collection) {
        return to(new CollectionTransformer<E>(collection));
    }

    /**
     * {@inheritDoc}
     * Same as to(new CollectionTransformer()).
     */
    @SuppressWarnings("unchecked")
    public final Collection<E> toCollection() {
        return (Collection<E>) to(new CollectionTransformer<E>());
    }
}
