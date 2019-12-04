package com.czh.httpd.util;

import java.io.File;
import java.io.FileInputStream;
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

    public static byte[] getResourceAsBytes(String filePath) {
        try {
            InputStream inputStream = ResourcesLoader.class.getResourceAsStream(filePath);
            byte[] bytes = new byte[inputStream.available()];
            int len = inputStream.read(bytes);
            return ArrayUtil.splitBytes(bytes, 0, len);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取resource出错");
        }
        return new byte[0];
    }

    /**
     *  **调用该方法前一定要判断该文件已存在**
     * @param file
     * @return
     */
    public static String getContent(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[fis.available()];
            int len = fis.read(bytes);
            return new String(bytes, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static byte[] getBytes(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[fis.available()];
            int len = fis.read(bytes);
            return ArrayUtil.splitBytes(bytes, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     *  **调用该方法前一定要判断该文件已存在**
     * @param file
     * @param off   偏移位
     * @param len   读取长度
     * @return
     */
    public static String getContent(File file, int off, int len) {
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[len];
            len = fis.read(bytes, off, len);
            return new String(bytes, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
