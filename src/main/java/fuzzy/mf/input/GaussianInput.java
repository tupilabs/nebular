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

public class GaussianInput extends FuzzyInput {

	private static final long serialVersionUID = -6305985683011951807L;

	private final Double sigma;
	private final Double a;

	public GaussianInput(Double x, Double sigma, Double a) {
		super(x);
		this.sigma = sigma;
		this.a = a;
	}

	/**
	 * @return the sigma
	 */
	public Double getSigma() {
		return sigma;
	}

	/**
	 * @return the a
	 */
	public Double getA() {
		return a;
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
		if(!(obj instanceof GaussianInput)) {
			return false;
		}
		final GaussianInput that = (GaussianInput)obj;
		return this.getX() == that.getX() && this.getSigma() == that.getSigma() && this.getA() == that.getA();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = "GaussianInput".hashCode();
		hash <<= 2;
		if(this.getX() != null) {
			hash <<= this.getX().hashCode();
		}
		if(this.getSigma() != null) {
			hash += this.getSigma().hashCode();
		}
		if(this.getA() != null) {
			hash += this.getA().hashCode();
		}
		return hash;
	}

}