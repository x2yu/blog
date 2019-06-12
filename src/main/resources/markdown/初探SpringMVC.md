
### <center>想要做点东西出来。</center>
<center><img src="/assets/blogImg/spring-logo.jpg" width="40%" height="40%"></center>

## 1. 什么是SpringMVC

SpringMVC 是类似于 Struts2 的一个 MVC 框架，在实际开发中，接收浏览器的请求响应，对数据进行处理，
然后返回页面进行显示，但是上手难度却比 Struts2 简单多了。
<!--more-->
而且由于 Struts2 所暴露出来的安全问题，SpringMVC 已经成为了大多数企业优先选择的框架。


**工作流程如下：**
![SpringMvc](/assets/blogImg/SpringMvc.jpg)

## 2. 创建WEB项目

### 2.1 导入相关的jar包（随后附链接）。

### 2.2 在WEB-INF目录下创建 web.xml

配置Spring MVC的入口 DispatcherServlet，把所有的请求都提交到该Servlet
**注意：<servlet-name>springmvc</servlet-name>**

load-on-startup元素标记容器是否在启动的时候就加载这个servlet(实例化并调用其init()方法)，数字越小优先级越高。

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.4" xmlns="http://java.sun.com/xml/ns/j2ee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee
http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd">
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>
            org.springframework.web.servlet.DispatcherServlet
        </servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```

### 2.3 创建springmvc-servlet.xml

在WEB-INF目录下创建 springmvc-servlet.xml

**springmvc-servlet.xml 与上一步中的<servlet-name>springmvc</servlet-name>   springmvc对应**
web.xml中配置的，加载的就是*-servlet.xml文件，当前默认加载的是springMVC-servlet.xml如果不给项目指定加载文件，默认加载的就是<servlet-name>名字</servlet-name>，名字-servlet.xml文件 , contextConfigLocation不是必须的

这是Spring MVC的 映射配置文件
表示访问路径/index会交给id=indexController的bean处理
id=indexController的bean配置为类：IndexController
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN//EN" "http://www.springframework.org/dtd/spring-beans.dtd">
<beans>
    <bean id="simpleUrlHandlerMapping"
        class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
        <property name="mappings">
            <props>
                <prop key="/index">indexController</prop>
            </props>
        </property>
    </bean>
    <bean id="indexController" class="com.x2y.controller.IndexController"></bean>
</beans>
```

### 2.4 创建index.jsp
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>SpringMVC</title>
</head>
<body>
	<h1>${message}</h1>
</body>
</html>
```

### 2.5 创建控制类ndexController

控制类 IndexController实现接口Controller ，提供方法handleRequest处理请求
**SpringMVC通过 ModelAndView 对象把模型和视图结合在一起**
```
package com.x2y.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class IndexController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("index.jsp");
		mav.addObject("message","Hello SpringMVC");
		return mav;
	}
}
```

### 2.6 测试
准备就绪了。
<img src="/assets/blogImg/tomcat-go.jpg" width="35%" height="35%">

然后报错
`Could not open ServletContext resource [/WEB-INF/springmvc-servlet.xml]`
这是因为我把springmvc-servlet.xml配置命名成了spring-servlet.xml。囧

**再次启动，没有问题。在浏览器输入：http://localhost:8080/SpringMVC/index**
页面显示Hello SpringMVC

## 3. 视图定位

前面我们声明视图解析器时
`ModelAndView mav = new ModelAndView("index.jsp");`
视图定位就是将代码写成这样，
`ModelAndView mav = new ModelAndView("index");`
但是仍然会跳转到 /WEB-INF/page/index.jsp（举例路径，WEB-INF目录对于用户是不可见的，把页面放在这个下面可以让其受保护）

1. 修改springmvc-servlet.xml,增加配置
```
<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/page/"></property>
		<property name="suffix" value=".jsp"></property>
	</bean>
