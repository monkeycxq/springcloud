package com.example.webcustomer.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @author cxq
 * @date 2021/6/25
 */
@Slf4j
@Service
public class DESUtil {

    // 向量
    @Value("${des.key}")
    private String key;

    private static IvParameterSpec ips = null;

    /**
     * 加解密
     * @author cxq
     * @date 2021/6/25
     * @param data 明文
     * @param mode 1加密，2解密
     * @return java.lang.String 密文或明文
     */
    public String desCBC(String data,int mode) throws Exception {
        byte[] _data = data.getBytes("UTF-8");
        byte[] _key = key.getBytes("UTF-8");
        DESKeySpec spec = new DESKeySpec(_key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DES");
        Key deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding"); // 加密方法／运算模式／填充模式
        if(ips == null) {
            byte[] iv = new byte[8];
            new SecureRandom().nextBytes(iv);
            ips = new IvParameterSpec(iv);
        }
        cipher.init(mode, deskey, ips);
        byte[] bOut = cipher.doFinal(_data);
        return bOut.toString();
    }

    /**
     * 加密
     * @author cxq
     * @date 2021/6/25
     * @param data 明文
     * @return java.lang.String 密文
     */
    public String desEncode(String data) throws Exception {
        return desCBC(data,Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密
     * @author cxq
     * @date 2021/6/25
     * @param data
     * @return java.lang.String
     */
    public String desDecode(String data) throws Exception {
        return desCBC(data,Cipher.DECRYPT_MODE);
    }

}
