package com.example.webcustomer;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;

/**
 * @author cxq
 * @date 2021/5/11
 */
public class ShrioTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before(value = "testAuthentication")// 在方法开始前添加一个用户
    public void addUser() {
        simpleAccountRealm.addAccount("wmyskxz", "123456");
    }

    @Test
    public void testAuthentication() {
        simpleAccountRealm.addAccount("wmyskxz", "123456");

        // 1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager); // 设置SecurityManager环境
        Subject subject = SecurityUtils.getSubject(); // 获取当前主体

        UsernamePasswordToken token = new UsernamePasswordToken("wmyskxz", "123456");
        System.out.println("token:" + JSON.toJSON(token));
        subject.login(token); // 登录

        // subject.isAuthenticated()方法返回一个boolean值,用于判断用户是否认证成功
        System.out.println("isAuthenticated:" + subject.isAuthenticated()); // 输出true

        subject.logout(); // 登出

        System.out.println("isAuthenticated:" + subject.isAuthenticated()); // 输出false

    }
}
