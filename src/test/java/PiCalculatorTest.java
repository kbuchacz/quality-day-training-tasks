import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.io.File;
import java.util.concurrent.Future;

@RunWith(Arquillian.class)
public class PiCalculatorTest {

    @EJB
    private PiCalculator piCalculator;

    @Deployment
    public static WebArchive createDeployment()
    {
        return ShrinkWrap.create(WebArchive.class)
            .addClasses(PiCalculator.class)
            .addAsWebInfResource(new File("src/main/webapp/WEB-INF/jboss-ejb3.xml"))
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void should_calculatePi_be_called_asynchronously() throws Exception
    {
        Assert.assertEquals(((Future) piCalculator.calculatePi(10000)).isDone(), false);
    }
}
