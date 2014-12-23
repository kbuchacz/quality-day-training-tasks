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
    public void should_piCalculator_be_ejb() throws Exception
    {
        Assert.assertNotNull(piCalculator);
    }

    @Test
    public void should_piFullCalculator_equal() throws Exception
    {
        Assert.assertEquals(piCalculator.piFullCalculator(1000000), 3.1415924535897797d);
    }

    @Test
    public void should_piLiteCalculator_return_null_if_more_than_100000_iterations() throws Exception
    {
        Assert.assertNull(piCalculator.piFullCalculator(100001));
    }

//    @Test
//    public void should_piLiteCalculator_equal() throws Exception
//    {
//        Assert.assertEquals(piCalculator.piFullCalculator(1000), 3.141592453589779d);
//    }
}
