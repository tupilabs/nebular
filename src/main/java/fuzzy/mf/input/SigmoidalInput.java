package fuzzy.mf.input;


public class SigmoidalInput extends FuzzyInput {

	private static final long serialVersionUID = -3397404028833016393L;
	
	private final Double a;
	private final Double b;

	public SigmoidalInput(Double x, Double a, Double b) {
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