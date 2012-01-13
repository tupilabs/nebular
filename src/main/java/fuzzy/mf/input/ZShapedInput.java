package fuzzy.mf.input;

public class ZShapedInput extends FuzzyInput {

	private static final long serialVersionUID = 6990335030142615579L;

	private final Double a;
	private final Double b;

	public ZShapedInput(Double x, Double a, Double b) {
		super(x);
		this.a = a;
		this.b = b;
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

}
