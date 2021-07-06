package com.example.webcustomer.util;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
/**
 * MD5加密工具类
 * @author cxq
 * @date 2021/6/25
 */
public class MD5Util {
    //用来将字节转换成16进制表示的字符
    private static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    /**
     * 私有构造函数，防止实例化使用
     */
    private MD5Util() {}

    /**
     * 对参数进行MD5加密
     * @param bytes 要加密的参数
     * @return MD5值
     */
    @SneakyThrows
    public static String getMD5(byte[] bytes) {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(bytes);
        byte[] temp = md.digest(); //MD5的计算结果是一个128位长整数，用字节表示就是16字节
        char[] chars = new char[32]; //每个字节用16进制表示的话，需要2个字符，所以共32个字符

        //对MD5的每个字节转换成16进制的字符
        int k = 0;
        for (int i = 0; i < 16; i++) {
            byte aByte = temp[i];
            chars[k++] = hexDigits[aByte >>> 4 & 0xf];
            chars[k++] = hexDigits[aByte & 0xf];
        }

        String result = new String(chars);
        return result;
    }

    /**
     * 对参数进行MD5加密
     * @param str 要加密的参数
     * @return MD5值
     */
    public static String getMD5(String str) {
        return getMD5(str.getBytes());
    }

    /**
     * 计算文件的MD5加密值，注意如果文件较大、计算MD5时可能性能较差
     * @param file 文件
     * @return MD5值
     */
    @SneakyThrows
    public static String getFileMD5(File file) {
        MessageDigest md = MessageDigest.getInstance("MD5");
        try(FileInputStream in = new FileInputStream(file)) {
            byte[] buffer = new byte[1024];
            while (in.read(buffer) != -1) {
                md.update(buffer);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1, md.digest());
        String result = bigInt.toString(16);
        return result;
    }

    /**
     * 计算文件的MD5加密值
     * @param fileName 文件名
     * @return MD5值
     */
    public static String getFileMD5(String fileName) {
        File file = new File(fileName);
        return getFileMD5(file);
    }

    public static void  main(String[] arg){
        String md5 = getMD5("111111");
        System.out.println(md5);
    }
}
