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

import org.apache.commons.functor.UnaryProcedure;
import org.apache.commons.functor.generator.BaseGenerator;


/**
 * A generator for the range <i>from</i> (inclusive) to <i>to</i> (exclusive).
 *
 * @since 1.0
 * @version $Revision: 1187620 $ $Date: 2011-10-21 23:18:52 -0200 (Fri, 21 Oct 2011) $
 * @author Jason Horman (jason@jhorman.org)
 * @author Rodney Waldhoff
 */
public final class IntegerRange extends BaseGenerator<Integer> {
    // attributes
    //---------------------------------------------------------------

    /**
     * The start index.
     */
    private final int from;

    /**
     * The end index.
     */
    private final int to;

    /**
     * The increment counter.
     */
    private final int step;

    // constructors
    //---------------------------------------------------------------
    /**
     * Create a new IntegerRange.
     * @param from start
     * @param to end
     */
    public IntegerRange(Number from, Number to) {
        this(from.intValue(), to.intValue());
    }

    /**
     * Create a new IntegerRange.
     * @param from start
     * @param to end
     * @param step increment
     */
    public IntegerRange(Number from, Number to, Number step) {
        this(from.intValue(), to.intValue(), step.intValue());
    }

    /**
     * Create a new IntegerRange.
     * @param from start
     * @param to end
     */
    public IntegerRange(int from, int to) {
        this(from, to, defaultStep(from, to));
    }

    /**
     * Create a new IntegerRange.
     * @param from start
     * @param to end
     * @param step increment
     */
    public IntegerRange(int from, int to, int step) {
        if (from != to && signOf(step) != signOf(to - from)) {
            throw new IllegalArgumentException("Will never reach " + to + " from " + from + " using step " + step);
        }
        this.from = from;
        this.to = to;
        this.step = step;
    }

    // methods
    //---------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    public void run(UnaryProcedure<? super Integer> proc) {
        if (signOf(step) == -1) {
            for (int i = from; i > to; i += step) {
                proc.run(Integer.valueOf(i));
            }
        } else {
            for (int i = from; i < to; i += step) {
                proc.run(Integer.valueOf(i));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "IntegerRange<" + from + "," + to + "," + step + ">";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IntegerRange)) {
            return false;
        }
        IntegerRange that = (IntegerRange) obj;
        return this.from == that.from && this.to == that.to && this.step == that.step;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "IntegerRange".hashCode();
        hash <<= 2;
        hash ^= from;
        hash <<= 2;
        hash ^= to;
        hash <<= 2;
        hash ^= step;
        return hash;
    }

    // private methods
    //---------------------------------------------------------------
    /**
     * Get <code>value/|value|</code> (0 when value == 0).
     * @param value to test
     * @return int
     */
    private static int signOf(int value) {
        return value < 0 ? -1 : value > 0 ? 1 : 0;
    }

    /**
     * Calculate default step to get from <code>from</code> to <code>to</code>.
     * @param from start
     * @param to end
     * @return int
     */
    private static int defaultStep(int from, int to) {
        return from > to ? -1 : 1;
    }

}
