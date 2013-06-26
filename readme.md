介绍
==
> Tango致力于图像识别，数据挖掘技术，云计算等技术在移动设备上的应用研究。
新闻
==
- 0.0.1-SNAPSHOP发布  
[版本信息][2]
    
开发  
==
模块
--   
- Tango-server  
Tango-server为移动端提供图像识别和信息抓取(百度知道，百度百科，百度新闻)等API服务。  
- Tango-android  
Tango-android是一个android应用，用户可以通过Tango-android将所拍摄照片传送到Tango-server,并获取Tango-server解析结果，展示抓取的信息。
开发环境  
--
- Java：1.6  
- Maven:3.0.4  
- Android SDK:_r21.1  
- tesseract-ocr:3.02
Eclipse
--
- eclipse版本：Indigo or Juno
- Tango-server  
1,在Tango-server目录下执行Maven命令将Tango-server转换成Eclipse工程：<pre><code>mvn eclipse:eclipse</code></pre>  
2,在Eclipse里导入上一步生成的工程；  
3,调试时可以使用eclipse插件，也可以在项目目录下执行:<pre><code>mvn jetty:run</code></pre>
- Tango-android  
1,安装插件:  
Help -> Eclipse Marketplace... 查询 "android m2e".  
2,导入Tango-android项目:  
Import –> Maven -> Existing Maven Projects    
Java Build Path -> Source -> Add Folder 选择gen目录  
如果报test目录错误，删除就可以了
 
不使用IDE
--
如果你喜欢使用vim,emaces等文本编辑器做开发(赞！！！)，你可使用maven工具做调试，常用命令：  

- mvn clean  
- mvn compile  
- mvn package 
- mvn install 
- mvn android:deploy （在tango-android目录下执行）
- mvn jetty:run （在tango-server目录下执行）
使用/测试
==
> 准备一台android手机，无线网络设备和测试服务器(有无线网卡)  
1,将客户端应用安装到手机：安装生成的APK文件，或者右击项目:`run as android application`,target 选择你的手机；  
2,将服务端应用安装到测试服务器：将生的war部署的jsp容器，或者使用：`mvn jeety:run`;  
3,服务器还要安装配置tesseract-ocr和中文包，参考[安装文档][3]；  
4,将手机和测试服务器连接到同一无线网络,并确保服务器能连上互联网(抓取百度信息)；   
5,在手机上打开应用，点击“设置”，修该服务器的IP，端口，应用名称后返回；  
6,点击“拍照”，拍一张带有文字的图片(尽量清晰，最好是印刷体:-))，然后点击“上传照片；  
7,上传成功，并且服务端解析成功，会返回解析的文字列表，点击文字会抓取相关的“百度百科”，“百度知道”，“百度新闻”信息。
todolist
--
- 图片预处理，提高文字识别的正确率；
- 消除tango-android项目对eclipse的依赖； 
常见问题
==

帮助
==
- 论坛：[百度开放研究社区][4]
团队
==
- wancheng.com.cn@gmail.com


[1]: http://opencode.baidu.com/git/tango
[2]: http://opencode.baidu.com/git/tango
[3]: http://code.google.com/p/tesseract-ocr/wiki/ReadMe
[4]: http://openresearch.baidu.com
