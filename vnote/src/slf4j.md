slf4j (Simple Logging Facade for Java)
=======================

## 1.简单回顾门面模式
slf4j是门面模式的典型应用，因此在讲slf4j前，我们先简单回顾一下门面模式，
门面模式，其核心为外部与一个子系统的通信必须通过一个统一的外观对象进行，使得子系统更易于使用。用一张图来表示门面模式的结构为：

![facade model](https://images2018.cnblogs.com/blog/801753/201803/801753-20180321204740208-1670144043.png)

门面模式的核心为Facade即门面对象，门面对象核心为几个点：

- 知道所有子角色的功能和责任
- 将客户端发来的请求委派到子系统中，没有实际业务逻辑
- 不参与子系统内业务逻辑的实现

## 2. 我们为什么要使用slf4j
```
我们自己的系统中使用了logback这个日志系统
我们的系统使用了A.jar，A.jar中使用的日志系统为log4j
我们的系统又使用了B.jar，B.jar中使用的日志系统为slf4j-simple

这样，我们的系统就不得不同时支持并维护logback、log4j、slf4j-simple三种日志框架，非常不便。
```

解决这个问题的方式就是引入一个适配层，由适配层决定使用哪一种日志系统，而调用端只需要做的事情就是打印日志而不需要关心如何打印日志，slf4j或者commons-logging就是这种适配层，slf4j是本文研究的对象。

从上面的描述，我们必须清楚地知道一点：slf4j只是一个日志标准，并不是日志系统的具体实现。理解这句话非常重要，slf4j只做两件事情：

- 提供日志接口
- 提供获取具体日志对象的方法
slf4j-simple、logback都是slf4j的具体实现，log4j并不直接实现slf4j，但是有专门的一层桥接slf4j-log4j12来实现slf4j。

## 3. slf4j应用举例

上面讲了，slf4j的直接/间接实现有slf4j-simple、logback、slf4j-log4j12，我们先定义一个pom.xml，引入相关jar包：
```xml
<!-- 原文：五月的仓颉http://www.cnblogs.com/xrq730/p/8619156.html -->
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>

      <groupId>org.xrq.log</groupId>
      <artifactId>log-test</artifactId>
      <version>1.0.0</version>
      <packaging>jar</packaging>

      <name>log-test</name>
      <url>http://maven.apache.org</url>

      <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
      </properties>

      <dependencies>
        <dependency>
            <groupId>junit</groupId>
              <artifactId>junit</artifactId>
              <version>4.11</version>
              <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.25</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.2.3</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>1.7.25</version>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>1.7.21</version>
        </dependency>
      </dependencies>
</project>
```
写一段简单的Java代码：
```java
@Test
public void testSlf4j() {
    Logger logger = LoggerFactory.getLogger(Object.class);
    logger.error("123");
}
```
输出日志，但是会输出一些告警日志，提示我们同时引入了多个slf4j的实现，然后选择其中的一个作为我们使用的日志系统。
从例子我们可以得出一个重要的结论，即slf4j的作用：只要所有代码都使用门面对象slf4j，我们就不需要关心其具体实现，最终所有地方使用一种具体实现即可，更换、维护都非常方便。

## 4. slf4j实现原理
上面看了slf4j的示例，下面研究一下slf4j的实现，我们只关注重点代码。

slf4j的用法就是常年不变的一句"Logger logger = LoggerFactory.getLogger(Object.class);"，可见这里就是通过LoggerFactory去拿slf4j提供的一个Logger接口的具体实现而已，LoggerFactory的getLogger的方法实现为：

```java
public static Logger getLogger(Class<?> clazz) {
    Logger logger = getLogger(clazz.getName());
    if (DETECT_LOGGER_NAME_MISMATCH) {
        Class<?> autoComputedCallingClass = Util.getCallingClass();
        if (autoComputedCallingClass != null && nonMatchingClasses(clazz, autoComputedCallingClass)) {
            Util.report(String.format("Detected logger name mismatch. Given name: \"%s\"; computed name: \"%s\".", logger.getName(),
                            autoComputedCallingClass.getName()));
            Util.report("See " + LOGGER_NAME_MISMATCH_URL + " for an explanation");
        }
    }
    return logger;
}
```

从第2行开始跟代码，一直跟到LoggerFactory的bind()方法：
```java
private final static void bind() {
    try {
        Set<URL> staticLoggerBinderPathSet = null;
        // skip check under android, see also
        // http://jira.qos.ch/browse/SLF4J-328
        if (!isAndroid()) {
            staticLoggerBinderPathSet = findPossibleStaticLoggerBinderPathSet();
            reportMultipleBindingAmbiguity(staticLoggerBinderPathSet);
        }
        // the next line does the binding
        StaticLoggerBinder.getSingleton();
        INITIALIZATION_STATE = SUCCESSFUL_INITIALIZATION;
        reportActualBinding(staticLoggerBinderPathSet);
        fixSubstituteLoggers();
        replayEvents();
        // release all resources in SUBST_FACTORY
        SUBST_FACTORY.clear();
    } catch (NoClassDefFoundError ncde) {
        String msg = ncde.getMessage();
        if (messageContainsOrgSlf4jImplStaticLoggerBinder(msg)) {
            INITIALIZATION_STATE = NOP_FALLBACK_INITIALIZATION;
            Util.report("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
            Util.report("Defaulting to no-operation (NOP) logger implementation");
            Util.report("See " + NO_STATICLOGGERBINDER_URL + " for further details.");
        } else {
            failedBinding(ncde);
            throw ncde;
        }
    } catch (java.lang.NoSuchMethodError nsme) {
        String msg = nsme.getMessage();
        if (msg != null && msg.contains("org.slf4j.impl.StaticLoggerBinder.getSingleton()")) {
            INITIALIZATION_STATE = FAILED_INITIALIZATION;
            Util.report("slf4j-api 1.6.x (or later) is incompatible with this binding.");
            Util.report("Your binding is version 1.5.5 or earlier.");
            Util.report("Upgrade your binding to version 1.6.x.");
        }
        throw nsme;
    } catch (Exception e) {
        failedBinding(e);
        throw new IllegalStateException("Unexpected initialization failure", e);
    }
}
```

这个地方第7行是一个关键，看一下代码：

```java
static Set<URL> findPossibleStaticLoggerBinderPathSet() {
    // use Set instead of list in order to deal with bug #138
    // LinkedHashSet appropriate here because it preserves insertion order
    // during iteration
    Set<URL> staticLoggerBinderPathSet = new LinkedHashSet<URL>();
    try {
        ClassLoader loggerFactoryClassLoader = LoggerFactory.class.getClassLoader();
        Enumeration<URL> paths;
        if (loggerFactoryClassLoader == null) {
            paths = ClassLoader.getSystemResources(STATIC_LOGGER_BINDER_PATH);
        } else {
            paths = loggerFactoryClassLoader.getResources(STATIC_LOGGER_BINDER_PATH);
        }
        while (paths.hasMoreElements()) {
            URL path = paths.nextElement();
            staticLoggerBinderPathSet.add(path);
        }
    } catch (IOException ioe) {
        Util.report("Error getting resources from path", ioe);
    }
    return staticLoggerBinderPathSet;
}
```

这个地方重点其实就是第12行的代码，getLogger的时候会去classpath下找STATIC_LOGGER_BINDER_PATH，STATIC_LOGGER_BINDER_PATH值为"org/slf4j/impl/StaticLoggerBinder.class"，即所有slf4j的实现，在提供的jar包路径下，一定是有"org/slf4j/impl/StaticLoggerBinder.class"存在的，我们可以看一下：

![jar package1](https://images2018.cnblogs.com/blog/801753/201803/801753-20180322225204212-2118755582.png)

![jar package2](https://images2018.cnblogs.com/blog/801753/201803/801753-20180322225216313-1753469757.png)

![jar package3](https://images2018.cnblogs.com/blog/801753/201803/801753-20180322225225451-413964397.png)

我们不能避免在系统中同时引入多个slf4j的实现，所以接收的地方是一个Set。大家应该注意到，上部分在演示同时引入logback、slf4j-simple、log4j的时候会有警告：

![warning](https://images2018.cnblogs.com/blog/801753/201803/801753-20180322225633011-1104573372.png)

这就是因为有三个"org/slf4j/impl/StaticLoggerBinder.class"存在的原因，此时reportMultipleBindingAmbiguity方法控制台输出语句：

```java
private static void reportMultipleBindingAmbiguity(Set<URL> binderPathSet) {
    if (isAmbiguousStaticLoggerBinderPathSet(binderPathSet)) {
        Util.report("Class path contains multiple SLF4J bindings.");
        for (URL path : binderPathSet) {
            Util.report("Found binding in [" + path + "]");
        }
        Util.report("See " + MULTIPLE_BINDINGS_URL + " for an explanation.");
    }
}
```

那网友朋友可能会问，同时存在三个"org/slf4j/impl/StaticLoggerBinder.class"怎么办？首先确定的是这不会导致启动报错，其次在这种情况下编译期间，编译器会选择其中一个StaticLoggerBinder.class进行绑定，这个地方sfl4j也在reportActualBinding方法中报告了绑定的是哪个日志框架：

```java
1 private static void reportActualBinding(Set<URL> binderPathSet) {
2     // binderPathSet can be null under Android
3     if (binderPathSet != null && isAmbiguousStaticLoggerBinderPathSet(binderPathSet)) {
4         Util.report("Actual binding is of type [" + StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr() + "]");
5     }
6 }
```

对照上面的截图，看最后一行，确实是"Actual binding is of type..."这句。

最后StaticLoggerBinder就比较简单了，不同的StaticLoggerBinder其getLoggerFactory实现不同，拿到ILoggerFactory之后调用一下getLogger即拿到了具体的Logger，可以使用Logger进行日志输出。






## 5. 引用一个模块时,需要屏蔽此模块不一致的log实现
```xml
'add blow exclusion to the pom file
<exclusions>
    <exclusion>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
    </exclusion>
</exclusions>
```

## 6. config file for log4j
right now log4j 2.x only support XML and json config file and need to put the file under resource folder
##### Appender
    - ConsoleAppender: 日志输出到控制台；
    - FileAppender：输出到文件；
    - RollingFileAppender：输出到文件，文件达到一定阈值时，自动备份日志文件;
    - DailyRollingFileAppender：可定期备份日志文件，默认一天一个文件，也可设置为每分钟一个、每小时一个；
    - WriterAppender：可自定义日志输出位置。

##### 日志级别
- 一般日志级别包括：ALL，DEBUG， INFO， WARN， ERROR，FATAL，OFF
- Log4J推荐使用：DEBUG， INFO， WARN， ERROR

##### 输出格式
Log4J最常用的日志输出格式为：org.apache.log4j.PatternLayOut，其主要参数为：

- %n - 换行
- %m - 日志内容
- %p - 日志级别(FATAL， ERROR，WARN， INFO，DEBUG or custom)
- %r - 程序启动到现在的毫秒数
- %t - 当前线程名
- %d - 日期和时间, 一般使用格式 %d{yyyy-MM-dd HH:mm:ss， SSS}
- %l - 输出日志事件的发生位置， 同 %F%L%C%M
- %F - java 源文件名
- %L - java 源码行数
- %C - java 类名，%C{1} 输出最后一个元素
- %M - java 方法名

##### 代码示例
```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <!--
        Configuration后面的status，这个用于设置log4j2自身内部的信息输出，可以不设置，当设置成trace时，你会看到log4j2内部各种详细输出。 
    -->
    <!--
        monitorInterval：Log4j能够自动检测修改配置 文件和重新配置本身，设置间隔秒数。
    -->
    <configuration status="error" monitorInterval=”30″>
        <!--先定义所有的appender-->
        <appenders>
            <!--这个输出控制台的配置-->
            <Console name="Console" target="SYSTEM_OUT">
                <!--控制台只输出level及以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
                <ThresholdFilter level="trace" onMatch="ACCEPT" onMismatch="DENY"/>
                <!--这个都知道是输出日志的格式-->
                <PatternLayout pattern="%d{HH:mm:ss.SSS} %-5level %class{36} %L %M - %msg%xEx%n"/>
            </Console>
            <!--文件会打印出所有信息，这个log每次运行程序会自动清空，由append属性决定，这个也挺有用的，适合临时测试用-->
            <File name="log" fileName="log/test.log" append="false">
                <PatternLayout pattern="%d{HH:mm:ss.SSS} %-5level %class{36} %L %M - %msg%xEx%n"/>
            </File> 
            <!-- 这个会打印出所有的信息，每次大小超过size，则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，作为存档-->
            <RollingFile name="RollingFile" fileName="logs/app.log"
                     filePattern="log/$${date:yyyy-MM}/app-%d{MM-dd-yyyy}-%i.log.gz">
                <PatternLayout pattern="%d{yyyy-MM-dd 'at' HH:mm:ss z} %-5level %class{36} %L %M - %msg%xEx%n"/>
                <SizeBasedTriggeringPolicy size="50MB"/>
                <!-- DefaultRolloverStrategy属性如不设置，则默认为最多同一文件夹下7个文件，这里设置了20 -->
                <DefaultRolloverStrategy max="20"/>
            </RollingFile>
        </appenders>
        <!--然后定义logger，只有定义了logger并引入的appender，appender才会生效-->
        <loggers>
            <!--建立一个默认的root的logger-->
            <root level="trace">
                <appender-ref ref="RollingFile"/>
                <appender-ref ref="Console"/>
            </root> 
        </loggers>
    </configuration>
```

test code
```java
@Component
public class LogTest {
    Logger logger = Logger.getLogger("logTest1");

    @PostConstruct
    public void test(){
        for (int i=0; i<1000; i++) {
            logger.info(i + "----Log.Info----");
            logger.info(i + "----Log.Info----");
            logger.info(i + "----Log.Info----");
        }
    }
}

```

