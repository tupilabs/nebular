package fuzzy.mf;

import fuzzy.mf.input.ZShapedInput;

public class ZShapedMembershipFunction implements FuzzyMembershipFunction<ZShapedInput> {

	public Double evaluate(ZShapedInput input) {
		if(input.getX() <= input.getA()) {
			return 1.0;
		} else if((input.getA() <= input.getX()) && (input.getX() <= ((input.getA()+input.getB())/2))) {
			return (1-(2*Math.pow(((input.getX()-input.getA())/(input.getB()-input.getA())), 2)));
		} else if(((input.getA() + input.getB()) / 2) <= input.getX() && (input.getX() <= input.getB())) {
			return (2 * Math.pow((input.getB()-input.getX()) / (input.getB() - input.getA()), 2));
		} else if(input.getX() >= input.getB()) {
			return 0.0;
		}
		return 0.0;
	}

}
