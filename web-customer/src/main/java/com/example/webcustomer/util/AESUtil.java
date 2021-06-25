package com.example.webcustomer.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * AES 加密工具类
 * @author cxq
 * @date 2021/6/25
 */
public class AESUtil {
    //自己定义的秘钥,128bit即16位的随机串。也支持192,25bit,长度越长安全性越高，对应的加解密时间越长
    private final static String KEY_STR = "gOZ+l59TRoBajn3G";// 可以写进配置
    private final Key key;
    private static AESUtil instance = new AESUtil();
    private static final String ALGORITHM = "AES";
    private static final String RANDOM_ALGORITHM = "SHA1PRNG";

    /**
     * 私有构造函数，防止通过实例化使用
     */
    private AESUtil() {
        try {
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);//Java的秘钥生产器，使用的是AES
            SecureRandom random = SecureRandom.getInstance(RANDOM_ALGORITHM);//随机数的算法，NativePRNG和SHA1PRNG
            random.setSeed(KEY_STR.getBytes());//用我们设定的秘钥串作为随机数的种子,因为种子是我们固定的，产生的随机数也是固定的
            generator.init(random);
            key = generator.generateKey();//生成的秘钥，我们在加密解密需要用到相同秘钥
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("AES构造函数异常");
        }
    }

    public static AESUtil getInstance() {
        return instance;
    }

    /**
     * 对byte[]参数进行加密
     * @param bytes 要加密的参数
     * @return 加密后的参数
     */
    private byte[] getEncCode(byte[] bytes) {
        byte[] result = null;
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES");//获取算法实例
            cipher.init(Cipher.ENCRYPT_MODE, key);//初始化，入参为加密模式和秘钥
            result = cipher.doFinal(bytes);//进行加密
        } catch (Exception e) {
            throw new RuntimeException("AES加密异常");
        } finally {
            cipher = null;
        }
        return result;
    }

    /**
     * 对byte[]参数进行解密
     * @param bytes 要解密的参数
     * @return 解密后的参数
     */
    private byte[] getDesCode(byte[] bytes) {
        byte[] result = null;
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES");//获取算法实例
            cipher.init(Cipher.DECRYPT_MODE, key);//初始化，入参为解密模式和秘钥
            result = cipher.doFinal(bytes);//进行解密
        } catch (Exception e) {
            throw new RuntimeException("AES解密异常");
        } finally {
            cipher = null;
        }
        return result;
    }

    /**
     * 对string参数进行加密
     * @param str 要加密的参数
     * @return 加密后的参数
     */
    public String getEncString(String str) {
        BASE64Encoder base64en = new BASE64Encoder();
        byte[] input = null; //明文
        byte[] output = null; //密文
        String result = null;
        try {
            input = str.getBytes();
            output = getEncCode(input);
            result = base64en.encode(output);

        } catch (Exception e) {
            throw new RuntimeException("AES解密异常，参数：" + str);
        } finally {
            input = null;
            output = null;
        }
        return result;
    }

    /**
     * 对String参数进行解密
     * @param str 要解密的参数
     * @return 解密后的参数
     */
    public String getDesString(String str) {
        BASE64Decoder base64De = new BASE64Decoder();
        byte[] input = null; //密文
        byte[] output = null; //明文
        String result = null;
        try {
            input = base64De.decodeBuffer(str);
            output = getDesCode(input);
            result = new String(output);

        } catch (Exception e) {
            throw new RuntimeException("AES解密异常，参数：" + str);
        } finally {
            input = null;
            output = null;
        }
        return result;
    }
}
