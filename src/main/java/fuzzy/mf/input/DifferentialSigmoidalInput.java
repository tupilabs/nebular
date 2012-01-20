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
package fuzzy.mf.input;

public class DifferentialSigmoidalInput extends FuzzyInput {

	private static final long serialVersionUID = 6235692463810562056L;

	private final Double a;
	private final Double b;
	private final Double c;
	private final Double d;

	public DifferentialSigmoidalInput(Double x, Double a, Double b, Double c,
			Double d) {
		super(x);
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	/**
	 * @return the a
	 */
	public Double getA() {
		return a;
	}

	/**
	 * @return the b
	 */
	public Double getB() {
		return b;
	}

	/**
	 * @return the c
	 */
	public Double getC() {
		return c;
	}

	/**
	 * @return the d
	 */
	public Double getD() {
		return d;
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
		if(!(obj instanceof DifferentialSigmoidalInput)) {
			return false;
		}
		final DifferentialSigmoidalInput that = (DifferentialSigmoidalInput)obj;
		return this.getX() == that.getX() && this.getA() == that.getA() && this.getB() == that.getB() && this.getC() == that.getC() && this.getD() == that.getD();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = "TrapezoidalInput".hashCode();
		hash <<= 2;
		if(this.getX() != null) {
			hash += this.getX().hashCode();
		}
		if(this.getA() != null) {
			hash += this.getA().hashCode();
		}
		if(this.getB() != null) {
			hash += this.getB().hashCode();
		}
		if(this.getC() != null) {
			hash += this.getC().hashCode();
		}
		return hash;
	}

}