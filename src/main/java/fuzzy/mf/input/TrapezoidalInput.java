package fuzzy.mf.input;


public class TrapezoidalInput extends FuzzyInput {

	private static final long serialVersionUID = 5212587638057082912L;
	
	private final Double a;
	private final Double b;
	private final Double c;
	private final Double d;

	public TrapezoidalInput(Double x, Double a, Double b, Double c, Double d) {
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