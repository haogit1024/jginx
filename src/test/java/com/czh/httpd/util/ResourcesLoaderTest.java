package com.czh.httpd.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;

import java.io.File;
import java.io.IOException;


class ResourcesLoaderTest {

    @Test
    void getResourceAsString() throws IOException {
        String content = ResourcesLoader.getResourceAsString("/static/index.html");
        System.out.println(content);
        System.out.println(content.length());
    }

    @Test
    void newGetBytes() {
        String filePath = "C:\\Users\\admin\\Desktop\\test\\test22.txt";
        File file = new File(filePath);
//        System.out.println(file.exists());
        byte[] bytes = ResourcesLoader.getBytes(file, 0, 1024);
        System.out.println(new String(bytes));
    }

    @Test
    void newGetByte2() {
        String filePath = "C:\\Users\\admin\\Desktop\\test\\test22.txt";
        File file = new File(filePath);
//        System.out.println(file.exists());
        byte[] bytes = ResourcesLoader.getBytes(file, 1025, (int)(file.length() - 1));
        System.out.println(new String(bytes));
    }

    @Test
    void getResourceFromUrlAsString() throws IOException {
        String name = ResourcesLoader.class.getResource("/static/fuck.html").getFile();
        System.out.println(name);
    }
}