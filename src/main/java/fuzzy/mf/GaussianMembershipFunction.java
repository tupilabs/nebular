package fuzzy.mf;

import fuzzy.mf.input.GaussianInput;


public class GaussianMembershipFunction implements FuzzyMembershipFunction<GaussianInput> {

	public Double evaluate(GaussianInput input) {
		final Double e = Math.E;
		
		return (
				Math.pow(
						e, 
						-(	Math.pow( (input.getX() - input.getA() ), 2 ) ) / ( 2 * Math.pow(input.getSigma(), 2))						
						)
				);
	}

}
