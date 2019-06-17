
### <center>很重要的问题</center>
<center><img src="/assets/blogImg/duplicate.jpg" width="40%" height="40%"></center>

## 1. Set去重
经常被问到的list如何去重，都知道set集合的特点就是没有重复的元素。如果集合中的数据类型是**基本数据类型**，
<!--more-->
可以直接将list集合转换成set，就会自动去除重复的元素，这个就相对比较简单。

```
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		List list = new ArrayList();
		list.add(1);
		list.add(4);
		list.add(6);
		list.add(5);
		list.add(4);
		list.add(1);
		System.out.println(list);
		
		Set set = new HashSet();
		List newList = new ArrayList();
		set.addAll(list);
		newList.addAll(set);
		System.out.println(newList);
	}
}
```
运行结果：
[1, 4, 6, 5, 4, 1]
[1, 4, 5, 6]

**但是如果集合中存的是对象类型就不能简单的和Set互转去重了**

定义一个对象类
```
public class People {
	private String name;
    private String phoneNumber;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "People [name=" + name + ", phoneNumber=" + phoneNumber + "]";
	} 
}
```

如果直接使用上面的Set去重
```
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {

	public static void main(String[] args) {

		List<People> list = new ArrayList<>();
		list.add(new People("张三","111"));
		list.add(new People("李四","222"));
		list.add(new People("王五","333"));
		list.add(new People("张三","111"));
		list.add(new People("张三","222"));
		
		Set<People> set = new HashSet<>();
		set.addAll(list);
		System.out.println(list);
		System.out.println(set.toString());
	}
}
```
输出结果：
[People [name=张三, phoneNumber=111], People [name=李四, phoneNumber=222], People [name=王五, phoneNumber=333], People [name=张三, phoneNumber=111], People [name=张三, phoneNumber=222]]

[People [name=张三, phoneNumber=111], People [name=张三, phoneNumber=111], People [name=王五, phoneNumber=333], People [name=张三, phoneNumber=222], People [name=李四, phoneNumber=222]]

**可以看出并没有去重**
**当list集合中存储的是对象时，我们需要在对象的实体类中去重写equals()方法和hashCode()方法**
```
public class People {
	private String name;
    private String phoneNumber;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public People( String name, String phoneNumber) {
		super();
        this.name = name;
        this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "People [name=" + name + ", phoneNumber=" + phoneNumber + "]";
	}
	@Override
	public boolean equals(Object obj) {
		People p = (People)obj;
		return name.equals(p.name) && phoneNumber.equals(p.phoneNumber);
	}
	@Override
	public int hashCode() {
		String str = name + phoneNumber;
		return str.hashCode();
	}    
}
```
运行测试结果如下：

[People [name=张三, phoneNumber=111], People [name=李四, phoneNumber=222], People [name=王五, phoneNumber=333], People [name=张三, phoneNumber=111], People [name=张三, phoneNumber=222]]

[People [name=王五, phoneNumber=333], People [name=李四, phoneNumber=222], People [name=张三, phoneNumber=222], People [name=张三, phoneNumber=111]]

**可以发现重复的张三111已经去掉了。**

其实HashSet是由HashMap来实现的，只要看HashSet的构造方法和add方法就能理解了。
```
public HashSet() {
  map = new HashMap<>();
}
/**
* 显然，存在则返回false，不存在的返回true
*/
public boolean add(E e) {
  return map.put(e, PRESENT)==null;
}
```
HashSet的去重复就是根据HashMap实现的，而HashMap的实现又完全依赖于hashcode和equals方法。

> String中的equals()方法和hashCode()方法源码

**equals()**
```
public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof String) {
            String anotherString = (String)anObject;
            int n = count;
            if (n == anotherString.count) {
                char v1[] = value;
                char v2[] = anotherString.value;
                int i = offset;
                int j = anotherString.offset;
                while (n-- != 0) {
                    if (v1[i++] != v2[j++])
                        return false;
                }
                return true;
            }
        }
        return false;
    }
```
比较两个对象时，首先先去判断两个对象是否具有相同的地址，如果是同一个对象的引用，则直接放回true；如果地址不一样，则证明不是引用同一个对象，接下来就是挨个去比较两个字符串对象的内容是否一致，完全相等返回true，否则false。

