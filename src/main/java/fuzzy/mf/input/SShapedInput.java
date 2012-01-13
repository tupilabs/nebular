package fuzzy.mf.input;


public class SShapedInput extends FuzzyInput {

	private static final long serialVersionUID = 1827352336571435369L;
	
	private final Double a;
	private final Double b;

	public SShapedInput(Double x, Double a, Double b) {
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