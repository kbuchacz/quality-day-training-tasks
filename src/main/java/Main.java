import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;

public class Main {

    private Logger logger = LoggerFactory.getLogger(Main.class);

    private Double piResult;
    
    @EJB
    private PiCalculator piCalculator;

    public void main()
    {
        
    }

    public Double getPiResult()
    {
        return piResult;
    }

    public void setPiResult(Double piResult)
    {
        this.piResult = piResult;
    }

    public PiCalculator getPiCalculator()
    {
        return piCalculator;
    }
}
