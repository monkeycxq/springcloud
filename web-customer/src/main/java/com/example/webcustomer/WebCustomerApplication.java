package com.example.webcustomer;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import io.github.yedaxia.apidocs.Ignore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

//表明自己是消费者
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={"com.example.*"})
@EnableRetry
@EnableFeignClients
@Controller
@Slf4j
@Ignore
public class WebCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebCustomerApplication.class, args);

        // 生成api文档
       /* DocsConfig config = new DocsConfig();
        config.setProjectPath("D:\\studyPro\\springcloud\\web-customer"); // 项目根目录
        config.setProjectName("customer-web"); // 项目名称
        config.setApiVersion("V1.0");       // 声明该API的版本
        config.setDocsPath("D:\\studyPro\\springcloud\\web-customer\\src\\main\\resources\\static"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档*/
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(2000);
        factory.setConnectTimeout(2000); // 超时时间配置2秒
        return new RestTemplate(factory);
    }


    /**
     * api 文档
     * @author cxq
     * @date 2020/12/10
     * @param
     * @return java.lang.String
     */
    @GetMapping(value = "/apiDoc")
    public String apiDoc(){
        log.info("api 文档页面");
       return "/V1.0/index";
    }

    /**
     * index
     * @author cxq
     * @date 2020/12/10
     * @param
     * @return java.lang.String
     */
    @GetMapping(value = "/index")
    public String index(){
        log.info("index页面");
        return "/index";
    }
}
