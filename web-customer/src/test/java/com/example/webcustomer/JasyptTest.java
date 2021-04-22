package com.example.webcustomer;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.jupiter.api.Test;

/**
 * @authoe cxq
 * @date 2021/3/15
 */
public class JasyptTest {

    @Test
    public void test(){
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt(盐)
        textEncryptor.setPassword("just4fun");
        //要加密的数据（数据库的用户名或密码）
        String username = textEncryptor.encrypt("root");
        String password = textEncryptor.encrypt("just4fun");
        System.out.println("username:" + username);
        System.out.println("password:" + password);

        // 解密
        String realUsername = textEncryptor.decrypt(username);
        String realPassword = textEncryptor.decrypt(password);
        System.out.println("realUsername:" + realUsername);
        System.out.println("realPassword:" + realPassword);
    }

}
