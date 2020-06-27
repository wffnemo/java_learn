# MySQL常用操作

## 一、插入数据

* 毫无疑问，SELECT是最常用的SQL语句了。但是，还有其他3个经常使用过的SQL语句需要学习。第一个就是INSERT，INSERT是用来插入（或添加）行到数据库表的

### 1.插入完整的行

~~~mysql
INSERT INTO customers
VALUES(NULL,'Pep E. LaPew','100 Main Street','Los Angeles','CA','90046','USA',NULL,NULL);
~~~
<font color=#228B22>上例插入一个新客户到customers表，存储到每个表列中的数据在VALUES子句中给出，对每个列都必须提供一个值。如果某个列没有值，应该使用NULL值（假定表允许对该列指定空值）。各个列必须以它们在表定义中的出现次序填充。第一列cust_id也为NULL，这是因为每次插入一个新行时，该列由MySQL自动增量</font>

* 编写依赖于特定列次序的SQL语句时很不安全的，如果这样做，有时难免会出问题

~~~mysql
INSERT INTO customers(cust_name,cust_address,cust_city,cust_state,cust_zip,cust_country,cust_contact,cust_email)
VALUES('Pep E. LaPew','100 Main Street','Los Angeles','CA','90046','USA',NULL,NULL);
~~~
此例子完成与前一个INSERT语句完全相同的工作，但在表名后的括号里明确地给出了列名。在插入时，MySQL将用VALUES列表中地相应值填入列表中地对应项。因为提供了列名，不一定按各个列出现在实际表中的次序

* 总是使用列的列表：一般不要使用没有明确给出列的列表的INSERT语句。使用列的列表能使SQL代码继续发挥作用，即使表结构发生了变化
* 省略列：如果表的定义允许，则可以在INSERT操作中省略某些列。省略的列必须满足以下某个条件
	* 该列定义为允许NULL值
	* 在表定义中给出默认值。这表示如果不给出值，将使用默认值
如果对表中不允许NULL值且没有默认值的列不给出值，则MySQL将产生一条错误信息，并且相应的行插入不成功

### 2.插入多个行

如果想要插入多行数据，可以使用多条INSERT语句，甚至一次提交它们，每条语句用一个分号结束

~~~mysql
INSERT INTO customers(cust_name,cust_address,cust_city,cust_state,cust_zip,cust_country)
VALUES('Pep E. LaPew','100 Main Street','Los Angeles','CA','90046','USA');
INSERT INTO customers(cust_name,cust_address,cust_city,cust_state,cust_zip,cust_country)
VALUES('M. Martian','42 Galaxy Way','New York','NY','11213','USA');
~~~

或者，只要每条INSERT语句的列名（和次序）相同，可以组合个语句

~~~mysql
INSERT INTO customers(cust_name,cust_address,cust_city,cust_state,cust_zip,cust_country)
VALUES('Pep E. LaPew','100 Main Street','Los Angeles','CA','90046','USA'),
('M. Martian','42 Galaxy Way','New York','NY','11213','USA');
~~~
这种方法能够提高数据库处理的性能，因为MySQL用单条INSERT语句处理多个插入比使用多条INSERT语句快

### 3.插入检索出的数据

* 可以利用INSERT将一条SELECT语句的结果插入表中

~~~mysql
INSERT INTO customers(cust_id,cust_contact,cust_email,cust_name,cust_address,cust_city,cust_state,cust_zip,cust_country)
SELECT cust_id,cust_contact,cust_email,cust_name,cust_address,cust_city,cust_state,cust_zip,cust_country
FROM custnew;
~~~
这个例子使用INSERT SELECT从custnew中将所有数据导入customers，同时导入了cust_id（假设你能够确保cust_id的值不重复）。你也可以简单地省略这个列，这样MySQL就会生成新值

* <font color=#228B22>MySQL不关心SELECT返回的列名，它使用的是列的位置，这对于从使用不同列名的表中导入数据是非常有用的</font>

