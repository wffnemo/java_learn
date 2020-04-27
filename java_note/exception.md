# 异常处理

## 一、异常概述

Java的异常机制主要依赖于try、catch、finally、throw和throws五个关键字，其中try关键字后紧跟一个花括号，里面放置可能引发异常的代码。catch后对应异常类型和一个代码块，用于表明该catch块用于处理这种类型的代码块。多个catch块后还可以跟一个finally块用于回收在try块里打开的物理资源，异常机制会保证finally块总被执行。throws关键字用于声明方法可能抛出的异常，而throw用于抛出一个实际的异常

## 二、异常处理机制

### 1.使用try...catch捕获异常

1. 把可能引发异常的代码放在try块中处理，把所用异常放在catch块中处理，一个try块后可以跟多个catch块。
2. 如果执行try块时出现异常，系统自动生成一个异常对象，该异常对象被提交给Java运行时环境，这个过程被称为抛出异常
3. Java运行时环境寻找能处理该异常对象的catch块，如果找到合适的catch块，则把该异常对象交给该catch块处理，这个过程被称为捕获异常，如果找不到对应的catch块，则运行时环境终止，Java程序退出

### 2.异常类的继承体系

* try块和catch块的花括号不能省略
* try块中声明的变量时代码块局部变量，在catch块中不能访问该变量

<font color=#D15FEE>Java中的异常情况非为两种：`error`和`exception`，它们都继承Throwable父类。error错误程序通常无法处理，因此程序不应试图捕获error对象</font>

<font color=#DC143C>捕获异常时，不仅应该把Exception类对应的catch块放在最后，而且所有父类异常的catch块都应该放在子类异常catch块的后面</font>

### 3.多异常捕获

Java7开始，一个catch块可以捕获多种类型的异常

使用一个catch块捕获多种类型的异常时需要注意：

* 多种异常类型之间用竖线（|）隔开
* 异常变量有隐式的final修饰，因此程序不能对异常变量重新赋值

### 4.访问异常信息

如果程序需要在catch块中访问异常对象的相关信息，则可以通过访问catch块后异常形参来获得

### 5.使用finally回收资源

为了保证回收try块中打开的物理资源，异常处理机制提供了finally块，不管try块中代码是否出现异常，finally块总会被执行

<font color=#FF83FA>异常处理语法结构中只有try块是必需的，catch块和finally块都不是必需的，但是它们俩中至少出现一个。多个catch块必须位于try块之后，finally块必须位于所有catch块之后</font>

<font color=#CD661D>除非在try块、catch块中调用了退出虚拟机的方法，否则不管在try块、catch块中执行怎样的代码，出现怎样的情况，异常处理的finally块总会被执行</font>

<font color=#B23AEE>当Java程序执行try块、catch块时遇到了return或throw语句，这两个语句都会导致方法结束，但是系统执行这两个语句不会立即结束该方法，而是先执行fianlly块，再回来执行return或throw语句。如果finally块中使用了return或throw语句，那么系统就不会再跳回去执行try块或catch块中的代码，因此应避免在finally块中使用return和throw语句</font>

### 6.异常处理的嵌套

异常处理流程代码可以放在任何能放可执行代码的地方，因此完整的异常处理流程既可以放在try块里，也可以放在catch块，还可以放在finally块里

### 7.自动关闭资源的try语句

略  p377
