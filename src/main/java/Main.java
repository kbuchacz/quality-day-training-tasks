import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class Main {

    private Logger logger = LoggerFactory.getLogger(Main.class);
    
    private Double adminPiResult;

    @EJB
    private PiCalculator calculator;

    @PostConstruct
    public void main() throws Exception
    {
        setAdminPiResult(calculator.piFullCalculator(10000000));
        logger.info("admin pi precision: " + Double.toString(getAdminPiResult()));
    }

    public Double getAdminPiResult()
    {
        return adminPiResult;
    }

    public void setAdminPiResult(Double adminPiResult)
    {
        this.adminPiResult = adminPiResult;
    }
}
