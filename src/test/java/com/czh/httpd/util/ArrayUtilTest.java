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
}