```

**分别配置前缀和后缀，把视图约定在 /WEB-INF/page/*.jsp 这个位置**

2. 修改IndexController

`ModelAndView mav = new ModelAndView("index.jsp");`
**改为**
`ModelAndView mav = new ModelAndView("index");`

3. 创建/WEB-INF/page文件夹，并且复制index.jsp到目录下

运行访问http://localhost:8080/SpringMVC/index

显示Hello SpringMVC，没有问题。

## 4. 注释方式配置SpringMVC

### 4.1 修改IndexController

在类前面加上@Controller 表示该类是一个控制器
在方法handleRequest 前面加上 @RequestMapping("/index") 表示路径/index会映射到该方法上
**注意：不再让IndexController实现Controller接口**

之前是实现的`import org.springframework.web.servlet.mvc.Controller;`中的Controller接口
`public class IndexController implements Controller{`

引入注解`import org.springframework.stereotype.Controller;`，对应导入包不同了。

```
package com.x2y.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

@Controller
public class IndexController{

	@RequestMapping("/index")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("message","Hello SpringMVC");
		return mav;
	}
}
```

### 4.2 修改springmvc-servlet.xml

去掉映射相关的配置，增加Controller扫描路径
`<context:component-scan base-package="com.x2y.controller" />`

emmm，也就是这样，这里要引入了新的命名空间context,不然无法使用
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
    http://www.springframework.org/schema/context        
    http://www.springframework.org/schema/context/spring-context-3.0.xsd">
     
    <context:component-scan base-package="com.x2y.controller" />
    <bean id="irViewResolver"
        class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/page/" />
        <property name="suffix" value=".jsp" />
    </bean>
</beans>
```

然后测试运行输入http://localhost:8080/SpringMVC/index
也是没有问题的。

## 5. 接受表单数据

### 5.1 准备pojo以及jsp页面

1）product对象
```
package com.x2y.pojo;

public class Product {
	private int id;
	private String name;
	private float price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
```

2）addProduct.jsp
**注意这里将jsp页面放在web目录下，产品名称的input name属性为name**
**action=“addProduct意思为提交数据的路径，填写的是addProduct，就是提交到当前页面”**
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<form action="addProduct">
 
    产品名称 ：<input type="text" name="name" value=""><br />
    产品价格： <input type="text" name="price" value=""><br />
 
    <input type="submit" value="增加商品">
</form>
```

3）showProduct.jsp
**在WEB-INF/page 目录下创建 showProduct.jsp**
用 EL 表达式显示用户提交的名称和价格
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 
产品名称： ${product.name}<br>
产品价格： ${product.price}
```

### 5.2 准备ProductController

控制器ProductController，准备一个add方法映射/addProduct路径
为addProduct方法准备一个Product参数，用于接收注入
最后跳转到showProduct页面显示用户提交的数据

**这里面的"/addProduct"是匹配请求路径路，你的form表单提交时候你的action是addProduct那么提交就会生成 /addProduct 这个请求的url 然后被你的requestMapping("/addProduct")匹配到他就执行这个方法。**
```
package com.x2y.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.x2y.pojo.Product;

@Controller
public class ProductController {
	
	@RequestMapping("/addProduct")
	public ModelAndView addProduct(Product product)throws Exception{//Product 参数，用于接收注入
		ModelAndView mav = new ModelAndView("showProduct");
		return mav;
	}
}
```

**运行访问**http://localhost:8080/SpringMVC/addProduct.jsp
输入参数后正常跳转到了showProduct页面。****
![showProduct](/assets/blogImg/showProduct.png)

### 5.3 流程理解

流程理解：
输入完表单请求的数据后，提交。来到DispatcherServlet中，处理器映射器根据提交的路径，找到了相匹配的处理器Controller（已注解了路径）。

接下来：在Controller的方法中，通过**参数绑定**，表单中的数据直接注入到了形参（Product product）中，前提是表单中的属性和Bean：Product中的属性是相匹配的，这样Spring才能找到。然后进入方法，在方法中创建了一个ModelAndView，并且通过这个构造方法制定了返回的View，与Springmvc-servlet中的配置配合后，showProject+.jsp就变成了最后返回视图的路径。

 **比较牛逼的就是这里直接在controller方法形参上定义默认类型的对象，就可以使用这些对象。就是在不写mvc.addObjec的前提下就可以自动将方法中的形参，project导入到了ModelAndView中。**

还有疑问就是在showProduct页面中例如`${product.name}`这个product是哪里来的。嗯？？？
之前我以为是
`@RequestMapping("/addProduct")`
`	public ModelAndView addProduct(Product product)throws Exception{//Product 参数，用于接收注入`参数中的product对象。

**后来了解到不是这样。**
首先EL 表达式中的参数应该是来自于 addObject("abc",xxx) 中的第一个参数 abc，上面代码参数 product 会默认被当做值加入到 ModelAndView 中，但是这是**addObject() 第二个参数的来历**

**第一个参数的来历是addProduct()方法的【参数类型】会自动转小驼峰并加入到 ModelAndView 中作为 addObject() 的【第一个参数】，就相当于是Product转成了product陈成为了第一个参数。**

下面来测试一下
如果我把Product产品类重命名为MyProduct
`public class MyProduct {`

