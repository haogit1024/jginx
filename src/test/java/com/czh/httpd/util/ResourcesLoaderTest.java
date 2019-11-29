package com.czh.httpd.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ResourcesLoaderTest {

    @Test
    void getResourceAsString() throws IOException {
        String content = ResourcesLoader.getResourceAsString("/static/index.html");
        System.out.println(content);
        System.out.println(content.length());
    }
}