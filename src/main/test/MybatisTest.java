import com.mybatistest.SqlSessionFactoryUtils;
import com.mybatistest.UserDao;
import com.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class MybatisTest {

    @Test
    public void testGetUserById(){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.getUserById(33);
        System.out.println(user);
        sqlSession.close();
    }
}