然后Controller中参数命名如下
`@RequestMapping("/addProduct")`
`public ModelAndView addProduct(MyProduct product)throws Exception{//Product 参数，用于接收注入`
那么按照上面说的在showProduct页面中调用对象就应该是`${myProduct.name}`这样才对了。**注意转小驼峰只是第一个大写转换了，后面没有变化。**

补充：
如果使用了`@ModelAttribute`来修饰入参，则key为`@ModelAttribute`注解的value属性值

例`test(``@ModelAttribute``(value=``"users"``) User user){} `这样的方法key就是users

运行测试结果是正确的就不展示了。

## 6 参数绑定相关
> [Spring MVC] - SpringMVC的各种参数绑定方式](http://www.cnblogs.com/HD/p/4107674.html)
> [SpringMVC中的参数绑定总结](https://blog.csdn.net/eson_15/article/details/51718633)

springmvc提供了很多转换器来将页面参数绑定到controller方法的形参上。

### 6.1 默认支持的类型

springmvc中，有支持的默认类型的绑定。也就是说，直接在controller方法形参上定义默认类型的对象，就可以使用这些对象。

 1.  HttpServletRequest对象
 2.  HttpServletResponse对象
 3.  HttpSession对象
 4.  Model/ModelMap对象
在controller的方法的形参中直接定义上面这些类型的参数，springmvc会自动绑定。

### 6.2 基本数据类型

这里以int为例子
1.jsp页面
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<form action="docount">
    测试int<input type="text" name="count" value="777"><br />	
    	   <input type="submit" value="传递整数">
</form>
```

2.Controller
```
@RequestMapping("/docount")
	public ModelAndView doCount(int count)throws Exception{
		ModelAndView mav = new ModelAndView("showInt");
		System.out.println(count);
		mav.addObject("productcount", count);
		return mav;
	}
```

3.showInt
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>SpringMVC</title>
</head>
<body>
	<h1>${productcount}</h1>
</body>
</html>
```

**表单中input的name值和Controller的参数变量名保持一致，就能完成数据绑。**
其实只是这样的看着出来好像意义不大，想了下比较多的业务用处就是传id删除,或者单个查询的时候，不过这里int不用`mav.addObject("productcount", count);`就没有将count绑定上，跳转其他页面的时候就没有带过去。有点晕

* **包装类（以Integer为例）只是把Controller的方法参数中定义为包装类型**
`public ModelAndView doCount(Integer count)throws Exception{`就行了。
和基本数据类型基本一样，不同之处在于，表单传递过来的数据可以为null或“"，如果表单中num为”"或者表单中无num这个input，那么，Controller方法参数中的num值则为null。

### 6.3 普通pojo类型

普通的对象类型的就和前面的Product的绑定注入是一样的，这里试一下****自定义复合对象类型对象**的绑定。

1.增加Category类
```
package com.x2y.pojo;

public class Category {
	private String cid;
	private String cname;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
}
```

2.在Product中注入Category
```
private Category category;

public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
```

3.mutipojo_bind.jsp页面
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<form action="addProductAndCategory">
 
    产品名称 ：<input type="text" name="name" value=""><br />
    产品价格： <input type="text" name="price" value=""><br />
    产品分类： <input type="text" name="category.cname" value=""><br />
    <input type="submit" value="增加商品">
</form>
```

4.Controller
```
	@RequestMapping("/addProductAndCategory")
	public ModelAndView addProductAndCategory(Product product)throws Exception{
		ModelAndView mav = new ModelAndView("showProduct");
		System.out.println(product.getId());
		System.out.println(product.getName());
		System.out.println(product.getCategory().getCid());
		System.out.println(product.getCategory().getCname());
		return mav;
	}
```

5.showProduct.jsp
**注意这里对分类的调用方式**
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 
产品名称： ${product.name}<br>
产品价格： ${product.price}<br>
产品分类：${product.category.cname}
```

然后运行访问http://localhost:8080/SpringMVC/mutipojo_bind.jsp填入参数看控制台打印和页面跳转都正常。

**需要注意的就是和普通对象一样在表单代码中，需要使用“属性名(对象类型的属性).属性名”来命名input的name。**

### 6.4 List绑定

**List需要绑定在对象上，而不能直接写在Controller方法的参数中。**
1.ProductList对象
```
package com.x2y.pojo;

import java.util.List;

public class ProductList {
	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}	
}
```

2.List_bind.jsp页面
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<form action="addListProducts">
 
    产品名称 ：<input type="text" name="products[0].name" value=""><br />
    产品价格： <input type="text" name="products[0].price" value=""><br />
    产品分类： <input type="text" name="products[0].category.cname" value=""><br />
    
    产品名称 ：<input type="text" name="products[1].name" value=""><br />
    产品价格： <input type="text" name="products[1].price" value=""><br />
    产品分类： <input type="text" name="products[1].category.cname" value=""><br />
    
    产品名称 ：<input type="text" name="products[2].name" value=""><br />
    产品价格： <input type="text" name="products[2].price" value=""><br />
    产品分类： <input type="text" name="products[2].category.cname" value=""><br />
    
    产品名称 ：<input type="text" name="products[3].name" value=""><br />
    产品价格： <input type="text" name="products[3].price" value=""><br />
    产品分类： <input type="text" name="products[3].category.cname" value=""><br />
    
    <input type="submit" value="提交商品">
</form>
```

3. Controller
```
@RequestMapping("addListProducts")
	public void addListProducts(ProductList productList)throws Exception{
		ModelAndView mav = new ModelAndView("showList");
		mav.addObject("products",productList);
		for(Product product:productList.getProducts()) {
			System.out.println(product.getName()+" "+product.getPrice()+" "+product.getCategory().getCname());
		}
	}
```

运行在后台打印了List数组中的数据。
**Spring会创建一个以最大下标值为size的List对象，所以，如果表单中有动态添加行、删除行的情况，就需要特别注意，譬如一个表格，用户在使用过程中经过多次删除行、增加行的操作之后，下标值就会与实际大小不一致，这时候，List中的对象，只有在表单中对应有下标的那些才会有值，否则会为null。**

也就是如果在前端只传`products[0]`和`products[3]`，依然是传的4个对象，且这4个product对象都不会为null,但是第2,3个对象中的属性值都为null。

### 6.5 Map绑定

Map的绑定其实和List的绑定是差不多的，它也需要绑定在对象上，不能直接写在Controller方法的参数中。

1.ProductMap类
```
package com.x2y.pojo;

import com.sun.javafx.collections.MappingChange.Map;

public class ProductMap {
	private Map<String, Product>products;

	public Map<String, Product> getProducts() {
		return products;
	}

	public void setProducts(Map<String, Product> products) {
		this.products = products;
	}	
}
```

2.表单代码
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<form action="addMapProduct">
<table>
<thead>
<tr>
<th>产品名称</th>
<th>产品价格</th>
<th>产品分类</th>
</tr>
</thead>
<tfoot>
<tr>
<td colspan="2"><input type="submit" value="Save" /></td>
</tr>
</tfoot>
<tbody>
<tr>
<td><input name="products['x'].name" value="aaa" /></td>
<td><input name="products['x'].price" value="111" /></td>
<td><input name="products['x'].category.cname" value="bbb" /></td>
</tr>
<tr>
<td><input name="products['y'].name" value="ccc" /></td>
<td><input name="products['y'].price" value="222" /></td>
<td><input name="products['y'].category.cname" value="bbb" /></td>
</tr>
<tr>
<td><input name="products['z'].name" value="eee" /></td>
<td><input name="products['z'].price" value="333" /></td>
<td><input name="products['z'].category.cname" value="bbb" /></td>
</tr>
</tbody>
</table>
</form>
```

3.Controller代码
```
@RequestMapping("addMapProduct")
	public void addMapProduct(ProductMap productMap) {
			for(Entry<String, Product> entry : productMap.getProducts().entrySet())
			System.out.println(entry.getKey() + ": " + 
			entry.getValue().getName() + " - " +
			entry.getValue().getPrice()+ " - "+
			entry.getValue().getCategory().getCname() );
		}
```

运行访问http://localhost:8080/SpringMVC/Map_bind.jsp提交表单
后台运行打印结果为：
x: aaa - 111.0 - bbb
y: ccc - 222.0 - bbb
z: eee - 333.0 - bbb

**好像绑定常用的就这些，实际用起来要复杂不少，还是后面用到了再实际的练习。**

## 7. 客户端跳转

之前用的`/index`跳转`index.jsp`,还是`/addProduct `跳转到`showProduct.jsp`，都是服务端跳转。
服务端跳转：浏览器的地址是不变的

现在用客户端跳转来试试
在indexController中增加
```
@RequestMapping("/jump")
	public ModelAndView jump() {
		ModelAndView mav = new ModelAndView("redirect:/index");
		return mav;
	}
```
运行后在浏览器中输入http://localhost:8080/SpringMVC/jump会跳转到index页面。

**最后附上Github代码：**https://github.com/xxysama/SpringMVC





