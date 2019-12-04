package com.czh.httpd.util;

public class ArrayUtil {
    public static byte[] splitBytes(byte[] bytes, int offset, int len) {
        byte[] res = new byte[len];
        for (int i = offset, j = 0; i < offset + len; i++,j++) {
            res[j] = bytes[i];
        }
        return res;
    }
}
