# IntegralWall
Ann9 IntegralWall Interface Demo

## Config

###1.配置数据连接

    Mysql 帐号：root
    Mysql密码： password
    Mysql端口号： 3306




###2.创建数据库表格
#### users表：
         +-------------+--------------+------+-----+-------------------+-----------------------------+
         | Field       | Type         | Null | Key | Default           | Extra                       |
         +-------------+--------------+------+-----+-------------------+-----------------------------+
         | udid        | varchar(100) | YES  |     | NULL              |                             |
         | appid       | varchar(100) | YES  |     | NULL              |                             |
         | dateCreated | timestamp    | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
         +-------------+--------------+------+-----+-------------------+-----------------------------+
 
####usersagent表:
 
     | Field       | Type         | Null | Key | Default             | Extra |
     +-------------+--------------+------+-----+---------------------+-------+
     | udid        | varchar(100) | YES  |     | NULL                |       |
     | appid       | varchar(100) | YES  |     | NULL                |       |
     | multipleurl | varchar(100) | YES  |     | NULL                |       |
     | isAcitve    | tinyint(4)   | YES  |     | NULL                |       |
     | iscallback  | tinyint(4)   | YES  |     | NULL                |       |
     | dateCreated | timestamp    | NO   |     | 0000-00-00 00:00:00 |       |
     +-------------+--------------+------+-----+---------------------+-------+




## 启动

    
    
        ./mvnw install package
        java -jar target/test-0.0.1-SNAPSHOT.war


## 测试

    点击接口
    http://ip:8080/?t=click&udid=b82c611c-41c8-11e7-a9dc-d017c28c4914&multipleurl=http://www.ann9.com&appid=12312345

    Check IDFA 接口
    curl "http://ip:8080/?t=checkidfa&idfa=b82c611c-41c8-11e7-a9dc-d017c28c4914&appid=12312345

    客户端用户激活接口
    http://ip:8081?t=active&idfa=b82c611c-41c8-11e7-a9dc-d017c28c4914&appid=12312345



