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
 * Product Sigmoidal Membership Function. Equivalent to Matlab 
 * <a href="http://www.mathworks.com/help/toolbox/fuzzy/psigmf.html">psigmf</a> 
 * function. 
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class ProductSigmoidalMembershipFunction implements MembershipFunction, Serializable {

	/**
     * serialVersionUID declaration.
     */
	private static final long serialVersionUID = -6402333359433841451L;
	
	protected final static double DEFAULT_LOW_ASYMPTOTE = 0.0;
	protected final static double DEFAULT_HIGH_ASYMPTOTE = 1.0;
	
	protected transient Sigmoid sigmoid;
	
	private double a1;
	private double c1;
	private double a2;
	private double c2;
	
	private final double lowAsymptote;
	private final double highAsymptote;
	
	public void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
	}
	
	public void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException {
		in.defaultReadObject();
		
		sigmoid = new Sigmoid(lowAsymptote, highAsymptote);
	}
	
	public ProductSigmoidalMembershipFunction(double a1, double c1, double a2, double c2) {
		this(DEFAULT_LOW_ASYMPTOTE, DEFAULT_HIGH_ASYMPTOTE, a1, c1, a2, c2);
	}
	
	public ProductSigmoidalMembershipFunction(double lowAsymptote, double highAsymptote, double a1, double c1, double a2, double c2) {
		sigmoid = new Sigmoid(lowAsymptote, highAsymptote);
		this.a1 = a1;
		this.c1 = c1;
		this.a2 = a2;
		this.c2 = c2;
		
		this.lowAsymptote = lowAsymptote;
		this.highAsymptote = highAsymptote;
	}
	
	public Double evaluate(Double x) {
		final double r1 = sigmoid.value(a1*(x-c1));
		final double r2 = sigmoid.value(a2*(x-c2));
		
		return (r1 * r2);
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
		if(!(obj instanceof ProductSigmoidalMembershipFunction)) {
			return false;
		}
		final ProductSigmoidalMembershipFunction that = (ProductSigmoidalMembershipFunction)obj;
		return this.a1 == that.a1 && this.c1 == that.c1 && this.a2 == that.a2 && this.c2 == that.c2;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = "ProductSigmoidalMembershipFunction".hashCode();
		hash <<= 2;
		hash ^= (int)this.a1;
		hash <<= 2;
		hash ^= (int)this.c1;
		hash <<= 2;
		hash ^= (int)this.a2;
		hash <<= 2;
		hash ^= (int)this.c2;
		return hash;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product Sigmoidal Membership Function ["+a1+" "+c1+" "+a2+" "+c2+"]";
	}

}
