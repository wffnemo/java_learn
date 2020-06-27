# MySQL常用操作

## 一、创建计算字段

* 计算字段并不存在于数据库表中，计算字段是运行时在SELECT语句内创建的
* **字段**：基本上于列（column）的意思相同，经常互换使用，不过数据库列一般称为列，二术语字段通常用在计算字段的链接上

1. Concat拼接字段
~~~mysql
SELECT Concat(name,' (',age,')')
FROM mytable
ORDER BY name;
~~~
`Concat`拼接串，即把多个串连接起来形成一个较长的串，上例输出`name (age)`

2. RTrim()函数删除右侧多余的空格
~~~mysql
SELECT Concat(RTrim(name),' (',RTrim(age),')')
FROM mytable
ORDER BY name;
~~~
`RTrim()`函数用于删除串右侧多余的空格，此外，`LTrim()`删除串左侧多余的空格，`Trim()`删除串两侧多余的空格

3. 使用别名
SQL支持列别名，别名（alias）是一个字段或值的替换名，也被称为导出列使用`AS`关键字赋予
~~~mysql
SELECT Concat(RTrim(name),' (',RTrim(age),')') AS item
FROM mytable
ORDER BY name;
~~~

4. 执行算术运算
计算字段还能够对检索出的数据进行算术运算
~~~mysql
SELECT id, quantity, price, quantity*price AS total_price
FROM mytalbe
WHERE num = 2005;
~~~

除此之外，MySQL支持以下的基本算术操作

|操作符|说明|
|:-:|:-:|
|+|加|
|-|减|
|\*|乘|
|/|除|

## 二、使用数据处理函数

* MySQL支持使用函数来处理数据，但是函数没有SQL的可移植性强

* 使用函数
大多数SQL支持以下类型的函数
	* 用于处理文本串的文本函数
	* 用于在数值数据上进行算术运算操作的数值函数
	* 用于处理日期和时间值并从这些值中提取特定成分的日期和时间函数
	* 返回DBMS正使用的特殊信息的系统函数

1. 文本处理函数
之前已经了解到了`RTrim()`、`LTrim()`、`Trim()`，下面是另一个例子，`Upper()`函数

~~~mysql
SELECT name,Upper(name) AS name_upcase
FROM mytable
ORDER BY name;
~~~
`Upper()`将文本转换为大写

下表是常用的文本处理函数

|函数|说明|
|:-:|:-:|
|Left()|返回串左边的字符|
|Length()|返回串的长度|
|Locate()|找出串的一个子串|
|Lower()|将串转换为小写|
|LTrim()|去掉串左边的空格|
|Right()|返回串右边的字符|
|RTrim()|去掉串右边的空格|
|Soundex()|返回串的SOUNDEX值|
|SubString()|返回子串的字符|
|Upper()|将串转换为大写|

2. 日期和时间处理函数
常用日期和时间处理函数

|函数|说明|
|:-:|:-:|
|AddDate()|增加一个日期|
|AddTime()|增加一个时间|
|CurDate()|返回当前日期|
|CurTime|返回当前时间|
|Date()|返回日期时间的日期部分|
|DateDiff()|计算两个日期之差|
|Date_Add()|高度灵活的日期运算函数|
|Date_Format()|返回一个格式化的日期或时间串|
|Day()|返回一个日期的天数部分|
|DayofWeek()|返回对应日期的星期|
|Hour()|返回对应时间的小时部分|
|Minute()|返回对应时间的分钟部分|
|Month()|返回一个日期的月份部分|
|Now()|返回当前日期和时间|
|Second()|返回对应时间的秒部分|
|Time()|返回一个日期时间的时间部分|
|Year()|返回对应日期的年份部分|

* <font color=#228B22>无论什么时候指定一个日期，不管是插入还是更新表值还是用WHERE子句进行过滤，日期格式必须为`yyyy-mm-dd`</font>

~~~mysql
SELECT id,age
FROM mytable
WHERE date = '2007-12-23';
~~~
如果仅是对日期或时间部分进行比较，则应该使用`Day()`或`Time()`函数

~~~mysql
SELECT id,age
FROM mytable
WHERE Date(date) BETWEEN '2005-09-01' AND '2005-9-30';
~~~

3. 数值处理函数
常用数值处理函数：

|函数|说明|
|:-:|:-:|
|Abs()|返回一个数的绝对值|
|Cos()|返回一个角度的余弦|
|Exp()|返回一个数的指数值|
|Mod()|返回除操作的余数|
|Pi()|返回圆周率|
|Rand()|返回一个随机数|
|Sin()|返回一个角度的正弦|
|Sqrt()|返回一个数的平方根|
|Tan()|返回一个角度的正切|

## 三、汇总函数

* 聚集函数：运行在行组上，计算和返回单个值得函数

SQL聚集函数：

|函数|说明|
|:-:|:-:|
|AVG()|返回某列平均值|
|COUNT()|返回某列行数|
|MAX()|返回某列最大值|
|MIN()|返回某列最小值|
|SUM()|返回某列值之和|

1. AVG()
~~~mysql
SELECT AVG(age) AS avg_age
FROM mytable
WHERE salary<4000;
~~~
AVG()函数会忽略列值为NULL的行

2. COUNT()函数
使用COUNT()函数有两种方式：
	* 使用COUNT(*)对表中行的数目进行计算
	* 使用COUNT(column)对特定列中具有值的行进行计数

~~~mysql
SELECT COUNT(age) AS num_age
FROM mytable;
~~~

