# 异常

## 异常分类

1. 在Java语言中，将程序执行中发生的不正常情况称为“异常”，Java程序在执行过程中所发生的异常事件分为两类：
* Error：Java虚拟机无法解决的严重问题，如：StackOverflowError和OOM
* Exception：由编程错误或偶然的外在因素导致的一般性问题，如：空指针访问、试图读不存在的文件、网络连接中断、数组下标越界

2. Java异常分为编译时异常和运行时异常
* 编译时异常：编译器要求必须处置的异常，即由外在因素造成的一般性异常。编译器要求Java程序必须捕获或声明所有编译时异常
* 运行时异常：编译器不要求必须处置的异常，即由程序的逻辑错误造成的异常。java.lang.RuntimeException类及其子类都是运行时异常

## 异常处理方式

1. 异常机制
* Java是采用面向对象的方式来处理异常的
	* 抛出异常：在执行一个方法时，如果发生异常，则这个方法生成一个代表该异常的对象，停止当前执行路径，并把异常对象提交给JRE。异常对象可以由虚拟机自动生成或开发人员手动创建
	* 捕获异常：JRE得到该异常后，寻找相应代码来处理异常。JRE在方法的调用栈中查找，从生成异常的方法开始回溯，直到找到相应的异常处理代码为止。如果一个异常回到main()方法，并且main()方法也无法处理，那么程序终止运行

2. 常见异常类型
* 空指针异常(NullPointerException)
* 算术异常(ArithmeticException)
* 数组下标越界异常(ArrayIndexOutOfBoundsException)
* 类型转换异常(ClassCastException)

3. Java处理异常的方式：
* 当前方法知道如何处理该异常，程序应使用try-catch-finally块来捕获和处理该异常
* 当前方法不知道如何处理该异常，程序应在声明方法时使用throws 声明抛出该异常

4. 异常处理方式一
* 使用try...catch捕获异常
	* 把可能引发异常的代码放在try块中处理，把所用异常放在catch块中处理，一个try块后可以跟多个catch块。
	* 如果执行try块时出现异常，系统自动生成一个异常对象，该异常对象被提交给Java运行时环境，这个过程被称为抛出异常
	* Java运行时环境寻找能处理该异常对象的catch块，如果找到合适的catch块，则把该异常对象交给该catch块处理，这个过程被称为捕获异常，如果找不到对应的catch块，则运行时环境终止，Java程序退出
	* <font color=#DC143C>捕获异常时，不仅应该把Exception类对应的catch块放在最后，而且所有父类异常的catch块都应该放在子类异常catch块的后面</font>
	
* 使用finally回收资源
	* <font color=#FF83FA>异常处理语法结构中只有try块是必需的，catch块和finally块都不是必需的，但是它们俩中至少出现一个。多个catch块必须位于try块之后，finally块必须位于所有catch块之后</font>

	* <font color=#CD661D>除非在try块、catch块中调用了退出虚拟机的方法，否则不管在try块、catch块中执行怎样的代码，出现怎样的情况，异常处理的finally块总会被执行</font>

	* <font color=#B23AEE>当Java程序执行try块、catch块时遇到了return或throw语句，这两个语句都会导致方法结束，但是系统执行这两个语句不会立即结束该方法，而是先执行fianlly块，再回来执行return或throw语句。如果finally块中使用了return或throw语句，那么系统就不会再跳回去执行try块或catch块中的代码，因此应避免在finally块中使用return和throw语句</font>

* 自动回收资源的try语句
	* Java7允许在try关键字后紧跟一堆圆括号，圆括号里可以声明、初始化一个或多个资源，这些资源在try语句结束时自动关闭
		* 为保证try正常关闭资源，这些资源必须实现`Closeable`或`AutoCloseable`接口，实现这两个接口就必须实现`close()`方法
		
		* `Closeable`接口里的`close()`声明抛出了`IOException`，因此其实现类实现`close()`方法时只能抛出`IOException`或其子类。`AutoCloseable`接口里的`close()`声明抛出了`Exception`，因此其实现类实现`close()`方法时可以抛出任何类型的异常
		
	* Java9不要求try后的圆括号内声明并创建资源，只需要自动关闭的资源是final修饰或有效的final（effictively final），就可以将资源变量放在try后的圆括号内

5. 异常处理方式二
* 使用throws声明抛出异常
	* 如果当前方法不知道如何处理该异常，就应该使用`throws`声明抛出该异常，表明该方法不对异常进行处理，交由上一级调用者处理。如果`main()`方法也不知道如何处理该异常，也可以使用`throws`声明抛出异常，该异常交由JVM处理，JVM的处理方式是：打印异常跟踪栈信息，并终止程序运行
	* `throws`声明的异常类型可以是方法中产生的异常类型，也可以是其父类。`throws`声明只能在方法签名中使用，可以声明多个异常类，多个异常类间用逗号隔开
	* 重写方法不能抛出比被重写方法范围更大的异常类型

* 使用throw抛出异常
	* 如果需要在程序中自行抛出异常，则应使用`throw`语句，`throw`语句可以单独使用，throw语句抛出的不是异常类，而是一个异常实例，且每次只能抛出一个异常实例
	* <font color=#388E8E>如果`throw`语句抛出的异常是`Checked`异常，则该`throw`语句要么处于try块里，显式捕获该异常，要么放在一个带`throws`声明抛出的方法中，即把该异常交给方法的调用者处理；如果`throw`语句抛出的异常是`Runtime`异常，则该语句无须放在try块里，也无须放在带`throws`声明抛出的方法中；程序既可以显式使用try...catch来捕获该异常，也可以完全不理会该异常，把该异常交给该方法调用者处理</font>

