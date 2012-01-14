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
package fuzzy.mf;

import fuzzy.mf.input.TrapezoidalInput;

public class TrapezoidalMembershipFunction implements FuzzyMembershipFunction<TrapezoidalInput> {

	public Double evaluate(TrapezoidalInput input) {
		final Double minimum1 = Math.min((input.getX()-input.getA())/(input.getB()-input.getA()), 1);
		final Double minimum2 = Math.min(minimum1, (input.getD()-input.getX())/(input.getD()-input.getC()));
		return Math.max(minimum2, 0);
	}

}