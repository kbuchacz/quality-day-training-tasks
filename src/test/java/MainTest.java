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
public class MainTest {
    
    @EJB
    private Main main;

    @Deployment
    public static WebArchive createDeployment()
    {
        return ShrinkWrap.create(WebArchive.class)
            .addClasses(Main.class)
            .addAsWebInfResource(new File("src/main/webapp/WEB-INF/jboss-ejb3.xml"))
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void should_Main_be_injected() throws Exception
    {
        Assert.assertNotNull(main);
    }

    @Test
    public void should_main_be_postConstruct_and_piResult_contain_value() throws Exception
    {
        Assert.assertNotNull(main.getPiResult());
    }

    @Test
    public void should_main_piCalculator_be_instantiated() throws Exception
    {
        Assert.assertNotNull(main.getPiCalculator());
    }
}