3. MAX()函数
~~~mysql
SELECT MAX(salary) AS max_salary
FROM mytable;
~~~
MAX()函数会忽略列值为NULL的行

4. MIN()函数
~~~mysql
SELECT MIN(salary) AS min_salary
FROM mytable;
~~~
MIN()函数会忽略列值为NULL的行

5. SUM()函数
~~~mysql
SELECT SUM(salary) AS total_salary
FROM mytable;
~~~
SUM()函数会忽略列值为NULL的行

6. 聚集不同值
对于上述5个聚集函数，都可以使用下面两个参数：
	* ALL(默认)：对所有行执行计算
	* DISTINCT：只包含不同的值
~~~mysql
SELECT SUM(DISTINCT salary) AS total_salary
FROM mytable;
~~~

7. 组合聚集函数
可以在SELECT语句中同时使用多个聚集函数
~~~mysql
SELECT SUM(salary) AS total_salary,MAX(salary) AS max_salary,MIN(salary) AS min_salary
FROM mytable;
~~~

## 四、分组数据

* 分组是在SELECT语句的GROUP BY子句中建立的

1. 创建分组
~~~mysql
SELECT 	age,COUNT(*) AS num_person
FROM mytable
GROUP BY age;
~~~
`GROUP BY`子句指示MySQL按`age`排序并分组数据，这导致对每个`age`而不是整个表计算`num_person`一次

* `GROUP BY`子句的重要规定：
	* GROUP BY子句可以包含任意数目的列，这使得能对分组进行嵌套
	* 如果在GROUP BY子句中使用了嵌套，数据将在最后规定的分组上进行汇总
	* GROUP BY子句中列出的每个列都必须是检索列或有效的表达式（但不能是聚集函数）。如果在SELECT中使用表达式，则必须在GROUP BY子句中指定相同的表达式，不能使用别名
	* 除聚集计算语句外，SELECT语句中的每个列都必须在GROUP BY子句中给出
	* 如果分组列中具有NULL值，则NULL值将作为一个分组返回，如果列中有多行NULL值，它们将分为一组
	* GROUP BY子句必须出现在WHERE子句之后，ORDER BY子句之前
* <font color=#228B22>使用ROLLUP：使用WITH ROLLUP关键字，可以得到每个分组以及每个分组汇总级别（针对每个分组）的值</font>

~~~mysql
SELECT vend_id,COUNT(*) AS num_prods
FROM products
GROUP BY vend_id WITH ROLLUP;
~~~

2. 过滤分组

* MySQL提供了HAVING子句，HAVING非常类似于WHERE。事实上，目前为止所有类型的WHERE子句都可以用HAVING来替代，唯一的差别是WHERE过滤行，HAVING过滤分组

~~~mysql
SELECT cust_id,COUNT(*) AS orders
FROM orders
GROUP BY cust_id
HAVING COUNT(*) >= 2;
~~~
正如所说，这里WHERE子句不起作用，因为过滤是基于分组聚集值而不是特定行值的

* 也可以说WHERE在分组前进行过滤，而HAVING在分组后进行过滤

~~~mysql
SELECT vend_id COUNT(*) AS num_prods
FROM products
WHERE prod_price >= 10
GROUP BY vend_id
HAVING COUNT(*) >= 2;
~~~

3. 分组和排序

* 虽然GROUP BY和ORDER BY经常完成相同的工作，但是它们是非常不同的，下表汇总了它们之间的差别

|ORDER BY|GROUP BY|
|:-:|:-:|
|对输出进行排序|对行进行分组，分组顺序无法保证|
|任意列都可以使用|只能使用且必须使用全部的选择列和表达式列，表达式列不能使用别名|
|需要排序时使用|如果使用了聚集函数，则必须使用|

* <font color=#228B22>一般在使用GROUP BY 子句时，应该也给出ORDER BY子句，这是保证数据正确性的唯一方法，千万不要依赖GROUP BY排序的数据</font>

~~~mysql
SELECT oder_num,SUM(quantity*item_price) AS odertotal
FROM orderitems
GROUP BY order_num
HAVING SUM(quantity*item_price) >= 50
ORDER BY odertotal
~~~

4. SELECT子句顺序

* SELECT子句及其顺序

|子句|说明|是否必须使用|
|:-:|:-:|:-:|
|SELECT|指示要返回的列或表达式|是|
|FROM|从中检索数据的表|仅在从表选择数据时使用|
|WHERE|行级过滤|否|
|GROUP BY|分组说明|仅在使用聚集函数时使用|
|HAVING|组级过滤|否|
|ORDER BY|对输出排序|否|
|LIMIT|指示输出的行数|否|

## 五、使用子句查询

### 1.利用子查询进行过滤

* 可以把一条SELECT语句返回的结果用于另一条SELECT语句的WHERE子句

~~~mysql
SELEC cust_id
FROM orders
WHERE order_num IN (SELECT order_num
					FROM orderitems
					WHERE prod_id = 'TNT2');
~~~

* 在SELECT语句中，子查询总是从内向外处理。SELECT对于能嵌套的子查询的数目没有限制，不过在实际使用时由于性能的限制，不能嵌套太多的子查询
* 虽然子查询一般与IN操作符结合使用，但也可以用于测试等于（=）、不等于（<>）等

### 2.作为计算字段使用子查询

* 使用子查询的另一个方法是创建计算字段

~~~mysql
SELECT cust_name,cust_state,(SELECT COUNT(*)
							FROM orders
                            WHERE orders.cust_id = customers.cust_id)
FROM customers
ORDER BY cust_name;
~~~
这种涉及到外部查询的子查询被称为相关子查询
