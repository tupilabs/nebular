/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package fuzzy.internal.functions;

import java.util.Collection;

/**
 * Utility methods for Doubles.
 *
 * @since 0.2
 */
public class Doubles {

	/**
	 * Hide constructor.
	 */
	private Doubles() {

	}

    /**
     * Convert a collection of boxed Doubles into an array with primitive
     * doubles.
     *
     * @param col collection of boxed Doubles
     * @return array with primitive doubles
     */
    public static double[] toArray(Collection<Double> col) {
        col.remove(null);
        double[] r = new double[col.size()];
        int i = 0;
        for (Double value : col) {
            r[i] = value;
            i++;
        }
        return r;
    }

}
