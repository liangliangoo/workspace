# Drools 使用案例

## 什么是规则引擎
规则引擎是处理复杂规则集合的引擎。通过输入一些基础事件，
以推演或者归纳等方式，得到最终的执行结果。规则引擎的核心作用在于将复杂、
易变的规则从系统中抽离出来，由灵活可变的规则来描述业务需求

## Drools 简介
<a href="https://docs.drools.org/7.73.0.Final/drools-docs/html_single/index.html">官方文档地址</a><br>
Drools 是 Java 编写的一款开源规则引擎。Drools 的核心算法基于 Rete。
早些版本中，Drools 使用的是基于 Rete 二次开发的 ReteOO 算法。
在 7.x 版本的 Drools 中，其内部算法已经改为使用 Phreak。
Phreak 也是Drools 团队自研的算法，虽然网上关于该算法的资料很少，
但是总体来说与 Rete 算法相似。阅读本文之前可以先了解下 <a href= "https://blog.csdn.net/goodjava2007/article/details/121989398">Rete 算法</a>


