package com.example.webcustomer;

import com.example.webcustomer.util.AESUtil;
import com.example.webcustomer.util.DESUtil;
import com.example.webcustomer.util.MD5Util;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @authoe cxq
 * @date 2021/3/15
 */
public class JasyptTest {

    @Test
    public void testJasypt(){
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

    @Test
    public void testAes(){
        String encPwd = AESUtil.getInstance().getEncString("just4fun");
        System.out.println("encPwd:" + encPwd);//SmblNLk15qjuom+S201mWg==

        String pwd = AESUtil.getInstance().getDesString(encPwd);
        System.out.println("pwd:" + pwd);
    }

    @Test
    public void testMd5(){
       String md5 = MD5Util.getMD5("just4fun");
        System.out.println("md5:" + md5);//54489653fb9e8da76c4dbd03bda11ac2
    }

    @Autowired
    DESUtil desUtil;

    @Test
    public void testDes() throws Exception {
        String pwd = desUtil.desEncode( "123456");
        System.out.println("密文:" + pwd);

        String pwd2 = desUtil.desEncode("root");
        System.out.println("密文2:" + pwd2);

        String s = desUtil.desDecode(pwd);
        System.out.println("明文:" + s);

        String s2 = desUtil.desDecode(pwd2);
        System.out.println("明文2:" + s2);
    }

}
