package com.example.webcustomer.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * DES算法工具类
 * @author cxq
 * @date 2021/6/25
 * 由String 转换得到的 byte[] 就一定可以使用原来的编码方案转换成原来的 String,
 * 但是加密的结果 byte[] 却不能用任何字符编码方案得到String, 一般使用16进制编码或者利用Base64编码成String，
 * 然后进行存储或者比较。
 */
@Slf4j
@Component
public class DESUtil {

    /**
     * 加密：明文 String 使用 getBytes()  - 加密为密文 byte[] - 转为16进制String储存
     * 解密：16进制String - 转为byte[]的密文 - 解密为明文 byte[] 用toString得到明文
     */


    private static String key;

    private static IvParameterSpec ips = null;

    @Value("${des.key}")
    public void setKey(String str) {
        key = str;
    }

    /**
     * 加解密
     * @author cxq
     * @date 2021/6/25
     * @param data 明文
     * @param mode 1加密，2解密
     * @return java.lang.String 密文或明文
     */
    private static String desCBC(String data,int mode) throws Exception {
        byte[] _data = (mode == 1 ? data.getBytes() : HexUtil.hexToByte(data));
        byte[] _key = key.getBytes();
        DESKeySpec spec = new DESKeySpec(_key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        Key desKey = keyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding"); // 加密方法／运算模式／填充模式
        if(ips == null) {
            byte[] iv = new byte[8];
            new SecureRandom().nextBytes(iv);
            ips = new IvParameterSpec(iv);
        }
        cipher.init(mode, desKey, ips);
        byte[] bOut = cipher.doFinal(_data);
        return (mode ==  1 ? HexUtil.byteToHex(bOut) : new String(bOut));
    }

    /**
     * 加密
     * @author cxq
     * @date 2021/6/25
     * @param data 明文
     * @return java.lang.String 密文
     */
    public static String desEncode(String data) throws Exception {
        return desCBC(data,Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密
     * @author cxq
     * @date 2021/6/25
     * @param data
     * @return java.lang.String
     */
    public static String desDecode(String data) throws Exception {
        return desCBC(data,Cipher.DECRYPT_MODE);
    }

}
