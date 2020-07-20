## MyBatis入门

### 1、MyBatis简介

* Mybatis是面向sql的ORM持久层框架，他封装了jdbc访问数据库的过程，我们开发，只需专注于sql语句本身的拼装，其它复杂的过程全部可以交给mybatis去完成

> **持久层框架**：持久是相对于瞬时来说的，持久层，可以理解成数据 保存在 *数据库* 或者 *硬盘* 一类可以保存很长时间的设备里面，不像放在内存中那样断电就消失了，也就是把数据存在持久化设备上。你想一下 内存中的数据 断电就没有了，硬盘的数据 是否会像内存中的数据一样断电消失么？
> **ORM**: 对象关系映射

### 2、工程搭建流程

* 1、maven添加依赖

  2、resource文件夹（projectstructure中文件夹类型**设置为resource类型**）下新建并配置SqlMapConfig.xml，即MyBatis的配置文件，**用来配置数据库连接池**

  3、创建pojo（用于将数据库返回的一个数据对象化）

  4、config文件夹下配置sql查询的映射文件

  5、SQLMapConfig.xml中加载上面的映射文件

### 3、需求解决

#### 3.1 根据用户ID查询用户信息

* 1、编写SQL语句

  ```mysql
  SELECT
  		`id`,
  		`username`,
  		`birthday`,
  		`sex`,
  		`address`
  FROM `user`
  WHERE id = 1
  ```

  

  2、配置SQL查询的映射文件，在mapper标签下添加要查询的映射内容

  > ```markdown
  > 这里#{}表示占位符，后面调用时会根据函数传入值获取到不同的id
  > ```

  ```xml
  <!--namespace用于隔离SQL语句-->
  <mapper namespace="user">
      <!--
          id : sql id，sql语句的唯一标识符
          parameterType: 入参的数据类型
          resultType: 返回结果的数据类型
  		最后一句的#{}表示占位符，后面调用时会根据函数传入值获取到不同的id
      -->
      <select id="getUserById" parameterType="int" resultType="pojo.User">
          <!--将SQL语句放在这里,此处仅写最后一行，与原SQL语句不同-->
          WHERE id = #{id} 
      </select>
  </mapper>
  ```

  

  3、编写测试程序

  ```java
  @Test
  public void testGetUserById() throws IOException {
      //创建SqlSessionFactoryBuilder对象
      SqlSessionFactoryBuilder ssfa = new SqlSessionFactoryBuilder();
      //创建核心配置文件的输入流
      InputStream inputStream = Resources.getResourceAsStream("SQLMapConfig.xml");
      //通过输入流创建SqlSessionFactory对象
      SqlSessionFactory ssf = ssfa.build(inputStream);
      //创建SqlSession对象
      SqlSession ss = ssf.openSession();
      //执行查询，字符串内容为namespace.id
      User user = ss.selectOne("user.getUserById",1);
      System.out.println(user);
      //释放资源
      ss.close();
  }
  ```

#### 3.2 根据用户名模糊查询用户列表

* 1、编写Sql语句

  ```mysql
  WHERE username LIKE '%张%' /*只写了最后一句，其余和上面相同*/
  ```

  2、配置SQL查询的映射文件，在mapper标签下添加要查询的映射内容

  ```xml
  <select id="getUserByName" parameterType="string" resultType="pojo.User">
      SELECT
      `id`,
      `username`,
      `birthday`,
      `sex`,
      `address`
      FROM `user`
      WHERE username LIKE #{username} 
  </select>
  ```

  3、编写测试程序

  > 这里已经将生成SqlSessionFactory的过程封装成了一个工具类，减少冗余

  ```java
  @Test
  public void testGetUserByName(){
  	//直接创建SqlSession对象
  	SqlSessionFactory ssf = SqlSessionFactoryUtils.getSqlSessionFactory();
  	SqlSession ss = ssf.openSession();
  	//执行查询
  	List<User> users = ss.selectList("user.getUserByName", "%张%");
  	for (User user: users) {
  		System.out.println(user);
  	}
  	ss.close();
  }
  ```




