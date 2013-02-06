package fuzzy.df;

import java.util.Collection;

public class CentroidDefuzzificationFunction {

    public double defuzzify(Collection<Double> fuzzy) {
        Double[] values = fuzzy.toArray(new Double[0]);
        
        double upperIntegral = 0.0;
        double lowerIntegral = 0.0;
        
        int lower = 0;
        int upper = 0;
        
        while (lower < upper) {
            if (lowerIntegral <= upperIntegral) {
                lower++;
                lowerIntegral += values[lower];
            } else {
                upper--;
                upperIntegral += values[upper];
            }
        }
        
        return 0.1 * lower;
    }
    
}
