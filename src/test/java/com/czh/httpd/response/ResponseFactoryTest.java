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
}