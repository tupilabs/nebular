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
import org.apache.commons.functor.BinaryProcedure;
import org.apache.commons.functor.Function;
import org.apache.commons.functor.Predicate;
import org.apache.commons.functor.Procedure;
import org.apache.commons.functor.UnaryFunction;
import org.apache.commons.functor.UnaryPredicate;
import org.apache.commons.functor.UnaryProcedure;

/**
 * Utility methods for creating conditional functors.
 * @version $Revision: 1180156 $ $Date: 2011-10-07 16:01:58 -0300 (Fri, 07 Oct 2011) $
 * @author Rodney Waldhoff
 */
public final class Conditional {

    // constructor - for beanish apis
    // ------------------------------------------------------------------------

    /**
     * <p>{@code Conditional} instances should NOT be constructed in
     * standard programming. Instead, the methods of the class should be invoked
     * statically.</p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    public Conditional() { }

    // ------------------------------------------------------------------------

    /**
     * Create a guarded Procedure.
     * @param q if
     * @param r then
     * @return Procedure
     */
    public static Procedure procedure(Predicate q, Procedure r) {
        return new ConditionalProcedure(q, r);
    }

    /**
     * Create a conditional Procedure.
     * @param q if
     * @param r then
     * @param s else
     * @return Procedure
     */
    public static Procedure procedure(Predicate q, Procedure r, Procedure s) {
        return new ConditionalProcedure(q, r, s);
    }

    /**
     * Create a conditional Function.
     * @param <T> the input functions parameter type
     * @param q if
     * @param r then
     * @param s else
     * @return Function<T>
     */
    public static <T> Function<T> function(Predicate q, Function<? extends T> r, Function<? extends T> s) {
        return new ConditionalFunction<T>(q, r, s);
    }

    /**
     * Create a conditional Predicate.
     * @param q if
     * @param r then
     * @param s else
     * @return Predicate
     */
    public static Predicate predicate(Predicate q, Predicate r, Predicate s) {
        return new ConditionalPredicate(q, r, s);
    }

    /**
     * Create a guarded UnaryProcedure.
     *
     * @param <A> the predicates argument type.
     * @param q if
     * @param r then
     * @return UnaryProcedure<A>
     */
    public static <A> UnaryProcedure<A> procedure(UnaryPredicate<? super A> q, UnaryProcedure<? super A> r) {
        return new ConditionalUnaryProcedure<A>(q, r);
    }

    /**
     * Create a conditional UnaryProcedure.
     *
     * @param <A> the predicates argument type.
     * @param q if
     * @param r then
     * @param s else
     * @return UnaryProcedure<A>
     */
    public static <A> UnaryProcedure<A> procedure(UnaryPredicate<? super A> q, UnaryProcedure<? super A> r,
            UnaryProcedure<? super A> s) {
        return new ConditionalUnaryProcedure<A>(q, r, s);
    }

    /**
     * Create a conditional UnaryFunction.
     * @param <A> the predicates argument type.
     * @param <T> the output function returned value type.
     * @param q if
     * @param r then
     * @param s else
     * @return UnaryFunction<A, T>
     */
    public static <A, T> UnaryFunction<A, T> function(UnaryPredicate<? super A> q,
            UnaryFunction<? super A, ? extends T> r, UnaryFunction<? super A, ? extends T> s) {
        return new ConditionalUnaryFunction<A, T>(q, r, s);
    }

    /**
     * Create a conditional UnaryPredicate.
     * @param <A> the predicates argument type.
     * @param q if
     * @param r then
     * @param s else
     * @return UnaryPredicate<A>
     */
    public static <A> UnaryPredicate<A> predicate(UnaryPredicate<? super A> q, UnaryPredicate<? super A> r,
            UnaryPredicate<? super A> s) {
        return new ConditionalUnaryPredicate<A>(q, r, s);
    }

    /**
     * Create a guarded BinaryProcedure.
     * @param <L> the left argument type.
     * @param <R> the right argument type.
     * @param q if
     * @param r then
     * @return BinaryProcedure<L, R>
     */
    public static <L, R> BinaryProcedure<L, R> procedure(BinaryPredicate<? super L, ? super R> q,
            BinaryProcedure<? super L, ? super R> r) {
        return new ConditionalBinaryProcedure<L, R>(q, r);
    }

    /**
     * Create a conditional BinaryProcedure.
     *
     * @param <L> the left argument type.
     * @param <R> the right argument type.
     * @param q if
     * @param r then
     * @param s else
     * @return BinaryProcedure<L, R>
     */
    public static <L, R> BinaryProcedure<L, R> procedure(BinaryPredicate<? super L, ? super R> q,
            BinaryProcedure<? super L, ? super R> r, BinaryProcedure<? super L, ? super R> s) {
        return new ConditionalBinaryProcedure<L, R>(q, r, s);
    }

    /**
     * Create a conditional BinaryFunction.
     *
     * @param <L> the left argument type.
     * @param <R> the right argument type.
     * @param <T> the output function returned value type.
     * @param q if
     * @param r then
     * @param s else
     * @return BinaryFunction<L, R, T>
     */
    public static <L, R, T> BinaryFunction<L, R, T> function(BinaryPredicate<? super L, ? super R> q,
            BinaryFunction<? super L, ? super R, ? extends T> r, BinaryFunction<? super L, ? super R, ? extends T> s) {
        return new ConditionalBinaryFunction<L, R, T>(q, r, s);
    }

    /**
     * Create a conditional BinaryPredicate.
     *
     * @param <L> the left argument type.
     * @param <R> the right argument type.
     * @param q if
     * @param r then
     * @param s else
     * @return BinaryPredicate<L, R>
     */
    public static <L, R> BinaryPredicate<L, R> predicate(BinaryPredicate<? super L, ? super R> q,
            BinaryPredicate<? super L, ? super R> r, BinaryPredicate<? super L, ? super R> s) {
        return new ConditionalBinaryPredicate<L, R>(q, r, s);
    }

}
