import javax.annotation.security.RunAs;
import javax.ejb.Stateless;
import java.util.concurrent.Callable;

@Stateless
@RunAs(Roles.USER)
public class UserGateway {
    public <V> V call(Callable<V> callable) throws Exception {
        return callable.call();
    }
}
