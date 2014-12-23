import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.concurrent.Callable;

@Startup
@Singleton
public class Main {

    private Logger logger = LoggerFactory.getLogger(Main.class);

    @EJB
    private AdminGateway admin;

    @EJB
    private UserGateway user;

    @EJB
    private PiCalculator calculator;

    @PostConstruct
    public void main() throws Exception
    {
        Long threadId = Thread.currentThread().getId();
        logger.info("Main launched, thread id: " + Long.toString(threadId));

        //Use PiCalculator methods with correct access
        user.call(new Callable<Object>() {
            @Override
            public Object call() throws Exception
            {
                final String pi = Double.toString(calculator.piLiteCalculator(100000));
                logger.info("normal user pi precision: " + pi);
                return pi;
            }
        });
        admin.call(new Callable<Object>() {
            @Override
            public Object call() throws Exception
            {
                final String pi = Double.toString(calculator.piFullCalculator(10000000));
                logger.info("admin pi precision: " + pi);
                return pi;
            }
        });
    }
}
