import org.apache.commons.functor.generator.range.DoubleRange;

import fuzzy.df.CentroidDefuzzificationFunction;
import fuzzy.mf.MembershipFunction;
import fuzzy.mf.TrapezoidalMembershipFunction;


public class A {

    public static void main(String[] args) {
        MembershipFunction<Double> trapmf 
            = new TrapezoidalMembershipFunction(-10, -8, -4, 7);
        DoubleRange x = new DoubleRange(-10.0, 10.0, 0.01);
        CentroidDefuzzificationFunction cog = new CentroidDefuzzificationFunction();
        double r = cog.defuzzify(x, trapmf);
        System.out.printf("%.4f", r);
    }
    
}
