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
package org.apache.commons.functor.generator;

import org.apache.commons.functor.UnaryFunction;
import org.apache.commons.functor.UnaryProcedure;
import org.apache.commons.lang3.Validate;

/**
 * Generator that transforms the elements of another Generator.
 *
 * @param <I> the type of elements held in the wrapped generator.
 * @param <E> the type of elements held in this generator.
 * @version $Revision: 1234990 $ $Date: 2012-01-23 19:18:10 -0200 (Mon, 23 Jan 2012) $
 */
public class TransformedGenerator<I, E> extends BaseGenerator<E> {

    /**
     * The UnaryFunction to apply to each element.
     */
    private final UnaryFunction<? super I, ? extends E> func;

    /**
     * Create a new TransformedGenerator.
     * @param wrapped Generator to transform
     * @param func UnaryFunction to apply to each element
     */
    public TransformedGenerator(Generator<? extends I> wrapped, UnaryFunction<? super I, ? extends E> func) {
        super(Validate.notNull(wrapped, "Generator argument was null"));
        this.func = Validate.notNull(func, "UnaryFunction argument was null");
    }

    /**
     * {@inheritDoc}
     */
    public void run(final UnaryProcedure<? super E> proc) {
        getWrappedGenerator().run(new UnaryProcedure<I>() {
            public void run(I obj) {
                proc.run(func.evaluate(obj));
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected Generator<? extends I> getWrappedGenerator() {
        return (Generator<? extends I>) super.getWrappedGenerator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TransformedGenerator<?, ?>)) {
            return false;
        }
        TransformedGenerator<?, ?> other = (TransformedGenerator<?, ?>) obj;
        return other.getWrappedGenerator().equals(getWrappedGenerator()) && other.func == func;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = "TransformedGenerator".hashCode();
        result <<= 2;
        result ^= getWrappedGenerator().hashCode();
        result <<= 2;
        result ^= func.hashCode();
        return result;
    }
}
