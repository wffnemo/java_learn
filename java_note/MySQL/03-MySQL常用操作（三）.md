# MySQL常用操作

## 一、联结表

* 联结
SQL最强大的功能之一就是能在数据检索查询的执行中联结（join）表，联结是SQL中的SELECT能执行的最重要的操作

### 1.关系表

* 外键：为某个表中的一列，它包含另一个表的主键值，定义了两个表之间的关系
* 分解数据能更有效地存储，更方便地处理，并且具有更大地可伸缩性，但是这些好处是有代价的。如果数据存储在多个表中，为了在单条SELECT语句中检索数据，此时需要使用联结

### 2.创建联结

* 全限定列名：在引用的列可能出现二义性时，必须使用全限定列名（用一个点分隔的表名和列名）
* 笛卡尔积（cartesian product）：由没有联结条件的表关系返回的结果为笛卡尔积，检索出的行的数目将是第一个表中的行数乘以第二个表中的行数
* 应该保证所有联结都有WHERE子句，否则MySQL将返回不必要的数据，导致错误

~~~mysql
SELECT vend_name,prod_name,prod_price
FROM vendors,products
WHERE vendors.vend_id = products.vend_id
ORDER BY vend_name,prod_name;
~~~

1. 内部联结

* 目前为止所用的联结为等值联结（equijion），它基于两个表之间的相等测试。这种联结也称为内部联结

~~~mysql
SELECT vend_name,prod_name,prod_price
FROM vendors INNER JOIN products
ON vendors.vend_id = products.vend_id;
~~~
内部联结由INNER JOIN指定，联结条件用ON子句而不是WHERE子句给出，传递给ON的实际条件与传递给WHERE的相同。ANSI SQL规范首选INNER JOIN语法。

2.联结多个表

* SQL对一条SELECT语句中可以联结的表的数目没有限制。创建联结的基本规则也相同，首先列出所有表，然后定义表之间的关系

~~~mysql
SELECT prod_name,vend_name,prod_price,quantity
FROM orderitems,products,vendors
WHERE products.vend_id = vendors.vend_id
AND orderitems.prod_id = products.prod_id
AND order_num = 20005;
~~~

## 二、创建高级联结

### 1.使用表别名

~~~mysql
SELECT Concat(RTrim(vend_name),'(',RTrim(vend_country),')') AS vendtitle
FROM vendors
ORDER BY vend_name;
~~~
* 别名除了用于列名和计算字段外，SQL还允许给表名其别名，这样做有两个主要理由：
	* 缩短SQL语句
	* 允许在单条SELECT语句中多次使用相同的表

~~~mysql
SELECT cust_name,cust_contact
FROM customers AS c,orders AS o,orderitems AS oi
WHERE c.cust_id = o.cust_id
AND oi.order_num = o.order_num
AND prod_id = 'TNT2';
~~~
表别名不仅能用于WHERE子句，它还可以用于SELECT的列表、ORDER BY子句以及语句的其他部分

### 2.使用不同类型的联结

目前为止，我们使用的只是称为内部联结或等值联结的简单联结，现在来看另外三种联结，分别是自联结、自然联结和外部联结

1. 自联结

~~~mysql
SELECT p2.prod_id,p2.prod_name
FROM products AS p1,products AS p2
WHERE p1.vend_id = p2.vend_id
AND p1.prod_id = 'DTNTR';
~~~

* <font color=#228B22>用自联结而不用子查询：自联结通常作为外部语句来替代从相同表中检索数据时使用的子查询语句。虽然最终的结果相同，但有时候处理联结远比处理子查询快得多，应该试一下两种方法，确定哪一种的性能更好</font>

2. 自然联结

* 标准联结返回所有数据，甚至相同的列多次出现。自然联结排除多次出现，使每个列只返回一次

~~~mysql
SELECT c.*,o.order_num,o.order_data
		oi.prod_id,oi.quantity,oi.item_price
