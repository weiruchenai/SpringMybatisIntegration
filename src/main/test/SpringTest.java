import com.springtest.TestImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringTest {
    //@Resource(name = "user")
    @Autowired
    @Qualifier(value = "Test")
    private TestImpl user;

    @Test
    public void springTest1(){
        user.search();
        user.update();
    }
}
