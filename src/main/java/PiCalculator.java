import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Stateless
public class PiCalculator {

    private Logger logger = LoggerFactory.getLogger(PiCalculator.class);

    @Resource
    private SessionContext sessionContext;

    public Double piLiteCalculator(int iterations) throws ExecutionException, InterruptedException
    {
        final PiCalculator businessObject = sessionContext.getBusinessObject(PiCalculator.class);
        final Future<Double> piFuture;
        final Double pi;
        final Long threadId = Thread.currentThread().getId();
        if (iterations > 1000000) {
            piFuture = businessObject.calculatePi(1000000);
        } else {
            piFuture = businessObject.calculatePi(iterations);
        }
        logger.info("piLiteCalculator should appear before calculatePi: " + Long.toString(threadId));

        //Do something while waiting for results

        final Object monitor = new Object();
        while(!piFuture.isDone()) {
            logger.info("Waiting...");
            //noinspection SynchronizationOnLocalVariableOrMethodParameter
            synchronized (monitor) {
                monitor.wait(1000);
            }
        }
        pi = piFuture.get();
        return pi;
    }

    public Double piFullCalculator(int iterations) throws ExecutionException, InterruptedException
    {
        final PiCalculator businessObject = sessionContext.getBusinessObject(PiCalculator.class);
        final Double pi;
        Long threadId = Thread.currentThread().getId();
        final Future<Double> piFuture = businessObject.calculatePi(iterations);
        logger.info("piFullCalculator should appear before calculatePi: " + Long.toString(threadId));

        //Do something while waiting for results

        final Object monitor = new Object();
        while(!piFuture.isDone()) {
            logger.info("Waiting...");
            //noinspection SynchronizationOnLocalVariableOrMethodParameter
            synchronized (monitor) {
                monitor.wait(1000);
            }
        }

        pi = piFuture.get();
        return pi;
    }

    @Asynchronous
    public Future<Double> calculatePi(int iterations)
    {
        double pi = 4;
        boolean plus = false;
        for (int i = 3; i < iterations; i += 2) {
            pi = plus ? (pi + 4.0 / i) : (pi - 4.0 / i);
            plus = !plus;
        }
        Long threadId = Thread.currentThread().getId();
        logger.info("calculatePi, thread id: " + Long.toString(threadId));
        return new AsyncResult<Double>(pi);
    }
}
