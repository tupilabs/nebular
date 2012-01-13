package fuzzy.mf;

import fuzzy.mf.input.SShapedInput;


public class SShapedMembershipFunction implements FuzzyMembershipFunction<SShapedInput> {

	public Double evaluate(SShapedInput input) {

		final Double e = Math.E;
				
		return (
				1 
				/
				( 1 + (input.getB()*Math.pow(e, -(input.getA()*input.getX()))))
				);
	}

}
