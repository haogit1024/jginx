package com.czh.httpd.response;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class IndexResponseTest {

    @Test
    void getHeader() {
    }

    @Test
    void getContent() {
    }

    @Test
    void getResponse() throws IOException {
        System.out.println(new IndexResponse().getResponse());
    }
}