#### 3.3 插入用户

* 1、编写SQL语句

* 2、配置SQL查询的映射文件

  >此处标签为inser；此外，因为id是自动分配，因此可以不用设置

  ```xml
  <!--插入用户-->
  <insert id="insertUser" parameterType="pojo.User">
      INSERT INTO `user` ( `username`, `birthday`, `sex`, `address` )
      VALUES
      ( #{username},
      #{birthday},
      #{sex},
      #{address});
  </insert>
  ```

* 3、编写测试文件

  > 这里要记得调用SqlSession.commit()；提交事务，不然数据库不会更新

  ```java
  @Test
  public void insertUser(){
      SqlSession ss = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
      User user = new User();
      user.setUsername("张口就莱");
      user.setSex("男");
      user.setAddress("浙江杭州");
      user.setBirthday(new Date());
      ss.insert("user.insertUser", user);
      ss.commit();
      ss.close();
  }
  ```



#### 3.4 修改和删除

* 修改的映射文件

  ```xml
  <!--修改用户-->
  <update id="updateUser" parameterType="pojo.User">
      UPDATE USER
      SET username = #{username}
      WHERE
      id = #{id}
  </update>
  ```

* 删除的映射文件

  ```xml
  <delete id="deleteUser" parameterType="pojo.User">
      /*SET FOREIGN_KEY_CHECKS = 0*/
      DELETE FROM `user`
      WHERE `id` = #{id}
  </delete> 
  ```

#### 3.5 MyBatis框架的架构体系

<img src="https://s1.ax1x.com/2020/07/03/NX7z7V.png" alt="NX7z7V.png" style="zoom:50%;" />

#### 遇到的几个问题

* 时区出错，加上时区
* 还出错，&改成&amp；
* 注释中的#{}中也必须要有内容
* 去除xml中的黄线提示
* 删除时提示错误，要设置FOREIGN_KEY_CHECKS



## Mybatis DAO开发方式

> DAO: data access object 数据访问对象

### 1、传统DAO包装方式

*  思路：将查询插入等操作抽象为一个接口，并定义一个类实现接口，以实现查询插入等操作。实际使用时只需调用方法即能实现操作。*本质是把SqlSession的生成、调用、释放的过程封装了起来。*
* user.xml配置，过程与前一部分的sql映射配置文件相同
* 接口的定义

```java
interface UserDao{
    /**
     * 根据id查询用户
     * @param: Interger 用户id
     * @return: User 查询到的用户
    */
    User getUserById(Integer id);

    /**
     * 插入用户
     * @param: User
    */
    void insertUser(User user);
}
```

* 实现类

```java
public class UserDaoImpl implements UserDao{
    @Override
    public User getUserById(Integer id) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        User user = sqlSession.selectOne("user.getUserById",id);
        sqlSession.close();
        return user;
    }

    @Override
    public void insertUser(User user) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        sqlSession.insert("user.insertUser", user);
        sqlSession.commit();
    }
}
```

* 创建UserDaoImpl类实例，通过实例中的方法来直接执行增删改查等操作。

### 2、动态代理DAO包装

> **动态代理开发规则**：
> 1、只有接口没有实现类
> 2、mapper中的namespace必须是接口的全路径名
> 3、接口的方法名必需与mapper映射文件的sql id一致
> 4、接口的输入参数类型必需与映射文件的parameterType类型一致
> 5、接口的返回类型必须与映射文件的resultType类型一致

* 声明一个接口命名为UserMapper，方法内容和传统DAO的接口相同（同上）
* UserMapper.xml配置，注意namespace，sql的id，parameterType以及resultType的声明要遵守上面的规则
* 创建测试文件，在测试文件中通过动态代理来实现DAO

