# 网络编程

## 一、网络概述

* 国际标准化组织于1978年提出`OSI(Open System Interconnection)`，即开放系统互连参考模型
* 开放系统互连参考模型把计算机网路网络分成物理层、数据链路层、网路层、传输层、会话层、表示层、应用层七层
* `IP(Internet Protocol)`协议又称为互联网协议，`TCP(Transmission Control Protocol)`协议又称为传输控制协议
* `TCP/IP`协议将网络分成物理+数据链路层、网络层、传输层、应用层四层
* `IP`地址用于唯一地标识网络中的一个通信实体，`IP`地址是一个32位整数
	* `IP`地址分类方式一：`IPV4`和`IPV6`
		* `IPV4`：4个字节组成，分成4个8位二进制数
		* `IPV6`：16个字节组成，分成8个16位二进制数
	* `IP`地址分类方式二：公网地址（万维网使用）和私有地址（局域网使用）
* 一个通信实体可以有多个通信程序同时提供网络服务，此时需要使用端口，端口是一个16位整数，用于表示数据交给哪个通信程序处理
	* 公认端口：0-1023，被预定的服务通信占用
	* 注册端口：1024-49151，分配给进程或应用程序
	* 动态/私有端口：49152-65535，应用程序动态端口
* `IP`地址与端口号组合为网络套接字：`Socket`

## 二、Java的基本网络支持

* 传输层中有两个重要协议：`TCP(Transmission Control Protocol)`和`UDP(User Datagram Protocol)`，传输控制协议和用户数据报协议
	* `TCP`：可靠，传输大小无限制，但是需要时间建立连接，差错控制开销大
	* `UDP`：不可靠，传输大小限制在64KB内，不需要建立连接，差错控制开销小

### 1.使用InetAddress类

* `java.net.InetAddress`类用于代表IP地址，`InetAddress`没有构造器，可以通过`getByName(String host)`或`getByAddress(byte[] addr)`静态方法获得`InetAddress`实例

### 2.基于TCP协议的网络编程

* 服务器程序工作过程：
	* 调用`ServerSocket(int port)`：用指定的端口创建一个`ServerSocket`对象，用于监听来自客户端的`Socket`连接
	* 调用`Socket accept()`：监听连接请求，如果接收到一个连接请求，返回一个与客户端`Socket`对应的`Socket`，否则一致处于等待状态
	* 调用`InputStream getInputSteam()`或`OutputStream getOutputStream()`：饭后该`Socket`对象的输入输出流
	* 调用`close()`：关闭`ServerSocket`和`Socket`对象

### 3.基于UDP协议的网络编程

* 建立发送端、接收端`DatagramSocket`
* 建立数据包`DatagramPacket`
* 调用`send(DatagramPacket p)`或`receive(DatagramPacket p)`来发送或接受数据包
* 关闭`DatagramSocket`

