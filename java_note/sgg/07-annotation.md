# 注解

## 一、注解概念

* 从JDK5.0开始，Java增加了对元数据（MetaData）的支持，也就是`Annotation`
* 注解其实就是代码里的特殊标记，，这些标记可以在编译、类加载、运行时被读取，并执行相应的处理。通过使用注解，程序开发人员可以在不改变原有逻辑的情况下，在源文件中嵌入一些补充信息。代码分析工具、开发工具和部署工具可以通过这些补充信息进行验证或部署
* 注解是一个接口，程序可以通过反射来获取指定程序元素的`java.lang.annotation.Annotation`对象，然后通过`java.lang.annotation.Annotation`对象来获得注解里的元数据
* 注解能被用来为程序元素设置元数据，但注解不影响程序代码的执行。可以通过某种配套的工具对注解中的信息进行访问和处理，访问和处理注解的工具统称为`APT`(Annotation Processing Tool)

## 二、基本注解

* `java.lang`包下提供了5个基本注解
	* `@Override`：限定重写父类方法，只能修饰方法
	* `@Deprecated`：表示某个程序元素已过时，当其他程序使用已过时的类、方法或接口时，编译器会给出警告。Java9为其新增了两个属性：
		* `forRemoval`：该`boolean`类型的属性指定该API在未来是否会被删除
		* `since`：该`String`类型的属性指定该API从哪个版本开始被标记为过时
	* `@SuppressWarnings`：抑制编译器警告，被该注解修饰的程序元素（以及该程序元素中的所有子元素）取消显式指定的编译器警告，通过在括号里使用`name=value`的形式为为该注解的成员变量设置值
	* `@SafeVarargs`：抑制堆污染警告。当把一个不带泛型的对象赋给一个带泛型的变量时，往往就会发生堆污染，此时可以使用`@SafeVarargs`修饰引发该警告的方法或构造器，抑制堆污染警告
	* `FunctionalInterface`：限定为函数式接口，只能修饰接口

## 三、JDK的元注解

* 使用`@Retention`：用于指定被修饰的注解可以保留多长时间，只能用于修饰注解定义，`@Retention`包含一个`RetentionPlolicy`类型的value成员变量，value成员变量的值可以是如下三个
	* `RetentionPolicy.CLASS`：编译器将注解记录在`class`文件中，当运行Java程序时，JVM不可获取注解信息，这是默认值
	* `RetentionPolicy.RUNTIME`：编译器将注解记录在`class`文件中，当运行Java程序时，JVM也可获取注解信息
	* `RetentionPolicy.SOURCE`：注解只保留在源代码中，编译器直接丢掉这种注解

* 使用`@Target`：用于指定被修饰的注解能用于修饰哪些程序单元，只能用于修饰注解定义，`@Target`包含一个`ElementType`类型的value成员变量，value成员变量的值可以是如下几个：
	* `ElementType.ANNOTATION_TYPE`
	* `ElementType.CONSTRUCTOR`
	* `ElementType.FIELD`
	* `ElementType.LOCAL_VARIABLE`
	* `ElementType.METHOD`
	* `ElementType.PACKAGE`
	* `ElementType.PARAMETER`
	* `ElementType.TYPE`

* 使用`@Documented`：用于指定被修饰的注解将被`javadoc`工具提取成文档
* 使用`@Inherited`：用于指定被修饰的注解将具有继承性

## 四、自定义注解

* 定义新的注解类型使用`@interface`关键字，自定义注解自动继承了`java.lang.annotation.Annotation`接口
* 指定注解成员变量时可以指定其默认值，使用`default`关键字
* 根据是否包含成员变量，注解可以分为：
	* 标记注解：没有定义成员变量的注解
	* 元数据注解：包含成员变量的注解

## 五、Java8新增的重复注解

## 六、Java8新增的类型注解

