import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.functor.generator.range.DoubleRange;

import fuzzy.df.CentroidDefuzzificationFunction;
import fuzzy.mf.MembershipFunction;
import fuzzy.mf.TrapezoidalMembershipFunction;


public class A {

    public static void main(String[] args) {
        MembershipFunction<Double> trapmf 
            = new TrapezoidalMembershipFunction(-10, -8, -4, 7);
        DoubleRange x = new DoubleRange(-10.0, 10.0, 0.1);
        List<Double> fuzzyValues = new ArrayList<Double>();
        for (double d : x.toCollection()) {
            fuzzyValues.add(trapmf.evaluate(d));
        }
        CentroidDefuzzificationFunction cog = new CentroidDefuzzificationFunction();
        double r = cog.defuzzify(fuzzyValues);
        System.out.printf("%.4f", r);
    }
    
}
