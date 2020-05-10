# 查漏补缺

## 一、常用类

### 1.包装类

|基本数据类型|包装类|
|:-:|:-:|
|byte|Byte|
|short|Short|
|int|Integer|
|long|Long|
|float|Float|
|double|Double|
|char|Character|
|boolean|Boolean|

### 2.自动装箱与自动拆箱

* 自动装箱：可以直接把基本数据类型的变量或值直接赋给一个包装类变量或其父类（Object）
* 自动拆箱：可以直接把包装类类型的变量直接赋给一个基本类型的变量

### 3.包装类数据类型转换

* String -> 包装类：
    * 包装类.parseXXX(String str)
    * 包装类.valueOf(String str)
* 包装类 -> String：
    * String.valueOf(包装类)

### 4.包装类的比较

* 包装类变量可以直接和基本数据类型变量或值比较大小
* 包装类变量之间不能比较数值大小，只有指向同一对象时才为true

### 5.String类

* String:不可变字符序列
* 字符串的比较时==运算符和equals()的区别
* 常量池会缓存字符串直接量
* <font color=#FFA500>StringBuilder:可变字符序列，线程不安全、效率高</font>
* <font color=#FFA500>StringBuffer:可变字符序列，线程安全、效率低</font>

### 6.时间类

* 计算机中，以1970年01月01日00:00:00为起始时间，以毫秒为计时单位用long类型来表示时间
* Date:时间类，用于表示一个特定的瞬间，精确到毫秒
* DateFormat:时间格式类，用于把Date对象转换为指定格式的字符串或把指定格式的字符串转换为Date对象
    * <font color=steelblue>SimpleDateFormat:DateFormat是一个抽象类，一般用其子类SimpleDateFormat类实现</font>
* Calender:日期类，用于日期计算。
    * <font color=steelblue>Gregor  ianCalendar:Calendar是一个抽象类，一般用其子类GregorianCalendar实现</font>
    * 月份中，0-11表示1-12月；日期中，1表示星期天，2-7表示星期一至星期六
    * <font color=steelblue>时间对象和日期对象的转换：`Calendar对象.setTime(Date对象)`</font>

### 7.Math类和Random类

## 二、容器

### 1.容器与数组

* 相似点：都可以存储多个对象，对外作为一个整体存在
* 不同点：
    * 数组的长度必须在初始化时确定，且固定不变
    * 数组采用连续的存储空间，添加和删除效率低下
    * 数组无法直接保存映射关系
    * 数组缺乏封装，操作繁琐

### 2.List

* List是有序可重复的容器
    * 有序：List中的元素都有索引标记，可以根据元素的索引标记访问元素，从而精确控制这些元素
    * 可重复：List允许加入重复的元素，具体来说，List允许满足`e1.equals(e2)`的元素重复加入容器
* <font color=#B23AEE>List接口的常用实现类有：`ArrayList`、`LinkedList`、`Vector`</font>
    * <font color=#B23AEE>`ArrayList`:`ArrayList`底层使用数组实现，查询效率高、增删效率低、线程不安全</font>
    * <font color=#B23AEE>`LinkedList`:`LinkedList`底层用双向链表实现,查询效率低、增删效率高、线程不安全</font>
    * <font color=#B23AEE>`Vector`:`Vector`底层使用数组实现，相关方法都增加了同步检查，效率低、线程安全</font>

### 3.Map

* Map是用于保存具有映射关系的数据的容器
    * Map中的`Key`和`Value`都可以是任何引用类型的数据，但是`Key`值不允许重复，即同一个`Map`对象的任何两个`Key`通过`equals()`比较总是返回false
    * `Key`和`Value`之间存在单向一对一的关系，即通过指定的`Key`总能找到唯一的、确定的`Value`
* <font color=#B23AEE>Map接口的常用实现类有：`HashMap`、`TreeMap`、`HashTable`、`Properties`</font>
    * <font color=#B23AEE>`TreeMap`:`TreeMap`采用红黑二叉树实现</font>
    * <font color=#B23AEE>`HashMap`效率高,线程不安全，允许`key`或`value`为`null`;`TreeMap`效率低，线程安全，不允许`key`或`value`为`null`</font>

### 4.Set

* Set是无序不可重复的容器
	* 无序：Set中的元素没有索引，只能遍历查找元素
	* 不可重复：Set中不能存在重复的元素，具体来说，同一个Set中不能同时存在两个通过`equals()`方法比较为true的方法
* Set接口继承Collection接口，Set接口中没有新增方法。Set接口的常用实现类为：HashSet、TreeSet

## 三、泛型

### 1.泛型的本质

* 泛型的本质就是数据类型参数化，我们可以把泛型理解为数据类型的一个占位符（形式参数），即告诉编译器，在调用泛型时必须传入实际类型
* 泛型常用写法：\<T E V>
* <font color=#BF3EFF>泛型的实参不能是基本数据类型</font>
* 泛型得协变、逆变

## 四、异常

### 1. 异常机制

* Java是采用面向对象的方式来处理异常的。
    * 抛出异常：在执行一个方法时，如果发生异常，则这个方法生成一个代表该异常的对象，停止当前执行路径，并把异常对象提交给JRE
    * 捕获异常：JRE得到该异常后，寻找相应代码来处理异常。JRE在方法的调用栈中查找，从生成异常的方法开始回溯，直到找到相应的异常处理代码为止

### 2.常见异常类型

* 空指针异常(NullPointerException)
* 算术异常(ArithmeticException)
* 数组下标越界异常(ArrayIndexOutOfBoundsException)
* 类型转换异常(ClassCastException)

