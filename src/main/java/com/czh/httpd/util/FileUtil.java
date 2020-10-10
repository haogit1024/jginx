package com.czh.httpd.util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 文件工具类
 * @author chenzh
 * @date 2020/10/10
 */
public class FileUtil {
    public static String readTextFile(String filePath) {
        return readTextFile(new File(filePath), StandardCharsets.UTF_8);
    }

    public static String readTextFile(String filePath, Charset charset) {
        return readTextFile(new File(filePath), charset);
    }

    public static String readTextFile(File file) {
        return readTextFile(file, StandardCharsets.UTF_8);
    }

    public static String readTextFile(File file, Charset charset) {
        if (file == null || !file.exists()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, charset);
             BufferedReader bufferedReader = new BufferedReader(isr)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
