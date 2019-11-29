package com.czh.httpd.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author chenzh
 * 资源加载器
 */
public class ResourcesLoader {
    public static String getResourceAsString(String filePath) throws IOException {
        InputStream inputStream = ResourcesLoader.class.getResourceAsStream(filePath);
        byte[] bytes = new byte[inputStream.available()];
        int len = inputStream.read(bytes);
        return new String(bytes, 0, len, StandardCharsets.UTF_8);
    }
}
