# Mybatis 在哈啰code review 中被一个Java开发专业问到

#被问到的问题如下：
***1、Ibatis 和 Mybatis 的区别***<br/>
***2、Ibatis 或则 Mybatis有什么优点***<br/>
***3、使用JDBC和使用mybatis有什么区别***<br/>
***4、为什么Mybatis 不需要写具体的实现类***<br/>
***5、mybatis 的执行流程是什么样的***<br/>
***6、mybatis的一些功能是如何实现的，比如缓存，拦截***<br/>
一级缓存是通过BaseExecutor<br/>
二级缓存是通过CachingExecutor  <br/>
缓存采用了装饰器和委托的设计模式（LoggingCache、SynchronizedCache是其装饰类）<br/>
一级缓存和二级缓存都存放在PerpetualCache对象中，PerpetualCache持有一个Map<Object, Object> Cache属性
Key为CacheKey，Value为sql执行后的结果集。CacheKey 根据namespace、offset、limit、sql输个属性生成。
二级缓存支持其他缓存介质接入。
<br/>
***7、mybatis和Spring是怎么整合的***<br/>
***8、怎么熟悉和使用1个新的框架***<br/>

