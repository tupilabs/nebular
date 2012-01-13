package fuzzy.mf;

import fuzzy.mf.input.PiShapedInput;


public class PiShapedMembershipFunction implements FuzzyMembershipFunction<PiShapedInput> {

	public Double evaluate(PiShapedInput input) {
		final Double e = Math.E;
		
		if(input.getB() <= input.getX() && input.getX() <= input.getC()) {
			return 1.0;
		} else if(input.getA() >= input.getX()) {
			return 0.0;
		} else if(input.getX() >= input.getD()) {
			return 0.0;
		} else if(input.getA() <= input.getX() && input.getX() <= input.getB()) {
			return (1/(1+(Math.pow(e, -(input.getA() * (input.getX() - input.getB()))))));
		}
		
		return 0.0;
	}
	
}
