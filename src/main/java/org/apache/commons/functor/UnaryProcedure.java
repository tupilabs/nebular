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
package org.apache.commons.functor;

/**
 * A functor that takes one argument and returns no value.
 * <p>
 * Implementors are encouraged but not required to make their functors
 * {@link java.io.Serializable Serializable}.
 * </p>
 *
 * @param <A> the argument type.
 * @since 1.0
 * @version $Revision: 1156741 $ $Date: 2011-08-11 16:03:45 -0300 (Thu, 11 Aug 2011) $
 * @author Rodney Waldhoff
 */
public interface UnaryProcedure<A> extends UnaryFunctor<A> {
    /**
     * Execute this procedure.
     * @param obj an A parameter to this execution
     */
    void run(A obj);
}