package com.czh.httpd.response;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ResponseFactoryTest {

    @Test
    void getIndexResponse() {
        Response response = ResponseFactory.getIndexResponse("", null);
        System.out.println(response);
    }

    @Test
    void getResponseByUrl() {
        Path path = Paths.get("D:\\www", "bilibili");
        System.out.println(path.toString());
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