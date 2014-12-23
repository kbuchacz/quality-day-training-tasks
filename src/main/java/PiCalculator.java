import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PiCalculator {

    private Logger logger = LoggerFactory.getLogger(PiCalculator.class);

    public Double piLiteCalculator(int iterations)
    {
        return null;
    }

    public Double piFullCalculator(int iterations)
    {
        return null;
    }

    public double calculatePi(int iterations)
    {
        double pi = 4;
        boolean plus = false;
        for (int i = 3; i < iterations; i += 2) {
            pi = plus ? (pi + 4.0 / i) : (pi - 4.0 / i);
            plus = !plus;
        }
        return pi;
    }
}
