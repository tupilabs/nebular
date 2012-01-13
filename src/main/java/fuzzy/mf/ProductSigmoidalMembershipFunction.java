package fuzzy.mf;

import fuzzy.mf.input.ProductSigmoidalInput;


public class ProductSigmoidalMembershipFunction implements FuzzyMembershipFunction<ProductSigmoidalInput> {

	public Double evaluate(ProductSigmoidalInput input) {
		final Double e = Math.E;
		
		final Double function1 = (
					1
					/
					(1+(
						Math.pow(e, -(input.getA() * (input.getX()-input.getB())))
					))
				);
		
		final Double function2 = (
				1
				/
				(1+(
					Math.pow(e, -(input.getC() * (input.getX()-input.getD())))
				))
			);
		
		return (function1 * function2);
	}

}
