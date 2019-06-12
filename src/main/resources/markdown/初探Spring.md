
### <center>真的是绿的我发慌。</center>
<center><img src="/assets/blogImg/Spring.jpg" width="35%" height="35%"></center>

## 1. Spring IOC/DI

**使用eclipse进行操作,先学会使用，仅此而已。**

Spring是一个基于IOC和AOP的结构J2EE系统的框架
<!--more-->
**IOC 反转控制**是Spring的基础，Inversion Of Control
简单说就是创建对象由以前的程序员自己new 构造方法来调用，变成了交由Spring创建对象
**DI 依赖注入**Dependency Inject. 简单地说就是拿到的对象的属性，已经被注入好相关值了，直接使用即可。

### 1.1 导入相关的Spring的库

库比较多，第一次用的时候直接百度，后面留备份吧。

### 1.2 创建pojo对象及配置文件

1.创建pojo对象
```
package com.x2y.pojo;

public class Category {
	private int id;
	private String name;
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
}
```

2.创建applicationContext.xml文件

在src目录下新建applicationContext.xml文件
applicationContext.xml是Spring的核心配置文件，**通过关键字c即可获取Category对象，该对象获取的时候，即被注入了字符串"category 1“到name属性中**
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:aop="http://www.springframework.org/schema/aop"
    xmlns:tx="http://www.springframework.org/schema/tx"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="
   http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
   http://www.springframework.org/schema/aop
   http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
   http://www.springframework.org/schema/tx
   http://www.springframework.org/schema/tx/spring-tx-3.0.xsd
   http://www.springframework.org/schema/context     
   http://www.springframework.org/schema/context/spring-context-3.0.xsd">
  
    <bean name="c" class="com.x2y.pojo.Category">
        <property name="id" value="77" />
        <property name="name" value="category 1" />
    </bean>
  
</beans>
```

### 1.3 测试获取对象
```
package com.x2y.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.x2y.pojo.Category;

public class TestSpring {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {"applicationContext.xml"});
		Category c = (Category) context.getBean("c");
		
		System.out.println(c.getId());
		System.out.println(c.getName());
	}
}
```
运行结果：
category 1
77

就获取对象的方式进行比较

**传统的方式：**
通过new 关键字主动创建一个对象
`Category c = new Category();`

**IOC方式**
对象的生命周期由Spring来管理，直接从Spring那里去获取一个对象。 IOC是反转控制 (Inversion Of Control)的缩写，就像控制权从本来在自己手里，交给了Spring。

## 2. Spring 注入对象

上面对Category的name属性注入了"category 1"字符串
线面对Product对象，注入一个Category对象

### 2.1 创建Product对象
```
package com.x2y.pojo;

public class Product {
	private int id;
	private String name;
	private Category category;
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
}
```

### 2.2 写入配置xml文件

类似配置Category对象一般，配置语句如下，配置的name要和pojo中的属性名称相同对应。
```
	<bean name="p" class="com.x2y.polo.Product">
  		<property name="name" value="product 1"/>
  		<property name="category" ref="c"/>
  	</bean>

```

### 3.3 测试获取Product对象
```
package com.x2y.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.x2y.pojo.Category;
import com.x2y.pojo.Product;

public class TestSpring {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {"applicationContext.xml"});
		Product product = (Product) context.getBean("p");
		System.out.println(product.getId());
		System.out.println(product.getName());
		System.out.println(product.getCategory().getName());
	}
}
```
运行结果：
0
product 1
category 1

**这次并没有配置Product的id属性，但是被Spring初始化了为0。**
**还有xml配置文件中<bean>标签的id属性和name属性基本上没有什么区别，但是使用id会更加符合规范，因为xml中id要求是唯一的**

## 3. Spring 注解方式 IOC/DI

### 3.1 什么是注解？
用一个词就可以描述注解，那就是元数据，即一种描述数据的数据。所以，可以说注解就是源代码的元数据。比如，下面这段代码：
```
@Override
public String toString() {

return "This is String Representation of current object.";
}
```
上面的代码中，我重写了`toString()`方法并使用了`@Override`注解。但是，即使我不使用@Override注解标记代码，程序也能够正常执行。
那么，该注解表示什么？这么写有什么好处吗？事实上，`@Override`告诉编译器这个方法是一个重写方法(描述方法的元数据)，如果父类中不存在该方法，编译器便会报错，提示该方法没有重写父类中的方法。如果我不小心拼写错误，例如将`toString()`写成了`toStrring(){double r}`，而且我也没有使用`@Override`注解，那程序依然能编译运行。但运行结果会和我期望的大不相同。现在我们了解了什么是注解，并且使用注解有助于阅读程序。

Annotation是一种应用于类、方法、参数、变量、构造器及包声明中的特殊修饰符。它是一种由JSR-175标准选择用来描述元数据的一种工具。

### 3.2 配置applicationContext.xml文件

注释掉原来的注入
```
<context:annotation-config/>
    <bean name="c" class="com.how2java.pojo.Category">
        <property name="name" value="category 1" />
    </bean>
    <bean name="p" class="com.how2java.pojo.Product">
        <property name="name" value="product1" />