FROM customers AS c,orders AS o,orderitems AS oi
WHERE c.cust_id = o.cust_id
AND o.order_num = oi.order_num
AND prod_id = 'FB';
~~~
自然联结是这样一种联结，你只能手动选择那些唯一的列，这一般是通过对表使用通配符（\*），对多有其他表使用明确的子集来完成的

3. 外部联结

* 联结包含了那些在相关表中没有关联行的行的联结被称为外部联结
* 存在两种基本的外部联结形式：左外部联结和右外部联结。它们之间的唯一差别是所关联的表的顺序不同

~~~mysql
SELECT customers.cust_id,orders.order_num
FROM customers LEFT OUTER JOIN orders
ON customers.cust_id = orders.cust_id;
~~~

### 3.使用带聚集函数的联结

* 聚集函数可以与联结一起使用

聚集函数与内部联结
~~~mysql
SELECT customers.cust_name,customers.cust_id,COUNT(orders.order_num) AS num_ord
FROM customers INNER JOIN orders
ON customers.cust_id = orders.cust_id
GROUP BY customers.cust_id;
~~~

聚集函数与外部联结
~~~mysql
SELECT customers.cust_name,customers.cust_id,COUNT(orders.order_num) AS num_ord
FROM customers LEFT OUTER JOIN orders
ON customers.cust_id = orders.cust_id
GROUP BY customers.cust_id;
~~~

### 4.使用联结和联结条件

* 注意所使用的联结类型，一般我们使用内部联结，但使用外部联结也是有效的
* 保证使用正确的联结条件，否则将返回不正确的数据
* 应该总是提供联结条件，否则将得出笛卡尔积
* 在一个联结中可以包含多个表，甚至对于每个联结可以采用不同的联结类型。虽然这样做是合法的，一般也很有用，但应该在一起测试它们前，分别测试每个联结。这将使故障排除更加简单。

## 四、组合查询

* MySQL也允许执行多个查询（多条SELECT语句），并将结果作为单个查询结果返回。这些组合查询通常称为并（union）或复合查询（compound query）
* 有两种基本情况，其中需要使用组合查询：
	* 在单个查询中从不同的表返回类似结构的数据
	* 对单个表执行多个查询，按单个查询返回数据
* 多数情况下，组合相同表的两个查询完成的工作与具有多个WHERE子句条件的单条查询完成的工作相同，这两种技术在不同的查询中性能也不同，因此应该试一下这两种技术确定哪一种性能更好

1. 创建组合查询

* 使用UNION操作符来组合数条SQL查询

使用多条WHERE子句而不是使用UNION的查询
~~~mysql
SELECT vend_id,prod_id,prod_price
FROM products
WHERE prod_price <= 5
OR vend_id IN (1001,1002);
~~~

使用UNION来组合多条查询
~~~mysql
SELECT vend_id,prod_id,prod_price
FROM products
WHERE prod_price <=5
UNION
SELECT vend_id,prod_id,prod_price
FROM products
WHERE vend_id IN (1001,1002);
~~~

2. UNION规则

* <font color=#228B22>UNION必须由两条或两条以上的SELECT语句组成，语句之间用关键字UNION分隔</font>
* <font color=#228B22>UNION中的每个查询必须包含相同的列、表达式或聚集函数（不过各个列不需要以相同的次序列出）</font>
* <font color=#228B22>列数据类型必须兼容，类型不必完全相同，但必须是DBMS可以隐含地转换的类型（不同的数值类型或日期类型）</font>

3. 包含或取消重复的行

* UNION自动从查询结果集中去除重复的行，如果想要返回所有匹配行，可以使用UNION ALL而不是UNION
* 如果确实需要每个条件的匹配行全部出现（包括重复行），则必须使用UNION ALL而不是WHERE

4. 对组合查询结果排序

