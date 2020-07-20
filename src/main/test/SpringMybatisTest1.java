import com.springmybatis.UserDao2Impl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext2.xml")
public class SpringMybatisTest1 {
    @Autowired
    @Qualifier(value = "userDao2")
    private UserDao2Impl userDao2;

    @Test
    public void springBatisTest1(){
        System.out.println(userDao2.getUserById(22));
    }
}
