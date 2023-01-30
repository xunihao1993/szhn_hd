
项目介绍：     
------  
- 架构介绍
该图书管理系统是基于SpringBoot,spring-security, mybatis-plus, redis, swagger等常见框架，包含角色管理、菜单管理、用户管理、图书管理等。

           
默认管理员账号：admin  密码：123456
------
功能介绍：
------       
1.角色管理
2.菜单管理
3.人员管理
4.图书管理
  
项目特点
------
1.项目基于SpringBoot，简化了大量的配置和Maven依赖。   
2.日志记录系统，记录项目运行中出现的各种异常情况     
3.使用mybatis-plus对数据库数据进行相关操作，大大减少了开发量，使得开发人员能够将更多的经理放到业务中    
4.通过角色管理来配置菜单，达到菜单为不同角色显示的目的，间接实现了权限的管理。   
5.图书管理通过区分管理员以及普通用户的角色进行操作上的区分

所用框架
------
### 前端
    

### 后端

 1. SpringBoot
 2. Spring
 3. SpringSecurity
 4. MyBatis Plus
 5. Redis
 6. Oauth2
 7. Swagger
 
### 部署文档
#### 所需环境
1. jdk1.8 
2. maven3.5
3. git

#### 部署步骤
1. 创建mysql数据库，数据库名称为：library_manager，运行sql文件初始化数据库
2. 使用git命令拉取本项目: git clone https://gitee.com/feixiangyunxiao/library_manager.git
3. 使用maven命令进行打包操作:  mvn clean package 
4. 打包完成之后进入: starter_server/target文件夹中，可看到starter_server*.jar包文件
5. 使用命令进行启动: nohup java -jar starter_server-01301616.jar(jar包名称，根据实际改动) --spring.profiles.active=prod > libraryManager.log &
6. 打开浏览器：地址栏输入：http://localhost:8088/swagger-ui.html可看到swagger地址，本项目启动完成