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
package org.apache.commons.functor.core.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.commons.functor.UnaryPredicate;
import org.apache.commons.lang3.Validate;

/**
 * Iterator that filters another Iterator by only passing through those elements
 * that are matched by a specified UnaryPredicate.
 *
 * @param <T> the {@link Iterator} generic type
 * @version $Revision: 1345136 $ $Date: 2012-06-01 09:47:06 -0300 (Fri, 01 Jun 2012) $
 */
public final class FilteredIterator<T> implements Iterator<T> {
    // attributes
    // ------------------------------------------------------------------------

    /**
     * The predicate used to test this Iterator elements.
     */
    private final UnaryPredicate<? super T> predicate;
    /**
     * The wrapped iterator.
     */
    private final Iterator<? extends T> iterator;

    /**
     * Reference to next element has to be returned by this iterator.
     */
    private T next = null;
    /**
     * Flag to mark this iterator has more elements or not.
     */
    private boolean nextSet = false;
    /**
     * Flag to mark current iterator element can be removed.
     */
    private boolean canRemove = false;

    // constructor
    // ------------------------------------------------------------------------
    /**
     * Create a new FilteredIterator.
     * @param iterator to filter
     * @param predicate to apply
     */
    public FilteredIterator(Iterator<? extends T> iterator, UnaryPredicate<? super T> predicate) {
        this.iterator = Validate.notNull(iterator, "Iterator argument was null");
        this.predicate = Validate.notNull(predicate, "filtering UnaryPredicate argument was null");
    }

    // iterator methods
    // ------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     * @see java.util.Iterator#hasNext()
     */
    public boolean hasNext() {
        return nextSet || setNext();
    }

    /**
     * {@inheritDoc}
     * @see java.util.Iterator#next()
     */
    public T next() {
        if (hasNext()) {
            return returnNext();
        }
        throw new NoSuchElementException();
    }

    /**
     * {@inheritDoc}
     * @see java.util.Iterator#remove()
     */
    public void remove() {
        if (canRemove) {
            canRemove = false;
            iterator.remove();
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FilteredIterator<?>)) {
            return false;
        }
        FilteredIterator<?> that = (FilteredIterator<?>) obj;
        return predicate.equals(that.predicate) && iterator.equals(that.iterator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "FilteredIterator".hashCode();
        hash <<= 2;
        hash ^= predicate.hashCode();
        hash <<= 2;
        hash ^= iterator.hashCode();
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "FilteredIterator<" + iterator + "," + predicate + ">";
    }

    // static methods
    // ------------------------------------------------------------------------
    /**
     * Get a filtered Iterator instance applying <code>pred</code> to <code>iter</code>.
     * @param <T> the input iterator generic type
     * @param iter to filter
     * @param pred to apply
     * @return Iterator
     */
    @SuppressWarnings("unchecked")
    public static <T> Iterator<T> filter(Iterator<? extends T> iter, UnaryPredicate<? super T> pred) {
        return null == pred ? (Iterator<T>) iter : (null == iter ? null : new FilteredIterator<T>(iter, pred));
    }

    // private
    // ------------------------------------------------------------------------
    /**
     * Set next element.
     * @return whether the current iterator position is valid
     */
    private boolean setNext() {
        while (iterator.hasNext()) {
            canRemove = false;
            T obj = iterator.next();
            if (predicate.test(obj)) {
                nextSet = true;
                next = obj;
                return true;
            }
        }
        next = null;
        nextSet = false;
        return false;
    }

    /**
     * Get the next element.
     * @return next element.
     */
    private T returnNext() {
        T temp = next;
        canRemove = true;
        next = null;
        nextSet = false;
        return temp;
    }

}
