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

import org.apache.commons.math3.util.FastMath;

/**
 * S-Shaped Membership Function. Equivalent to Matlab 
 * <a href="http://www.mathworks.com/help/toolbox/fuzzy/smf.html">smf</a> 
 * function.
 *
 * @since 0.1
 */
public class SShapedMembershipFunction implements MembershipFunction<Double>, Serializable {

	/**
     * serialVersionUID declaration.
     */
	private static final long serialVersionUID = -4171080765970704081L;
	
	private final double a;
	private final double b;
	
	public SShapedMembershipFunction(double a, double b) {
		this.a = a;
		this.b = b;
	}
	
	public Double evaluate(Double x) {
		if(x <= a) {
			return 0.0;
		} else if(a <= x && x <= ((a+b)/2)) {
			return 2 * FastMath.pow((x-a)/(b-a), 2);
		} else if((a+b)/2 <= x && x <= b) {
			return 1 - (2 * FastMath.pow((x-b)/(b-a), 2));
		} else if(x >= b) {
			return 1.0;
		}
		
		return 0.0;
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
		if(!(obj instanceof SShapedMembershipFunction)) {
			return false;
		}
		final SShapedMembershipFunction that = (SShapedMembershipFunction)obj;
		return this.a == that.a && this.b == that.b;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = "SShapedMembershipFunction".hashCode();
		hash <<= 2;
		hash ^= (int)this.a;
		hash <<= 2;
		hash ^= (int)this.b;
		return hash;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "S-Shaped Membership Function ["+this.a+" "+this.b+"]";
	}

}
