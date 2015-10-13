DOM、SAX、JDOM、JAXB对XML档的解析

1.  DOM(Document Object Model)
此方法主要由W3C提供，它将xml文件全部读入内存中，然后将各个元素组成一棵数据树，以便快速的访问各个节点 。
 因此非常消耗系统性能 ，对比较大的文档不适宜采用DOM方法来解析。
 DOM API 直接沿袭了 XML 规范。
 每个结点都可以扩展的基于 Node 的接口，就多态性的观点来讲，它是优秀的，但是在 Java 语言中的应用不方便，并且可读性不强。

dom4j、jdom

2.SAX (Simple API for XML)
此方法主要由XML-DEV 邮件列表的成员开发的，SAX是基于事件的方法，它很类似于标签库的处理机制，在标签开始、结束以及错误发生等等地方调用相应的接口实现方法，不是全部文 档都读入内存。 SAX具有优异的性能和利用更少的存储空间特点。SAX 的设计只考虑了功能的强大性，却没有考虑程序员使用起来是否方便。
使用必须扩展ContentHandler、ErrorHandler、DTDHandler等，但是必须扩展ContentHandler（或者DefaultHandler ）。

3.JDOM
JDOM的处理方式有些类似于DOM，但它主要是用SAX实现的 。JDOM用Java的数据类型来定义操作数据树的各个节点 。JDOM的性能也很优越。

4.JAXB (Java And XML Bi nding)
JAXB 是以SUN为主的一些公司公布的。JAXB将schema（或者DTD）映射为java对象（.java文件），然后使用这些java对象来解析xml文件。需要使用之前生成java文件，因而要有固定的schema，无法处理动态的xml文件。
首先使用xjc命令，生成java文件 xjc  [-options ...]
(生成的文件较多)


DOM、SAX、JDOM、JAXB
XPath
dom4j、jdom、
xStream