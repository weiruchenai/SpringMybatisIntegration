import com.springmybatis.UserDao2_1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext2.xml")
public class SpringMybatisTest2 {
    @Autowired
    @Qualifier(value = "userDao2_1")
    private UserDao2_1 userDao2_1;

/*    @Test
    public void springMybatisTest2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext2.xml");
        UserDao2_1 userDao2_1 = applicationContext.getBean(UserDao2_1.class);
        System.out.println(userDao2_1.getUserById(30));
    }*/

    @Test
    public void springMybatisTest2_2(){
        System.out.println(userDao2_1.getUserById(22));
    }
}
