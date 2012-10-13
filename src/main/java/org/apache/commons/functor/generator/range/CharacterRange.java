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

package org.apache.commons.functor.generator.range;

import java.util.Collection;

import org.apache.commons.functor.BinaryFunction;
import org.apache.commons.functor.UnaryProcedure;
import org.apache.commons.functor.generator.loop.LoopGenerator;
import org.apache.commons.lang3.Validate;

/**
 * A generator for a range of characters.
 *
 * @since 1.0
 * @version $Revision: $ $Date: $
 */
public final class CharacterRange extends LoopGenerator<Character> implements Range<Character, Integer> {

    // attributes
    // ---------------------------------------------------------------
    /**
     * Default left bound type.
     */
    public static final BoundType DEFAULT_LEFT_BOUND_TYPE = BoundType.CLOSED;

    /**
     * Default right bound type.
     */
    public static final BoundType DEFAULT_RIGHT_BOUND_TYPE = BoundType.CLOSED;

    /**
     * Left limit.
     */
    private final Endpoint<Character> leftEndpoint;

    /**
     * Right limit.
     */
    private final Endpoint<Character> rightEndpoint;

    /**
     * Increment step.
     */
    private final int step;

    /**
     * Calculate default step.
     */
    public static final BinaryFunction<Character, Character, Integer> DEFAULT_STEP
        = new BinaryFunction<Character, Character, Integer>() {

        public Integer evaluate(Character left, Character right) {
            return left > right ? -1 : 1;
        }
    };

    // constructors
    // ---------------------------------------------------------------
    /**
     * Create a new CharacterRange.
     *
     * @param from start
     * @param to end
     */
    public CharacterRange(char from, char to) {
        this(from, to, DEFAULT_STEP.evaluate(from, to).intValue());
    }

    /**
     * Create a new CharacterRange.
     *
     * @param from start
     * @param to end
     * @param step increment
     */
    public CharacterRange(char from, char to, int step) {
        this(from, BoundType.CLOSED, to, BoundType.CLOSED, step);
    }

    /**
     * Create a new CharacterRange.
     *
     * @param from start
     * @param leftBoundType type of left bound
     * @param to end
     * @param rightBoundType type of right bound
     * @param step increment
     */
    public CharacterRange(char from, BoundType leftBoundType, char to,
                          BoundType rightBoundType, int step) {
        this.leftEndpoint = Validate
            .notNull(new Endpoint<Character>(from, leftBoundType),
                     "Left Endpoint argument must not be null");
        this.rightEndpoint = Validate
            .notNull(new Endpoint<Character>(to, rightBoundType),
                     "Right Endpoint argument must not be null");
        this.step = step;
        if (from != to && Integer.signum(step) != Integer.signum(to - from)) {
            throw new IllegalArgumentException("Will never reach " + to
                                               + " from " + from
                                               + " using step " + step);
        }
    }

    /**
     * Create a new CharacterRange.
     *
     * @param from start
     * @param to end
     * @param step increment
     */
    public CharacterRange(Endpoint<Character> from, Endpoint<Character> to,
                          int step) {
        this.leftEndpoint = Validate
            .notNull(from, "Left Endpoint argument must not be null");
        this.rightEndpoint = Validate
            .notNull(to, "Right Endpoint argument must not be null");
        this.step = step;
        if (from != to
            && Integer.signum(step) != Integer.signum(to.getValue()
                                                   - from.getValue())) {
            throw new IllegalArgumentException("Will never reach " + to
                                               + " from " + from
                                               + " using step " + step);
        }
    }

