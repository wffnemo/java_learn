# IO流

## 一、IO流核心类

|类或接口|说明|
|:-:|:-:|
|File|文件类|
|InputStream|字节输入流|
|OutputStream|字节输出流|
|Reader|字符输入流|
|Writer|字符输出流|
|Closeable|关闭流接口|
|Flushable|刷新流接口|
|Serializable|序列化接口|

## 二、流分类

* 输入流：数据源到程序
* 输出流：程序到目的地
* 节点流：可以直接从数据源或目的地读写数据
* 处理流（包装流）：不直接连接到数据源或目的地，对其他流进行封装
* 字节流：按照字节读取数据
* 字符流：按照字符读取数据，<font color=	#FF8247>底层还是基于字节流操作</font>

## 三、File类

### 1.File类
* 文件和目录路径名的抽象表示
* <font color=#FFA500> 如何打印子目录、计算目录大小</font>
* API: `getName()`、`getPath()`、`getAbsolutePath()`、`getParent()`、`exists()`、`isFile()`、`isDirectory()`、`length()`、`createNewFile()`、`delete()`

### 2.编码与解码
* 编码：字符编码为字节码 `public byte[] getBytes(Charset charset)`
* 解码：字节码解码为字符 `public String(byte bytes[], int offset, int length, String charsetName)`
* 乱码的原因：字节码不够编码为一个字符、字符集不统一

## 四、四个抽象类

|抽象类|说明|
|:-:|:-:|
|InputStream|字节输入流父类,单位:字节|
|OutputStream|字节输出流父类,单位:字节|
|Reader|字符输入流父类,单位:字符|
|Writer|字符输出流父类,单位:字符|

* <font color=#FFA500>文件的输入、输出、复制文件、复制文件夹、字节数组到文件、文件到字节数组</font>
* API: `FileInputStream`、`FileOutputStream`、`FileReader`、`FileWriter`、`ByteArrayInputStream`、`ByteArrayOutputStream`

## 五、装饰器模式

* 装饰器包括：抽象主件、具体主件、抽象装饰器、具体装饰器

## 六、IO装饰器

* API: `BufferedInputStream`、`BufferedOutputStream`、`BufferedReader`、`BufferedWriter`、`InputStreamReader`、`OutputStreamWriter`、`DataInputStream`、`DataOutputStream`、`ObjectOuputStream`、`ObjectInputStream`、`PrintStream`、`PrintWriter`、`RandomAccessFile`
* 缓冲流用于提高性能，底层仍然需要节点流，缓冲流close()时会自动关闭节点流
* 转换流适用于将纯文本字节流转换为对应字符集的字符流
* 数据流用于操作基本类型的数据，必须先写出再读取，读取的顺序与写出的顺序一致
* 对象流用于操作对象类型的数据，也可以操作基本类型的数据 。只有实现了Serializable接口的对象才能被放入流中，必须先写出再读取，读取的顺序与写出的顺序一致。
	* 序列化：将Java对象转换为字节序列的过程
	* 反序列化：将字节序列恢复为Java对象的过程
* 打印流  `PrintStream`和`PrintWriter`的主要区别在于一个操作字节流，一个操作字符流，前者用于处理二进制文件，后者用于处理文本文件，前者当然也可以处理文本文件，但是容易产生不必要的麻烦，一般需要处理中文时使用`PrintWriter`
* 随机流：支持随机访问