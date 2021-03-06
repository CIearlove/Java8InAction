# 第一章 为什么要关心java8

* java怎么又变了
* 日新月异的计算应用背景：多核和处理大型数据集
* 改进的压力：函数式比命令式更适应新的体系架构
* java8的核心新特性：**Lambda（匿名函数）**、**流**、**默认方法**

1. 更简洁的代码，这些代码读起来更接近问题的描述
2. 更简单的代码，更简单地使用了多核处理器
   * java8中提供了一个新的API(称为**“流”**，Stream)，它能支持许多处理数据的并行操作，其思路和在数据库查询语言中的思路类似——用更高级的方式表达想要的东西，而由“实现”（在这里是Streams库）来选择最佳低级执行机制。
   * java8里面将代码传递给方法的功能还让我们能够使用一整套新技巧，通常称为**函数式编程**

## 1.1 java怎么还在变
### 1.1.1 java在编程语言生态系统中的位置
* java是一个精心设计的面向对象的语言，有许多有用的库。有了集成的线程和锁的支持，它从第一天起就支持小规模并发。
* 将java编译成JVM字节码（一种很快被每一种浏览器支持的虚拟机代码）意味着它成为了互联网`applet`（小应用）的首选。
* 程序员越来越多地要处理所谓的大数据，并希望利用多核计算机或计算集群来有效地处理。这意味着需要使用并行处理——java以前对此并不支持。

### 1.1.2 流处理

* 第一个编程概念是<font face=楷体>流处理</font>。

* <font face=楷体>流</font>是一系列数据项，一次只生成一项。程序可以从输入流中一个一个读取数据项，然后以同样的方式将数据项写入输入流。

* 一个实际的例子是在Unix或Linux中，很多程序都从标准输入（Java中的System.in）读取数据，然后把结果写入标准输出（Java中的System.out）。

