package com.czh.httpd.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author chenzh
 * 资源加载器
 */
public class ResourcesLoader {
    public static String getResourceAsString(String filePath) throws IOException {
        InputStream inputStream = ResourcesLoader.class.getResourceAsStream(filePath);
//        StringBuilder sb = new StringBuilder();
//        // 一次读取一MB
//        byte[] bytes = new byte[1024 * 1024];
//        int len;
//        while ((len = inputStream.read()) != -1) {
//            sb.append(new String(bytes, 0, len, StandardCharsets.UTF_8));
//        }
//        return sb.toString();
        byte[] bytes = new byte[inputStream.available()];
        int len = inputStream.read(bytes);
        return new String(bytes, 0, len);
    }
}
