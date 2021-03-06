package com.czh.httpd.util;

/**
 * @author chenzh
 */
public class ArrayUtil {
    public static byte[] splitBytes(byte[] bytes, int offset, int len) {
        byte[] res = new byte[len];
        for (int i = offset, j = 0; i < offset + len; i++,j++) {
            res[j] = bytes[i];
        }
        return res;
    }

    public static byte[] mergeBytes(byte[] arr1, byte[] arr2) {
        int length = arr1.length + arr2.length;
        byte[] res = new byte[length];
        System.arraycopy(arr1, 0, res, 0, arr1.length);
        for (int i = arr1.length, j = 0; i < length; i++, j++) {
            res[i] = arr2[j];
        }
        return res;
    }
}
