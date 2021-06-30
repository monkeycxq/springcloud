一、扎实java基础
1.Java运行时环境JVM

2.java类库
（1）第一级别：精读源码
    该级别包含的包如下：
    java.io
    java.lang
    java.util
（2）第二级别：深刻理解
   该级别包含的包如下：
   java.lang.reflect
   java.net
   javax.net.*
   java.nio.*
   java.util.concurrent.*
（3）第三级别：会用即可
   该级别包含的包如下：
   java.lang.annotation
   javax.annotation.*
   java.lang.ref
   java.math
   java.rmi.*
   javax.rmi.*
   java.security.*
   javax.security.*
   java.sql
   javax.sql.*
   javax.transaction.*
   java.text
   javax.xml.*
   org.w3c.dom.*
   org.xml.sax.*
   javax.crypto.*
   javax.imageio.*
   javax.jws.*
   java.util.jar
   java.util.logging
   java.util.prefs
   java.util.regex
   java.util.zip
（4）第四级别：请无视它

3.java自带的开发工具
    这些开发工具主要就是辅助你开发的了，javac应该是最常用的一个了，虽然你几乎不用手动执行它。
    此外，其实还有一些比较实用的工具，可以帮助你排查问题。而且有的面试官，也会问你这类问题，
    比如问你平时都用什么工具排查问题。我觉得比较实用的几个工具主要有jmap、jconsole、jstack、jvisualvm，
    至于这几个工具有什么作用，我这里就不提了，如果你要了解这些命令的详细内容，可以去谷歌或者官网上找，还是非常好找的。
    当然，如果你有兴趣的话，也可以自己去JDK的bin目录下找找，看有没有什么更好玩的工具。
    
    JConsole 是一个内置 Java 性能分析器，可以从命令行或在 GUI shell 中运行。可以轻松地使用 JConsole来监控 Java 应用程序性能和跟踪 Java 中的代码。
    
    Jvisualvm 升级版的jConsole。
    
    Jmap 用来显示Java进程的内存映射。