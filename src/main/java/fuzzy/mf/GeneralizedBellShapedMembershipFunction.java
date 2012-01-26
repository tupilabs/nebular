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

import java.io.Serializable;

import org.apache.commons.math.util.FastMath;

/**
 * Generalized Bell-Shaped Membership Function. Equivalent to Matlab 
 * <a href="http://www.mathworks.com/help/toolbox/fuzzy/gbellmf.html">gbellmf</a> 
 * function. 
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class GeneralizedBellShapedMembershipFunction implements MembershipFunction, Serializable {

	/**
     * serialVersionUID declaration.
     */
	private static final long serialVersionUID = 8440666936218883278L;
	
	private final double a;
	private final double b;
	private final double c;
	
	public GeneralizedBellShapedMembershipFunction(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public Double evaluate(Double x) {
		return (1/(1+FastMath.pow(FastMath.abs((x-c)/a), (2*b))));
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(obj == this) {
			return true;
		}
		if(!(obj instanceof GeneralizedBellShapedMembershipFunction)) {
			return false;
		}
		final GeneralizedBellShapedMembershipFunction that = (GeneralizedBellShapedMembershipFunction)obj;
		return this.a == that.a && this.b == that.b && this.c == that.c;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = "GeneralizedBellShapedMembershipFunction".hashCode();
		hash <<= 2;
		hash ^= (int)this.a;
		hash <<= 2;
		hash ^= (int)this.b;
		hash <<= 2;
		hash ^= (int)this.c;
		return hash;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Generalized Bell-Shaped Membership Function ["+a+" "+b+" "+c+"]";
	}

}