<!--         <property name="category" ref="c" /> -->
    </bean>
```

### 3.3 加上注解

1.在Product.java的category属性前加上@Autowired注解
```
@Autowired
	private Category category;
```

**测试代码普通配置注入一样，结果也是一样的**

**除了在属性前加上@Autowired 这种方式外，也可以在setCategory方法前加上@Autowired，这样来达到相同的效果**

2.除了@Autowired之外，@Resource也是常用的手段
```
@Resource(name="c")
private Category category;
```

## 3. 对Bean的注解

### 3.1 配置applicationContext.xml文件

将<bean>标签和注解配置删除，添加如下语句，也就是配置扫描路径
```
<context:component-scan base-package="com.x2y.pojo"/>
```

### 3.2 为Class类添加@Component注解

为Product类加上@Component注解，即表明此类是bean**之前的Autowired注解保留不做更改**
```
@Component("p")
public class Product {
```
为Category 类加上@Component注解
```
@Component("c")
public class Category {
```

**需要注意的是，因为配置从applicationContext.xml中移出来了，所以属性初始化放在属性声明上进行了。如果不初始化默认为null**
```
private String name="product 1";
private String name="category 1";
```

**测试代码没有变化，结果也一致。**

## 4. Spring AOP
>  参考理解：[轻松理解AOP思想(面向切面编程)](https://www.cnblogs.com/Wolfmanlq/p/6036019.html)

在面向切面编程的思想里面，把功能分为核心业务功能，和周边功能。
所谓的核心业务，比如登陆，增加数据，删除数据都叫核心业务
所谓的周边功能，比如性能统计，日志，事务管理等等

周边功能在Spring的面向切面编程AOP思想里，即被定义为切面

在面向切面编程AOP的思想里面，核心业务功能和切面功能分别独立进行开发
然后把切面功能和核心业务功能 "编织" 在一起，这就叫AOP
![Spring-AOP](/assets/blogImg/Spring-AOP.png)

### 4.1 业务类准备

1.
```
package com.x2y.service;

public class ProductService {
	public void doSerivice() {
		System.out.println("doSomeService!");
	}
}
```

2.在引入切面之前调用该业务类
```
package com.x2y.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.x2y.service.ProductService;

public class TestSpring {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {"applicationContext.xml"});
		ProductService ps = (ProductService) context.getBean("ps");
		ps.doSerivice();
	}
}
```

### 4.2 准备日志切面LoggerAspect
```
package com.x2y.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class LoggerAspect {
	public Object log(ProceedingJoinPoint joinPoint)throws Throwable{
		System.out.println("start log:"+joinPoint.getSignature().getName());
		Object object = joinPoint.proceed();
		System.out.println("end log:"+joinPoint.getSignature().getName());
		return object;
	}
}
```

### 4.3 配置applicationContext.xml文件
**常规配置，不使用注解**
<aop:pointcut
指定右边的核心业务功能

<aop:aspect
指定左边的辅助功能

<aop:config
把业务对象与辅助功能编织在一起。

`execution(* com.x2y.service.ProductService.*(..))`
这表示对满足如下条件的方法调用，进行切面操作：
* 返回任意类型
com.x2y.service.ProductService.* 包名以 com.x2y.service.ProductService 开头的类的任意方法
(..) 参数是任意数量和类型

```
  	<bean name="ps" class="com.x2y.service.ProductService"></bean>
  	
  	<bean name="loggerAspect" class="com.x2y.aspect.LoggerAspect"></bean>
  	
  	<aop:config>
  		<aop:pointcut expression="execution(* com.x2y.service.ProductService.*(..))" id="loggerCutpoint"/>
  		<aop:aspect id="logAspect" ref="loggerAspect">
  			<aop:around method="log" pointcut-ref="loggerCutpoint"/>
  		</aop:aspect>
  	</aop:config>
```


**OK,现在运行上面的测试代码，运行结果如下：**
start log:doSerivice
doSomeService!
end log:doSerivice

**再简单做个计时效率统计**
1.aspect
```
package com.x2y.aspect;

