# JavaSE

> **现在使用的IDEA快捷键**
>
> crtl + n：查找class
> crtl + shift：查找file
> alt + f1：定位类文件位置
> crtl + x：删除本行
> crtl + d：复制本行
> alt + shift + 上下：移动该行代码
> 两下shift：全局查找
> shift + Tab：前向缩进
> shift + F6 & crtl + shift + f：批量修改和全局替换
> crtl + shift + / ：多行注释
> crtl + / ：自动注释
> crtl + 鼠标点击 ：查看源码
> crtl + alt + 方向键 ： 回到之前的光标定位
> ctrl + F12：查看该类下的所有方法
> alt + Q ：查看当前方法或类，无需鼠标滚动



## 1、基础知识

* 包名用于解决相同类名的问题，要求全部小写，一般是公司域名倒着写
* 常量大写，如MAX, MAX_VALUE
* double可以不用加d，float必须加f（默认的是双浮点型，不加f会出错）
* 数据转换中弱数据类型可直接转换为强数据类型

> byte(shor.char三者无强弱之分需要强制类型转换)<int<long<float<double

* 整数的加法默认类型为int，两个byte相加会报错
* 基本算数运算符不能用于引用数据类型的比较，只能用==，！=
* 任何数据类型用+与字符串连接，会变成新的字符串
* char可以存储一个中文汉字
* 左移几位就是乘以2的几次幂，右移即除
* if分支后如果是一条语句可省略大括号，多条不能省略，建议始终不省略，else后不加条件



## 2、分支语句、类和继承

* switch中不要把break忘掉了，default的break可以省略
* for中的表达式3是**每次循环之后再执行**
* break跳出循环（或语句块）；contiue结束当前循环开始下次循环；
* 一个文件只能有一个公共类，公共类名与文件名相同
* 构造方法没有返回类型，若类中已有构造方法，系统不会再创建默认的无参构造方法
* 基本数据类型和引用数据类型，栈和堆的概念（P29，30）
* static变量和方法(随着类的加载而加载)先于对象而存在，被类的所有对象共享，static不能与this共存（this随对象而出现）
* 类变量和类方法是指由static修饰的变量和方法，静态方法和变量推荐用**类名.** 调用
* **静态方法只能访问静态成员变量**
* 若类中所有方法都是静态的，要再类中添加一个私有的构造方法（目的是不允许创建对象）
* 类public，成员变量private，构造方法public，带get和set的成员方法public
* 子类的成员变量和成员方法与父类同名时，父类的被覆盖而不是被继承（可用super关键字来调用父类被覆盖的变量与方法）
* 子类可向父类转型反之不行，如People p1= new Student();（Student是People的子类），且向父类转型的方法被子类覆盖，参数不被覆盖仍是父类参数
* **子类转父类后，父类可强制转型为子类，转型后可调用子类中的方法和参数。但若父类声明new父类，父类无法强制转为子类。**
* final在继承中的作用（P97）
* super调用参数，成员方法，无参和有参构造方法： super.name  super.getName ；super()     super(x,x,x)
* private 本类中访问， protected 不同包下的子类能访问，不同包下的无关类不能访问（为了保护子类的其某些成员变量或方法的使用）
* public 不同包下的无关类能访问， 默认权限修饰符(不加) 同一包下的不同类



## 3、抽象、方法和接口

* 抽象方法abstract public double area();（没有大括号而是用分号结尾）

* 类不能同时用final和abstract，成员方法不能同时用static与abstract

* 接口可继承多个父接口，接口成员变量默认public static final，其成员方法默认public abstract

* 接口内只能含常量定义和抽象方法，接口不能为protected和private，接口不含构造方法

* 实现接口的类，要实现该接口及其所有重父接口的方法（或从该类父类中继承）

* 接口与抽象类的区别（P120）

* 创建内部类对象 `JInner i = new JOut.JInner();`

  或 `Jout o = new Jout(); JInner i = o.new JInner();`

* 若内部类被私有，在外部类中定义一个方法来访问私有内部类，这样的方式来被外部访问。

* **内部类访问外部类的变量，`Outter.this.name`的方式**

* 方法的返回类型不同不叫做方法重载

* 多态的作用：提高代码的复用性和可拓展性



## 4、数组

* 数组的初始化中默认值布尔型为false，引用类型为null，char‘/0’，整型0，浮点型0.0
* 基本数据类型与String可在声明时静态初始化int[] a =new int[] {1,2,3,4}，new int[]可省略
* 引用数据类型数组的成员，全部需要new（除了String）
* 二维数组空间不是连续分配的，不要求二维数组每一维的大小相同
  * 如，`int[][] a = new int[2][];a[0] = new int[3]; a[1]=new int[4];`初始化可在声明时如`int[][] a ={{2,3},{4,5},{6,7}}； `     
