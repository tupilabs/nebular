package fuzzy.mf.input;

public class GaussianInput extends FuzzyInput {

	private static final long serialVersionUID = -6305985683011951807L;

	private final Double sigma;
	private final Double a;

	public GaussianInput(Double x, Double sigma, Double a) {
		super(x);
		this.sigma = sigma;
		this.a = a;
	}

	/**
	 * @return the sigma
	 */
	public Double getSigma() {
		return sigma;
	}

	/**
	 * @return the a
	 */
	public Double getA() {
		return a;
	}

}