## 二、更新和删除数据

### 1.更新数据

* 为了更新（修改）表中的数据，可以使用UPDATE语句，可采用两种方式使用UPDATE：
	* 更新表中特定行
	* 更新表中所有行

* 基本的UPDATE语句由3部分组成，分别是：
	* 要更新的表
	* 列名和它们的新值
	* 确定要更新行的过滤条件

更新单个列
~~~mysql
UPDATE customers
SET cust_email = 'elmer@fudd.com'
WHERE cust_id = 10005;
~~~

也可以更新多个列
~~~mysql
UPDATE customers
SET cust_name = 'The Fudds',cust_email = 'elmer@fudd.com'
WHERE cust_id = 10005;
~~~

* 在UPDATE语句中使用子查询：UPDATE语句中可以使用子查询，使得能用SELECT语句检索出的数据更新列数据
* <font color=#228B22>IGNORE关键字：如果用UPDATE语句更新多行，并且在跟新这些行中的一行或多行时出现了一个错误，则整个UPDATE操作被取消（错误发生前更新的所有行被恢复到它们原来的值）。即使是发生错误，也继续进行更新，可以使用IGNORE关键字，如：UPDATE IGNORE customers...</font>

也可以删除某个列的值， 设置它为NULL
~~~mysql
UPDATE customers
SET cust_email = NULL
WHERE cust_id = 10005;
~~~

### 2.删除数据

* 为了从一个表中删除数据,可以使用DELETE语句,可以两种方式使用DELETE:
	* 从表中删除特定的行
	* 从表中删除所有行

删除一行
~~~mysql
DELETE FROM customers
WHERE cust_id = 10006;
~~~

* DELETE不需要列名或通配符。DELETE删除整行而不是删除列，为了删除指定的列，请使用UPDATE语句
* DELETE语句从表中删除行，甚至是删除所有行，但是DELETE不删除表本身
* <font color=#228B22>如果想从表中删除所有行，不要使用DELETE，可以使用TRUNCATE TABLE语句，它完成相同的工作，但速度更快（TRUNCATE TABLE实际是删除原来的表并重新创建一个表）</font>

### 3.更新和删除的指导原则

* 除非确实打算更新和删除每一行，否则绝对不要使用不带WHERE子句的UPDATE或DELETE语句
* 保证每个表都有主键，尽可能像WHERE子句那样使用它
* 在对UPDATE或DELETE语句使用WHERE子句前，应该先用SELECT进行测试
* 使用强制实施引用完整性的数据库，这样MySQL将不允许删除具有与其他表相关联的数据的行

## 三、创建和操纵表

### 1.创建表

* 可以使用CREATE TABLE语句创建表

创建customers表
~~~mysql
CREATE TABLE customers(cust_id int NOT NULL AUTO_INCREMENT,
cust_name char(50) NOT NULL,
cust_address char(50) NULL,
cust_city char(50) NULL,
cust_state char(5) NULL,
cust_zip char(10) NULL,
cust_country char(50) NULL,
cust_contact char(50) NULL,
cust_email char(255) NULL,
PRIMARY KEY (cust_id)
)ENGINE = InnoDB;
~~~

* <font color=#228B22>处理现有的表：在创建新表时，指定的表名必须不存在，否则将出错。如果要防止意外覆盖已有的表，SQL要求必须首先手工删除该表，然后重建它，而不是简单地用创建表语句覆盖它。如果你仅想在一个表不存在时创建它，应该在表名后给出IF NOT EXIST。这样做不检查已有表的模式是否与你打算创建的表模式相匹配，它只是查看表明是否存在，并且仅在表名不存在时创建它</font>

1. 使用NULL值

* 每个表列或者是NULL列，或者是NOT NULL列，这种状态在创建时由表的定义规定

