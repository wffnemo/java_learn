# 面向对象编程

## 方法

* 方法的参数传递机制：Java里方法的参数传递方式只有一种：值传递。即将实际参数值的副本传入方法内，而参数本身不会受到任何影响

## 访问控制权限

|修饰符|类内部|同一个包|不同包的子类|同一个工程|
|:-:|:-:|:-:|:-:|:-:|
|private|yes||||
|default|yes|yes|||
|protected|yes|yes|yes||
|public|yes|yes|yes|yes|

## 方法重写

* <font color=#BF3EFF>子类与父类中同名同参数的方法必须同时声明为非static方法（此时为重写），或者同时声明为static方法（此时不是重写）。应为static方法是属于类的，子类无法覆盖父类的方法。static修饰的方法不能被重写</font>

## 子类调用父类构造器

* 子类中所有的构造器默认都会访问父类的无参数构造器
* 当父类中没有无参数构造器时，子类构造器必须通过`this`或`super`语句调用本类或父类中相应的构造器，`this`和`super`引用不能同时出现，且必须放在构造器首行
* 如果子类构造器未显式调用父类或本类中的构造器，且父类中没有无参构造器，则编译出错

## this与super

* 因为this与super都必须位于构造器首行，所以在构造器中它们不能同时出现。因为调用子类构造器之前必须先调用父类构造器，所以它们必须位于构造器首行。Java是强数据类型语言，没有声明的变量不能对其赋值。

## 子类继承父类

* 若子类重写了父类方法，意味着子类方法覆盖了被重写的父类方法，系统不会将父类方法转移到子类中
* 对于实例变量则不存在这个现象，即使子类中定义了与父类相同的实例变量，这个实例变量依然不会覆盖父类中定义的实例变量

## 重写equals()方法原则

* 对称性
* 传递性
* 自反性
* 一致性

## 接口中的默认方法

* 若接口中定义了一个默认方法，而另外一个接口中也定义了一个同名同参数的方法（不论是否是默认方法），在实现类同时实现这两个接口时，会出现接口冲突。解决方法：实现类必须覆盖接口中的默认方法
* 若接口中定义了一个默认方法，而父类中也定义了一个同名同参数的非抽象方法，则不会出现冲突问题，此时遵守类优先原则，接口中的默认方法会被忽略

## effictively final 

* <font color=#BF3EFF>局部内部类和匿名内部类访问的局部变量必须使用final修饰，Java8开始，可以不加final修饰符，由系统默认添加，Java将这个功能称为effictively final功能</font>
