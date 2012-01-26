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
 * Triangular Membership Function. Equivalent to Matlab 
 * <a href="http://www.mathworks.com/help/toolbox/fuzzy/trimf.html">trimf</a> 
 * function. 
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class TriangularMembershipFunction implements MembershipFunction, Serializable {

	/**
     * serialVersionUID declaration.
     */
	private static final long serialVersionUID = 7696291193049536862L;
	
	private final double a;
	private final double b;
	private final double c;
	
	public TriangularMembershipFunction(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public Double evaluate(Double x) {
		return FastMath.max(FastMath.min(((x-a)/(b-a)), ((c-x)/(c-b))), 0.0);
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
		if(!(obj instanceof TriangularMembershipFunction)) {
			return false;
		}
		final TriangularMembershipFunction that = (TriangularMembershipFunction)obj;
		return this.a == that.a && this.b == that.b && this.c == that.c;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = "TriangularMembershipFunction".hashCode();
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
		return "Triangular Membership Function ["+this.a+" "+this.b+" "+this.c+"]";
	}

}