> 这里通过`UserDao userDao = sqlSession.getMapper(UserDao.class);`来动态获取接口对象，并直接调用方法（内部工作由Mybatis自动完成）

```java
public void testGetUserById(){
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    UserDao userDao = sqlSession.getMapper(UserDao.class);
    User user = userDao.getUserById(33);
    System.out.println(user);
    sqlSession.close();
}
```



## Mybatis 相应知识点

### 1、输入输出映射

* parameterType（输入类型）：

  * 简单类型，int ，string...

  * 简单pojo类型，\#{}或者${}括号中的值为pojo属性名称。
  * 包装pojo类型，包装pojo即将user对象为单位的插入查找等方法包装到一个包装类里去

* resultType（输出类型）
  * 简单类型，如查询用户总记录数，返回一个int值
  
  * reslutMap
  
    * 适用情况，当pojo的成员变量和数据库中的**因java和数据库的命名规范导致字段名不一致时**（如pojo为userId，但数据库字段名为user_id），**数据调用可能会出错**，这时可以通过resultMap来通过映射将两者关联起来。相应的mapper映射文件内容：
  
      ```xml
          <resultMap id="orderListResultMap" type="pojo.Order">
              <!--id用于映射主键，property为pojo的变量名，column为数据库的字段名-->
              <id property="id" column="id"/>
              <!--result用于映射普通字段-->
              <result property="userId" column="user_id"/>
              <result property="number" column="number"/>
              <result property="creattime" column="creattime"/>
              <result property="note" column="note"/>
          </resultMap>
          <select id="getOrderResultMap" resultMap="orderListResultMap">
              SELECT
      		`id`,
      		`user_id`,
      		`number`,
      		`createtime`,
      		`note`
              FROM `order`
          </select>
      ```
  

### 2、动态SQL

* 

### 3、关联查询

* 

## Mybatis 整合Spring

### 1、整体思路与步骤

* 思路
  * 1、SqlSessionFactory对象应该放到spring容器中作为单例存在。
  * 2、传统dao的开发方式中，应该从spring容器中获得sqlsession对象。
  * 3、Mapper代理形式中，应该从spring容器中直接获得mapper的代理对象。
  * 4、数据库的连接以及数据库连接池事务管理都交给spring容器来完成。

* 步骤
  * 1、创建一个maven工程。
  
  * 2、maven添加依赖
  
  * 3、mybatis的配置文件sqlmapConfig.xml
  
  * 4、编写Spring的配置文件applicationContext.xml
    
    * （1）数据库连接及连接池
    
      ```xml
      <!--加载JDBC配置文件-->
      <context:property-placeholder location="jdbc.properties"/>
      <!--数据库连接池-->
      <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
          <property name="url" value="${jdbc.url}"/>
          <property name="driverClassName" value="${jdbc.driver}"/>
          <property name="username" value="${jdbc.username}"/>
          <property name="password" value="${jdbc.password}"/>
          <!--连接池最大连接数与最大空闲数-->
          <property name="maxActive" value="10"/>
          <property name="maxIdle" value="5"/>
      </bean>
      ```
    
      其中`jdbc.properties`内容
    
      ```properties
      jdbc.driver=com.mysql.cj.jdbc.Driver
      jdbc.url=jdbc:mysql://localhost:3306/mybatistest?characterEncoding=utf-8&serverTimezone=UTC
      jdbc.username=root
      jdbc.password=Wdc82563815
      ```
    
    * （2）将sqlsessionFactory对象配置到spring容器中，mybatis-spring整合的包里有`SqlSessionFactoryBean`对象，直接将其配置到sping配置文件中去，并对其内部的两个关键属性赋值
    
      ```xml
      <!--配置SqlSessionFactory-->
      <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
          <!--数据源,ref即数据库连接池的id-->
          <property name="dataSource" ref="dataSource"/>
          <!-- mybatis核心配置文件 -->
          <property name="configLocation" value="classpath:SQLMapConfig2.xml"/>
      </bean>
      ```
    
      
    

