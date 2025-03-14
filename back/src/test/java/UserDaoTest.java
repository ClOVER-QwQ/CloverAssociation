import com.clover.Main;
import com.clover.dao.UserDao;
import com.clover.pojo.User;
import com.clover.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Main.class)
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;

    @Test
    public void testFindUserByUserName() {
        User user = userDao.findUserByUserName("clover");
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("User not found");
        }
    }

    @Test
    public void testRegisterUser() {
        int result = userService.register("clover", "clover20040302");
        System.out.println(result);
    }
}