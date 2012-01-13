package fuzzy.mf;

import fuzzy.mf.input.GaussianBellShapedInput;


public class GaussianBellShapedMembershipFunction implements FuzzyMembershipFunction<GaussianBellShapedInput> {

	public Double evaluate(GaussianBellShapedInput input) {
		return (
					1 
					/ 
					(
						1 + 
						Math.pow(
								Math.abs((input.getX()-input.getC()) / input.getA()), 
								(2*input.getB()))
					)
				);
	}

}