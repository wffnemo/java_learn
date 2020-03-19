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