* 数组长度可用 数组名.length ，二维不标行数返回行数，标行数返回该行长度
* 直接打印arr，[I@地址，表示  几个[（代表维个数组）+类型@+地址
* 数组赋值之后int[] arr2 = arr1;**两者是同一个数组，同一个地址**，改变arr2的值arr1也会变
* 两个问题：1.ArrayIndexOutofBoundExceptio(数组索引越界异常)，访问超出数组范围；2.NullPointException（空指针异常），数组引用赋值arr=null再调用arr[i]时出现
* 二维数组内存图

<img src="https://s1.ax1x.com/2020/04/24/JB00Ld.png" alt="JB00Ld.png" style="zoom: 80%;" />

* 二维数组中的参数传递：（1）基本数据类型的值传递，不改变原值，因为调用后就会弹栈；（2）引用数据类型的值传递，改变传递，及时方法弹栈，但堆内存的数组值仍存在
* 数组作为方法参数`public static void getMax(int[] arr)`
* 数组作为方法返回值`public static int[] getArray(int[] arr){ ..........return arr;}`



## 5、内部类

* Math中所有方法都是静态，无法创建对象，直接用Math.XXX类名调用函数即可

* 若内部类被私有，在外部类中定义一个方法来访问私有内部类，这样的方式来被外部访问。

* 内部类访问外部类的变量，`Outter.this.name`的方式

* 内部类可以访问外部类成员包括私有的

* 实名内部类的参数调用

  ![N7Q1j1.png](https://s1.ax1x.com/2020/07/01/N7Q1j1.png)

* **匿名内部类的实质**：继承了该类或该接口的子类匿名对象；参数或返回类型为接口或抽象类时用匿名内部类来实现
  * 匿名内部类属于局部内部类，要写在**方法内部**
  * `new inter(){ };`代表实现接口，需要在{}内部覆盖接口中的所有方法 
  * 匿名内部类不能向下转型（一般只在仅有一个方法时使用）
  * 匿名内部类开发使用时当做一个参数（实质是返回的是对象），**即当一个方法其中的参数为抽象类或接口时，使用匿名内部类。**
  
* 链式编程：调用方法后还能调用方法，说明返回的是对象



## 6、Object对象

* `hashcode()`返回一个int型的地址值
* `getClass()`获取该对象的字节码文件，`getClass()`再`getName()`能获取类名
* `toString()` Object类中返回对象名以及对象引用地址，形式为 类名@地址（16进制）,一般在构造方法中重写toString()以输出属性值，若直接打印对象的引用，会默认调用toString
* `equels()` Object中是比较两个对象的地址值，没有什么意义，实际使用需要重写比较属性值



## 7、String与StringBuffer

* String（不可变字符串）
  * 构造方法
    * `String s1 = “Hello”`直接进入栈，若有新的String对象直接赋值为Hello，那么指向相同的栈
      `String s2 = new String(“Hello”)`则是指向堆
  * 成员方法
    * **判断两个String是否相同时，必须用equals()方法，因为用==比较的是地址值**
    * equals比较连个字符串内容是否相同，`equalsIgnoreCase()`比较内容是否相同（忽略大小写）
    * `contain()`比较s1是否包含s2；
    * `s1.startsWith(s2,6)`表示s1从第六个字符后是否以s2开始；
    * length方法为获取数组长度（占用Unicode的长度而不是字节数）（数组中length为属性）
    * `subString(3,7)`字符串截取（**不包含7**）
    * `charAt() indexOf()`等等字符串查找
    * `getBytes()`把字符串转成字节数组
    * `toCharArray()`把字符串转成字符数组
    * `valueOf()`将不同类型的数据（包括数组）转成字符串（底层是构造方法实现）
    * `toLowerCase() toUpperCase()`转成小写大写格式
    * `replace()`替换字符串中的字符
    * `trim()`去掉两端的空格
  * “ ”空串是一个对象，null不是一个对象是空常量
* StringBuffer
  * `sb.length()`实际大小，`sb.capacity()`理论大小
  * 对同一个new的StringBuffer再调用方法是**不会再重新创建对象**，而是不断地向原缓冲区添加字符
  * StringBuffer中的`subString()`截取方法，返回的是String
  * 添加，插入，删除，替换等方法中的都是包含头不包含尾
  * StringBuffer线程安全同步的效率低，StringBuilder线程不安全效率高
  * String虽是引用数据类型，但其与基本数据类型一样当做参数传递之后不会被改变
  * 如果某个方法只在本类中使用，则可定义为private



## 8、常用工具类

### Arrays类

* `toString()` 数组转字符串；
* `sort ()`排序（用的快排）；
* `binarySearch()` 二分法查找，找不到时返回（- 插入点 - 1）；

### 包装类：

* 目的：可以在对象中定义更多功能来操作该数据

* 除int为Integer，char为Charactor，其他基本数据类型均为首字母大写

* 均为静态，可通过类名直接调用

* 字符串转换为基本数据类型：
  * 包装类名.parse包装类名（“xxx”），如int a = Integer.parseInt(“100”)；
  * 但char不能这样转换（char中只能存储一个字符），可通过tocharArray

* 自动装箱过程中，（-128-127）为byte范围，若在这个范围内，自动装箱不会新创建对象，而是从常量池中获取，超出这个范围则创建新的对象（Day13_23）

### Pateern与Match类

* match中的`matches()`方法，试图将整个区域与模式匹配
* `find()`方法，能否在字符串中找到满足正则表达式的序列，返回true与false
* `group()`方法，返回查找到的序列

### Random类

* 以一定几率分配生成某几个数，可利用Radom中的随机几率均等规律，再利用if语句实现  如`nextInt（100）`是0-99的几率均为1%，`if(m<55){m=1}`,即生成1的概率为55%

### System类

* System（P168），获取程序运行时间，System.CurrentTimeMillis()，

* Bignteger在Math类下，构造方法参数放字符串，有加减乘除的方法

* BigDecimal 进行带小数的运算时，通过构造方法（参数列表的数值为字符串形式）

* df = new SimpleDateFormat(“yyyy年MM月dd日HH:mm:ss”) 创建日期格式化类对象

* 输出时调用 df.format(new Date())来输出时间

### Date类

* 基本使用

* ```java
  	Date date = new Date();
    	System.out.println(date.toString());
    	//运行结果：Thu Jul 02 11:02:42 CST 2020
  ```

* SimpleDateFomat类允许用户用自动以格式的日期时间格式

* ```java
  	Date dNow = new Date( );
    	SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
    	System.out.println("当前时间为: " + ft.format(dNow));
    	//运行结果：当前时间为: 2020-07-02 11:10:54
  ```

## 9、集合

### List

* 分ArrayList和LinkedList，**前者用数组实现，存取快查找慢；后者用链表实现，存取慢查找快**
* ArrayList中的contain方法底层是equals，在检测自定义类的对象时，**若没有重写equals方法，会调用Object方法。**（remove方法底层也是equals方法，用法相同）

* 泛型

  * 好处：1、提高安全性(将运行期的错误转换到编译期) ；2、 省去强转的麻烦

  * 基本使用： <>中放的必须是引用数据类型
  * 使用注意事项：前后的泛型必须一致,或者后面的泛型可以省略不写(1.7的新特性菱形泛型) 
  * 把泛型定义在方法上
    * 定义格式：`public <泛型类型> 返回类型 方法名(泛型类型 变量名)`
    * 但静态方法泛型类型必须与类的泛型类型不一致，泛型是在对象生成时才定义，而静态方法先于对象存在

* 迭代器遍历中**无法通过**集合名.remove()或.add()来删除元素，会发生并发修改异常，可以通过迭代器.remove()

* 增强for循环底层靠的是迭代器Iterator，因此其**无法通过**集合名.remove()或.add()来删除元素，会发生并发修改异常，` for(int a : arr)`，arr为被遍历的容器名，int为该容器内存储的数据类型，a为用来存放容器内每次遍历对应元素的临时变量

* 可变参数定义 `方法（数据类型... 变量名）`，若有多个变量可变参数必须为最后一个变量。实际是一个数组，如果一个方法有可变参数，且有多个参数，那么可变参数肯定是最后一个

* Arrays工具类的asList()方法，使数组转集合。但转换成的集合**无法修改**，不过能使用集合中丰富的方法。若是基本数据类型转换成集合，**会将数组中的所有元素看成集合中的一个元素**，元素内容为数组形式。为避免这种情况，可在声明基本数据类型的数组时，定义为包装类（如定义为Integer[]而不是int[]）

* 集合转数组，用Collection中的`toArray()`方法

### Set

* **分类**:HashSet无序，LinkedHashSet有序，TreeSet对集合元素自动进行排序
* set集合无索引，不可以重复，无序（存取不一致）
* 当向set集合中存储重复元素时add()方法会返回false
* **只有当两个对象HashCode值一样时才会调用equals方法，进而进行比较，因此，在向set集合的类存储自定义引用数据类型时，hashCode和equals都要重写才能去除重复对象。实际使用中自动重写即可。使不同对象的hashCode值不同，尽量少调用equals方法提高效率**
* LinkedHashSet类底层用链表实现，是HashSet的子类，保证元素唯一，但也是实现set接口的类中唯一一个保证元素顺序的
* inkedHashSet和HashSet可以用来去除集合中的重复元素
* set集合没有索引，遍历时只能使用迭代器
* TreeSet集合是用来排序的，也保证元素的唯一。Tree存储自定义类时，要实现Comparable接口，并重写compareTo方法（**返回0，只存储一个元素；返回正数，怎么存怎么取；返回负数，倒序存取，这是排序的最根本原理，底层用二叉树实现**）
* TreeSet集合的排序方法：自然排序与比较器排序
  * 自然排序：会把存入对象提升为Comparable类型，并调用对象的comepareTo()方法与集合中的元素进行比较，根据返回的int数进行排序（可以自己重写compareTo()自定义排序方式）
  * 比较器排序：声明TreeSet对象时构造函数传入Comparator，add方法会自动调用Comparator接口中的`compare(list1,list2)`方法排序，list1为新的元素，list2为已存在的元素
  * 区别：TreeSet若传入Compatator，会优先调用比较器排序的方式。对于非自定义数据类型，若要实现保留重复元素，只能用比较器重新定义(定义时让其相同时不返回0即可)，因为非自定义数据类型都已经重写了compareTo()方法，都会去除重复元素

### Map

* **分类**：HashMap无序，LinkedHashMap怎么存怎么取，TreeMap存放后进行排序

* **Set底层依赖的是Map，实际中Set把V给隐藏掉了**

* Map接口和Collection接口的不同

  * Map是双列的,Collection是单列的
  * Map的键唯一,Collection的子体系Set是唯一的

  * Map集合的数据结构值针对键有效，跟值无关;Collection集合的数据结构针对元素

* 方法：（其中的K V均为泛型）

  * `V put(K key,V value)`:添加元素。
    * 如果键是第一次存储，就直接存储元素，返回null
    * 如果键不是第一次存在，就用值把以前的值替换掉，返回以前的值

  * 删除功能

    * `void clear()`:移除所有的键值对元素

    * `V remove(Object key)`：根据键删除键值对元素，并把值返回

  * 判断功能
    * `boolean containsKey(Object key)`：判断集合是否包含指定的键
    * `boolean containsValue(Object value)`:判断集合是否包含指定的值
    * `boolean isEmpty()`：判断集合是否为空

  * 获取功能
    * `Set<Map.Entry<K,V>> entrySet()`: Entry是Map内部接口，有`getKey,getValue`方法
    * `V get(Object key)`:根据键获取值
    * `Set<K> keySet()`:获取集合中所有键的集合，再根据键调用get方法获取值
    * `Collection<V> values()`:获取集合中所有值的集合

  * 长度功能
    * `int size()`：返回集合中的键值对的个数

* 键找值思路：获取所有键的集合,遍历键的集合，获取到每一个键,根据键的集合找值

* `keySet()`方法返回值是Map中key值的集合；`entrySet()`的返回值也是返回一个Set集合，此集合的类型为`Map.Entry`。

  * Map.Entry()最高效的遍历集合的方式

  * ```java
    for (Map.Entry<String, String> entry : map.entrySet()) {
       System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
      }
    
    ```

  > entry理解：Map.entrySet()获得了entry的集合，其中的每一个entry其实就是把一个键值对作为一个类似于对象的东西，而map本身是存放这一个个对象的容器。与Map.entrySet()相比，map保留了键和值的独立性。

* Map中的键存放**自定义类**时，自定义类要重写equals()和hashcode()，不然无法保证键唯一
* TreeMap在进行排序时是**对键进行排序**，且**键为自定义类型时必须实现自然排序或者比较器排序**
* TreeMap的构造函数在传入Comparator时，**比较的是键**
* HashMap和Hashtable的区别
  
  * 前者线程不安全，能存储null键和null值；后者线程安全，不能存储null键和null值

### Collections工具类

* `void sort(Collection)`，对集合进行排序，但是集合中存放的元素必须实现comparable接口
* `void sort(Collection, Comparator<K,V>)`，对集合根据比较器进行排序。
* `int binarySearch(Collection, key)`在list中查找key并返回索引
* `T max (Collection<T>)`找出list中的最大值
* `void reserve(Collection) `对list中的元素进行反转
* `void shuffle(Collection) `对list中的元素进行随机置换，相当于用来洗牌
* 集合中的泛型固定上边界 ? extend E，目的是使某泛型的集合中能存放该泛型的子类对象
* 集合中的泛型固定下边界 ? super E, （用于TreeSet,TreeMap）在重写比较器后，目的使某泛型的集合汇总能对该泛型的子类对象进行排序。



## 10、异常处理

* try后面跟多个catch则小的异常在前面，大的异常在后面
* 编译时异常是未雨绸缪，不处理编译不给通过，如IO流的异常；运行时异常是运行时才会发生
* Throwable异常类的结果方法，toString，打印异常类名与异常信息
* `printStackTrace()`，打印异常类名与异常信息，以及异常在程序中的位置
* final，finally，finalize的区别
  * final是类、方法、变量类型关键字，类则不能被继承，方法则不能被覆盖可被继承，变量则不能被二次赋值
  * finally是异常处理语句中的最后一块
  * finalize是方法，垃圾回收用的；
* 在方法中，若catch中有return，finally执行在return之后，但执行return已建立返回路径但会先执行finally再返回
* throws写在方法上，后跟异常类名，可跟多个异常类名逗号隔开。表示该方法可能会出现该异常，但不会对异常进行处理，而是交给调用该方法的方法去处理，同时也强制调用其的main方法对其进行try catch进行捕获，否则会报错；throw用在方法内，跟的是异常对象名，只能跟一个异常对象，throw后需要在方法声明上添加throws
* 子类抛出异常不能超过父类的异常范围。父类不抛异常子类也不能throws。
* \表示转义字符，用来表示路径时要写成\\，正则表达式中也是如此



## 11、IO流

### File类

* 重命名和删除功能
  * `public boolean renameTo(File dest)`:把文件重命名为指定的文件路径
  * `public boolean delete()`:删除文件或者文件夹
  * 重命名注意事项
    * 如果路径名相同，就是改名。如果路径名不同，就是改名并剪切。
  * 删除注意事项：
    * Java中的删除不走回收站。
    * 要删除一个文件夹，请注意该文件夹内不能包含文件或者文件
* 判断功能
  * `public boolean isDirectory()`:判断是否是目录
  *  `public boolean isFile()`:判断是否是文件
  * ` public boolean exists()`:判断是否存在
  * `public boolean canRead()`:判断是否可读 Windows认为所有文件都是刻可读的
  * `public boolean setReadable(false)`,设置为可读（可写也相同）
  * `public boolean canWrite()`:判断是否可写  Windows中可设置文件为不可写
  * `public boolean isHidden()`:判断是否隐藏
  * `public boolean delete()`; 删除功能，若为文件夹，则必须为空才可删除
* 获取功能
  * `public String getAbsolutePath()`：获取绝对路径
  * `public String getPath():`获取路径 （获取构造方法中传入的路径）
  * `public String getName()`:获取名称
  * `public long length()`:获取长度。字节数
  *  `public long lastModified()`:获取最后一次的修改时间，毫秒值，可通过Date类中构造方法转成年月日，并用simpleDateFormat进行格式化
  * `public String[] list()`:获取指定目录下的所有文件或者文件夹的名称数组
  * `public File[] listFiles()`:获取指定目录下的所有文件或者文件夹的File数组
  * 

### 字节流

* 字节流结构关系(InputStream为例，OutputStream同理)

  * ```mermaid
    graph TD
    InputStream --> FileInputStream
    InputStream --> FilterInputStream
    FilterInputStream --> BufferedInputStream
    ```

* 字节流中，文件读取结束标记为-1，读取文件时可通过读取到的不是-1而循环读取下一个字节，直到读到-1停止并关闭输入流。

* 字节输入流中的read()方法，返回值是int的原因，为了**防止读到byte类型的-1**(11111111)而提前结束文件读取

* ```java
  	//创建文件输入流
    	FileInputStream file1 = new FileInputStream("xxx.txt"); 
    	//创建文件输出流  
    	FileOutputStream fos = new FileOutputStream("yyy.txt"，true);
  ```

* 在输入输出流拷贝文件：

  * 1、用`(b = fis.read()) != -1`为判断条件逐个字节复制；（实际不考虑，效率过低）
  * 2、用available()方法将文件的大小转换成一个字节数组，并用read(arr)直接读取文件中数组arr长度的字节数据；write(arr)的方式直接整个读取；（实际不考虑，因为当文件过大时可能会导致内存溢出）
  * 3、 定义小数组，`(len = fis.read(arr)) != -1`为判断条件，将文件内容每次按数组长度大小读取，并用`write(arr,0,len)`方法写入，能防止重复复制之前的内容（小数组的大小一般为1024的整数倍）

* 带Buffer的输入输出字节流如何提高效率

  *  BufferedInputStream内置了一个缓冲区(数组)：

    * > 1、从BufferedInputStream中读取一个字节时,
      >
      > 2、BufferedInputStream会一次性从文件中读取8192个, 存在缓冲区中, 返回给程序一个字节
      >
      > 3、程序再次读取时, 就不用找文件了, 直接从缓冲区中获取
      >
      > 4、直到缓冲区中所有的都被使用过, 才重新从文件中读取8192个

      

    * ![Nb8NZR.png](https://s1.ax1x.com/2020/07/02/Nb8NZR.png)

* flush()方法：用来刷新缓冲区的,刷新后可以再次写出；close()方法：刷新缓冲区，然后关闭，调用后不能再写出 
*  写出时换行，fos.write(“\r\n”.getBytes)，在写入字符串时必须将其转换为字符数组
* 异常处理，将会出异常的代码放到try中，将**关流放到finally中**

### 字符流

* 字符流结构关系(Reader为例，Writer同理)

  * ```mermaid
    graph TD
    A[Reader]-->B[BufferedReader]
    A-->C[InputStreamReader]
    B-->D[LineNumberReader]
    C-->E[FileReader]
    ```

* 在进行文档拷贝时不推荐字符流，因为要将字符转为字节，再换为字符，效率低

* 当文件**只读或只写**时推荐用字符流，此时不转字节，能防止出现半个中文

* 字符流只能拷贝纯文本，不能拷贝非纯文本文件（一次性读取的是两个字节，两个字节不一定能拼成一个字符，所以拷贝的内容会出现错误）

* 流对象尽量晚开早关，用时开用完关，节约资源

* 事实上，BufferedReader等都用到了装饰设计模式，本质是被装饰类在装饰后，原有功能得到了升级

  * > 装饰设计模式：
    >
    > 1.获取被装饰类的引用
    >
    > 2.在构造方法中传入被装饰类的对象
    >
    > 3.对原有功能进行升级
    >
    > 优点：耦合性不强，装饰类与被装饰类相关性不大，不会有父类改变子类也改变的问题

  * 实际使用：

    ```java
    BufferedReader br = new BufferedReader(new InputStreamReader
    (new FileInputStream("UTF-8.txt"), "UTF-8"));//高效的用指定的编码表UTF-8读
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter
    (new FileOutputStream("GBK.txt"), "GBK"));//高效的用指定的编码表GBK写
    ```

* FileWriter底层也用到了缓冲区，没有关流会导致没有写入。
* readLiner()方法属于BuefferedReader, newline()方法属于BufferedWriter
* **递归**：函数自己调用自己，优点是不用知道循环次数，弊端是调用次数过多会造成占内存溢出，构造方法不能递归递归的方法通常带有返回值，**在递归时需要变量来接收这个返回值**

### 其他流

* 序列输入流：将多个字节输入流整合成一个。
  * `SequenceInputStream(FileInputStream, FileInputStream)`，
  * 如果要整合更多个字节流，可在构造函数中传入枚举。
* 内存输出流：可以向内存中写数据.
  * 把内存当做一个缓冲区，写出之后一次性取出所有数据.
  * `ByteArrayOutoutStream()`可以无参或`int size`指定内存中字节数组大小
  * 内存输入流不需要关闭
* 随机访问流
  * RandomAccessFile不属于流，而是Object的子类
  * 但融合了InputStream和OutputStream的内容, 有read(), write(), seek()方法
* 对象操作流
  * 可以将一个对象写出，或读取一个对象到程序中，执行序列化和反序列化
  * `new ObjectInputStream(InputStream)`；`new ObjectOutputStream(OutputStream)`
  * 有方法write(Object)写入对象（写入后有可能会乱码，但不影响回头读取对象）,因为写入时类型为Object，因此读取时，需要强转类型，如(Person) ois.readObject
* 数据输入输出流
  * `new DataInputSteram(InputStream)`;`new DataOutputSteram(OutputStream)`
  * 有readInt()和 writeInt()方法，能够读写int数
* 打印流
  * PrintStream打印字节流，System.Out的类型为一个PrintStream
  * PrintWriter打印字符流
  * 两者都只操作数据的目的
* 标准输入输出流
  * `System.in`是InputStream默认可以从键盘输入读取字节数据
  * `System.out `是OutputStream默认可以向控制台输出字符和字节数据
  * 修改标准输入输出流：System.setIn和System.setOut

### Properties

* 实质是一个HashTable的Map集合

* 表示了一个持久的属性类，可保存在流中或从流中加载，属性列表总的每个键及其对应的值都是一个字符串。Properties不带泛型

* 特殊功能：`setProperty (String key, String value)`；`String getProperty(String key)`，根据键获取值

* 另外，因为Properties需要硬盘中配置文件与内存中对象交互，提供了load和store方法

  `load(InputStream, String comment)`**,comment是对文件列表的描述**，如果不描述，可以写null

  配置文件**中存放键值对用“=”连接**，该类文件的注释用#



## 12、多线程

* **几个概念**：

  * 线程概念：线程是程序执行的一条路径, 一个进程中可以包含多条线程
  * 多线程并行：并行就是两个任务同时运行，就是甲任务进行的同时，乙任务也在进行。(需要多核CPU)

* 多线程并发：并发是指两个任务都请求运行，而处理器只能按受一个任务，就把这两个任务安排轮流进行，由于时间间隔较短，使人感觉两个任务都在运行。其实就是CPU不断地切换任务。

* **java程序运行，jvm是多线程的，至少有java主线程和垃圾回收线程同时执行。**

* **java实现多线程的方法1**

  * 要实现多线程的类继承Thread类

  * 重写run方法，把新线程要做的事写在run方法中

  * 创建线程对象**并调用start()方法**（会自动调用父类Thread的start()）, 内部会自动执行run方法。

    ```java
    //匿名内部类的实现方法
          new Thread(){
                @Override
                public void run() { //写多线程需要执行的代码
                    for (int i = 0; i < 1000; i++) {
                        System.out.println("eee");
                    }
                }
            }.start();
    ```

    > 优点是代码简单，缺点是若该类已有父类，则不能使用。

* **实现多线程的方法2**

  * 定义类实现Runnable接口，实现run方法，把新线程要做的事写在run方法中

  * 创建自定义的Runnable的子类对象

  * 创建Thread对象, 传入Runnable

  * 调用start()开启新线程, 内部会自动调用Runnable的run()方法

    > **同一个线程不能多次start(),是非法的**

    ```java
     		ThreadTest1 test1 = new ThreadTest1();
     		Thread t = new Thread(test1);
     		t.start(); 
    ```

    ```java
    //匿名内部类实现方式
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        System.out.println("fff");
                    }
                }
            }).start();
    ```

    > 优点是子类有了父类仍旧可以实现，缺点是需要用作参数传入Thread的构造方法来启动start()

* 线程名称会默认Thread-number;如果想改变线程名称，在构造方法中传入String字符串或调用setName()方法。通过getName()来获取该名字

* 获取当前线程对象，static CurrentThread(),返回的是一个Thread对象。

* **线程睡眠**：static sleep(int 毫秒值)，方法能让当前线程睡眠，时间结束后继续执行。

* **守护线程**：setDaemon(), 设置一个线程为守护线程, , 当其他非守护线程都执行结束后, 守护线程也会自动退出

* **加入线程**： join(), 当前线程暂停, 等待指定的线程执行结束后, 当前线程再继续   

* **同步代码块**

  * 当多线程并发, 有多段代码同时执行时, 我们希望某一段代码执行的过程中CPU不要切换到其他线程工作. 这时就需要同步.

  * 如果两段代码是同步的, 那么同一时间只能执行一段, 在一段代码没执行结束之前, 不会执行另外一段代码.

  * 处理方法，用synchronized关键字来实现同步。中间的d可以是任意对象，不能是匿名内部类。不同代码块使用相同的d，就能实现同步。

    ```java
    		synchronized(d){//d有时也用this来指代调用该方法的对象	
    			//执行内容
            }  
    ```

  * *多线程并发要改变同一个数据的时候，把修改数据的代码块添加同步，能防止出错*

* **同步方法**： 在方法声明时添加synchronized关键字即可。 

  * 非静态的方法，关键字的对象是this
  * 静态的方法，同步关键字的对象是该类的**字节码对象**，类名.class

* **死锁：**同步代码块嵌套的情况下，容易出现死锁，线程一个流程没运行完没释放其中一个同步对象，另一个线程就拿不到这个同步对象，就会导致线程锁住无法进行。***因此不要对同步代码块进行嵌套***

* **以前说过的线程安全问题**

  * Vector是线程安全的,ArrayList是线程不安全的
  * StringBuffer是线程安全的,StringBuilder是线程不安全的
  * Hashtable是线程安全的,HashMap是线程不安全的
  * **Collections.synchroinzed(xxx)**能让线程不安全的转变成线程安全的。



* **多线程——单例设计模式**：保证类在内存中只有一个对象(私有构造，使用其他方法来访问对象)

  >如java中的Runtime类，即使用了单例设计模式中的饿汉式。

  * 实现方法1： 饿汉式

    ```java
    //饿汉式单例设计模式
    class HungerSingleton{
        private static HungerSingleton s = new HungerSingleton(); //为防止该对象被调用时能被修改，设为私有，并提供get方法获取对象
        private HungerSingleton(){} //私有构造方法，不让其他调用
        public static HungerSingleton getInstance(){
            return  s;
        }
    }
    ```

  * 实现方式2：懒汉式

    ```java
    //懒汉式单例设计模式，单例设计模式延迟策略
    class LazySingleton{
        private static LazySingleton s; //创建私有成员变量，仅声明引用，不创建对象
        private LazySingleton(){}
        //在调用方法的时候才创建对象
        public static synchronized LazySingleton getInstance(){  //此处加锁为了防止多线程访问方法时多次创建对象
            if(null == s){
                s = new LazySingleton();
            }
            return s;
        }
    }
    ```

  * 实现方式3：直接创建对象，但用final修饰来防止被修改

    ```java
    //直接final修饰,来解决对象能被修改的问题
    class FinalSingleton {
        public static final  FinalSingleton f = new FinalSingleton();
        private FinalSingleton(){}
    }
    ```

* **Timer类**：在指定时间执行指定任务

  * t.schedule(TimerTask,  Date, long);

    > 第一个是要执行的任务，第二格式执行的时间，第三个是如果要每个多少时间重复执行一次该任务

* **线程之间的通信**

  * 什么时候需要通信
    * 多个线程并发执行时, 在默认情况下CPU是随机切换线程的
    * 如果我们希望他们有规律的执行, 就可以使用通信，比如每个线程轮流执行一次
  * 怎么实现通信
    * 如果希望线程等待, 就调用wait() ；**同步代码块中，用那个对象锁，就用那个对象调用wait()方法**
    * 如果希望唤醒等待的线程, 就调用notify()；**如果多个线程之间通信, 需要使用notifyAll()通知所有线程, 用while来反复判断条件**
    * 这两个方法必须在同步代码中执行, 并且使用同步锁对象来调用

* **sleep()方法和wait()方法的区别**

  * 前者需要参数，后者可以不需参数
  * **sleep若在同步代码块中，不释放锁，而wait()若在同步代码块中，会把锁释放。**

* **互斥锁 ReentrantLock()**

  * 使用ReentrantLock类的lock()和unlock()方法进行同步**（代替syncronized来同步代码块，代码块开头ReentrantLock.lock(),代码块结束ReentrantLock.unlock()）**
  * 利用ReentrantLock中的需要等待的时候使用**Condition**的await()方法, 唤醒的时候用signal()方法
  * 不同的线程使用不同的Condition, 这样就能区分唤醒的时候找哪个线程了

* **线程组**：用ThreadGroup来表示线程组，可以设置整组的优先级或守护线程

* **线程的5个生命周期**：新建， 就绪(有执行资格，当前没有执行权)，阻塞(没有执行资格并且没有执行权)， 运行， 死亡

* **线程池概念**：

* **简单工厂模式(静态工厂方法模式)**:定义一个工厂类来负责创建一些类的实例

  * 优点：客户端不用负责对象的创建，从而明确了各个类的职责，并且后期维护容易。
  * 缺点：静态工厂负责所有对象创建，若创建新对象，需要不断修改工厂类，不利于后期维护

* **工厂方法模式**：工厂方法模式中抽象工厂类负责定义创建对象的接口，具体对象的创建工作由继承抽象工厂的具体类实现***简单描述，就是有一个抽象的动物工厂的interface，定义生成animal，然后存在继承该类的子工厂，如猫工厂狗工厂来创建具体的猫&狗等动物***

  * 优点：如果有新的对象增加，只需要增加一个继承animal工厂的interface的具体类即可，不影响已有的代码，后期维护容易，增强了系统的扩展性





##  13、反射

* **反射定义**：Java 反射机制在程序**运行时**，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性。这种 **动态地获取信息** 以及 **动态调用对象的方法** 的功能称为 **java 的反射机制**。

* **理解**：程序中一般的对象的类型都是在编译期就确定下来的，而 Java 反射机制可以动态地创建对象并调用其属性，这样的对象的类型在编译期是未知的。所以我们可以通过反射机制直接创建对象，即使这个对象的类型在编译期是未知的。
  反射机制很重要的一点就是“运行时”，其使得我们可以在程序运行时加载、探索以及使用编译期间完全未知的 `.class` 文件。换句话说，Java 程序可以加载一个运行时才得知对象类型的 `.class` 文件，然后获悉其完整构造，并生成其对象实体、或对其 fields（变量）设值、或调用其 methods（方法）。

* **几个理解的点**：

  * 运行程序时，运行的是java编译后的.class文件

* **反射的应用**：

  > 实质是一个框架已经确定了，你无法去改变源码，但是可以通过反射来动态地获取和修改里面的内容。/

  * 读取和修改类的私有成员变量、私有成员方法、与`final`修饰的常量，执行框图

    <img src="https://user-gold-cdn.xitu.io/2017/8/12/7a376b8628ac1343bba0715b211a531b?imageView2/0/w/1280/h/960/format/webp/ignore-error/1" style="zoom: 50%;" />

  * 利用`Class.ForName`来**读取**配置文件中所要读取的**未知的类名**，并调用空参构造创建该类的对象并使用其方法

    ```java
            //目的是通过读取config文件，来直接获取到对象，并调用对象中的方法
            BufferedReader bf = new BufferedReader(new FileReader("src\\Day23Reflection\\config.properties"));
            Class clazz = Class.forName(bf.readLine());
            Fruit f = (Fruit) clazz.getDeclaredConstructor().newInstance();
            f.squeeze();
    ```

  * 利用反射调用有参构造函数

    ```java
            Class clazz1 = Class.forName("Day10ClassTest.PackageTest1.Person");
            Constructor constructor = clazz1.getConstructor(String.class, int.class);//获取有参构造
            Person p = (Person) constructor.newInstance("张三", 23);//利用有参构造创建对象
            System.out.println(p);
    ```

  * 利用暴力反射访问私有成员变量并修改

    ```java
     	    Field f = clazz1.getDeclaredField("name");//暴力获取成员变量
            f.setAccessible(true);//去除私有权限
            f.set(p, "李四");//修改私有成员变量的值
    ```

  * 利用反射获取方法

    ```java
            //无参数方法
    		Method method1 = clazz1.getMethod("eat");
            method1.invoke(p);
    		//有参数方法
            Method method2 = clazz1.getMethod("eat", String.class);//“eat”为方法名，String.class是该方法要传入的参数
            method2.invoke(p, "香蕉");
    
    		//若为私有方法，则仍用getDeclaredMethod("")与method.setAccessible(True)来暴力调		用该方法;
    ```

  * 利用反射绕过泛型检查

    > 泛型只在编译器进行规范，运行期泛型会被擦除

    ```java
            ArrayList<Integer> arrayList = new ArrayList<>();
    		Class clazz = Class.forName("java.util.ArrayList");
            Method method = clazz.getMethod("add", Object.class);//获取add()方法
            method.invoke(arrayList, "abc");
            System.out.println(arrayList);
    ```

  * **利用反射实现动态代理**

    > 本来应该自己做的事情请别人来做，在程序运行过程中通过反射产生这个动态代理对象来做这件事。

    * `java.lang.reflect`包下的`Proxy`类和一个`InvocationHandler`接口，使用这个类和接口来生成动态代理对象。这一个代理对象，可以代理不同的类，即动态代理。调用一个类的方法时，实际上调用了代理类里面的方法。



## 14、枚举

* **枚举概述**：将变量的值一一列出，变量的值只限于列举出来的值的范围内。

* **自己实现枚举类**

  > 实质类似于单例设计模式，构造方法私有，类定义内部创建若干个对象，但外部不允许无限制创建对象。
  > enum关键字与class关键字类似，表示枚举类

  * 实现方法一：无参构造

    ```java
    class Week1{
        public static final Week1 MON = new Week1();
        public static final Week1 TUE = new Week1();
        private Week1() {}
    }
    ```

  * 实现方法二：有参构造

    ```java
    class Week2{
        public static final Week2 MON = new Week2("MON");
        public static final Week2 TUE = new Week2("TUE");
        
        private String name;
        private Week2(String name){
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }
    ```

  * 实现方法三：抽象类的有参构造实现

    ```java
    abstract class Week3{
        //因为是抽象类，无法直接创建对象，因此使用匿名内部类来实现抽象类中的show()方法
        public static final Week3 MON = new Week3("MON"){
            @Override
            public void show() {
                System.out.println("MON");
            }
        };
        public static final Week3 TUE = new Week3("TUE"){
            @Override
            public void show() {
                System.out.println("TUE");
            }
        };
    
        private String name;
        private Week3(String name){
            this.name = name;
        }
        public String getName() {
            return name;
        }
    
        abstract public void show();
    }
    ```

* **使用enum关键字实现枚举类（三种方法与上面一一对应）**

  * 一

    ```java
    enum  Week4{
        MON, TUE;
    }
    ```

  * 二

    ```java
    enum Week5{
        MON("Mon"), TUE("Tue");
    
        private String name;
        private Week5(String name){
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }
    ```

  * 三

    > 虽然存在抽象方法，但在enum关键字前可以不必加abstract

    

    ```java
    enum Week6{
        MON("Mon"){
            @Override
            public void show() {
                System.out.println("Mon");
            }
        }, TUE("Tue"){
            @Override
            public void show() {
                System.out.println("Tue");
            }
        };
    
        private String name;
        private Week6(String name){
            this.name = name;
        }
    
        abstract public void show();
    }
    ```

* **枚举注意事项**

> 1、所有枚举都是Enum的子类
> 2、枚举类的第一行必须是枚举项，逗号隔开，最后分号
> 3、枚举可以有构造其，但必须private，其默认也为private
> 4、枚举类可以有抽象方法，但枚举项必须重写该方法