**hashCode()**
```
public int hashCode() {
        int h = hash;
        if (h == 0 && count > 0) {
            int off = offset;
            char val[] = value;
            int len = count;
            for (int i = 0; i < len; i++) {
                h = 31*h + val[off++];
            }
            hash = h;
        }
        return h;
    }
```
**hashCode()官方定义：**
hashcode方法返回该对象的哈希码值。支持该方法是为哈希表提供一些优点，例如，java.util.Hashtable 提供的哈希表。

## 2. 使用java8新特性stream进行去重 

Java8 Stream distinct（）， distinct（）返回由该流的不同元素组成的流。distinct（）是Stream接口的方法。distinct（）使用hashCode（）和equals（）方法来获取不同的元素。
```
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class Test {

	public static void main(String[] args) {
		List list = new ArrayList();
		list.add(1);
		list.add(4);
		list.add(6);
		list.add(5);
		list.add(4);
		list.add(1);
		System.out.println(list);
		
		List newList = (List) list.stream().distinct().collect(Collectors.toList());
		System.out.println(newList);
	}
}
```
运行结果：
[1, 4, 6, 5, 4, 1]
[1, 4, 6, 5]

**若果储存的是对象，也需要重写equals()方法和hashCode()方法**

不重写方法示例
```
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

	public static void main(String[] args) {

		List<People> list = new ArrayList<>();
		list.add(new People("张三","111"));
		list.add(new People("李四","222"));
		list.add(new People("王五","333"));
		list.add(new People("张三","111"));
		list.add(new People("张三","222"));
		
		List newList = (List) list.stream().distinct().collect(Collectors.toList());
		System.out.println(list);
		System.out.println(newList.toString());
	}
}
```
运行结果：
[People [name=张三, phoneNumber=111], People [name=李四, phoneNumber=222], People [name=王五, phoneNumber=333], People [name=张三, phoneNumber=111], People [name=张三, phoneNumber=222]]

[People [name=张三, phoneNumber=111], People [name=李四, phoneNumber=222], People [name=王五, phoneNumber=333], People [name=张三, phoneNumber=111], People [name=张三, phoneNumber=222]]

**重写equals()方法和hashCode()方法当然是去重成功了就不展示了。这里可以看到不像Set，用stream储存顺序并未改变**
**用Set也可以按照原顺序储存**
```
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		List list = new ArrayList();
		list.add(1);
		list.add(4);
		list.add(6);
		list.add(5);
		list.add(4);
		list.add(1);
		System.out.println(list);
		
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Object in:list) {
            if(set.add(in)){
                newList.add(in);
            }
        }
		System.out.println(newList);
	}
}
```
运行结果：
[1, 4, 6, 5, 4, 1]
[1, 4, 6, 5]

**对象时**
```
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {

	public static void main(String[] args) {

		List<People> list = new ArrayList<>();
		list.add(new People("张三","111"));
		list.add(new People("李四","222"));
		list.add(new People("王五","333"));
		list.add(new People("张三","111"));
		list.add(new People("张三","222"));
		
		Set<People> set = new HashSet<>();
		
		List newList = new ArrayList();
		for (People in:list) {
            if(set.add(in)){
                newList.add(in);
            }
        }
		System.out.println(list);
		System.out.println(set.toString());
		System.out.println(newList);
	}
}
```
运行结果：
[People [name=张三, phoneNumber=111], People [name=李四, phoneNumber=222], People [name=王五, phoneNumber=333], People [name=张三, phoneNumber=111], People [name=张三, phoneNumber=222]]

[People [name=王五, phoneNumber=333], People [name=李四, phoneNumber=222], People [name=张三, phoneNumber=222], People [name=张三, phoneNumber=111]]

[People [name=张三, phoneNumber=111], People [name=李四, phoneNumber=222], People [name=王五, phoneNumber=333], People [name=张三, phoneNumber=222]]



