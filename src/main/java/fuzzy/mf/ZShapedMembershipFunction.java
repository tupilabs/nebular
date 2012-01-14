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

import fuzzy.mf.input.ZShapedInput;

public class ZShapedMembershipFunction implements FuzzyMembershipFunction<ZShapedInput> {

	public Double evaluate(ZShapedInput input) {
		if(input.getX() <= input.getA()) {
			return 1.0;
		} else if((input.getA() <= input.getX()) && (input.getX() <= ((input.getA()+input.getB())/2))) {
			return (1-(2*Math.pow(((input.getX()-input.getA())/(input.getB()-input.getA())), 2)));
		} else if(((input.getA() + input.getB()) / 2) <= input.getX() && (input.getX() <= input.getB())) {
			return (2 * Math.pow((input.getB()-input.getX()) / (input.getB() - input.getA()), 2));
		} else if(input.getX() >= input.getB()) {
			return 0.0;
		}
		return 0.0;
	}

}
