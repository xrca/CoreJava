import com.xrca.javaproxy.dao.UserDao;public class $Proxy implements UserDao {private UserDao target;public $Proxy(UserDao target) {this.target = target;}public void query (String p) {System.out.println("log proxy...");target.query(p);}}