* 在用UNION组合查询时，只能使用一条ORDER BY子句，它必须出现在最后一条SELECT语句之后。对于结果集，不存在用一种方式排序一部分，而又用另一种方式排序另一部分的情况，因此不允许使用多条ORDER BY子句

~~~mysql
SELECT vend_id,prod_id,prod_price
FROM products
WHERE prod_price <=5
UNION
SELECT vend_id,prod_id,prod_price
FROM products
WHERE vend_id IN (1001,1002)
ORDER BY vend_id,prod_price;
~~~

## 五、全文本搜索

### 1.理解全文本搜索

* 并非所有引擎都支持全文本搜索：MySQL支持几种基本的数据库引擎。并非所有的引擎都支持全文本搜索。两个最常用的引擎为MyISAM和InnnoDB，前者支持全文本搜索，而后者不支持
* 通配符和正则表达式都用来匹配文本，虽然这些搜索机制非常有用，但存在几个重要的限制
	* 性能：通配符和正则表达式匹配通常要求MySQL尝试匹配表中所有行（而且这些搜索极少使用表索引）。因此，由于被搜索行数不断增加，这些搜索可能非常耗时
	* 明确控制：使用通配符和正则表达式，很难明确地控制匹配什么和不匹配什么
	* 智能化的结果：虽然基于通配符和正则表达式的搜索提供了非常灵活的搜索，但它们都不能提供一种智能化的选择结果的方法

### 2. 使用全文本搜索

为了进行全文本搜索，必须索引被搜索的列，而且要随着数据改变不断地重新索引。在对表列进行适当设计后，MySQL会自动进行所有的索引和重新索引

1. 启用全文本搜索支持

一般在创建表时启用全文本搜索。CREATE TABLE语句接受FULL TEST子句，它给出被索引列的一个逗号分隔的列表

~~~mysql
CREATE TABLE productnotes(
note_id int      NOT NULL AUTO_INCREMENT,
prod_id char(10) NOT NULL,
note_data datetime NOT NULL,
note_text text   NULL,
PRIMARY KEY(note_id),
FULLTEXT(note_text)
)ENGINE=MyISAM;
~~~
这里的FULLTEXT索引单个列，如果需要也可以指定多个列。在定义后，MySQL自动维护该索引，在增加、删除或更新行时，索引随之自动更新
可以在创建表时指定FULLTEXT，或者在稍后指定（在这种情况下所有已有数据必须立即索引）

* 不要在导入数据时使用FULLTEXT：更新索引要花时间，虽然不是很多，但毕竟要花时间。如果正在导入数据到一个新表，此时不应该启动FULLTEXT索引，应该受先导入所有数据，然后再修改表，定义FULLTEXT。这样有助于更快地导入数据

2. 进行全文本搜索

* 在索引之后，使用两个函数Match()和Against()执行全文本搜索，其中Match()指定被搜索的列，Against()指定要使用的搜索表达式

~~~mysql
SELECT note_text
FROM productnotes
WHERE Match(note_text) Against('rabbit');
~~~

* 使用完整的Match()说明：传递给Match()的值必须与FULLTEXT()定义中的相同，如果指定多个列，则必须列出它们（而且次序正确）
* 搜索不区分大小写：除非使用BINARY方式，否则全文本搜索不区分大小写
* 全文本的一个重要部分是对结果排序，具有较高等级的行先返回，文本匹配程度高的数据等级高（单个搜索项时，先出现搜索项的数据等级高，多个搜索项时，匹配数目多的数据等级高）

3. 使用查询扩展

* 查询扩展用来设法放宽所返回的全文本搜索结果的范围。在使用查询扩展时，MySQL对数据 和索引进行两遍扫描来完成搜索：
	* 首先，进行一个基本的全文本搜索，找出与搜索条件匹配的所有行
	* 其次，MySQL检查这些匹配行并选择所有有用的词
	* 再其次，MySQL再次进行全文本搜索，这次不仅使用原来的条件，而且还使用所有有用的词

