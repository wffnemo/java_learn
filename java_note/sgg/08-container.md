# Java容器

## 一、容器概述

* 为了保存不确定的数据，以及具有映射关系的数据，Java提供了集合类。集合类也被称为容器类，所有的集合类都位于`java.util`包下，集合里只能保存对象的引用变量
* Java集合可分为`Collection`、`Map`两种体系
	* `Collection`接口：单列数据
		* `List`接口：有序、可重复的集合
		* `Set`接口：无序、不可重复的集合
	* `Map`接口：双列数据，保存具有映射关系的集合
* `Collection`接口继承树：
<img src="D:\学习笔记\java_learn\java_note\sgg\Collection接口继承树.PNG" style="zoom: 50%;" />

* `Map`接口继承树：
<img src="D:\学习笔记\java_learn\java_note\sgg\Map接口继承树.PNG" style="zoom:50%;" />

## 二、Collection接口

* `Collection`接口是`List`、`Set`和`Queue`接口的父接口，该接口里定义的方法既可以用于操作`Set`集合，又可以操作`List`和`Queue`集合

## 三、Iterator接口

* `Iterator`对象被称为迭代器，主要用于遍历`Collection`集合中的元素，不具有盛装对象的能力
* `Iterator`必须依附于`Collection`对象，若有一个`Iterator`对象，则必然有一个与之关联的`Collection`对象
* `Collection`继承了`java.lang.Iterable`接口，该接口有一个`iterator()`方法，所有实现了`Cllection`接口的集合类都有一个`iterator()`方法，用来返回一个`Iterator`接口的对象
* `Iterator`接口里定义了四个方法：
	* `boolean hasNext()`
	* `Object next()`
	* `void remove()`
	* `void forEachRemaining(Consumer action)`

## 四、Set集合

* Set是无序不可重复的集合
	* 无序：Set中的元素没有索引，只能遍历查找元素
	* 不可重复：Set中不能存在重复的元素，具体来说，同一个Set中不能同时存在两个通过`equals()`方法比较为`true`的方法

### 1.HashSet

* `HashSet`使用`Hash`算法来存储集合中的元素，因此具有很好的存取、查找、删除性能
* `HashSet`具有以下特点：
	* 不能保证元素的排列顺序
	* 不是线程安全的
	* 集合元素可以是`null`
* <font color=#228B22>`HashSet`集合判断两个元素相等的标准是两个对象通过`equals()`方法比较相等，并且两个对象的`hashCode()`方法返回值也相等。当向`HashSet`集合中存入一个元素时，`HashSet`会调用该对象的`hashCode()`方法来得到该对象的`hashCode`值，然后根据该`hashCode`值决定该对象在`HashSet`中的存储位置</font>
	* <font color=#228B22>若两个对象通过`equals()`方法比较返回为`true`，但它们的`hashCode`值不相等，`HashSet`会将它们保存在`Hash`表的不同位置</font>
	* <font color=#228B22>若两个对象通过`equals()`方法比较返回为`false`，但它们的`hashCode`值相等，`HashSet`会在`Hash`表的对应位置用链式结构来保存多个对象</font> 
	* <font color=#228B22>如果要把某个类的对象保存到`HashSet`集合中，应该重写这个类的`equals()`方法和`hashCode()`方法，且保证`equals()`方法返回`true`时，`hashCode()`方法返回值也相等</font>

### 2.LinkedHashSet

* `LinkedHashSet`是`HashSet`的子类，也是根据元素的`hashCode`值来决定元素的存储位置，但它同时使用双向链表维护元素的插入次序，当遍历`LinkedHashSet`集合里的元素时，将会按元素的添加顺序来访问集合里的元素
* `LinkedHashSet`需要维护元素的插入顺序，因此性能略低于`HashSet`的性能，但在迭代访问`Set`里的全部元素时将具有很好的性能

### 3.TreeSet

* `TreeSet`是`SortedSet`接口的实现类，`TreeSet`可以确保集合元素处于排序的状态
* <font color=#228B22>`TreeSet`并不是根据元素的插入顺序进行排序的，而是根据元素的实际值大小来进行排序的。`TreeSet`支持两种排序算法，且`TreeSet`中只能添加同一种类型的对象</font>
	* <font color=#228B22>自然排序：默认为自然排序，升序排序。集合元素必须实现`Comparable`接口，当把一个对象加入到`TreeSet`集合中时，`TreeSet`调用该对象的`comparaTo(Object obj)`方法于容器中的其他对象比较大小，然后根据红黑树找到它的存储位置，如果相等，新对象将无法添加到`TreeSet`集合中</font>
	* <font color=#228B22>定制排序：在创建`TreeSet`集合对象时，需要将一个`Comparator`对象传递给`TreeSet`构造器，由该`Comparator`对象负责元素的排序逻辑，`Comparator`是一个函数式接口，可以使用`Lambda`表达式来代替`Comparator`对象</font>
* `HashSet`、`LinkedHashSet`采用`hash`算法来存储集合中的元素，而`TreeSet`采用红黑树来存储集合中的元素

## 五、List集合

