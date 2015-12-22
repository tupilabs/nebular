package fuzzy.df;

import java.util.function.BiFunction;

import org.apache.commons.functor.generator.range.NumericRange;

import fuzzy.mf.MembershipFunction;

/**
 * A defuzzification function. Operates over the result of a membership function
 * applied to a numeric range. Its output is always a crisp value.
 *
 * @param <T> numeric type used in this defuzzification function
 * @since 0.2
 */
public interface DefuzzificationFunction<T extends Number & Comparable<T>>
		extends BiFunction<NumericRange<T>, MembershipFunction<T>, Double> {
	/**
	 * Defuzzifies the result of a membership function applied to a numeric
	 * range.
	 * <p>
	 * The range contains crisp values, that are transformed using the
	 * membership function. After the <strong>fuzzification</strong>, this
	 * method is called to obtain a crisp output.
	 * <p>
	 * The crisp output depends on the chosen fuzzification and defuzzification
	 * functions.
	 * <p>
	 * This method may throw different exceptions, depending on the
	 * implementation function.
	 *
	 * @param x numeric range
	 * @param mf membership function applied to numeric range
	 * @return crisp result
	 */
	Double apply(NumericRange<T> x, MembershipFunction<T> mf);
}