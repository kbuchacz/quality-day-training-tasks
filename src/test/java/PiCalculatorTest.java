import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@RunWith(Arquillian.class)
public class PiCalculatorTest {

    @EJB
    private PiCalculator piCalculator;

    @EJB
    private UserGateway user;

    @EJB
    private AdminGateway admin;

    @Deployment
    public static WebArchive createDeployment()
    {
        return ShrinkWrap.create(WebArchive.class)
            .addClasses(AdminGateway.class, UserGateway.class, PiCalculator.class)
            .addAsWebInfResource(new File("src/main/webapp/WEB-INF/jboss-ejb3.xml"))
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void should_calculatePi_equal() throws Exception
    {
        Assert.assertEquals(callAsAdmin(Method.piFullCalculator, 10000000), 3.1415924535897797d);
    }

    @Test
    public void should_calculatePi_be_called_asynchronously() throws Exception
    {
        Assert.assertEquals(((Future) callAsUser(Method.piCalculator, 100)).isDone(), false);
    }

    @Test
    public void should_piFullCalculator_be_called_as_admin() throws Exception
    {
        Assert.assertNull(callAsUser(Method.piFullCalculator, 1));
    }

    @Test
    public void should_piLiteCalculator_be_called_as_admin_or_user() throws Exception
    {
        if (null == callAsUser(Method.piLiteCalculator, 1) || null == callAsAdmin(Method.piLiteCalculator, 1)) {
            Assert.fail();
        }
    }

    private Object callAsAdmin(final Method method, final int iterations) throws Exception
    {
        return admin.call(new Callable<Object>() {
            @Override
            public Object call() throws Exception
            {
                try {
                    switch (method) {
                        case piCalculator:
                            return piCalculator.calculatePi(iterations);
                        case piFullCalculator:
                            return piCalculator.piFullCalculator(iterations);
                        case piLiteCalculator:
                            return piCalculator.piLiteCalculator(iterations);
                        default:
                            return null;
                    }
                } catch (EJBAccessException e) {
                    return null;
                }
            }
        });
    }

    private Object callAsUser(final Method method, final int iterations) throws Exception
    {
        return user.call(new Callable<Object>() {
            @Override
            public Object call() throws Exception
            {
                try {
                    switch (method) {
                        case piCalculator:
                            return piCalculator.calculatePi(iterations);
                        case piFullCalculator:
                            return piCalculator.piFullCalculator(iterations);
                        case piLiteCalculator:
                            return piCalculator.piLiteCalculator(iterations);
                        default:
                            return null;
                    }
                } catch (EJBAccessException e) {
                    return null;
                }
            }
        });
    }

    private enum Method {
        piFullCalculator, piLiteCalculator, piCalculator
    }
}
