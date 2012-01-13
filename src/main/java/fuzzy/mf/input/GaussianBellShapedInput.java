package fuzzy.mf.input;

public class GaussianBellShapedInput extends FuzzyInput {

	private static final long serialVersionUID = -5851810198616555720L;

	private final Double a;
	private final Double b;
	private final Double c;

	public GaussianBellShapedInput(Double x, Double a, Double b, Double c) {
		super(x);
		this.a = a;
		this.b = b;
		this.c = c;
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

}