import java.text.SimpleDateFormat;
import org.aspectj.lang.ProceedingJoinPoint;

public class CountTime {
	public Object count(ProceedingJoinPoint joinPoint)throws Throwable{
		//设置时间格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long start = System.currentTimeMillis();
		
		System.out.println("-----开始统计-----"+df.format(start));
		Object object = joinPoint.proceed();
		
		//模拟逻辑层运行时间
		Thread.sleep(3000);
		
		long end = System.currentTimeMillis();
		System.out.println("-----统计结束-----"+df.format(end));
		System.out.println("花费时间为:"+ (end-start));
		return object;
	}
}
```

2.配置xml
```
	<bean name="ps" class="com.x2y.service.ProductService"></bean>
  	
  	<bean name="loggerAspect" class="com.x2y.aspect.LoggerAspect"></bean>
  	
  	<bean name="countTime" class="com.x2y.aspect.CountTime"></bean>
  	
  	<aop:config>
  		<aop:pointcut expression="execution(* com.x2y.service.ProductService.*(..))" id="loggerCutpoint"/>
  		<aop:pointcut expression="execution(* com.x2y.service.ProductService.*(..))" id="countTimeCutpoint"/>
  		
  		<aop:aspect id="logAspect" ref="loggerAspect">
  			<aop:around method="log" pointcut-ref="loggerCutpoint"/>
  		</aop:aspect>
  		<aop:aspect id="countAspect" ref="countTime">
  			<aop:around method="count" pointcut-ref="countTimeCutpoint"/>
  		</aop:aspect>
  	</aop:config>
  	
```

3.运行结果：
start log:doSerivice
-----开始统计-----2019-05-16 14:25:48
doSomeService!
-----统计结束-----2019-05-16 14:25:51
花费时间为:3001
end log:doSerivice

**顺便这里有两个切面，那么怎么规定他们的顺序呢？**
可以在使用xml配置的时候，在定义切面时候，可以加上order表示先后顺序，**越小越先执行 **
`<aop:aspect id="aspect1" ref="Handler1" order="1"> `
`<aop:aspect id="aspect2" ref=“Handler2" order="2"> `
要是不指定order，就按定义的先后顺序来执行

## 5. 注解方式的AOP

1.首先将xml配置都注释，添加扫描路径
```
<context:component-scan base-package="com.x2y.aspect"/>
<context:component-scan base-package="com.x2y.service"/>
<aop:aspectj-autoproxy/> 
```

2.注解配置业务类
```
package com.x2y.service;

import org.springframework.stereotype.Component;

@Component("ps")
public class ProductService {
	public void doSerivice() {
		System.out.println("doSomeService!");
	}
}
```

3.注解配置切面

@Aspect 注解表示这是一个切面
@Component 表示这是一个bean,由Spring进行管理
@Around(value = "execution(* com.how2java.service.ProductService.*(..))") 表示对com.how2java.service.ProductService 这个类中的所有方法进行切面操作

```
package com.x2y.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
	
	@Around(value = "execution(* com.x2y.service.ProductService.*(..))")
	public Object log(ProceedingJoinPoint joinPoint)throws Throwable{
		System.out.println("start log:"+joinPoint.getSignature().getName());
		Object object = joinPoint.proceed();
		System.out.println("end log:"+joinPoint.getSignature().getName());
		return object;
	}
}
```

时间统计Aspect相同
```
package com.x2y.aspect;

import java.text.SimpleDateFormat;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CountTime {
	
	@Around(value = "execution(* com.x2y.service.ProductService.*(..))")
	public Object count(ProceedingJoinPoint joinPoint)throws Throwable{
		//设置时间格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long start = System.currentTimeMillis();
		
		System.out.println("-----开始统计-----"+df.format(start));
		Object object = joinPoint.proceed();
		
		//模拟逻辑层运行时间
		Thread.sleep(3000);
		
		long end = System.currentTimeMillis();
		System.out.println("-----统计结束-----"+df.format(end));
		System.out.println("花费时间为:"+ (end-start));
		return object;
	}
}
```

**运行测试结果**
-----开始统计-----2019-05-16 14:47:20
start log:doSerivice
doSomeService!
end log:doSerivice
-----统计结束-----2019-05-16 14:47:23
花费时间为:3004

## 6. 补充问题

**1.发现切面的运行顺序和前面xml配置的不同**
用@Order注解切面，数字越小优先级越高

