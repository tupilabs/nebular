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

import org.apache.commons.functor.BinaryFunction;
import org.apache.commons.functor.BinaryPredicate;
import org.apache.commons.functor.UnaryFunction;
import org.apache.commons.functor.UnaryPredicate;
import org.apache.commons.functor.UnaryProcedure;

/**
 * Utility/fluent methods for creating composite functors.
 * @version $Revision: 1175833 $ $Date: 2011-09-26 09:37:35 -0300 (Mon, 26 Sep 2011) $
 * @author Rodney Waldhoff
 */
public final class Composite {
    // constructor - for beanish apis
    // ------------------------------------------------------------------------
    /**
     * <p>{@code Composite} instances should NOT be constructed in
     * standard programming. Instead, the methods of the class should be invoked
     * statically.</p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    public Composite() { }

    /**
     * Create a composite UnaryProcedure.
     * @param <A> the procedure argument type.
     * @param procedure UnaryProcedure to execute against output of <code>f</code>
     * @return CompositeUnaryProcedure<A>
     */
    public static <A> CompositeUnaryProcedure<A> procedure(UnaryProcedure<? super A> procedure) {
        return new CompositeUnaryProcedure<A>(procedure);
    }

    /**
     * Create a composite UnaryProcedure.
     * @param <A> the function argument type.
     * @param <T> the the procedure argument type and function returned value type.
     * @param procedure UnaryProcedure to execute against output of <code>f</code>
     * @param function UnaryFunction to apply
     * @return CompositeUnaryProcedure<A>
     */
    public static <A, T> CompositeUnaryProcedure<A> procedure(UnaryProcedure<? super T> procedure,
            UnaryFunction<? super A, ? extends T> function) {
        return new CompositeUnaryProcedure<T>(procedure).of(function);
    }

    /**
     * Create a composite UnaryPredicate.
     * @param <A> the predicate argument type.
     * @param pred UnaryPredicate to test the output of <code>f</code>
     * @return CompositeUnaryPredicate<A>
     */
    public static <A> CompositeUnaryPredicate<A> predicate(UnaryPredicate<? super A> pred) {
        return new CompositeUnaryPredicate<A>(pred);
    }

    /**
     * Create a composite UnaryPredicate.
     * @param <A> the function argument type.
     * @param <T> the predicate argument type and the function returned value type.
     * @param predicate UnaryPredicate to test the output of <code>f</code>
     * @param function UnaryFunction to apply
     * @return CompositeUnaryPredicate<A>
     */
    public static <A, T> CompositeUnaryPredicate<A> predicate(UnaryPredicate<? super T> predicate,
            UnaryFunction<? super A, ? extends T> function) {
        return new CompositeUnaryPredicate<T>(predicate).of(function);
    }

    /**
     * Create a composite BinaryPredicate.
     * @param <L> the output predicate left argument type.
     * @param <R> the output predicate right argument type.
     * @param <G> the input functions left argument type.
     * @param <H> the input functions right argument type.
     * @param p BinaryPredicate to test <i>output(</i><code>f</code><i>), output(</i><code>g</code><i>)</i>
     * @param g left UnaryFunction
     * @param h right UnaryFunction
     * @return BinaryPredicate
     */
    public static <L, R, G, H> UnaryCompositeBinaryPredicate<L, R> predicate(
            BinaryPredicate<? super G, ? super H> p, UnaryFunction<? super L, ? extends G> g,
            UnaryFunction<? super R, ? extends H> h) {
        return new UnaryCompositeBinaryPredicate<L, R>(p, g, h);
    }

    /**
     * Create a composite UnaryFunction.
     * @param <A> the function argument type.
     * @param <T> the function returned value type.
     * @param f UnaryFunction to apply to the output of <code>g</code>
     * @return UnaryFunction
     */
    public static <A, T> CompositeUnaryFunction<A, T> function(UnaryFunction<? super A, ? extends T> f) {
        return new CompositeUnaryFunction<A, T>(f);
    }

    /**
     * Create a composite UnaryFunction.
     * @param <A> the function argument type.
     * @param <X> the function argument type.
     * @param <T> the function returned value type.
     * @param f UnaryFunction to apply to the output of <code>g</code>
     * @param g UnaryFunction to apply first
     * @return UnaryFunction
     */
    public static <A, X, T> CompositeUnaryFunction<A, T> function(UnaryFunction<? super X, ? extends T> f,
            UnaryFunction<? super A, ? extends X> g) {
        return new CompositeUnaryFunction<X, T>(f).of(g);
    }

//    /**
//     * Chain a BinaryFunction to a UnaryFunction.
//     * @param <L>
//     * @param <R>
//     * @param <X>
//     * @param <T>
//     * @param f UnaryFunction to apply to the output of <code>g</code>
//     * @param g BinaryFunction to apply first
//     * @return BinaryFunction<L, R, T>
//     */
//    public static <L, R, X, T> BinaryFunction<L, R, T> function(UnaryFunction<? super X, ? extends T> f,
//             BinaryFunction<? super L,
//             ? super R, ? extends X> g) {
//        return new CompositeUnaryFunction<X, T>(f).of(g);
//    }

    /**
     * Create a composite<UnaryFunction> BinaryFunction.
     * @param <L> the output predicate left argument type.
     * @param <R> the output predicate right argument type.
     * @param <G> the input functions left argument type.
     * @param <H> the input functions right argument type.
 *   * @param <T> the function returned value type.
     * @param f BinaryFunction to apply to <i>output(</i><code>f</code><i>), output(</i><code>g</code><i>)</i>
     * @param g left UnaryFunction
     * @param h right UnaryFunction
     * @return BinaryFunction
     */
    public static <L, R, G, H, T> UnaryCompositeBinaryFunction<L, R, T> function(
            BinaryFunction<? super G, ? super H, ? extends T> f, UnaryFunction<? super L, ? extends G> g,
            UnaryFunction<? super R, ? extends H> h) {
        return new UnaryCompositeBinaryFunction<L, R, T>(f, g, h);
    }

    /**
     * Create a composite<BinaryFunction> BinaryFunction.
     * @param <L> the output predicate left argument type.
     * @param <R> the output predicate right argument type.
     * @param <G> the input functions left argument type.
     * @param <H> the input functions right argument type.
 *   * @param <T> the function returned value type.
     * @param f BinaryFunction to apply to <i>output(</i><code>f</code><i>), output(</i><code>g</code><i>)</i>
     * @param g left BinaryFunction
     * @param h right BinaryFunction
     * @return BinaryFunction
     */
    public static <L, R, G, H, T> BinaryCompositeBinaryFunction<L, R, T> function(
            BinaryFunction<? super G, ? super H, ? extends T> f, BinaryFunction<? super L, ? super R, ? extends G> g,
            BinaryFunction<? super L, ? super R, ? extends H> h) {
        return new BinaryCompositeBinaryFunction<L, R, T>(f, g, h);
    }
}
