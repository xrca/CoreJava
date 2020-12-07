# IO流

## 一、File类的使用

**File类特点：**

- java.io.File类：文件或文件目录路径的抽象表示，与平台无关
- File能新建、删除、重命名文件和目录，但是File不能访问文件内容本身。如果需要访问文件本身，则需要使用输入输出流。
- 想要在Java程序中表示一个真实存在的文件或目录，那么必须要有一个File对象，但是Java程序中的一个File对象，可能没有一个真实存在的文件或目录。
- File对象可以作为参数传递给流的构造器。



**File类构建方法：**

- File (String pathname)

  以pathname为路径创建File对象，可以是**绝对路径或者相对路径**，如果pathname是绝对路径，则默认为当前路径在系统属性user.dir中存储。

  - 绝对路径：是一个个固定的路径，从盘符开始
  - 相对路径：是相对于某个位置开始

- File(String parent, String child)

  以parent为父路径，child为子路径创建File对象

- FIle(File parent, String child)

  根据一个父File对象和子路径创建File对象



**路径分割符:**

- 路径中的每级目录之间用一个**路径分隔符**隔开
- 路径分割符与系统有关：
  - windows和DOS默认使用“\”来表示（和w键一排的就是windows）
  - UNIX和URL使用“/”来表示
- Java程序支持跨平台运行，因此路径分隔符要谨慎使用
- 为了解决这个隐患，File类提供了一个常量：public static final String separator。根据操作系统，动态的提供分隔符，如File file = new File("d:" + File.sparator + "xrca" + File.sparator + "code.java")



**File类常用方法：**

- public String getAbsolutePath()：获取绝对路径
- public String getPath()：获取路径
- public String getName()：获取名称
- public String getParent()：获取上层文件目录。若无，返回null
- public long length()：获取文件长度（即：字节数）。不能获取目录长度
- public long lastModified()：获取最后一次的修改时间，毫秒值
- public String[] list()：获取指定目录下的所有文件或者文件目录的名称数组
- public File[] listFiles()：获取指定目录下的所有文件或者文件目录的File数组
- renameTo：重命名



**File类判断功能：**

- public boolean isDirectory()：判断是否是文件目录
- public boolean isFile()：判断是否是文件
- public boolean exists()：判断是否存在
- public boolean canRead()：判断是否可读写
- public boolean canWrite()：判断是否可写
- public boolean isHidden()：判断是否隐藏









## 二、IO流原理以及流的分类



## 三、节点流（或文件流）



## 四、缓冲流



## 五、转换流



## 六、标准输入输出流