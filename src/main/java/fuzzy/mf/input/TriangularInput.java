package fuzzy.mf.input;


public class TriangularInput extends FuzzyInput {

	private static final long serialVersionUID = -7029205198841168968L;
	
	private final Double a;
	private final Double b;
	private final Double c;

	public TriangularInput(Double x, Double a, Double b, Double c) {
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