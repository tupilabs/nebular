package fuzzy.df;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.functor.Function;
import org.apache.commons.functor.generator.range.BoundType;
import org.apache.commons.functor.generator.range.DoubleRange;

import fuzzy.mf.MembershipFunction;
import fuzzy.mf.TrapezoidalMembershipFunction;

public class CentroidDefuzzificationFunction {

    public double defuzzify(DoubleRange x, MembershipFunction<Double> mf) {
        Collection<Double> fuzzyValues = new ArrayList<Double>();
        for (double crispValue : x.toCollection()) {
            fuzzyValues.add(mf.evaluate(crispValue));
        }
        double totalArea = Sum.of(fuzzyValues);
        if (totalArea == 0)
            throw new IllegalArgumentException(
                    "Total area is zero in centroid defuzzification!");
        Collection<Double> product = Product.of(x.toCollection(), mf);
        double sum2 = Sum.of(product);
        double out = sum2 / totalArea;
        return out;
    }
    
    /* --- Functions --- */
    
    private static class Sum implements Function<Double> {
        private Collection<Double> x;
        
        public Sum(Collection<Double> x) {
            this.x = x;
        }
        
        public Double evaluate() {
            double result = 0.0;
            for (double value : x) {
                result += value;
            }
            return result;
        }
        
        public static double of(Collection<Double> x) {
            return new Sum(x).evaluate();
        }
    }

    private static class Product implements Function<Collection<Double>> {

        private Collection<Double> x;
        private MembershipFunction<Double> mf;

        public Product(Collection<Double> x, MembershipFunction<Double> mf) {
            this.x = x;
            this.mf = mf;
        }

        public Collection<Double> evaluate() {
            Collection<Double> result = new ArrayList<Double>();
            for (double value : x) {
                double fuzzyValue = mf.evaluate(value);
                result.add(value * fuzzyValue);
            }
            return result;
        }

        public static Collection<Double> of(Collection<Double> x,
                MembershipFunction<Double> mf) {
            return new Product(x, mf).evaluate();
        }

    }

    public static void main4(String[] args) {
        MembershipFunction<Double> trapmf = new TrapezoidalMembershipFunction(
                -10, -8, -4, 7);
        DoubleRange x = new DoubleRange(1.0, BoundType.CLOSED, 2.0, BoundType.CLOSED, 1.0);
//        for (Double d : Product.of(x.toCollection(), trapmf)) {
//            System.out.printf("%.4f ", d);
//        }
        System.out.printf("%.4f", Sum.of(Product.of(x.toCollection(), trapmf)));
    }
    
    public static void main2(String[] args) {
        MembershipFunction<Double> trapmf = new TrapezoidalMembershipFunction(
                -10, -8, -4, 7);
        DoubleRange x = new DoubleRange(1.0, BoundType.CLOSED, 2.0, BoundType.CLOSED, 1.0);
        for (Double d : Product.of(x.toCollection(), trapmf)) {
            System.out.printf("%.4f ", d);
        }
    }

}
