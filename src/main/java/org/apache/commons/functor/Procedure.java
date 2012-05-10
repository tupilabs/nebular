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
 * A functor that takes no arguments and returns no value.
 * <p>
 * Note that this functor implements the
 * {@link Runnable Runnable}
 * interface, making all <code>Procedure</code>s
 * immediately usable in a number of contexts (Swing, Jelly, etc.).
 * </p>
 * <p>
 * Implementors are encouraged but not required to make their functors
 * {@link java.io.Serializable Serializable}.
 * </p>
 *
 * @since 1.0
 * @version $Revision: 647290 $ $Date: 2008-04-11 17:08:44 -0300 (Fri, 11 Apr 2008) $
 * @author Rodney Waldhoff
 */
public interface Procedure extends NullaryFunctor, Runnable {
    /** Execute this procedure. */
    void run();
}
