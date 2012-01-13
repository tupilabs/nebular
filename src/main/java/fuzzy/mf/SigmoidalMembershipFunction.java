package fuzzy.mf;

import fuzzy.mf.input.SigmoidalInput;


public class SigmoidalMembershipFunction implements FuzzyMembershipFunction<SigmoidalInput> {

	public Double evaluate(SigmoidalInput input) {
		final Double e = Math.E;
		
		return (
				1
				/
				(1+(Math.pow(e, -(input.getA()*(input.getX()-input.getB())))))
				);
	}

}
