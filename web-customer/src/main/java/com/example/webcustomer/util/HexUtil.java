package com.example.webcustomer.util;

/**
 * byte[]与String转换
 */
public class HexUtil {
    private final static String HEX = "0123456789ABCDEF";
    /**
     * 二进制转(16进制)字符串
     */
    public static String byteToHex(byte[] bytesKey) {
        if (bytesKey == null)
            return "";
        StringBuilder sb = new StringBuilder();
        for (byte b : bytesKey) {
            sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
        }
        return sb.toString();
    }

    /**
     * (16进制)字符转二进制
     */
    public static byte[] hexToByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        return result;
    }
}
