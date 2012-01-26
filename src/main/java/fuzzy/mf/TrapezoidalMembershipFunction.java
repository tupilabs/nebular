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

/**
 * Trapezoidal Shaped Membership Function. Equivalent to Matlab 
 * <a href="http://www.mathworks.com/help/toolbox/fuzzy/trapmf.html">trapmf</a> 
 * function.
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class TrapezoidalMembershipFunction implements MembershipFunction, Serializable {

	/**
     * serialVersionUID declaration.
     */
	private static final long serialVersionUID = 6694250592149329781L;
	
	private final double a;
	private final double b;
	private final double c;
	private final double d;
	
	public TrapezoidalMembershipFunction(double a, double b, double c, double d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	
	public Double evaluate(Double x) {
		if(x <= a) {
			return 0.0;
		} else if(a <= x && x <= b) {
			return ((x-a)/(b-a));
		} else if(c <= x && x <= d) {
			return ((d-x)/(d-c));
		} else if(d <= x) {
			return 0.0;
		}
		
		// TODO: find out what happens in Matlab when the value is out of range
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
		if(!(obj instanceof TrapezoidalMembershipFunction)) {
			return false;
		}
		final TrapezoidalMembershipFunction that = (TrapezoidalMembershipFunction)obj;
		return this.a == that.a && this.b == that.b && this.c == that.c && this.d == that.d;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = "TrapezoidalMembershipFunction".hashCode();
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
		return "Trapezoidal-Shaped Membership Function ["+this.a+" "+this.b+"]";
	}

}