# Java集合

## 一、理解集合

为了保存不确定的数据，以及保存具有映射关系的数据，Java提供了集合类。集合类也被称为容器类，所有的集合类都位于java.util包下。<font color=red>集合里只能保存对象的引用变量。</font>

Java集合类分为三大类：Set集合是无序集合，List集合是有序集合，Map集合是无序集合，Map是具有映射关系的集合

## 二、Collection和Iterator接口

`Collection`接口是`List`、`Set`和`Queue`接口的父接口，该接口里定义的方法既可以用于操作Set集合，又可以操作List和Queue集合。

### 1.使用Lambda表达式遍历集合

`Collection`接口是`Iterable`接口的子接口，继承了Iterable接口的`forEach(Consumer action)`默认方法，而forEach()遍历集合元素时，程序会依次将集合元素传给函数式接口Consumer的accept(T t)方法（该接口的唯一抽象方法），因此可以使用Lambda表达式来遍历集合元素。
<font color=red>CollectionEach.java</font>

### 2.使用Iterator遍历集合

<font color=#FF8C00>Iterator接口也是集合框架成员，其不盛装对象，主要用来遍历集合中的元素，因此Iterator对象也被称为迭代器。</font>
<font color=#FF0000>Iterator必须依赖于Collection对象，若有一个Iteration对象，则必然有一个与之关联的Collection对象。Iterator提供了两个方法来迭代访问Collection集合里的元素，并可通过remove()方法来删除集合中上一次next()方法返回的集合元素</font>
<font color=#8B008B>当使用Iterator迭代访问Collection集合元素时，Collection元素不能被改变，只有通过Iterator方法的remove()方法删除上一次next()方法返回的集合元素才可以</font>

### 3.使用Lambda表达式遍历Iterator

可以使用Lambda表达式遍历Iterator接口，Iterator的`forEachRemaining(Consumer action)`方法中Consumer接口是一个函数式接口

### 4.使用foreach循环遍历集合

可是使用foreach循环遍历集合，同样，在使用foreach循环遍历集合时，集合不能被改变

### 5.使用Predicate操作集合

Collection集合有一个`removeIf(Predicate filter)`方法，该方法将会批量删除符合filter条件的所有元素。该方法需要一个Predicate(谓词)对象作为参数，Predicate也是函数式接口，因此可使用Lambda表达式作为参数

### 6.使用Stream操作集合

略

## 三、Set集合

### 1.HashSet类

HashSet是Set接口的典型实现，大多数时候使用Set集合时就是使用这个实现类。HashSet按Hash算法来存储集合中的元素，因此具有很好的存取和查找性能

HashSet具有以下特点：

* 不能保证元素的排列顺序，顺序可能与添加顺序不同
* HashSet不是同步的，如果多个线程同时访问一个HashSet，则必须通过代码来保证其同步
* 集合元素值可以是null

<font color=#EE0000>当向HashSet集合中存入一个元素时，HashSet会调用该对象的`hashCode()`方法来得到该对象的hashCode值，然后根据该hashCode值决定该对象在hashSet中的存储位置。如果有两个元素通过`equals()`方法比较返回true，但它们的hashCode()方法返回值不相等，HashSet将会把它们存储在不同的位置，依然可以添加成功</font>

<font color=#9A32CD>也就是说，hashSet集合判断两个元素相等的条件是它们通过equals()方法比较相等，同时它们的hashCode()方法返回值也相等。改写一个类的`equals()`方法时，也要同时改写其`hashCode()`方法，规则是如果两个对象通过`equals()`方法比较相等，那么它们的hashCode值也应该相等</font>

<font color=#8B2500>重写`hashCode()`方法的一般步骤： 略</font>

<font color=#228B22>如果向HashSet中添加一个可变对象后，后面程序修改了该可变对象的实例变量，则可能导致它与集合中的其他元素相同，这就有可能导致HashSet中包含了两个相同的对象。因此向HashSet中添加对象时，必须十分小心</font>

<font color=red>HashSetTest.java</font>

### 2.LinkedHashSet类

HashSet有一个子类LinkedHashSet，LinkedHashSet集合也是根据元素的hashCode值来决定元素的存储位置，但它同时使用链表维护元素的次序，这样使得元素看起来是以插入的顺序来保存的。当遍历LinkedHashSet集合里的元素时，LinkedHashSet将会按元素的添加顺序来访问集合里的元素。

### 3.TreeSet类

略

### 4.EnumSet类

略

## 四、List集合

### 1.List集合和ListIterator接口

List接口是Collection接口的子接口，可以使用Collection接口里的全部方法。同时，与Set集合相比，List增加了根据索引来插入、替换和删除集合元素的方法。

<font color=red>ListTest.java</font>

<font color=#0000EE>List判断两个对象相等只要通过`equals()`方法比较返回true即可</font>

与Set只提供了一个`iterator()`方法不同，List还额外提供了一个`listIterator()`方法，该方法返回一个`ListIterator`对象，ListIterator接口继承了Iterator接口，提供了向前迭代的功能

### 2.ArrayList和Vector实现类

略

### 3.固定长度的List

略

## 五、Queue集合

### 1.PriorityQueue实现类

`PriorityQueue`是一个比较标准的队列实现类，因为PriorityQueue保存队列元素的顺序并不是按加入队列的顺序，而是按队列元素的大小进行重新排序，因此当调用`peek()`或`pull()`方法去除队列中的元素时，并不是取出最先进入队列的元素，而是取出队列中最小的元素。

<font color=steelblue>PriorityQueue不允许插入null元素，它还需要对队列元素进行排序，其有两种排序方式：</font>

* <font color=steelblue>自然排序：队列元素必须实现Comparable接口，且必须是同一个类的实例</font>
* <font color=steelblue>定制排序：创建队列时必须传入一个Comparable对象，不要求队列元素实现Comaparable接口</font>

### 2.Deque接口与ArrayDeque实现类

略

### 3.LinkedList实现类

略

### 4。各种线性表的性能分析

略

## 六、Map集合

Map用于保存具有映射关系的数据，因此Map集合里保存着两组值，一组值用于保存Map里的key，另外一组值用于保存Map里的value，key和value都可以是任何引用类型的数据。Map的key不允许重复。
<font color=#00CD00>Map的key集类似于Set集合，Map的value集类似于List集合，每个元素都可以根据索引来查找，只不过Map中是根据key值来提供索引</font>

### 1.Map新增的方法

略

### 2.HashMap和Hashtable实现类

略

### 3.LinkedHashMap实现类

略

### 4.使用Properties读写属性文件

略

### 5.SortedMap接口和TreeMap实现类

略

### 6.WeakHashMap实现类

略

### 7.Identity HashMap实现类

略

### 8.EnumMap实现类

略

### 9.各Map实现类的性能分析

略

## 七、HashSet和HashMap的性能选项

略

## 八、操作集合的工具类：Collections

### 1.排序操作

略

### 2.查找、替换工作

略

### 3.同步控制

略

### 4.设置不可变集合

略

### 5.Java9新增的不可变集合

略

## 九、繁琐的接口：Enumeration

略