    // methods
    // ---------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    public Endpoint<Character> getLeftEndpoint() {
        return this.leftEndpoint;
    }

    /**
     * {@inheritDoc}
     */
    public Endpoint<Character> getRightEndpoint() {
        return this.rightEndpoint;
    }

    /**
     * {@inheritDoc}
     */
    public Integer getStep() {
        return this.step;
    }

    /**
     * {@inheritDoc}
     */
    public void run(UnaryProcedure<? super Character> proc) {
        final int step = this.getStep();
        final boolean includeLeftValue = this.getLeftEndpoint()
            .getBoundType() == BoundType.CLOSED;
        final boolean includeRightValue = this.getRightEndpoint()
            .getBoundType() == BoundType.CLOSED;
        final char leftValue = this.getLeftEndpoint().getValue();
        final char rightValue = this.getRightEndpoint().getValue();
        if (step < 0) {
            final char from = (char) (includeLeftValue ? leftValue : leftValue
                                                                     + step);
            if (includeRightValue) {
                for (char i = from; i >= rightValue; i += step) {
                    proc.run(i);
                }
            } else {
                for (char i = from; i > rightValue; i += step) {
                    proc.run(i);
                }
            }
        } else {
            final char from = (char) (includeLeftValue ? this
                .getLeftEndpoint().getValue() : (this.getLeftEndpoint()
                .getValue() + step));
            if (includeRightValue) {
                for (char i = from; i <= rightValue; i += step) {
                    proc.run(i);
                }
            } else {
                for (char i = from; i < rightValue; i += step) {
                    proc.run(i);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "CharacterRange<" + this.leftEndpoint.toLeftString() + ", "
                + this.rightEndpoint.toRightString() + ", " + step + ">";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CharacterRange)) {
            return false;
        }
        CharacterRange that = (CharacterRange) obj;
        return this.leftEndpoint.equals(that.leftEndpoint)
                && this.rightEndpoint.equals(that.rightEndpoint)
                && this.step == that.step;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = "CharacterRange".hashCode();
        hash <<= 2;
        hash ^= this.leftEndpoint.getValue();
        hash <<= 2;
        hash ^= this.rightEndpoint.getValue();
        hash <<= 2;
        hash ^= this.step;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isEmpty() {
        double leftValue = this.getLeftEndpoint().getValue().charValue();
        double rightValue = this.getRightEndpoint().getValue().charValue();
        boolean closedLeft = this.getLeftEndpoint().getBoundType() == BoundType.CLOSED;
        boolean closedRight = this.getRightEndpoint().getBoundType() == BoundType.CLOSED;
        if (!closedLeft && !closedRight
                && this.getLeftEndpoint().equals(this.getRightEndpoint())) {
            return Boolean.TRUE;
        }
        double step = this.getStep().intValue();
        if (step > 0.0) {
            double firstValue = closedLeft ? leftValue : leftValue + step;
            return closedRight ? firstValue > rightValue
                              : firstValue >= rightValue;
        } else {
            double firstValue = closedLeft ? leftValue : leftValue + step;
            return closedRight ? firstValue < rightValue
                              : firstValue <= rightValue;
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean contains(Character obj) {
        if (obj == null) {
            return Boolean.FALSE;
        }
        char leftValue = this.getLeftEndpoint().getValue().charValue();
        char rightValue = this.getRightEndpoint().getValue().charValue();
        boolean includeLeft = this.getLeftEndpoint().getBoundType() == BoundType.CLOSED;
        boolean includeRight = this.getRightEndpoint().getBoundType() == BoundType.CLOSED;
        int step = this.getStep().intValue();
        int value = (int) obj.charValue();

        int firstValue = 0;
        int lastValue = 0;

        if (step < 0.0) {
            firstValue = includeLeft ? leftValue : leftValue + step;
            lastValue = includeRight ? rightValue : rightValue + 1;
            if (value > firstValue || value < lastValue) {
                return Boolean.FALSE;
            }
        } else {
            firstValue = includeLeft ? leftValue : leftValue + step;
            lastValue = includeRight ? rightValue : rightValue - 1;
            if (value < firstValue || value > lastValue) {
                return Boolean.FALSE;
            }
        }
        return ((double) (value - firstValue) / step + 1) % 1.0 == 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public boolean containsAll(Collection<Character> col) {
        if (col == null || col.size() == 0) {
            return Boolean.FALSE;
        }
        boolean r = Boolean.TRUE;
        for (Character t : col) {
            if (!this.contains(t)) {
                r = Boolean.FALSE;
                break;
            }
        }
        return r;
    }

}
