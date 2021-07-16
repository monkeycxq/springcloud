# springcloud 从0 开始创建 springcloud 项目 2.3.5
1. 创建 spring cloud ，创建 注册中心 eureka, 生产者服务user-service ,消费者 web-service;
2. 创建 公共模块 common , 编写全局异常处理类，自定义统一响应体，全局处理响应数据，fastjson；
3. 使用ribbon + restTemplate 进行服务调用;
4. 使用feign 进行服务调用
5. 使用 unirest 请求外部接口; 
6. 使用 JApiDocs 接口文档生成工具;（删除了）
7. 使用 mongoDB 储存数据;
8. 使用jasypt加密；
9. 使用redis，及分布式锁;
10. nacos 替换eureka;
11. 使用rabbitmq;
12. 使用登录拦截器，token;
13. ELK:Elasticsearch、Logstash 和 Kibana;（如果只是查看日志，不需要集成到项目，只需要安装这三个软件即可）
14. shrio;
15. 使用sentinel 限流,熔断降级;
16. 使用smart-doc 接口文档生成工具;
17. 加入java加密技术：用户密码可用MD5、敏感信息用AES
18. 使用nacos做配置中心：在nacos配置管理界面，配置customer-web.yaml文件，本地必须有bootstrap.properties;



todo:
2. 加入 mybatis、mysql;
2. 加入 gateway;
3. 定时器;
4. 分布式事务框架 Seata;
5. docker 部署spring cloud;
6. 日志剧增，空间不足问题；
7. 是用Spring cloud alibaba的其他组件，nacos、sentinel、dubbo RPC、dubbo proxy、SCS RocketMQ、
dubbo LB、seata;
8. 压力测试;

可能会使用到的技术栈：

1. 反向代理：nginx，可做动静分离部署

2. 统一网关：基于spring-cloud-gateway，配合JWT进行的简单的验权操作

3. 分布式事务：Spring Cloud Alibaba Seata，阿里内部分布式事务产品不断迭代演进而来。

4. 降级、限流：hysrix/Spring Cloud Alibaba Sentinel

5. 服务注册\发现：Spring Cloud Alibaba Nacos

6. 分布式配置中心：Spring Cloud Alibaba Nacos

7. 客户端负载均衡：openFeign

8. 异步消息：RocketMQ，阿里开源，交由Apache孵化

9. 链路跟踪：Skywalking，华为开源，交由Apache孵化



10. 分布式缓存：Redis，基础数据缓存

11. 健康监控：spring-boot-admin

12. 分布式锁：Redission

13. 代码简化：Lambok，mybatis-plus，mybatis-generator

14. RPC框架：apache dubbo

























todo：
 test intermediate engineer:  (do 2 of 4)
1. create Level class (id,name,parentId)，递归输出树结构的json 对象;
2. 读取本地图片，显示到页面;
3. 创建一个对象Customer (id,name,age),将对象放入List,采用java8 stream api加分；
    queryDistinctCustomer(List<Customer> customerList),返回List,报保证name,没有重复;
    queryLargeAge(Integer age),返回Customer 对象，保证age 比输入参数大，并且为偶数,而且age 是最大的对象; 
4. 将目录a文件 test.txt, 使用流操作 ，移到目录 b 下;

数据库：
    表结构, student_grade (id,student_name,subject_name 科目名称,subject_score 成绩) 
     (1) 按科目名称，汇总各个科目成绩;
     (2) 按学生名称，汇总学生总成绩，并只显示前10 名学生;
     (3) 查出大于2门科目成绩上90分的学生;
     
senior engineer：
    写一个责任链或者装饰者模式;
