package fuzzy.mf.input;

public class DifferentialSigmoidalInput extends FuzzyInput {

	private static final long serialVersionUID = 6235692463810562056L;

	private final Double a;
	private final Double b;
	private final Double c;
	private final Double d;

	public DifferentialSigmoidalInput(Double x, Double a, Double b, Double c,
			Double d) {
		super(x);
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
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

	/**
	 * @return the c
	 */
	public Double getC() {
		return c;
	}

	/**
	 * @return the d
	 */
	public Double getD() {
		return d;
	}

}