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

import fuzzy.mf.input.SShapedInput;


public class SShapedMembershipFunction implements FuzzyMembershipFunction<SShapedInput> {

	public Double evaluate(SShapedInput input) {

		final Double e = Math.E;
				
		return (
				1 
				/
				( 1 + (input.getB()*Math.pow(e, -(input.getA()*input.getX()))))
				);
	}

}
