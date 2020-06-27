# Java常用类

## 一、字符串相关的类

### 1.String类

* `String`类是不可变的字符序列，`String`对象的字符内容是存储在一个字符数组`value[]`中的
* 字符串常量存储在字符串常量池，字符串非常量对象存储在堆中
* 字符串的连接：
	* 常量与常量拼接结果在常量池
	* 变量与常量或变量拼接结果在堆中
	* 如果拼接结果调用`intern()`方法，返回值就在常量池

### 2.StringBuider类

* `StringBuilder`类是可变字符序列，可以对字符串内容进行增删，此时不会产生新的对象
* `StringBuilder`类不同于`String`，其对象必须使用构造器生成，默认初始容量为16

### 3.StringBuffer类

* `StringBuffer`类是可变字符序列，可以对字符串内容进行增删，此时不会产生新的对象
* `StringBuffer`类不同于`String`，其对象必须使用构造器生成，默认初始容量为16

### 4.String、StringBuilder、StringBuffer

* `String`：不可变字符序列
* `StringBuilder`：可变字符序列，效率高，线程不安全
* `StringBuffer`：可变字符序列，效率低，线程安全

## 二、时间日期类

* 计算机中，以1970年01月01日00:00:00为起始时间，以毫秒为计时单位用long类型来表示时间
* Date:时间类，用于表示一个特定的瞬间，精确到毫秒
* DateFormat:时间格式类，用于把Date对象转换为指定格式的字符串或把指定格式的字符串转换为Date对象
    * <font color=steelblue>SimpleDateFormat:DateFormat是一个抽象类，一般用其子类SimpleDateFormat类实现</font>
* Calender:日期类，用于日期计算。
    * <font color=steelblue>Gregor  ianCalendar:Calendar是一个抽象类，一般用其子类GregorianCalendar实现</font>
    * 月份中，0-11表示1-12月；日期中，1表示星期天，2-7表示星期一至星期六
    * <font color=steelblue>时间对象和日期对象的转换：`Calendar对象.setTime(Date对象)`</font>

## 三、Java比较器

* Java实现对象排序的方式有两种：
	* 自然排序：java.lang.Comparable
	* 定制排序：java.util.Comparator

* 自然排序
	* `Comparable`接口强制对实现它的类的对象进行整体排序，这种排序被称为类的自然排序
	* 实现`Comparable`接口的类必须实现`compareTo(Object obj)`方法，两个对象通过`compareTo()`方法的返回值来比较大小，如果当前对象大于形参对象，返回值为正整数；如果当前对象小于形参对象，返回值为负整数；如果两个对象相等，则返回零
	* 实现`Comparable`接口的对象列表或对象数组可以通过`Collections.sort()`或`Arrays.sort()`方法进行自动排序

* 定制排序
	* `Comparator`接口强制对实现它的类进行整体排序，这种排序被称为类的定制排序
	* 实现`Comparator`接口的类必须实现`compare(Object obj1,Object obj2)`方法，两个对象通过`compareTo()`方法的返回值来比较大小，如果前者大于后者，返回值为正整数；如果当前前者小于后者，返回值为负整数；如果两个对象相等，则返回零
	* 可以将`Comparator`传递给`Collections.sort()`或`Arrays.sort()`方法，从而允许在排序顺序上实现精确控制。也可以使用`Comparator`来控制某些数据结构的顺序，如有序set或有序map

## 四、System类

* `java.lang.System`类的构造器是`private`的，成员变量和成员方法是`static`的
* `System`类包含三个成员变量：`in`、`out`、`err`，分别代表标准输入流、标准输出流、标准错误输出流
* `System`类的常见成员方法：
	* `native long currentTimeMillis()`：返回当前的计算机时间
	* `void exit(int status)`：退出程序
	* `void gc()`：请求系统进行垃圾回收
	* `String getProperty(String key)`：获得系统中属性名为key对应的值

## 五、Math类

* `java.lang.Math`

## 六、BigInteger与BigDecimal

* `java.math.BigInteger`类可以表示不可变的任意精度的有符号整数
* `java.math.BigDecimal`类可以表示不可变的任意精度的有符号十进制定点数