```
@Order(1)
public class LoggerAspect {

@Order(2)
public class CountTime {
```
运行结果：
start log:doSerivice
-----开始统计-----2019-05-16 16:59:18
doSomeService!
-----统计结束-----2019-05-16 16:59:21
花费时间为:3001
end log:doSerivice

**2.切面里面有多个方法的时候优先级是？**
这里对方法的注解使用的是@Around

**其实还有@Before，@AfterReturning， @After，@AfterThrowing，下面新建一个日志统计来测试**

1.业务逻辑Service
```
package com.x2y.service;

import org.springframework.stereotype.Service;

@Service("acountService")
public class AcountService {
	
	public void saveAccount() {
		System.out.println("执行了保存");
	}
}
```

2.切面Logger，并且进行注解
```
package com.x2y.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class CountLogger {
	/**
     * 切入点表达式
     */
    @Pointcut("execution(* com.x2y.service.AcountService.*(..))")
    private void pt1(){};
 
    /**
     * 前置通知
     */
    @Before("execution(* com.x2y.service.AcountService.*(..))")
    public void beforeAdvice(){
        System.out.println("before:开始记录日志...");
    }
    /**
     * 后置通知
     */
    @AfterReturning("execution(* com.x2y.service.AcountService.*(..))")
    public void afterAdvice(){
        System.out.println("afterAdvice......");
    }
    /**
     * 最终通知
     */
    @After("pt1()")//注意:不要忘记写括号
    public void finallyAdvice(){
        System.out.println("finallyAdvice.........");
    }
 
    /**
     * 异常通知
     */
    @AfterThrowing("pt1()")
    public void exceptionAdvice(){
        System.out.println("exceptionAdvice....");
    }
 
 
    /**
     * 环绕通知
     */
    @Around("pt1()")
    public Object aroundPringLog(ProceedingJoinPoint pjp){
        Object obj = null;
        try {
            Object[] args = pjp.getArgs();// 得到方法所需的参数
            System.out.println("环绕通知:前置...");
            //明确调用业务层方法
            obj = pjp.proceed(args);
            System.out.println("环绕通知:后置...");
            return obj;
        } catch (Throwable throwable) {
            System.out.println("环绕通知:异常...");
            throw new RuntimeException(throwable);
        }finally {
            System.out.println("环绕通知:最终...");
        }
    }
}
```

运行发现报错`0 can't find referenced pointcut pt1`

在jdk1.8的环境下，使用Spring的annotation实现AOP时，会报错can't find referenced pointcut。 原因是aspectjweaver.jar的版本太低，更换1.8版本就好了。

百度下载aspectjweaver 1.8.9导入项目内

**运行结果：**（ojbk）
环绕通知:前置...
before:开始记录日志...
执行了保存
finallyAdvice.........
afterAdvice......
环绕通知:后置...
环绕通知:最终...

**从上可以看到注解的运行顺序**

**3. @Component, @Repository, @Service的区别**
> [@Component, @Repository, @Service的区别](https://github.com/giantray/stackoverflow-java-top-qa/blob/master/contents/whats-the-difference-between-component-repository-service-annotations-in.md)
这次我注解Service没有用@Component，而是用的@Service

`@Component`是一个通用的Spring容器管理的单例bean组件。而`@Repository`, `@Service`, `@Controller`就是针对不同的使用场景所采取的特定功能化的注解组件。

当你的一个类被`@Component`所注解，那么就意味着同样可以用`@Repository`, `@Service`, `@Controller`来替代它，同时这些注解会具备有更多的功能，而且功能各异。

如果你不知道要在项目的业务层采用`@Service`还是`@Component`注解。那么，`@Service`是一个更好的选择。

| 注解 | 含义 |
| --- | :-: |
| @Component | 最普通的组件，可以被注入到spring容器进行管理 |
| @Repository | 作用于持久层 |
| @Service | 作用于业务逻辑层 |
| @Controller | 作用于表现层（spring-mvc的注解） |

这几个注解几乎可以说是一样的：因为被这些注解修饰的类就会被Spring扫描到并注入到Spring的bean容器中。

这里，有两个注解是不能被其他注解所互换的：

*   `@Controller` 注解的bean会被spring-mvc框架所使用。
*   `@Repository` 会被作为持久层操作（数据库）的bean来使用


**总的来说就是@Component是通用注解，其他三个注解是这个注解的拓展，并且具有了特定的功能**
*   `@Repository`注解在持久层中，具有将数据库操作抛出的原生异常翻译转化为spring的持久层异常的功能。
*   `@Controller`层是spring-mvc的注解，具有将请求进行转发，重定向的功能。
*   `@Service`层是业务逻辑层注解，这个注解只是标注该类处于业务逻辑层。
