package com.czh.httpd.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilTest {
    @Test
    public void testSplit() {
        byte[] bytes = {1,2,3,4,5,6,7,8,9};
        byte[] res = ArrayUtil.splitBytes(bytes, 2, 5);
        for (byte b : res) {
            System.out.println(b);
        }
    }

    @Test
    void mergeBytes() {
        byte[] arr1 = {1,2,3,4};
        byte[] arr2 = {5,6,7,8};
        byte[] res = ArrayUtil.mergeBytes(arr1, arr2);
        for (byte b : res) {
            System.out.println(b);
        }
    }
}