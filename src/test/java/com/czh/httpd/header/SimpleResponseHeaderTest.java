package com.czh.httpd.header;

import org.junit.jupiter.api.Test;

class SimpleResponseHeaderTest {

    @Test
    public void testCreate() {
        SimpleResponseHeader responseHeader = new SimpleResponseHeader();
        responseHeader.setFirstLine("HTTP/1.1 200 OK");
        responseHeader.setHeader("fuck", "you");
        System.out.println(responseHeader);
    }

}