~~~mysql
SELECT note_text
FROM producnotes
WHERE Match(note_text) Against('anvils' WITH QUERY EXPANSION);
~~~
查询扩展增加了返回的行数，但这样做也增加了你实际上并不想要的行的数目

* 行越多越好：表中的行越多（这些行中的文本就越多），使用查询扩展返回的结果越好

4. 布尔文本搜索

* MySQL支持全文本搜索的另外一种形式，称为布尔方式（boolean mode）。以布尔方式，可以提供关于如下内容的细节：
	* 要匹配的词
	* 要排斥的词（如果某行包含这个词，则不返回该行，即使它包含其他指定的词也是如此）
	* 排列提示（指定某些词比其他词更重要，更重要的词等级更高）
	* 表达式分组
	* 另外一些内容

* <font color=#228B22>即使没有FULLTEXT索引也可以使用：布尔方式与全文本搜索语法的不同点在于，即使没有定义FULLTEXT索引，也可以使用它。但这是一种非常缓慢的操作</font>

~~~mysql
SELECT note_text
FROM productnotes
WHERE Match(note_text) Against('heavy' IN BOOLEAN MODE);
~~~
匹配任何包含heavy的行，因没有指定布尔操作符，因此与没有指定布尔操作符的结果相同

~~~mysql
SELECT note_text
FROM productnotes
WHERE Match(note_text) Against('heavy -rope*' IN BOOLEAN MODE)；
~~~
匹配包含heavy但不包含任意以rope开始的词的行

* SQL支持的全文本布尔操作符

|布尔操作符|说明|
|:-:|:-:|
|+|包含，词必须存在|
|-|排除，词必须不存在|
|>|包含，且增加等级值|
|<|包含，且减少等级值|
|()|把词组成子表达式（允许这些子表达式作为一个组被包含、排除、排列等）|
|~|取消一个词的排序值|
|\*|词尾通配符|
|""|定义一个短语（与单个词的列表不一样，它匹配整个短语以便包含或排除这个短语）|

~~~mysql
SELECT note_text
FROM productnotes
WHERE Match(note_text) Against('+rabbit +bait' IN BOOLEAN MODE);
~~~
匹配包含rabbit和bait的行

~~~mysql
SELECT note_text
FROM productnotes
WHERE Match(note_text) Against('rabbit bait' IN BOOLEAN MODE);
~~~
匹配包含rabbit和bait中的至少一个词的行

~~~mysql
SELECT note_text
FROM productnotes
WHERE Match(note_text) Against('"rabbit bait"' IN BOOLEAN MODE);
~~~
匹配短语rabbit bait而不是匹配两个词rabbit和bait

~~~mysql
SELECT note_text
FROM productnotes
WHERE Match(note_text) Against('>rabbit <carrot' IN BOOLEAN MODE);
~~~
匹配rabbit和carrot，增加前者的等级，降低后者的等级

~~~mysql
SELECT note_text
FROM productnotes
WHERE Match(note_text) Against('+safe +(<combination)' IN BOOLEAN MODE);
~~~
搜索匹配词sage和combination，降低后者的等级

* <font color=#228B22>排列而不排序：在布尔方式中，不按等级值降序排序返回的行</font>

5. 全文本搜索的使用说明

* 在索引全文本数据时，短词被忽略且从索引中排除，短词定义为那些具有3个或3个以下字符的词
* MySQL带有一个内建的非用词（stopword）列表，这些词在索引全文本数据时总是被忽略
* 许多词出现的频率很高，搜索它们没有用处，如果一个词出现在50%以上的行中，则将它作为一个非用词忽略。50%规则不用于IN BOOLEAN MODE
* 如果表中行数少于3行，则全文本搜索不返回结果
* 忽略词中的单引号，如don‘t索引为dont
* 不具有词分隔符（包括日语和汉语）的语言不能恰当地返回全文本搜索结果
* 仅在MyISAM数据库引擎中支持全文本搜索

