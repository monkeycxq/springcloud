package com.example.userservice;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
@Slf4j
public class UserServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

   // protected static final Logger logger = LoggerFactory.getLogger(UserServiceApplication.class);

    @Value("${server.port}")
    String port;

   /* @RestController
    class EchoController {
        @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
        public String echo(@PathVariable String string) {
            return "Hello Nacos Discovery " + string;
        }
    }*/

    @SneakyThrows
    @RequestMapping("/hi")
    public String home(@RequestParam String name){
        log.info("----进入了hi接口---：name:{}",name);
        // 让线程休眠
        //Thread.sleep(10000);

        return "hi " + name + ",i am from port:" + port;
    }

    @DeleteMapping("/delete")
    public String delete(Integer id){
        log.debug("debug删除成功！入参：{}",id);
        log.info("info删除成功！入参：{}",id);
        log.warn("warn删除成功！入参：{}",id);
        return "删除成功";
    }

}
