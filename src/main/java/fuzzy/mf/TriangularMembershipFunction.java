package fuzzy.mf;

import fuzzy.mf.input.TriangularInput;


public class TriangularMembershipFunction implements FuzzyMembershipFunction<TriangularInput> {

	public Double evaluate(TriangularInput input) {
		return Math.max(Math.min((input.getX()-input.getA()), (input.getX())/(input.getC()-input.getB())), 0);
	}

}
