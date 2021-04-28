# springcloud 从0 开始创建 springcloud 项目 2.3.5
1. 创建 spring cloud ，创建 注册中心 eureka, 生产者服务user-service ,消费者 web-service;
2. 创建 公共模块 common , 编写全局异常处理类，自定义统一响应体，全局处理响应数据，fastjson；
3. 使用ribbon + restTemplate 进行服务调用;
4. 使用feign 进行服务调用
5. 使用 unirest 请求外部接口; 
6. 使用 JApiDocs 接口文档生成工具;
7. 使用 mongoDB 储存数据;
8. 使用jasypt加密；
9. 使用redis;
10. nacos 替换eureka;



todo:
2. 加入mybatis、mysql;
3. 加入gateway;
4. shrio;
5. mq , 定时器;
6. 登录拦截器，token;




todo：
 test intermediate engineer:  (do 2 of 4)
1. create Level class (id,name,parentId)，递归输出树结构的json 对象;
2. 读取本地图片，显示到页面;
3. 创建一个对象Customer (id,name,age),将对象放入List,采用java8 stream api加分；
    queryDistinctCustomer(List<Customer> customerList),返回List,并保证name,没有重复;
    queryLargeAge(Integer age),返回Customer 对象，保证age 比输入参数大，并且为偶数,而且age 是最大的对象; 
4. 将目录a文件 test.txt, 使用流操作 ，移到目录 b 下;

数据库：
    表结构, student_grade (id,student_name,subject_name 科目名称,subject_score 成绩) 
     (1) 按科目名称，汇总各个科目成绩;
     (2) 按学生名称，汇总学生总成绩，并只显示前10 名学生;
     (3) 查出大于2门科目成绩上90分的学生;
     
senior engineer：
    写一个责任链或者装饰者模式;
