package com.czh.httpd.util;

import org.junit.jupiter.api.Test;

class HttpHeaderUtilTest {

    @Test
    void parseResponseRange() {
        String range = "bytes=0-100";
        int[] array = HttpHeaderUtil.parseRequestRange(range);
        for (int i : array) {
            System.out.println(i);
        }
    }

    @Test
    void genResponseRange() {
    }
}