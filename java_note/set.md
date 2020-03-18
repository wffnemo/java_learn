# Java集合

## 一、理解集合

为了保存不确定的数据，以及保存具有映射关系的数据，Java提供了集合类。集合类也被称为容器类，所有的集合类都位于java.util包下。<font color=red>集合里只能保存对象的引用变量。</font>

Java集合类分为三大类：Set集合是无序集合，List集合是有序集合，Map集合是无序集合，Map是具有映射关系的集合

## 二、Collection和Iterator接口

Collection接口是List、Set和Queue接口的父接口，该接口里定义的方法既可以用于操作Set集合，又可以操作List和Queue集合。

### 1.使用Lambda表达式遍历集合

Collection接口是Iterable接口的子接口，继承了Iterable接口的forEach(Consumer action)默认方法，而forEach()遍历集合元素时，程序会依次将集合元素传给函数式接口Consumer的accept(T t)方法（该接口的唯一抽象方法），因此可以使用Lambda表达式来遍历集合元素。