* Unix命令：会（假设file1和file2中每行都只有一个词）先把字母转换成小写字母，然后打印出按照词典排序出现在最后的三个单词。

  <br>![Unix命令.png](https://s2.ax1x.com/2019/07/02/ZYVOdf.png)

* 我们说sort把一个行流作为输入，产生了另一个行<font face=楷体>流</font>（进行排序）作为输出，如图1-2所示。请注意在Unix中，命令（cat,tr,sort和tail）是同时执行的，这样sort就可以在cat或tr完成前先处理头几行。<br>

  ![操作流的Unix命令.png](https://s2.ax1x.com/2019/07/02/ZYVzWQ.png)

* 基于这一思想，Java8在`java.util.stream`中添加了一个`Stream API`;`Stream<T>`就是一系列`T`类型的项目。`Stream API`的很多方法可以链接起来形成一个复杂的流水线，就像先前例子里面链接起来的`Unix`命令一样。

* 推动这种做法有两个好处：

     1.你可以站在更高的抽象层次上写`Java8`程序了：思路变成把这样的流变成那样的流（就像写数据库查询语句时的那种思路），而不是一次只处理一个项目。

     2.Java8可以透明地把输入的不相关部分拿到几个CPU内核上去分别执行你的`Stream`操作流水线——这是<font face=楷体>几乎免费</font>的并行，用不着费劲搞Thread了。
     
     

### 1.1.3 用行为参数化把代码传递给方法

* 另一个编程概念是通过API来传递代码的能力。

* 在Unix的例子里，你可能想告诉`sort`命令使用自定义排序。虽然`sort`命令支持通过命令行参数来执行各种预定义类型的排序，比如倒序，但这毕竟有限。

* 比方说，你有一堆发票代码，格式类似于`2013UK0001`、`2014US0002`....前面四位数代表年份，接下来的两个字母代表国家，最后四位是客户的代码。你可能想按照年份、国家、客户代码对发票进行排序。你真正想要的是，能够给`sort`命令一个参数让用户定义顺序：给`sort`命令传递一段独立代码。

* java8增加了把方法（你的代码）作为参数传递给另一个方法的能力。

* `Stream API`就是构建在通过传递代码使操作行为实现参数化的思想上的，当把`compareUsingCustomerId`传进去，你就把`sort`的行为参数化了。<br>

  ![Ztn3Ux.png](https://s2.ax1x.com/2019/07/03/Ztn3Ux.png)

###  1.1.4 并行与共享的可变数据

###  1.1.5 java需要演变



## 1.2 java中的函数

* java程序可能操作的值

  * 原始值：42（int类型）、3.14（double类型）

  * 对象的引用

  * 函数（java8中新增，值的一种形式）

    
### 1.2.1 方法和Lambda作为一等公民

* 我们介绍java8的第一个新功能是<font face=楷体>方法引用</font>

  比方说，你想要的筛选一个目录的所有隐藏文件，老写法：

  ```java 
  /*
  		 * 接口是不可以实例化的。
  		 * new FileFilter(){}是匿名内部类的写法。
  		 * 它实例化了一个匿名内部类，而这个匿名内部类实现了FileFilter接口。
  		 */
  		/*
  		File[] hiddenFiles = new File(".").listFiles(new FileFilter(){
  			public boolean accept(File file){
  				return file.isHidden();
  			}
  		});
  		*/
  ```

  

  如今在Java8里，你可以把代码重写成这个样子：

  ```JAVA 
  /*
  		 * java8里，你可以把代码重写成这个样子：
  		 */
  		File[] hiddenFiles = new File(".").listFiles(File::isHidden);
  ```

  

  你已经有了函数`isHidden`，因此只需用Java8的<font face=楷体>方法引用</font>：：语法（即“把这个方法作为值”）将其传给`listFiles`方法。

* Lambda——匿名函数

  除了允许函数成为一等公民外，Java8还体现了更广义的将<font face=楷体>函数</font>作为<font face=楷体>值  </font>的思想，包括Lambda（匿名函数）。

  你现在可以写`(int x)-> x+1`，表示“调用时给定参数`x`，就返回`x+1`值的函数”。

###  1.2.2 传递代码：一个例子

完整代码：[筛选苹果](https://github.com/java8/Java8InAction/blob/master/src/main/java/lambdasinaction/chap1/FilteringApples.java)

* 筛选出绿苹果

  ```java 
  public static List<Apple> filterGreenApples(List<Apple> inventory){
          List<Apple> result = new ArrayList<>();
          for(Apple apple:inventory){
              if("green".equals(apple.getColor())){
                  result.add(apple);
              }
          }
          return result;
      }
  ```

* 筛选出超过150克的苹果

  ```java 
  public static List<Apple> filterHeavyApples(List<Apple> inventory){
          List<Apple> result = new ArrayList<>();
          for(Apple apple:inventory){
              if(apple.getWeight() > 150){
                  result.add(apple);
              }
          }
          return result;
      }
  ```

这两个方法只有一行不同：if语句那行。

* `Java8`会把条件代码作为参数传递进去，这样可以避免`filter`方法出现重复的代码。

  ```java 
  public static boolean isGreenApple(Apple apple){
          return "green".equals(apple.getColor());
      }
  
      public  static boolean isHeavyApple(Apple apple){
          return apple.getWeight() > 150;
      }
  
      public interface Predicate<T>{
          boolean test(T t);
      }
  
      static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p){
          List<Apple> result = new ArrayList<>();
          for(Apple apple:inventory){
              if(p.test(apple)){
                  result.add(apple);
              }
          }
          return result;
      }
  ```

  

* 使用`isGreenApple`方法和`isHeavyApple`方法：

  ```java 
  List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                  new Apple(155,"green"),
                  new Apple(120,"red"));
  
  List<Apple> greenApples = filterApples(inventory, FilteringApples::isGreenApple);
  System.out.println(greenApples);
  
  List<Apple> heavyApples = filterApples(inventory, FilteringApples::isHeavyApple);
  System.out.println(heavyApples);
  ```

* 输出结果：

  ```JAVA 
  [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
  [Apple{color='green', weight=155}]
  ```

* 什么是谓词：

  前面的代码传递了方法`FilteringApples::isGreenApple`给`filterApples`方法，后者希望接受一个`Predicate<Apple>`参数。

  **谓词（predicate）**在数学上常常用来代表一个类似函数的东西，它接受一个参数值，并返回`true`或`false`。

  你在后面会看到，Java8允许你写`Function<Apple,Boolean>`，但用`Predicate<Apple>`是更标准的方式，效率也会更高一些。

### 1.2.3 从传递方法到Lambda 

* 把方法作为值来传递显然很有用，但要是为类似于`isGreenApple`和`isHeavyApple`这种可能只用一两次的短方法写一堆定义就优点烦人了。Java8中可以使用匿名函数（Lambda）：

  ```JAVA 
  filterApples(inventory, (Apple a)->"green".equals(a.getColor()));
  
  filterApples(inventory, (Apple a)->a.getWeight()>150);
  ```

  你甚至不需要为只用一次的方法写定义。

* 但要是Lambda的长度多于几行的话，那你还是应该用方法引用来指向一个有描述性名称的方法，而不是使用匿名类的Lambda。