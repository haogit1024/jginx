package com.czh.httpd.util;

import com.czh.httpd.App;
import com.czh.httpd.constant.CommonConstants;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class FileUtilTest {

    @Test
    void readTextFile() {
        String filePath = App.class.getResource("/").getPath() + CommonConstants.SystemConfig.DEFAULT_JSON_CONFIG_PATH;
        System.out.println(filePath);
        File file = new File(filePath);
        System.out.println(file.exists());
        /*System.out.println(App.class.getResource("/").getPath());
        File file = new File(App.class.getResource("/").getPath());
        System.out.println(file.exists());*/
        /*System.out.println(Thread.currentThread().getContextClassLoader().getResource("/").getPath());
        System.out.println(filePath);*/
        System.out.println(FileUtil.readTextFile(filePath));
    }

    @Test
    void testReadTextFile() {
        System.out.println("---");
        System.out.println(System.getProperty("line.separator"));
        System.out.println("---");
    }

    @Test
    void testReadTextFile1() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list.get(0));
    }

    @Test
    void testReadTextFile2() {
    }

    @Test
    void copy() {
        long start = System.currentTimeMillis();
        FileUtil.copyFile(new File("E:\\code"), new File("E:\\fuck\\you\\code"));
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }
}