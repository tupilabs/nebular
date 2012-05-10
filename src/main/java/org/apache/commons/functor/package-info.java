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

/**
 * <p>
 * Basic functor interfaces.
 * </p>
 * <p>
 * Implementors are encouraged, but not strictly required, to make each functor
 * implementation {@link java.io.Serializable Serializable}.
 * </p>
 * <p>
 * Note that each functor interface extends the
 * {@link java.lang.Object#equals Object.equals} contract to state that
 * <code>equals</code> can return <tt>true</tt> <i>only</i> if
 * the specified Object implements the same functor interface
 * and is known to produce the same results and/or side-effects
 * for the same arguments (if any).  Note that the default
 * <code>Object.equals</code> implementation
 * does in fact adhere to the functor <code>equals</code> contract.
 * </p>
 */
package org.apache.commons.functor;
