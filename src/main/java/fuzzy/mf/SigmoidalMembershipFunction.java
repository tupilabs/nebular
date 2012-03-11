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

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.math.analysis.function.Sigmoid;

/**
 * Sigmoidal Membership Function. Equivalent to Matlab 
 * <a href="http://www.mathworks.com/help/toolbox/fuzzy/sigmf.html">sigmf</a> 
 * function. 
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class SigmoidalMembershipFunction implements MembershipFunction<Double>, Serializable {

	/**
     * serialVersionUID declaration.
     */
	private static final long serialVersionUID = 5146360927589542693L;
	
	protected final static double DEFAULT_LOW_ASYMPTOTE = 0.0;
	protected final static double DEFAULT_HIGH_ASYMPTOTE = 1.0;
	
	protected transient Sigmoid sigmoid;
	
	private final double a;
	private final double c;
	
	private final double lowAsymptote;
	private final double highAsymptote;
	
	public void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
	}
	
	public void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException {
		in.defaultReadObject();
		
		sigmoid = new Sigmoid(lowAsymptote, highAsymptote);
	}
	
	public SigmoidalMembershipFunction(double a, double c) {
		this(DEFAULT_LOW_ASYMPTOTE, DEFAULT_HIGH_ASYMPTOTE, a, c);
	}
	
	public SigmoidalMembershipFunction(double lowAsymptote, double highAsymptote, double a, double c) {
		sigmoid = new Sigmoid(lowAsymptote, highAsymptote);
		this.a = a;
		this.c = c;
		
		this.lowAsymptote = lowAsymptote;
		this.highAsymptote = highAsymptote;
	}
	
	public Double evaluate(Double x) {
		return sigmoid.value(a*(x-c));
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
		if(!(obj instanceof SigmoidalMembershipFunction)) {
			return false;
		}
		final SigmoidalMembershipFunction that = (SigmoidalMembershipFunction)obj;
		return this.a == that.a && this.c == that.c;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = "SigmoidalMembershipFunction".hashCode();
		hash <<= 2;
		hash ^= (int)this.a;
		hash <<= 2;
		hash ^= (int)this.c;
		return hash;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sigmoidal Membership Function ["+this.a+" "+this.c+"]";
	}

}
