import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Stateless
public class PiCalculator {

    private Logger logger = LoggerFactory.getLogger(PiCalculator.class);

    private SessionContext sessionContext;

    public Double piFullCalculator(int iterations) throws ExecutionException, InterruptedException
    {
        Long threadId = Thread.currentThread().getId();
        
        //You are supposed to instantiate piFuture with promise from calculatePi function(you need to fix calculatePi return value)
        final Future<Double> piFuture = null;
        logger.info("piFullCalculator should appear before calculatePi: " + Long.toString(threadId));

        final Object monitor = new Object();
        while(!piFuture.isDone()) {
            logger.info("Waiting...");
            //noinspection SynchronizationOnLocalVariableOrMethodParameter
            synchronized (monitor) {
                monitor.wait(1000);
            }
        }

        return piFuture.get();
    }

    private Object calculatePi(int iterations)
    {
        double pi = 4;
        boolean plus = false;
        for (int i = 3; i < iterations; i += 2) {
            pi = plus ? (pi + 4.0 / i) : (pi - 4.0 / i);
            plus = !plus;
        }
        Long threadId = Thread.currentThread().getId();
        logger.info("calculatePi, thread id: " + Long.toString(threadId));
        return pi;
    }
}
