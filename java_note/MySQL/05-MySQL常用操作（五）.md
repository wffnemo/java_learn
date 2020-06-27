# MySQL常用操作

## 一、使用视图

### 1.视图

视图是虚拟的表，与包含数据的表不一样，视图只包含使用时动态检索数据的查询

此查询用来检索订购了某个特定产品的用户
~~~sql
SELECT cust_name,cust_contact
FROM customers,orders,orderitems
WHERE customers.cust_id = orders.cust_id
AND orderitems.order_num = orders.order_num
AND prod_id = 'TNT2';
~~~

假如可以把整个查询包装成一个名为`productcustomers`的虚拟表，则可以如下轻松地检索出相同的数据

~~~sql
SELECT cust_name,cust_contact
FROM productcustomers
WHERE prod_id = 'TNT2';
~~~
这就是视图的作用。`productcustomers`是一个视图，它不包含表中应该有的任何列或数据，它包含的是一个SQL查询

* 为什么使用视图
	* 重用SQL语句
	* 简化复杂的SQL操作，在编写查询后，可以方便地重用它而不必知道它的基本查询细节
	* 使用表的组成部分而不是整个表
	* 保护数据。可以给用户授予表的特定部分的访问权限而不是整个表的访问权限
	* 更改数据格式和表示。视图可返回与底层表的表示和格式不同的数据

* <font color=#228B22>重要的是知道视图仅仅是用来查看存储在别处的数据的一种设施。视图本身不包含数据，因此它们返回的数据是从其他表中检索出来的，在添加或更改这些表中的数据时，视图将返回改变过的数据</font>
* <font color=#228B22>性能问题：因为视图不包含数据，所以每次使用视图时，都必须处理查询执行时所需的任一个检索。如果你用多个联结和过滤创建了复杂的视图或嵌套了视图，可能会发现性能下降得很厉害。因此，在部署使用了大量视图的应用前，应该进行测试</font>

### 2.视图的规则和限制

* 下面是关于视图创建和使用的一些最常用的规则和限制
	* 与表一样，视图必须唯一命名
	* 对于可以创建的视图数目没有限制
	* 视图可以嵌套，即可以利用从其他视图中检索数据的查询来构造一个视图
	* ORDER BY可以用在视图中，但如果从该视图检索数据的SELECT语句也包含ORDER BY，那么该视图中的ORDER BY将被覆盖
	* <font color=#FF6EB4>视图不能索引，也不能有关联的触发器或默认值</font>
	* <font color=#FF6EB4>视图可以和表一起使用。例如，编写一条联结表 和视图的SELECT语句</font>

### 3.使用视图

* 视图用`CREATE VIEW`语句创建
* 使用`SHOW CREATE VIEW viewname`查看创建视图的语句
* 使用`DROP VIEW viewname`删除视图
* 更新视图时，可以先用DROP再用CREATE，也可以直接用CREATE OR REPLACE VIEW。如果要更新的视图不存在，则会创建一个视图，如果要更新的视图存在，则会替换原有视图

1. 利用视图简化复杂的联结

视图最常见的应用之一时隐藏复杂的SQL，这通常都会涉及联结

~~~sql
CREATE VIEW procudtcustomers AS
SELECT cust_name,cust_contact,pro_id
FROM customers,orders,orderitems
WHERE customers.cust_id = orders.cust_id
AND orderitems.order_num = orders.order_num;
~~~
可以看出，视图极大地简化了复杂SQL语句的使用。利用视图，可一次性编写基础的SQL，然后根据需要多次使用

2. 用视图重新格式化检索出的数据

视图另一个常见的用途是重新格式化检索出的数据

~~~sql
CREATE VIEW vendorlocations AS
SELECT Concat(RTrim(vend_name),'(',RTrim(vend_country),')')
FROM vendors
ORDER BY vend_name;
~~~~

3. 用视图过滤不想要的数据

~~~sql
CREATE VIEW customeremaillist AS
SELECT cust_id,cust_name,cust_email
FROM customers
WHERE cust_email IS NOT NULL;
~~~