~~~mysql
CREATE TABLE orders(order_num int NOT NULL AUTO_INCREMENT,
order_date datetime NOT NULL,
cust_id int NOT NULL,
PRIMARY KEY(order_num)
)ENGINE = InnoDB;
~~~

* NULL为默认设置，如果不指定NOT NULL，则认为指定的是NULL

2. 主键再介绍

* 主键值必须唯一。即，表中的每个行必须具有唯一的主键值。如果主键使用单个列，则它的值必须唯一，如果使用多个列，则这些列的组合值必须唯一。主键只能使用不允许NULL值的列

~~~mysql
CREATE TABLE orderitems(order_num int NOT NULL,
order_item int NOT NULL,
prod_id char(10) NOT NULL,
quantity int NOT NULL,
item_price decimal(8,2) NOT NULL,
PRIMARY KEY(order_num,order_item)
)ENGINE = InnoDB;
~~~

3. 使用AUTO_INCREMENT

* AUTO_INCREMET告诉MySQL，本列每当增加一行时自动增量。每次执行一个INSERT操作时，MySQL自动对该列增量
* <font color=#228B22>每个表只允许一个AUTO_INCREMENT列，而且它必须被索引（如，通过使他称为主键）</font>
* 确定AUTO_INCREMENT值：可以使用last_insert_id()函数获得最后一个AUTO_INCREMENT值。如：`SELECT last_inser_id()`

4. 指定默认值

如果在插入行时没有给出值，MySQL允许指定此时使用的默认值。默认值用CREATE TABLE语句的列定义中的DEFAULT关键字指定

~~~mysql
CREATE TABLE oderitems(order_num int NOT NULL,
order_item int NOT NULL,
prod_id char(10) NOT NULL,
quantity int NOT NULL DEFAULT 1,
item_price decimal(8,2) NOT NULL,
PRIMARY KEY(order_num,order_item)
)ENGINE = InnoDB;
~~~


* MySQL不允许使用函数作为默认值，它只支持常量
* 使用默认值而不是NULL值：许多数据库开发人员使用默认值而不是NULL列，特别是对于用于计算或数据分组的列更是如此

5. 引擎类型

MySQL具有多种引擎，它们具有不同的功能和特性

* 几个需要知道的引擎：
	* InnoDB是一个可靠的事务处理引擎，不支持全文本搜索
	* MEMORY在功能等同于MyISAM，但由于数据存储在内存（不是磁盘）中，速度很快
	* MyISAM是一个性能极高的引擎，它支持全文本搜索，但不支持事务处理

* <font color=#228B22>引擎类型可以混用，但外键不能跨引擎</font>

### 2.更新表

更新表使用ALERT TABLE语句

为表增加列
~~~sql
ALERT TABLE vendors
ADD vend_phone CHAR(20);
~~~

为表删除列
~~~sql
ALERT TABLE vendors
DROP COLUMN vend_phones;
~~~

ALERT TABLE的一种常见用途是定义外键
~~~sql
ALERT TABLE orderitems
ADD CONSTRAINT fk_orderiems_orders
FOREIGN KEY (order_num) REFERENCES orders (order_num);
~~~

* 复杂的表结构更改一般需要手动删除过程，它涉及以下步骤：
	* 用新的列布局创建一个新表
	* 使用INSERT SELECT 语句从旧表中复制数据到新表，如果有必要，可以使用转换函数和计算字段
	* 检验包含所需数据的新表
	* 重命名旧表（如果确定，可以删除它）
	* 用旧表名重命名新表
	* 根据需要，重新创建触发器、存储过程、索引和外键

### 3.删除表

使用DROP TABLE删除表

~~~sql
DROP TABLE customers2；
~~~

### 4.重命名表

使用RENAME TABLE重命名表

重命名单个表
~~~sql
RENAME TABLE customers2 TO customers;
~~~

重命名多个表
~~~sql
RENAME TABLE bakcup_cusomers TO customers,backup_vendors TO vendors,backup_products TO products;
~~~
