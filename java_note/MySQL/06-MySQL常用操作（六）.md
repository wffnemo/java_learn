# MySQL常用操作

## 一、游标

* 游标只能用于存储过程（和函数）

* 游标使用步骤：
	* 使用游标前，必须声明它
	* 声明后，游标必须打开以供使用，这个过程用前面定义SELECT语句把数据实际检索出来
	* 对于填有数据的游标，根据需要取出各行
	* 游标使用结束后，必须关系游标

声明游标后，可根据需要频繁打开和关闭游标。在游标打开后，可根据需要频繁地执行取操作

1. 创建游标

~~~sql
CREATE PROCEDURE processorders()
BEGIN
	DECLARE ordernumbers CURSOR
	FOR
	SELECT order_num FROM orders;
END;
~~~

2. 打开和关闭游标

~~~sql
OPEN ordernumbers;

CLOSE ordernumbers;
~~~

3. 使用游标数据

~~~sql
CREATE PROCEDURE processorders()
BEGIN
	DECLARE done BOOLEAN DEFAULT 0;
	DECLARE o INT;
	DECLARE ordernumbers CURSOR
	FOR
	SELECT order_num FROM orders;
	DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done=1;
	OPEN ordernumbers;
	REPEAT
		FETCH ordernumbers INTO o;
	UNTIL done END REPEAT;
	CLOSE ordernumbers;
END;
~~~

## 二、使用触发器

* 触发器是MySQL响应以下任意语句而自动执行地一条MySQL语句（或位于BEGIN和END语句之间地一组语句）：
	* DELETE
	* INSERT
	* UPDATE

* 其他语句不支持触发器

### 1.创建触发器

* 创建触发器需要给出4条信息
	* 唯一的触发器名
	* 触发器关联的表
	* 触发器应该响应的活动
	* 触发器何时执行

* 保持每个数据库的触发器唯一

~~~sql
CREATE TRIGGER newproduct AFTER INSERT ON products
FOR EACH ROW SELECT 'Product added';
~~~

* 只有表支持触发器，视图和临时表都不支持触发器
* 触发器按每个表每个事件每次地定义，每个表每个事件每次只允许一个触发器。因此，每个表最多支持6个触发器（每条INSERT、DELETE、UPDATE的之前和之后）。单一触发器不能与多个事件或多个表关联
* 触发器失败：如果BEFORE触发器失败，则MySQL将不执行请求的操作，如果BEFORE触发器或语句本身失败，MySQL将不执行AFTER触发器（如果有的话）

### 2.删除触发器

~~~sql
DROP TRIGGER newproduct;
~~~

* 触发器不能更新或覆盖，为了修改一个触发器，必须删除它，然后再重新创建

### 3.使用触发器

1. INSERT触发器
	* 在INSERT触发器内，可引用一个名为NEW的虚拟表，访问被插入的行
	* 在BEFORE INSERT触发器中，NEW中的值也可以被更新（允许更改被插入的值）
	* 对于AUTO_INCREMENT列，NEW在INSERT执行之前包含0，在INSERT执行之后包含新的自动生成值

~~~sql
CREATE TRIGGER neworder AFTER INSERT ON orders
FOR EACH ROW SELECT NEW.order_num;
~~~
对于orders表的每次插入使用这个触发器将总是返回新的订单号

2. DELETE触发器
	* 在DELETE触发器代码内，你可以引用一个名为OLD的虚拟表，访问被删除的行
	* OLD中的值全都是只读的，不能更新

~~~sql
CREATE TRIGGER deleteorder BEFORE DELETE ON orders
FOR EACH ROW
BEGIN 
	INSERT INTO archive_orders(order_num,order_date,cust_id)
	VALUES(OLD.order_num,OLD.order_date,OLD.cust_id);
END;
~~~
将要被删除的订单保存到一个名为archive_orders的存档表中

* 可以使用BEGIN END块作为触发器体，这样触发器能容纳多条SQL语句

3. UPDATE触发器
	* 在UPDATE触发器代码中，你可以引用一个名为OLD的虚拟表访问以前（UPDATE语句前）的值，引用一个名为NEW的虚拟表访问更新后的值
	* 在BEFORE UPDATE触发器中，NEW中的值可能也被更新（允许更改将要用于UPDATE语句中的值）
	* OLD中的值全都是只读的，不能更新

~~~sql
CREATE TRIGGER updatevendor BEFORE UPDATE ON vendors
FOR EACH ROW SET NEW.vend_state = Upper(NEW.vend.state);
~~~
保证州名缩写总是大写

## 三、管理事务处理

* 事务（transaction） ：指一组SQL语句
* 回退（rollback）：指撤销指定SQL语句的过程
* 提交（commit）：指将未存储的SQL语句结果写入数据库表
* 保留点（savepoint）：指事务处理中设置的临时占位符，你可以对它发布回退（与回退整个事务处理不同）

1. 控制事务处理

~~~sql
SELECT * FROM ordertotals;
START TRANSACTION;
DELETE FROM ordertotals;
SELECT * FROM ordertotals;
ROLLBACK;
SELECT * FROM ordertotals;
~~~

* 事务处理可以回退INSERT、UPDATE、DELETE语句，不能回退SELECT语句。也不能回退CREATE或DROP操作

2. 使用COMMIT

* 一般MySQL语句都是直接针对数据库表执行和编写的，这就是隐含提交，即提交是自动进行的。但是在事务处理块中，提交不会自动进行，需要使用COMMIT语句进行提交

~~~sql
START TRANSACTION
DELETE FROM orderitems WHERE order_num = 20010;
DELETE FROM orders WHERE order_num = 20010;
COMMIT;
~~~
使用事务处理块保证事务不会被部分删除

* 隐含事务关闭：当COMMIT或ROLLBACK语句执行后，事务会自动关闭

3. 使用保留点

* 为了支持回退部分事务处理，必须能在事务处理块中合适的位置放置占位符。这样，如果需要回退，可以回退到某个占位符

~~~sql
SAVEPOINT delete1;

ROLLBACK TO delete1;
~~~

* 保留点在事务处理完成（执行一条ROLLBACK或COMMIT）后自动释放，也可以使用RELEASE SAVEPOINT明确地释放保留点

4. 更改默认的提交行为

~~~sql
SET autocommit=0;
~~~
autocommit标志决定是否自动提交更改，不管有没有COMMIT语句。设置autocommit为0指示MySQL不自动提交更改（直到autocommit被设置为真为止）

