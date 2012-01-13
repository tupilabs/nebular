package fuzzy.mf.input;

public abstract class FuzzyInput implements Input<Double> {

	private static final long serialVersionUID = -2323407734389529808L;
	
	private final Double x;
	
	public FuzzyInput(Double x) {
		this.x = x;
	}
	
	public Double getX(){
		return x;
	}
	
}
