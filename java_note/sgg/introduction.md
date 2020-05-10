# introduction

## 一、编程语言介绍

### 1.编程语言

* 第一代语言：机器指令。以二进制代码形式存在
* 第二代语言：汇编语言。使用助记符表示一条机器指令
* 第三代语言：高级语言。目前使用的各类语言

### 2.Java语言

1. Java与C的区别：Java是一种纯粹的面向对象的程序设计语言，继承了C++语言面向对象技术的核心，舍弃了C语言中容易引起错误的指针（以引用取代）、运算符重载、多继承（以接口取代）等特性，增加了垃圾回收功能用于回收不再被引用的对象所占据的内存空间。
2. Java特点：
	* 面向对象
		* 两个基本概念：类、对象
		* 三大特性：封装、继承、多态
	* 健壮性：吸收了C/C++语言的优点，去掉了其影响程序健壮性的部分（如指针、内存的申请与释放），提供了一个相对安全的内存管理和访问机制
	* 跨平台性：可以在不同平台上运行
3. Java核心机制
	* Java虚拟机（Java Virtual Machine）
	* 垃圾回收机制（Garbage Collection）
4. JDK、JRE、JVM关系
	* JDK = JRE + 编译工具集
	* JRE = JVM + Java SE标准类库