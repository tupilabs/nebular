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
 * @version $Revision: 1187616 $ $Date: 2011-10-21 23:10:48 -0200 (Fri, 21 Oct 2011) $
 * @author Jason Horman (jason@jhorman.org)
 * @author Rodney Waldhoff
 */
public final class LongRange extends BaseGenerator<Long> {
    // attributes
    //---------------------------------------------------------------

    /**
     * The start index.
     */
    private final long from;

    /**
     * The end index.
     */
    private final long to;

    /**
     * The increment counter.
     */
    private final long step;

    // constructors
    //---------------------------------------------------------------
    /**
     * Create a new LongRange.
     * @param from start
     * @param to end
     */
    public LongRange(Number from, Number to) {
        this(from.longValue(), to.longValue());
    }

    /**
     * Create a new LongRange.
     * @param from start
     * @param to end
     * @param step increment
     */
    public LongRange(Number from, Number to, Number step) {
        this(from.longValue(), to.longValue(), step.longValue());
    }

    /**
     * Create a new LongRange.
     * @param from start
     * @param to end
     */
    public LongRange(long from, long to) {
        this(from, to, defaultStep(from, to));
    }

    /**
     * Create a new LongRange.
     * @param from start
     * @param to end
     * @param step increment
     */
    public LongRange(long from, long to, long step) {
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
    public void run(UnaryProcedure<? super Long> proc) {
        if (signOf(step) == -1L) {
            for (long i = from; i > to; i += step) {
                proc.run(Long.valueOf(i));
            }
        } else {
            for (long i = from; i < to; i += step) {
                proc.run(Long.valueOf(i));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "LongRange<" + from + "," + to + "," + step + ">";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LongRange)) {
            return false;
        }
        LongRange that = (LongRange) obj;
        return this.from == that.from && this.to == that.to && this.step == that.step;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "LongRange".hashCode();
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
     * Get <code>value/|value|</code> (0L when value == 0L).
     * @param value to test
     * @return long
     */
    private static long signOf(long value) {
        return value < 0L ? -1L : value > 0L ? 1L : 0L;
    }

    /**
     * Calculate default step to get from <code>from</code> to <code>to</code>.
     * @param from start
     * @param to end
     * @return long
     */
    private static long defaultStep(long from, long to) {
        return from > to ? -1L : 1L;
    }

}