### 2、传统Dao开发（需要接口的实现类）

* 创建UserDao2，编写user2.xml映射文件，将映射文件添加到SQLMapConfig2.xml

* 编写UserDao2Impl实现类，==关键是要继承`SqlSessionDaoSupport`==，原因是其内部有`getSqlSession()`方法来获取`SqlSession`对象（以根据id查找用户的方法为例）

  ```java
  public class UserDao2Impl extends SqlSessionDaoSupport implements UserDao2{
      @Override
      public User getUserById(Integer id) {
          //SqlSessionDaoSupport中有获取SqlSession的方法
          SqlSession sqlSession = super.getSqlSession();
          //查询用户并返回
          User user = sqlSession.selectOne("UserDao2.getUserById", id);
          return user;
      }
  }
  ```

* 在applicationContext2.xml中添加bean

  ```xml
  <!--传统Dao开发方式-->
  <bean class="com.springmybatis.UserDao2Impl">
      <!--sqlSessionFactory属性来自其父类SqlSessionDaoSupport，
              为其注入已经配置好的工厂类，ref为上面的id-->
      <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
  </bean>
  ```

* 编写测试类

### 2、动态Dao开发（无需dao接口的实现类）

* 创建并编写userMapper2.xml映射文件，将mapper添加到Mybatis核心配置文件中

* 在Spring配置文件中配置

  ```xml
  <!--动态dao开发方式-->
  <bean id="mapperScannerConfigurer" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
      <property name="basePackage" value="com.springmybatis"/>
      <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
  </bean>
  ```

* **编写测试类**

  * 不使用注解时，通过`applicationContext.getBean(UserDao2_1.class)`来获取dao对象，注意这里的`UserDao2_1.class`，此前参数为类的bean id，这里动态DAO方式直接通过.class来动态获取接口对象

    ```java
    @Test
    public void springMybatisTest2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext2.xml");
        UserDao2_1 userDao2_1 = applicationContext.getBean(UserDao2_1.class);
        System.out.println(userDao2_1.getUserById(30));
    }
    ```

  * 使用注解时，只需在dao接口类的上面添加`@Repository(value = "userDao2_1")`，然后通过对象属性注入即可使用

    ```java
    @Autowired
    @Qualifier(value = "userDao2_1")
    private UserDao2_1 userDao2_1;
    ```

    







问题

1、找不到SqlSessionDaoSupport

```
Error:(9, 8) java: 无法访问org.springframework.dao.support.DaoSupport
  找不到org.springframework.dao.support.DaoSupport的类文件
```

原因，maven引入spring-tx包

2、创建SqlSessionFactory异常

```
 org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'sqlSessionFactory' defined in class path resource [applicationContext2.xml]: Error setting property values; nested exception is org.springframework.beans.PropertyBatchUpdateException
```

原因：maven引入spring-jdbc

3、JDBC连接异常

```
Error querying database.  Cause: org.springframework.jdbc.CannotGetJdbcConnectionException: Failed to obtain JDBC Connection; nested exception is org.apache.commons.dbcp.SQLNestedException: Cannot create PoolableConnectionFactory (Cannot load connection class because of underlying exception: com.mysql.cj.exceptions.WrongArgumentException: Malformed database URL, failed to parse the connection string near ';serverTimezone=UTC'.)
### The error may exist in mybatis/user2.xml
### The error may involve UserDao2.getUserById
### The error occurred while executing a query
### Cause: org.springframework.jdbc.CannotGetJdbcConnectionException: Failed to obtain JDBC Connection;
```

原因：在xml中连接池中url的value中的`&`是实体字符，改成了`&amp;`我直接将这个url复制到了`jdbc.properties`中，出现这个问题，将`amp;`去掉即可