* WHERE子句与WHERE子句：如果从视图检索数据时使用了一条WHERE子句，则两组子句将自动组合

4. 使用视图与计算字段

~~~sql
CREATE VIEW orderitemsexpanded AS
SELECT order_num,prod_id,quantity,item_price,quantity*item_price AS expanded_price
FROM orderitems;
~~~

5. 更新视图

* <font color=#228B22>通常，视图是可以更新的（即，可以对它们使用INSERT、UPDATE和DELETE）。更新一个视图将更新其基表</font>
* <font color=#228B22>但是，并非所有视图都是可更新的，如果MySQL不能正确地确定被更新的基数据，则不允许更新（包括插入和删除）。如果视图定义中有以下操作，则不能进行视图的更新：</font>
	* 分组（使用GROUP BY和HAVING）
	* 联结
	* 子查询
	* 并
	* 聚集函数
	* DISTINCT
	* 导出（计算）列
* 将视图用于检索：一般，应该将视图用于检索而不用于更新


## 二、使用存储过程

### 1.存储过程

* 存储过程简单来说，就是为以后的使用而保存的一条或多条MySQL语句的集合。可将其视为批文件，虽然它们的作用不限于批处理

### 2.使用存储过程

1. 执行存储过程

* MySQL称存储过程的执行为调用，因此MySQL执行存储过程的语句为CALL。CALL接受存储过程的名字以及需要传递给它的任意参数。

~~~sql
CALL productpricing(@pricelow,@pricehigh,@priceaverage);
~~~

2. 创建存储过程

~~~sql
CREATE PROCEDURE productpricing()
BEGIN
	SELECT AVG(prod_price) AS priceaverage
	FROM products;
END;
~~~

* 如果命令行实用程序要解释存储过程自身内的`;`字符，则它们最终不会成为存储过程的成分，这会使存储过程中的SQL语句出现语法错误。解决办法是临时更改命令行实用程序的语句分隔符。如下所示：

~~~sql
DELIMITER //
CREATE PROCEDURE productpricing()
BEGIN
	SELECT AVG(prod_price) AS priceaverage
	FROM products;
END //
DELIMITER ;
~~~

3. 删除存储过程

* 存储过程在创建之后，被保存在服务器上以供使用，直至被删除

~~~sql
DROP PROCEDURE productpricing;
~~~
这条语句删除创建的存储过程，注意后面没有（），只给出存储过程名

* 仅当存在时删除：如果过程不存在，那么DROP PROCEDURE会报错，可以使用DROP PROCEDURE IF EXISTS（如果过程不存在，也不产生错误）

4. 使用参数

* 一般，存储过程并不显示结果，而是把结果返回给指定的变量
* 变量：内存中一个特定的位置，用来临时存储数据

~~~sql
CREATE PROCEDURE productpricing(OUT p1 DECIMAL(8,2),OUT p2 DECIMAL(8,2),OUT p2 DECIMAL(8,2))
BEGIN
	SELECT MIN(prod_price)
	INTO p1
	FROM products;
	SELECT MAX(prod_price)
	INTO p2
	FROM products;
	SELECT AVG(prod_price)
	INTO p3
	FROM products;
END;
~~~
这是product pricing的修改版本。存储过程的代码位于BEGIN和END语句内，它们是一系列SELECT语句，用来检索值，然后保存到相应的变量中（通过指定INTO关键字）

* MySQL支持IN（传递给存储过程）、OUT（从存储过程传出）、INOUT（对存储过程传入和传出）类型的参数

为调用修改过的存储过程，必须指定3个变量名，如下所示：

~~~sql
CALL productpricing(@pricelow,@pricehigh,@priceaverage);
~~~

* 所有MySQL变量都必须以@开始

~~~sql
CREATE PROCEDURE ordertotal(IN onumber INT, OUT ototal DECIMAL(8,2))
BEGIN 
	SELECT SUM(item_price*quantity)
	FROM orderitems
	WHERE order_num = onumber
	INTO ototal;
END;

CALL ordertotal(20005,@total);
SELECT @total;
~~~

5. 检查存储过程

* 为获得存储过程的详细信息，使用SHOW PROCEDURE STATUS;