* List是有序可重复的容器
	* 有序：List中的元素都有索引标记，可以根据元素的索引标记访问元素，从而精确控制这些元素
	* 可重复：List允许加入重复的元素，具体来说，List允许满足`e1.equals(e2)`的元素重复加入容器
	* <font color=#228B22>`List`判断两个元素相等的方法是通过`equals()`方法比较返回`true`</font>
	* 与`Set`只提供一个`iterator`方法不同，`List`还额外提供了一个`listIterator()`方法，该方法返回一个`ListIterator`对象，`ListIterator`接口继承了`Iterator`接口，提供了专门操作`List`的方法：
		* `boolean hasPrevious()`
		* `Object previous()`
		* `void add(Object o)`

### 1.ArrayList

* `ArrayList`是基于数组实现的`List`类，所以`ArrayList`封装了一个动态的、允许再分配的Object[]数组，`ArrayList`对象使用`initialCapacity`参数来设置数组的长度，当向`ArrayList`添加元素超出了该数组的长度时，它们的`initialCapacity`会自动增加，默认的数组长度为10

### 2.LinkedList

* `LinkedList`时基于双向链表实现的`List`类，内部没有声明数组，而是定义了一个`Node`类型的`first`和`last`用于记录首末元素，同时，定义内部类`Node`，作为`LinkedList`保存数据的基本结构。`Node`除了保存数据，还定义了两个变量：
	* `prev`：记录前一个元素的位置
	* `next`：记录下一个元素的位置

### 3.Vector

* 与`ArrayList`类似，唯一的区别在于`Vector`是线程安全的
* <font color=#228B22>默认情况下，选择使用`ArrayList`，当插入、删除操作频繁时，使用`LinkedList`，`Vector`总比`ArrayList`慢，应尽量避免使用</font>

### 4.固定长度的List

* 操作数组的工具类`Arrays`提供了`asList(Object... a)`方法，该方法可以把一个数组或指定个数的对象转换成一个`List`集合，这个`List`集合既不是`ArrayList`实现类的实例，也不是`Vector`实现类的实例，而是`Arrays`的内部类`ArrayList`的实例。
* `ArrayList`是一个固定长度的`List`集合，程序只能遍历访问集合里的元素，不能增加、删除集合里的元素

## 六、Map集合

* `Map`用于保存具有映射关系的数据，因此`Map`集合里保存着两组值，一组用于保存`Map`里的`key`，另一组用于保存`Map`里的`value`，`key`和`value`都可以是任何引用类型的数据
* `Map`中的`key`用`Set`来存放，不允许重复
* `key`和`value`之间存在单向一对一的关系，即通过指定的`key`，总能找到唯一确定的`value`
* <font color=#228B22>`Map`提供了一个`Entry`内部类来封装`key-value`对，而计算`Entry`存储时只考虑`Entry`封装的`key`。Java先实现了`Map`，然后通过包装一个所有`value`都为`null`的`Map`就实现了`Set`集合</font>

### 1.HashMap与Hashtable

* `HashMap`和`Hashtable`都是`Map`接口的实现类，它们十分类似，区别在于：
	* `HashMap`是线程不安全的，`Hashtable`是线程安全的，所以`HashMap`比`Hashtable`的性能高一点，不推荐使用`Hashtable`
	* `Hashtable`不允许使用`null`作为`key`和`value`，但`HashMap`允许使用`null`作为`key`和`value`
* <font color=#228B22>在`HashMap`、`Hashtable`中，用作`key`的对象必须实现`hashCode()`和`equals()`方法；`HashMap`、`Hashtable`判断两个`key`相等的标准是两个`key`通过`equals()`方法比较相等，并且它们的`hashCode()`方法返回值也相等；`HashMap`、`Hashtable`判断两个`value`相等的标准是通过`equals()`方法比较返回`true`;`HashMap`、`Hashtable`不能保证元素的顺序</font>
* <font color=#228B22>与`HashSet`类似的是，尽量不要使用可变对象作为`HashMap`、`Hashtable`的`key`，如果确实需要使用可变对象，则尽量不要在程序中修改作为`key`的可变对象</font>

### 2.LinkedHashMap

* `LinkedHashMap`是`HashMap`的子类，`LinkedHashMap`也使用双向链表来维护`key-value`对的次序，该链表负责维护`Map`的迭代顺序，迭代顺序与`key-value`对的插入顺序一致
* `LinkedHashMap`需要维护元素的插入顺序，因此性能略低于`HashMap`的性能，但在迭代访问`Map`里的全部元素时将有较好的性能

### 3.TreeMap

* `TreeMap`也是一个红黑树数据结构，每个`key-value`对作为红黑树的一个节点，`TreeMap`存储`key-value`对时，需要根据`key`对节点进行排序。`TreeMap`可以保证所有的`key-value`对处于有序状态。`TreeMap`也有两种排序方式
	* 自然排序：`TreeMap`的所有`key`必须实现`comparable`接口，且所有的`key`应该是同一个类型的对象
	* 定制排序：创建`TreeMap`对象时传入一个`Comparator`对象，该对象负责对`TreeMap`中的所有`key`进行排序，采用定制排序时不要求`Map`的`key`实现`Comparable`接口

### 4.Properties

* `Properties`是`Hashtable`类的子类，用于处理属性文件
* 由于属性文件里的`key`、`value`都是字符串类型，所以`Properties`里的`key`和`value`都是字符串类型

## 七、Collections

* `Collections`是一个操作`Set`、`List`、`Map`等集合的工具类
* `Collections`中提供了大量方法对集合元素进行排序、查询和修改等操作，还提供了将集合对象设置为不可变、对集合对象实现同步控制等方法


