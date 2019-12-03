package com.czh.httpd.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseFactoryTest {

    @Test
    void getIndexResponse() {
        Response response = ResponseFactory.getIndexResponse("");
        System.out.println(response);
    }

    @Test
    void getResponseByUrl() {
    }

    @Test
    void getNotFoundResponse() {
        Response response = ResponseFactory.getNotFoundResponse("wdnmd", "fuck");
        System.out.println(response);
    }

    @Test
    void testGetResponseByUrl() {
    }
}