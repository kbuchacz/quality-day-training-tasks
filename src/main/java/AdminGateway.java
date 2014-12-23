
import javax.annotation.security.RunAs;
import javax.ejb.Stateless;
import java.util.concurrent.Callable;

@Stateless
@RunAs(Roles.ADMIN)
public class AdminGateway {
    public <V> V call(Callable<V> callable) throws Exception {
        return callable.call();
    }
}
