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

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.functor.UnaryProcedure;

/**
 * Returns the absolute values of a collection.
 *
 * @param <T> numeric type
 */
public class MapAbs<T extends Number & Comparable<T>> implements UnaryProcedure<T> {

	/**
	 * A collection.
	 */
    private Collection<Double> col;

    /**
     * Default constructor.
     */
    public MapAbs() {
        col = new ArrayList<Double>();
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.functor.UnaryProcedure#run(java.lang.Object)
     */
    public void run(T obj) {
        col.add(Math.abs(obj.doubleValue()));
    }

    /**
     * Gets the collection with absolute values.
     * 
     * @return collection with absolute values
     */
    public Collection<Double> getCol() {
        return col;
    }
}
