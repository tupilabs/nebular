package fuzzy.mf;

import fuzzy.mf.input.TrapezoidalInput;

public class TrapezoidalMembershipFunction implements FuzzyMembershipFunction<TrapezoidalInput> {

	public Double evaluate(TrapezoidalInput input) {
		final Double minimum1 = Math.min((input.getX()-input.getA())/(input.getB()-input.getA()), 1);
		final Double minimum2 = Math.min(minimum1, (input.getD()-input.getX())/(input.getD()-input.getC()));
		return Math.max(minimum2, 0);
	}

}